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
Then Page goes to order number, order is highlighted in yellow
Examples:
|unitType         |labNumber        |
|Molecular Biology|20210000000002249|