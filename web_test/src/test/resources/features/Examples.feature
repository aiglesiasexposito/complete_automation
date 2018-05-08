@examples

Feature: Examples
  As a user
  I want to see the difference between declarative and imperative automation testing
  So that I can do better tests cases

#  #BAD DESCRIPTION (IMPERATIVE)
#  Scenario: I login to amazon
#    Given I am on 'Login Page' screen
#    When I click on sign in
#    Then the 'Credentials Page' should be displayed

  #GOOD DESCRIPTION (DECLARATIVE)
  Scenario Outline: User invited see first top offers from any city.
    Given I am a invited user in the platform
    When I go to top offers section
    Then I can to read the first top offers from the city selected
      | city | <city> |
    Examples: Several cities
      | city     |
      | AR_RASS  |
      | SHAQRA   |
