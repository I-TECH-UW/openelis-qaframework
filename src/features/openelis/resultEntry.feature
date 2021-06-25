Feature: Results Entry

Background:
    Given User visits Home page
    
@results
Scenario Outline: Accessing Results Entry
When User selects Results and clicks Enter By unit 
Then Results Unit Type search form displays 
And All unit names display correctly in drop down menu 
And User Can select unit "<unitType>" from drop down menu and redirects to entry page
When User Selects Results --> Search --> By Patient
Then Results search form displays
When User Selects Results --> Search --> By Order
Then Result accession number search form displays
When User Selects Results --> Search --> By Test Name, Date, or Status 
Then Results by Test, Date or Status search form displays
Examples:
|    unitType   |
|Molecular Bio A|