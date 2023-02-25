Feature:  User management Feature

  Background:
    Given User logins and Visits Home Page to Manage users
    When User goes to admin management menu
    And User clicks User management left menu item link

  @userManagement
  Scenario Outline: Create new user
    Given User is on user management Page
    When User Clicks add new user button
    Then username,password, repeat password,Firstname,lastname and Password Expiration Date Should be required
    And User enters username "<username>"
    And User enters first name "<firstName>"
    And User enters last name "<lastName>"
    And User enters password "<password>"
    Then Assign all the roles and permissions to the newly created user
    And User Clicks save button to create user information
    Then User logs out and logins with the newly created account "<username>" and "<password>"
    Examples:
      | firstName | lastName | username     | password  |
      | Namanya   | Abert    | abertnamanya | QKR*VB0#V |
