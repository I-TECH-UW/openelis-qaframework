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
|seruwu  |jimmy    |oe012        |20210000000003760|

@modifyOrder
Scenario Outline: Order Information 
When User Pulls up a known order with oder number "<labNo>"
Then Order appears on screen
And Patient information displays correctly on the Modify Oder Page
When User enters a Lab No "<incorrectLabNo>" with incorrect format, Under Modify Order section, in the New order number 
Then Pop-up message appears saying format is incorrect on the Modify Oder Page
When User enters a new unused Lab No "<unUsedLabNo>" in the correct 9-digit format
Then New order number Field ,accepts correct text 
When User enters a Known used Lab No "<usedLabNo>" in the correct 9-digit format
Then Pop-up message informs you that you cannot use an existing order number
When User Enters Order date in incorrect format "<incorrectDate>" on the Modify Oder Page
Then Text Box Highlighted in Red if entry is in incorrrect format
When User Enters Order date in future on the Modify Oder Page
Then Text Box Highlighted in Red and Displays Pop up message alert on the Modify Oder Page
When User Enters Order date in correct format on the Modify Oder Page
Then Order Date Field accepts correct Date format
When User Enters Recieved date in incorrect format "<incorrectDate>" on the Modify Oder Page
Then Recieved date Text Box Highlighted in Red if entry is in incorrrect format
When User Enters Recieved date in future on the Modify Oder Page
Then Recieved date Text Box Highlighted in Red and Displays Pop up message alert on the Modify Oder Page
When User Enters Recieved date in correct format on the Modify Oder Page
Then Recieved date Field accepts correct Date format
When User Enters time "<incorrectTime>" in incorrect format on the Modify Oder Page
Then Field Rejects non-numeric, additional digits
When User Enters time "<time>" in HHMM format on the Modify Oder Page
Then Field Automatically corrects straight numeric to proper HH:MM format
When User Enters time "<correctTime>" in HH:MM format  on the Modify Oder Page
Then Field accepts correct time  in HH:MM format 
When User Enters new site name from drop-down list
Then Site name and code drop-down list displays previously entered  options correctly and selection can be made
Examples:
|labNo            |incorrectLabNo  |unUsedLabNo    |usedLabNo      |incorrectDate|incorrectTime|time|correctTime|
|20210000000003760|24068xx706080889|210000000003790|210000000003780|09-02/2019   |XXMM         |1212|05:10      |

@modifyOrder
Scenario Outline: Current Test Information
When User Pulls up a known order with oder number "<labNo>"
Then Table headers are correct Under Current Tests
When User Enters new Collection Date "<incorrectDate>" in incorrect format on the Modify Oder Page
Then Collection Date Text Box Highlighted in Red if entry is in incorrrect format
When User Enters new Collection Date in future on the Modify Oder Page
Then Alert appears if Collection Date is in future
When User Enters modified date in correct format in Collection Date field
Then Field accepts correct format; Collection Date can be modified
When User Enters new Collection Time "<incorrectTime>" in incorrect format on the Modify Oder Page
Then Collection Time Rejects non-numeric entries, additional digits
When User Enters  Collection Time "<nonExistingTime>" that doesnt exist on the 12 or 24 hour clock
Then Red alert appears if time does not exist on 12 or 24 hour clock 
When User Enters  Collection Time "<time>" as HHMM on the Modify Oder Page
Then Collection Time Field Automatically corrects straight numeric to proper format HH:MM
When User Enters modified collection time  "<correctTime>" as HH:MM
Then Collection Time Field accepts correct format; collection time can be modified
When User Clicks Remove Samples check box on the Modify Oder Page
Then Remove Samples Check box sticks on the Modify Oder Page
When User Unchecks Remove Samples check box on the Modify Oder Page
Then Remove Samples Check box is Unselected on the Modify Oder Page
And User can View the `Delete test` column
And Cancel Test Checkbox activated for user with validation permissions
When User Clicks Delete test check box on the Modify Oder Page
Then Delete test Check box sticks on the Modify Oder Page
When User Unchecks Delete test check box on the Modify Oder Page
Then Delete test Check box is Unselected on the Modify Oder Page
When User Rechecks box Delete test check box on the Modify Oder Page
Then Can delete a test within a panel
Examples:
|labNo            |incorrectDate|incorrectTime|nonExistingTime|time|correctTime|
|20210000000003760|09-02/2019   |XXMM         |   30:30       |1212|05:10      |
