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
When User Selects correct patient 
Then Patient Information form populates with patient information
When User Clicks New Patient on the Add Patient Page
Then Patient Information form clears
Examples:
|lastName|firstName|subjectNumber|labNo            |
|seruwu  |jimmy    |oe012        |20210000000002250|

@patientEntry
Scenario Outline: Patient Information
When User Enters data into text fields
Then All text fields accept text 
And National ID is mandatory
And Alert is given if Subject Number is already in use
And If subject number is already in use, cannot save
And Alert given if National Identification Number  is already in use
And Cannot save if National Identification Number is already in use
And Alert given if Phone Number is not in correct format
When User Selects a Health County from the drop-down list
Then All XX counties are listed and one option can be selected
When User Selects a Health District from the drop-down list
Then All Health Districts under the Health County selected in the previous step should be visible 
When User Fills in Date of Birth "<dateOfBirth>"
Then Date of Birth is mandatory
And Alert appears if DOB format "<incorrectDob>" is incorrect 
And Alert appears if date of birth is in the future
And Automatically fills correct age when DOB "<dateOfBirth>" is filled in
Examples:
    | dateOfBirth | incorrectDob |
    | 24/04/1992  |  wrongDate   |