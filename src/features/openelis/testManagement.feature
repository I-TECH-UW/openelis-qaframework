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

  @testManagement
  Scenario Outline: Open Rename Existing Test Names page
    Given Check if rename existing test names link exists
    When User clicks rename existing test names link
    Then Laboratory test names (hyperlinked & underlined) should appear on page in three columns. All are in primary language
    When User selects an existing Test Name "<testName>"
    And User enters test name "<testNameEng>" in English
    And User enters test name "<testNameFR>" in French
    And User enters Reporting test name "<TestNameReportENG>" in English
    And User enters reporting test name "<TestNameReportFR>" in French
    Then User Clicks save button
    And User Clicks accept to confirm changes
    Then Confirm Test name "<testNameEng>" update was saved

    Examples:
      | testName           | testNameEng | testNameFR | TestNameReportENG | TestNameReportFR |
      | Glucose(Plasma)    | GlucoseENG  | GlucoseFR  | GlucoseReportENG  | GlucoseReportFR  |
      | GlucoseENG(Plasma) | Glucose     | Glucose    | GlucoseReport     | GlucoseReport    |


  @testManagement
  Scenario: User rejects the testName updates
    Given Check if rename existing test names link exists
    When User clicks rename existing test names link
    Then Laboratory test names (hyperlinked & underlined) should appear on page in three columns. All are in primary language
    When User selects an existing Test Name "Glucose(Plasma)"
    And User enters test name "GlucoseRejectEng" in English
    And User enters test name "GlucoseRejectFR" in French
    And User enters Reporting test name "GlucoseReportRejectEng" in English
    And User enters reporting test name "GlucoseReportRejectFR" in French
    Then User Clicks save button
    When User clicks reject button
    Then Confirm Test name "GlucoseRejectEng" update was rejected but contains "Glucose(Plasma)"

  @testManagement
  Scenario: User cancels the testName updates
    Given Check if rename existing test names link exists
    When User clicks rename existing test names link
    Then Laboratory test names (hyperlinked & underlined) should appear on page in three columns. All are in primary language
    When User selects an existing Test Name "Glucose(Plasma)"
    And User enters test name "GlucoseCancelEng" in English
    And User enters test name "GlucoseCancelFR" in French
    And User enters Reporting test name "GlucoseReportCancelEng" in English
    And User enters reporting test name "GlucoseReportCancelFR" in French
    When User clicks cancel button
    Then Confirm Test name "GlucoseCancelEng" update was canceled















