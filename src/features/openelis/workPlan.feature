Feature: WorkPlan Feature

  Background:
    Given User logins and goes to the Home page

  @workplan
  Scenario Outline: Work plan by Test
    Given User select workplan by test from the main menu down "<LabNo>"
    When user selects test type "<testType>" from the dropdown options
    Then Tests display in search drop-down list
    And Workplan select form appears
    Then All known orders are present "<testType>" and "<LabNo>"
    Then Total number of tests is correct <numberOfTests>
    Then Lab No is displayed correctly on work plan select form
    Then Received date and reception time display correctly on work plan select form
    Then Non conformity flags appear next to correct nonconforming orders
    And User Check Remove check box
    Then User clicks on print Workplan button
    Then Workplan appears  in a new tab or window
    Then Tests for which Remove checkbox was ticked do not appear on printable workplan

    Examples:
      | testType                     | numberOfTests | LabNo             |
      | HIV INFANT VIRAL LOAD(Serum) | 10            | 20231099040004863 |


  @workplan
  Scenario Outline: Work plan by Panel
    Given User select workplan by Panel from the main menu down
    When user selects Panel type "<panelType>" from the dropdown options
    Then All Panel Types displayed in drop down List
    Then Workplan by Panel select form appears
    Then Workplan by Panel should show all known orders are present "<panelType>" and "<LabNo>"
    Then Workplan by Panel Total number of tests is correct "<numberOfTests>"
    Then Workplan by Panel Lab No is displayed correctly on work plan select form
    Then Workplan by Panel Received date and reception time display correctly on work plan select form
    Then Workplan by Panel Non conformity flags appear next to correct nonconforming orders
    And Workplan by Panel user Check Remove check box
    Then Workplan by Panel user clicks on print Workplan button
    Then Workplan by Panel workplan appears  in a new tab or window
    Examples:
      | panelType         | numberOfTests | LabNo             |
      | Bilan Biochimique | 92            | 20231099040004863 |


  @workplan
  Scenario Outline: Work plan by Unit
    Given User select workplan by Unit from the main menu down
    When user selects Unit type "<unitType>" from the dropdown options
    Then All Unit Types displayed in drop down List
    Then Workplan by Unit select form appears
    Then Workplan by unit should show all known orders are present "<unitType>" and "<LabNo>"
    Then Workplan by unit Total number of tests is correct "<numberOfTests>"
    Then Workplan by unit Lab No is displayed correctly on work plan select form
    Then Workplan by unit Received date and reception time display correctly on work plan select form
    Then Workplan by unit Non conformity flags appear next to correct nonconforming orders
    And Workplan by unit user Check Remove check box
    Then Workplan by unit user clicks on print Workplan button
    Then Workplan by unit workplan appears  in a new tab or window
    Examples:
      | unitType          | numberOfTests | LabNo             |
      | Molecular Biology | 20            | 20231099040004863 |

  @workplan
  Scenario Outline: Verification
    Given User goes to Result Entry page By Unit
    When User select unit type from drop down select list "<unitType>"
    And User enter results for the tests "<LabNo>"
    Then Go to WorkPlan by test type "<testType>"
    Then Test "<LabNo>" nolonger exists on the workplan by test type
    Then Go to WorkPlan by panel Type "<panelType>"
    Then Test "<LabNo>" nolonger exists on the workplan by panel Type
    Then Go to WorkPlan by unit Type"<unitType>"
    Then Test "<LabNo>" nolonger exists on the workplan by unit Type
    Examples:
      | unitType          | testType                     | panelType         | LabNo             |
      | Molecular Biology | HIV INFANT VIRAL LOAD(Serum) | Bilan Biochimique | 20231099040004863 |


