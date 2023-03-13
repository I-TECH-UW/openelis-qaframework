Feature: Test Management Feature

  Background:
    Given User logins and Visits Home Page

  @testManagement
  Scenario: Validate list items in Test Management menu
    Given User clicks admin menu link
    Then User clicks Test Management left menu item link
    And Test Management page appears with functionality links
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
    Given User clicks admin menu link
    Then User clicks Test Management left menu item link
    And Check if rename existing test names link exists
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
    Given User clicks admin menu link
    Then User clicks Test Management left menu item link
    And Check if rename existing test names link exists
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
    And User clicks on finished button

  @testManagement
  Scenario: User cancels the testName updates
    Given User clicks admin menu link
    Then User clicks Test Management left menu item link
    And Check if rename existing test names link exists
    When User clicks rename existing test names link
    Then Laboratory test names (hyperlinked & underlined) should appear on page in three columns. All are in primary language
    When User selects an existing Test Name "Glucose(Plasma)"
    And User enters test name "GlucoseCancelEng" in English
    And User enters test name "GlucoseCancelFR" in French
    And User enters Reporting test name "GlucoseReportCancelEng" in English
    And User enters reporting test name "GlucoseReportCancelFR" in French
    When User clicks cancel button
    Then Confirm Test name "GlucoseCancelEng" update was canceled
    And User clicks on finished button


  @testManagement
  Scenario Outline: Add new tests
    Given User clicks admin menu link
    Then User clicks Test Management left menu item link
    And User Clicks Add new test link on the test management menu
    Then User Enters test name for English "<TestNameEng>" and French "<TestNameFrench>" into the respective text fields
    Then From the Unit of Measure dropdown select the appropriate unit "<UnitOfMeasure>"
    Then From the Test Section drop down select the appropriate option "<testSection>"
    Then From the Panel dropdown select the appropriate option "<panel>"
    Then From the Result type dropdown select the appropriate option
    Then Under Reporting Test name, click on Copy from Test Name
    Then User Enters Reporting test names for English and French into the respective fields if different from Test Name
    Then With all of the *required fields completed the Next button becomes active
    Then The tick box next to Active is auto-selected
    Then The tick box next to Orderable is auto-selected
    And User Clicks Next button
    Then User selects the Type "<sampleType>" from SampleType dropdown options
    Then User selects one more type from SampleType dropdown options
    Then Select the new Test Name and hold the selection move it to the desired position in the Test display order
    And User Clicks on Next button
    And User Clicks on Accept button
    Examples:
      | TestNameEng            | TestNameFrench               | UnitOfMeasure | testSection       | panel             | sampleType |
      | gastric fluid analysis | analyse du liquide gastrique | mlU/ml        | Molecular Biology | Bilan Biochimique | Serum      |

  @testManagement
  Scenario Outline: Verify Added new testName
    Given User Clicks on the Order menu tab and select Add order
    Then User selects sample type "<sampleType>" from the drop down
    And User Searches TestName "<TestName>" from the available Tests
    Examples:
      | sampleType | TestName               |
      | Serum      | gastric fluid analysis |
      | Urines     | gastric fluid analysis |












