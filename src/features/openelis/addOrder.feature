Feature: Add Order

Background:
    Given User logs in and visits Home page
    And User clicks add order and goes to Add order Page
   
@order
Scenario Outline: Order Number
    Then Order form should appear
    When User enters Accesion Number "<accesionNumber>"
    Then Validate "<status>" AccesionNumber Entered "<accesionNumber>"
    When User clicks Generate Button
    Then Generated Accesion Number should be a digit
    Examples:
     | accesionNumber     | status |
     | 20210000000001004  | valid  |
     | BETA119000047      |invalid |

@order
Scenario Outline: Request and Received Date
   Then View page Request Date and Received Date Default to the current date
   And Both request and received date should be mandatory
   When User enters incorrect Request and Received Date format "<incorrectDateValue>"
   Then Request and Received Date Fields should show error
   When User enters Request Date in future
   Then Alert should appear if date is in future
   When User enters correct Request and Received Date format
   Then Request and Received Date Fields should not show error
   Examples:
     | incorrectDateValue | 
     | 09-02/2019         | 

@order
Scenario Outline: Reception Time
When User enters Reception time "<entry>"
Then Field Automatically corrects "<action>" straight numeric to proper format HH:MM "<correctedTime>"
And Field validates "<status>" correct format 
Examples:
     | entry     |    action     | correctedTime | status   |
     | 1d2d77D   | auto-correct  |    12:77      | invalid  |
     | 1254      | auto-correct  |    12:54      | valid    |
     | 13:54     | none          |    13:54      | valid    |

@order
Scenario: Site Name
Then Site Name is mandatory
And User Selects Site Name from a Drop down Menu
And User Selects Program from a Drop down Menu


@order
Scenario Outline: Requester's Name
Then Requester's Last Name is mandatory
And User Enters Requester's Last Name "<lastName>" 
And User Enters Requester's First Name "<firstName>" 
Examples:
     | firstName  | lastName  | 
     | Aliou      | SADIO     |    

@order
Scenario Outline: Requester Phone/Fax/Email
When User Enters Telephone Number "<telephone>"
Then Validate "<status>" Telephone Number
And User Enters Fax "<fax>"
And User Enters Email "<email>"
Examples:
|     telephone    |     fax   |      email      | status |
| +23063458788     | 682737882 | uwash@gmail.com | valid  |     
| +225-33-45-87    |     -     |      -          |invalid | 

@order
Scenario: Add samples 
Then Sample addition is mandatory
When User Clicks on + Button next to Sample
Then Sample Selection Field appears 
And Sample types display in drop-down list
And User Selects Sample Type from Drop down menu 
And User Selects Sample Conditions from Drop down menu  
And User Clicks X to remove added Sample Conditions 
And User Clicks remove button to remove added Sample 
And User Re-adds Samples 
And User Clicks to Remove all  

@order
Scenario Outline: Collection Date
And User Clicks on + Button next to Sample
And User Selects Sample Type from Drop down menu 
When User enters Incorrect Date format "<incorrectDateValue>"
Then Text field hightlights in red
When User enters Date In the future
Then Pop-up alert appears if date is in the future
When User enters correct Date
Then Text field accepts the correct date format
Examples:
     | incorrectDateValue | 
     | 09-02/2019         |           
     | dd/mm/yyyyy        |

@order
Scenario Outline: Collection Time
And User Clicks on + Button next to Sample
And User Selects Sample Type from Drop down menu
When User enters Collection time "<entry>"
Then Field Automatically corrects "<action>" straight numeric to proper Collection Time format HH:MM "<correctedTime>"
And Field validates "<status>" Collection Time 
Examples:
     | entry     |    action     | correctedTime | status   |
     | 1d2d77D   | auto-correct  |    12:77      | invalid  |
     | 1254      | auto-correct  |    12:54      | valid    |
     | 13:54     | none          |    13:54      | valid    |
   

@order
Scenario Outline: Collector
And User Clicks on + Button next to Sample
And User Selects Sample Type from Drop down menu
When User Enters Collector "<collectorName>"
Then Field Acceps text "<collectorName>"
Examples:
     | collectorName   |  
     | Aliou  SADIO    |       

@order
Scenario Outline: Available Tests and Panels
And User Clicks on + Button next to Sample
And User Selects Sample Type from Drop down menu
Then Tests entry is marked mandatory
Then Available Tests exists
When User Checks checkbox next to test name
Then Checkbox sticks, test name appears under Tests box