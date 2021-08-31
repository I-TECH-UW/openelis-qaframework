Feature: Modify Order

Background:
    Given User Logs in to Home Page and goes to Modify Order Page

@modifyOrder
Scenario Outline: Order Search
Then Search appears at top of page
And Search button deactivated until text is entered in a search field
And Search text boxes display correct search criteria on the Modify Order Page
When User Enters known last name "<lastName>" in Last name Search field on the Modify Order Page
Then Search by Last name yields results for all patients with matching last name on the Modify Order Page
When User Enters known first name "<firstName>" in First name Search field on the Modify Order Page
Then Search by First name yields results for all patients with matching first name on the Modify Order Page
When User Enters known Patient ID "<subjectNumber>" in Patient ID search field on the Modify Order Page
Then Search by Patient ID yields results for all patients with matching Patient ID on the Modify Order Page
When User Enters known Lab Number "<labNo>" in Lab No. search on the Modify Order Page
Then Search by Lab Number yields results for all patients with matching Lab Number on the Modify Order Page
And If there is only one patient with that Lab No, the system auto-fills all the info about that patient, bypassing the selection process
And Patient Information form populates with patient information on the Modify Order Page
Examples:
|lastName|firstName|subjectNumber|labNo            |
|seruwu  |jimmy    |oe012        |20210000000002250|
