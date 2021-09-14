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
    |seruwu  |jimmy    |oe012        |20210000000003761|

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
    |20210000000003761|24068xx706080889|210000000003790|210000000003780|09-02/2019   |XXMM         |1212|05:10      |

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
    |20210000000003761|09-02/2019   |XXMM         |   30:30       |1212|05:10      |

@modifyOrder
Scenario Outline: Available Test Information
When User Search by lab number "<labNo>" from previous testing steps on the Modify Oder Page
Then Table headers are correct Under Available Tests
When User Check box next to  several tests
Then Assign test Check boxes stick
When User unCheck box next to  several tests
Then Assign test Check boxes will uncheck
Examples:
    |labNo            |
    |20210000000003761|

@modifyOrder
Scenario Outline: Add Order
And User Search by lab number "<labNo>" from previous testing steps on the Modify Oder Page
When User Click on drop-down Sample Type list on the Modify Oder Page
Then Sample types display in drop-down list on the Modify Oder Page
When User Select any sample type on the Modify Oder Page
Then Order information fields for the selected sample type appear. Sample types can be added one by one 
And Sample ID added to reflect correct next Sample number
When User Select sample Condition from drop-down list on the Modify Oder Page
Then Multiple sample conditions can be added
When User Clicks `X` beside a condition on the Modify Oder Page
Then Added sample conditions can be deleted
When User click Remove ,On the far right of the sample
Then Removes sample from order
When User Click Remove All ,on the Modify Oder Page
Then Removes all samples from order
And User can Re-add samples
Examples:
    |labNo            |
    |20210000000003761|

@modifyOrder
Scenario Outline: Collection Date 
And User Search by lab number "<labNo>" from previous testing steps on the Modify Oder Page
When User Enters Collection Date "<date>" on the Modify Oder Page
Then Collection Date Field validates "<validation>" the date format 
Examples:
    |labNo            |date       |validation                                |
    |20210000000003761|09-02/2019 |Rejects incorect Format not in DD/MM/YYYY |
    |20210000000003761|dd/mm/yyy  |Rejects incorect Format not Numeric       |
    |20210000000003761|09/02/5000 |Rejects Future date                       |
    |20210000000003761|09/01/2020 |Accepts correct Format in DD/MM/YYYY      |

@modifyOrder
Scenario Outline: Collection Time 
And User Search by lab number "<labNo>" from previous testing steps on the Modify Oder Page
When User Enters Collection Time "<time>" on the Modify Oder Page
Then Collection Time Field  validates "<validation>" the time format
Examples:
    |labNo            |time  |validation                                           |
    |20210000000003761|XXMM  |Rejects incorect Format ,non numeric                 |
    |20210000000003761|30:30 |Rejects time not existing on the  in 12/24 hour clock|
    |20210000000003761|12:122|Rejects extra digits                                 |
    |20210000000003761|1111  |Auto-corrects HHMM format to HH:MM                   |
    |20210000000003761|10:10 |Accepts correct Format in HH:MM                      |

@modifyOrder
Scenario Outline: Add Tests 
And User Search by lab number "<labNo>" from previous testing steps on the Modify Oder Page
And User Select any sample type on the Modify Oder Page
Then Test entry is marked mandatory on the Modify Oder Page
And Available Tests check box list appears for each sample type
When User Checks check box next to test name on the Modify Oder Page
Then Checkbox sticks, test name appears in Tests box on the Modify Oder Page 
When User Unchecks check box next to test name on the Modify Oder Page
Then Checkbox stays clear; Deselects test ;test name disappears from Tests box
When User Checks check box next to panel name on the Modify Oder Page
Then All panel tests are selected ,checkboxes stick, test names appear in Tests box 
When User unChecks check box next to panel name on the Modify Oder Page
Then All panel tests are diselected ;test name disappears from Tests box
When User Enters text in box Tests on the Modify Oder Page
Then Text cannot be added to box Tests on the Modify Oder Page
When User Deletes text from box Tests on the Modify Oder Page
Then Text cannot be deleted from  Tests box on the Modify Oder Page
Examples:
    |labNo            |
    |20210000000003761|   

@modifyOrder
Scenario Outline: Add Tests 
And User Search by lab number "<labNo>" from previous testing steps on the Modify Oder Page 
When User Leaves mandatory field without data on the Modify Oder Page
Then Save button deactivated until all mandatory fields are completed on the Modify Oder Page
When User Completes all mandatory fields on the Modify Oder Page
Then Save button activated when all mandatory fields are completed on the Modify Oder Page
When User Clicks Cancel on the Modify Oder Page
Then Pop-up message 'Leave Site? Changes you made may not be saved' appears
When User Clicks Cancel to Cancel the Confirmation box
Then Edit Sample form remains on screen on the Modify Oder Page
And User User Clicks Save on the Modify Oder Page
Then Save results in new Edit Sample page and message in green 'Save was successful'
When User Clicks Cancel on the Modify Oder Page
Then System returns to Returns to home page
Examples:
    |labNo            |
    |20210000000003761|    

@modifyOrder
Scenario Outline: Verification  
When User Goes to Workplan > By Test Type 
And User Selects Sample type "<sampleType>" of tests with results
And User Clicks 'Print Workplan'
Then Modified order and sample information correctly appears on appropriate work plan
When User Goes to Reports > Routine > Patient Status Report
And User Enters valid Lab Number "<labNo>"
And User Clicks 'Generate printable version' for this lab number 
Then Modified order information is correct and tests appear as In Progress on Patient Report
Examples:
    |sampleType                  |labNo            |
    |COVID-19 ANTIBODY IgG(SERUM)|20210000000003761|    
   
