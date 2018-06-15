@FunctionalTest

Feature: Read any sport news
  As a user
  I want to go to the sport section
  So that I can read the first news from any sport section

  @Invited
  Scenario Outline: User invited read first news from any sport section
    Given I am a invited user in the platform
    When I go to any sport news
      | section | <section> |
    Then I can to read the first news story
    Examples: Several section sports
      | section |
      | HOME    |
#      | FOOTBALL |
#      | FORMULA1 |
#      | CRICKET  |
#      | RUGBY    |
#      | TENNIS   |

  @RegisteredValid
  Scenario Outline: User registered read first news from any sport section
    Given I am a registered user in the platform
    When I go to any sport news
      | section | <section> |
    Then I can to read the first news story
    Examples: Several section sports
      | section |
      | HOME    |
#      | FOOTBALL |
#      | FORMULA1 |
#      | CRICKET  |
#      | RUGBY    |
#      | TENNIS   |

  @RegisteredInvalid
  Scenario: User with invalid credentials try register in the platform
    When I try register in the platform with invalid credentials
    Then I cant do login


  @ControlTest
  Scenario: User is registered and start on homePage
    Given I am a registered user in the platform
    Then I am in the homePage