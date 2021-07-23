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
When User Checks Save all results 
Then All results are checked `Save`
Examples:
|unitType         |labNumber        |exist |
|Molecular Biology|20210000000002249|true  |
|Molecular Biology|11111111111111111|false |