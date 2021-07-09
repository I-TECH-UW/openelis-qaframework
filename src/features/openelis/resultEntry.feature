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

@results
Scenario Outline:Results Entry Set-up
When User Selects Results --> Search --> By Order and searches by known Accession Number "<accesionNumber>"
Then Patient information display correctly by Accession Number
When User Select Results --> Search --> By Patient and  Pull up lab results for a known patient by LastName "<lastName>" and FirstName "<firstName>"
Then Patient information display correctly by Patient details
When User Select Results --> Enter by Unit from main menu drop-down and Selects a Unit Type "<unitType>" for which there are known tests
Then Only tests without results display
And Notes are visible with time and date stamp
And Lab number with sample extension displays 
And Sample Type displays
And Test date defaults to current date
When User Overwrites Test Date "<date>"
Then Field accepts text "<date>"
And Non-conforming samples appear with a red flag symbol
When User Enters known lab number "<accesionNumber>" in Lab no. search field at top right and Clicks search
Then Page goes to correct lab number and order is highlighted in yellow
And Message appears ,Accession number not found, if the format is incorrect or number is not in use
Examples:
|accesionNumber   |lastName|firstName  |unitType         |date      |
|20210000000002249|moses   | mutesasira|Molecular Biology|03/07/2021|  

@results
Scenario Outline:Entering Test Results 
When User Select Results --> Enter by Unit from main menu drop-down and Selects a Unit Type "<unitType>" for which there are known tests
Then Reference range or value displays under test name
When User Enters type-in result "<value>" for a selected test
Then Type-in result "<convertedValue>" can be entered in the field
Then Result units display correctly
Then Result converts to correct decimal "<convertedValue>"
When User Enters a result "<lowValue>" that is below the normal range 
Then Results Field Turn Yellow
When User Enters a result "<highValue>" that is above the normal range 
Then Results Field Turn Yellow
When User Clicks on Add Note icon
Then Note field displays
Examples:
|unitType         |value |convertedValue|lowValue|highValue|
|Molecular Biology|3     |3.00          |3       |20000000 |