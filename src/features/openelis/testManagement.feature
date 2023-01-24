Feature: Test Management Feature

  Background:
    Given User logins and Visits Home Page
    When User clicks admin menu link
    And User clicks Test Management left menu item link

  @testManagement
  Scenario: Validate list items in Test Management menu
    Given Test Management page appears with functionality links
      | Rename existing test names              |
      | Rename Existing Panels                  |
      | Rename Existing Sample Types            |
      | Rename Existing Test Sections           |
      | Rename Existing Unit of Measure Entries |
      | Rename existing result list options     |
      | Rename existing method names            |
      | View Test Catalog                       |
      | Manage Methods                          |
      | Add new tests                           |
      | Modify tests                            |
      | Activate/Deactivate tests               |
      | Enable/disable test orderability        |
      | Manage Test Units                       |
      | Manage Sample Types                     |
      | Manage Units of Measure                 |
      | Manage Panels                           |
      | Add result select list                  |
    Then Count List menu list Items should be <18>














