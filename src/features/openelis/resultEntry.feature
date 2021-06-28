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

@results
Scenario Outline:Searching for test result entry forms
When User Selects Results --> Search --> By Patient
Then Results search form displays
And Search button deactivated until search text entered
When User Searches by Last name "<lastName>"
Then Search by Last name yields all patients with matching last name
When User Searches by First name "<FirstName>"
Then Search by First name yields all patients with matching first name
When User Searches by Patient Identification Code "<patientId>"
Then Search by Patient Id yields all patients with matching patient id
When User Selects Results --> Search --> By Order
Then Result accession number search form displays
When User Searches by AccesionNumber "<accesionNumber>"
Then Search by Lab Number yields results for known accession number
Examples:
|patientId  |lastName|FirstName|accesionNumber|
|HT7823DAAAZ|Foxy    | McGee   |1234519000002 |
