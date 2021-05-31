Feature: User Login

  Background:
    Given User visits login page

  @login
  Scenario Outline: Failing or Succeeding to Login
    When User enters "<username>" username
    And User enters "<password>" password
    And User Logs in
    Then System Evaluates Login "<status>"
    Examples:
      | username  | password     | status |
      | itech     | adminADMIN!  | true   |
      | wrongUser | adminADMIN!  | false  |
      | itech     | wrongPass    | false  |
