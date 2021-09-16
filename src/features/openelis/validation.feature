Feature: Validation

Background:
    Given User goes to Home page

@validate
Scenario Outline: Validation
When User Selects Validation --> Routine,from the main menu
Then The Validation by Unit Type page displays
When User Selects a Unit Type "<unitType>" under which there are known tests
Then Tests display with Lab order Number, Test name, result and result reference 
When User Enters lab number "<labNumber>" in Lab Number search field at top right
Then Field Only accepts 9 characters
When User Clicks Search Button
Then If order number exists "<exist>" ,Page goes to order number, order is highlighted in yellow
And If order number does not exist "<exist>" , message `Accession number not found` appears
When User Selects Validation --> Routine,from the main menu
And User Selects a Unit Type "<unitType>" under which there are known tests
Then User Check for known non-conformity, Red flag displayed next to test
And Non-conformity Reason note displays with Date and Time stamp
When User Checks `Save all results` 
Then All results are checked `Save`
When User Unchecks `Save all results`
Then All results are de-checked `Save`
When User Checks `Save all results` again
And User Unchecks `Save` For selected tests
Then Seleted Save Check boxes are clear
When User Checks `Retest all results`
Then All results are checked `Retest`
When User Unchecks `Retest all results`
Then All results are de-checked `Retest`
When User Checks `Retest all results` again
And User Unchecks `Retest` for selected tests
Then Seleted Retest Check boxes are clear
And Tests cannot be checked both `Save` and `Retest` at the same time
When User Selects Validation --> Routine,from the main menu
And User Selects a Unit Type "<unitType>" under which there are known tests
And User Enters Validation notes "<notes>"
Then Field accepts validation text "<notes>"
When User Closes Validation note box
Then Validation Note field closes; triangle symbol changes to notepad symbol
Examples:
    |unitType         |labNumber        |exist |notes       |
    |Molecular Bio A  |20210000000002249|true  |sample Notes|   
    |Molecular Bio A  |11111111111111111|false |sample Notes|

@validate
Scenario Outline: Overall Page
When User Selects Validation --> Routine,from the main menu
And User Selects a Unit Type "<unitType>" under which there are known tests
And User Clicks Cancel button on Validation Page
Then Triggers prompt box ,to confirm leaving page
When User Clicks `Cancel` to Stay on Page
Then Stays on page 
When User Click Save button
Then Pop-up message asks you to confirm that you have indicated action for all items you wish to validate
When User Clicks Ok 
Then Returns to Validation Unit Type search page and message in green `Save was successful` appears
When User Selects a Unit Type "<unitType>" under which there are known tests
And User Clicks Cancel button on Validation Page
Then Triggers prompt box ,to confirm leaving page
When User Clicks `Leave` in cancel message 
Then Returned to home page
Examples:
    |unitType         |
    |Molecular Bio A  |

@validate
Scenario Outline: Verification
When User Goes to Patient Status Report Page 
And User Generates Patient Status Report for the accession number "<accesionNumber>"
Then Saved results without rejection reason appear on Patient Status Report
When User Goes to Workplan --> By Test Type
Then Retest tests appear on workplan for that accession number By Test Type "<testType>"
When User Goes to Workplan --> By Panel Type
Then Retest tests appear on workplan for that accession number By Panel Type "<panelType>"
When User Goes to Workplan --> By Unit
Then Retest tests appear on workplan for that accession number By Unit Type "<unitType>"
Examples:
    |accesionNumber   |testType                     |panelType             |unitType         |
    |20210000000002249|HEPATITIS C VIRAL LOAD(SERUM)|pnl_virology_molecular|Molecular Bio A |    