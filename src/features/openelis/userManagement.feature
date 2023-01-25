Feature:  User management Feature

  Background:
    Given User logins and Visits Home Page to Manage users
    When User goes to admin management menu
    And User clicks User management left menu item link

  @userManagement
  Scenario: Create new user
    Given User is on user management Page
    When User Clicks add new user button

