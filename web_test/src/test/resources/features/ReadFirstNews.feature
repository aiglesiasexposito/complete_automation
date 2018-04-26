@FunctionalTest

Feature: Read any sport news
  As a user
  I want to go to the sport section
  So that I can read the first news from any sport section

  Scenario Outline: User invited read first news from any sport section
    Given I am a invited user in the platform
    When I go to any sport news
      | section | <section> |
    Then I can to read the first news story
    Examples: Several section sports
      | section  |
      | HOME     |
      | FOOTBALL |
      | FORMULA1 |
      | CRICKET  |
      | RUGBY    |
      | TENNIS   |

  Scenario Outline: User registered read first news from any sport section
    Given I am a registered user in the platform
    When I go to any sport news
      | section | <section> |
    Then I can to read the first news story
    Examples: Several section sports
      | section  |
      | HOME     |
      | FOOTBALL |
      | FORMULA1 |
      | CRICKET  |
      | RUGBY    |
      | TENNIS   |


