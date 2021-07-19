Feature: Validation

Background:
    Given User goes to Home page

@validate
Scenario Outline: Validation
When User Selects Validation --> Routine,from the main menu
Then The Validation by Unit Type page displays
When User Selects a Unit Type "<unitType>" under which there are known tests
Then Tests display with Lab order Number, Test name, result and result reference 
Examples:
|unitType         |
|Molecular Biology|