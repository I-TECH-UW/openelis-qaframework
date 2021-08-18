Feature: Patient Entry

Background:
    Given User Vists Home Page and goes to Add Add|Modify Patient Page
   
@patientEntry
Scenario Outline: Patient Search
Then Add|Modify Patient page appears with search field
And Search button deactivated until search criteria is selected and text entered in the text field
And Search text boxes display correct search criteria 
When User enters known last name "<lastName>" in text box
Then Search by Last name yields all patients with matching last name on Add Patient Page
When User enters known first name "<firstName>" in text box
Then Search by First name yields all patients with matching first name on Add Patient Page
When User enters known last name "<lastName>" and first name "<firstName>"
Then Search by Last name and First name yields results for known matching names
When User enters known Subject Number "<subjectNumber>" 
Then Search by Subject Number yields results for known matching names
When User enters known Lab Number "<labNo>" 
Then Search by Lab Number yields results for known matching names
Examples:
|lastName|firstName|subjectNumber|labNo            |
|seruwu  |jimmy    |oe012        |20210000000002250|