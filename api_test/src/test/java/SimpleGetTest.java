import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.List;

public class SimpleGetTest {

    private String baseURI = "http://restapi.demoqa.com";

    @Test
    public void GetWeatherDetails() {
        // Specify the base URL to the RESTful web service
        RestAssured.baseURI = baseURI + "/utilities/weather/city";

        // Get the RequestSpecification of the request that you want to sent
        // to the server. The server is specified by the BaseURI that we have
        // specified in the above step.
        RequestSpecification httpRequest = RestAssured.given();

        // Make a request to the server by specifying the method Type and the method URL.
        // This will return the Response from the server. Store the response in a variable.
        Response response = httpRequest.request(Method.GET, "/Hyderabad");

        // Now let us print the body of the message to see what response
        // we have recieved from the server
        String responseBody = response.getBody().asString();
        int actualStatusCode = response.statusCode();

        System.out.println("Response Body is =>  " + responseBody);

        Assert.assertEquals(actualStatusCode /*actual value*/, 200 /*expected value*/, "Correct status code returned");

    }

    @Test
    public void GetWeatherDetailsInvalidCity() {
        RestAssured.baseURI = baseURI + "/utilities/weather/city";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/alejandrotest");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode /*actual value*/, 400 /*expected value*/, "Correct status code returned");
    }


    @Test
    public void GetWeatherStatusLine() {
        RestAssured.baseURI = baseURI + "/utilities/weather/city";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/Hyderabad");

        // Get the status line from the Response and store it in a variable called statusLine
        String statusLine = response.getStatusLine();
        Assert.assertEquals(statusLine /*actual value*/, "HTTP/1.1 200 OK" /*expected value*/, "Correct status code returned");
    }

    @Test
    public void GetWeatherHeaders() {
        RestAssured.baseURI = baseURI + "/utilities/weather/city";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/Hyderabad");

        // Reader header of a give name. In this line we will get
        // Header named Content-Type
        String contentType = response.getHeader("Content-Type");
        System.out.println("Content-Type value: " + contentType);
        Assert.assertEquals(contentType /* actual value */, "application/json" /* expected value */);

        // Reader header of a give name. In this line we will get
        // Header named Server
        String serverType = response.header("Server");
        System.out.println("Server value: " + serverType);
        Assert.assertEquals(serverType /* actual value */, "nginx/1.12.2" /* expected value */);

        // Reader header of a give name. In this line we will get
        // Header named Content-Encoding
        String contentEncoding = response.header("Content-Encoding");
        System.out.println("Content-Encoding: " + contentEncoding);
        Assert.assertEquals(contentEncoding /* actual value */, "gzip" /* expected value */);

    }

    @Test
    public void IteratingOverHeaders() {
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/Hyderabad");

        // Get all the headers. Return value is of type Headers.
        // Headers class implements Iterable interface, hence we
        // can apply an advance for loop to go through all Headers
        // as shown in the code below
        Headers allHeaders = response.headers();

        // Iterate over all the Headers
        for (Header header : allHeaders) {
            System.out.println("Key: " + header.getName() + " Value: " + header.getValue());
        }
    }

    @Test
    public void WeatherMessageBody() {
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/Hyderabad");

        // Retrieve the body of the Response
        ResponseBody body = response.getBody();

        // By using the ResponseBody.asString() method, we can convert the  body
        // into the string representation.
        System.out.println("Response Body is: " + body.asString());

        // To check for sub string presence get the Response body as a String.
        // Do a String.contains
        String bodyAsString = body.asString();
        Assert.assertEquals(bodyAsString.contains("Hyderabad") /*Expected value*/, true /*Actual Value*/, "Response body contains Hyderabad");

        // convert the body into lower case and then do a comparison to ignore casing.
        Assert.assertEquals(bodyAsString.toLowerCase().contains("hyderabad") /*Expected value*/, true /*Actual Value*/, "Response body contains Hyderabad");

    }

    @Test
    public void VerifyCityInJsonResponse() {
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/Hyderabad");

        // First get the JsonPath object instance from the Response interface
        JsonPath jsonPathEvaluator = response.jsonPath();

        // Then simply query the JsonPath object to get a String value of the node
        // specified by JsonPath: City (Note: You should not put $. in the Java code)
        String city = jsonPathEvaluator.get("City");

        // Let us print the city variable to see what we got
        System.out.println("City received from Response " + city);

        // Validate the response
        Assert.assertEquals(city, "Hyderabad", "Correct city name received in the Response");

    }

    @Test
    public void DisplayAllNodesInWeatherAPI() {
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/Hyderabad");

        // First get the JsonPath object instance from the Response interface
        JsonPath jsonPathEvaluator = response.jsonPath();

        // Let us print the city variable to see what we got
        System.out.println("City received from Response " + jsonPathEvaluator.get("City"));

        // Print the temperature node
        System.out.println("Temperature received from Response " + jsonPathEvaluator.get("Temperature"));

        // Print the humidity node
        System.out.println("Humidity received from Response " + jsonPathEvaluator.get("Humidity"));

        // Print weather description
        System.out.println("Weather description received from Response " + jsonPathEvaluator.get("Weather"));

        // Print Wind Speed
        System.out.println("City received from Response " + jsonPathEvaluator.get("WindSpeed"));

        // Print Wind Direction Degree
        System.out.println("City received from Response " + jsonPathEvaluator.get("WindDirectionDegree"));
    }

    @Test
    public void RegistrationSuccessful() {
        //Step 1: Create a Request pointing to the Service Endpoint
        RestAssured.baseURI = "http://restapi.demoqa.com/customer";
        RequestSpecification request = RestAssured.given();

        //Step 2: Create a JSON request which contains all the fields
        JSONObject requestParams = new JSONObject();
        requestParams.put("FirstName", "Virender"); // Cast
        requestParams.put("LastName", "Singh");
        requestParams.put("UserName", "sdimpleuser2dd2011");
        requestParams.put("Password", "password1");

        requestParams.put("Email", "sample2ee26d9@gmail.com");
        request.body(requestParams.toJSONString());

        //Step 3: Add JSON body in the request and send the Request
        //Add a header stating the Request body is a JSON
        request.header("Content-Type", "application/json");

        //Add the Json to the body of the request
        request.body(requestParams.toJSONString());

        //Post the request and check the response
        Response response = request.post("/register");

        //Step 4: Validate the Response
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, "201");
        String successCode = response.jsonPath().get("SuccessCode");
        Assert.assertEquals("Correct Success code was returned", successCode, "OPERATION_SUCCESS");
    }

    public class Book {

        String isbn;
        String title;
        String subtitle;
        String author;
        String published;
        String publisher;
        int pages;
        String description;
        String website;
    }

    @Test
    public void JsonPathUsage() throws MalformedURLException
    {
        RestAssured.baseURI = "http://restapi.demoqa.com/utilities/books/getallbooks";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("");

        // First get the JsonPath object instance from the Response interface
        JsonPath jsonPathEvaluator = response.jsonPath();

        // Read all the books as a List of String. Each item in the list
        // represent a book node in the REST service Response
        List<Book> allBooks = jsonPathEvaluator.getList("books", Book.class);

        // Iterate over the list and print individual book item
        // Note that every book entry in the list will be complete Json object of book
        for(Book book : allBooks)
        {
            System.out.println("Book: " + book.title);
        }
    }

}