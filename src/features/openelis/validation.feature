Feature: Validation

Background:
    Given User goes to Home page

@validate
Scenario Outline: Validation
Given User has an existing order "<existingLabNumber>"
When User Selects Validation --> Routine,from the main menu
Then The Validation by Unit Type "<unitType>" page displays
When User Selects a Unit Type "<unitType>" under which there are known tests
Then Tests display with Lab order Number, Test name, result and result reference
    When User Enters an none existing order number "<NoneExistingLabNumber>" ,then message `Accession number not found` appears
Then User Enters an existing order number "<existingLabNumber>" ,Page goes to order number, order is highlighted in yellow
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
Then User now saves tests with mixture of both `Save` and `Retest`
When User Selects Validation --> Routine,from the main menu
And User Selects a Unit Type "<unitType>" under which there are known tests
And User Enters Validation notes "<notes>"
Then Field accepts validation text "<notes>"
When User Closes Validation note box
Then Validation Note field closes; triangle symbol changes to notepad symbol
Examples:
    | unitType          | existingLabNumber | notes        | NoneExistingLabNumber |
    | Molecular Biology | 20210000000002249 | sample Notes | 11111111111111111     |

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
    | unitType          |
    | Molecular Biology |

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
Examples: Returns to Validation
    | accesionNumber    | testType                     | panelType         | unitType          |
    | 20210000000002249 | HIV INFANT VIRAL LOAD(Serum) | Bilan Biochimique | Molecular Biology |
