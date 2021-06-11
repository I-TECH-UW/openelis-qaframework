Feature: Add Order

Background:
    Given User logs in and visits Home page
    And User clicks add order and goes to Add order Page
   
@order
Scenario Outline: Order Number
    Then Order form should appear
    When User enters Accesion Number "<accesionNumber>"
    Then Assert AccesionNumber Entered "<accesionNumber>"
    When User clicks Generate Button
    Then Generated Accesion Number should be a digit
    Examples:
     | accesionNumber |
     | 1234519000029  |

@order
Scenario Outline: Request and Received Date
   Then View page Request Date and Received Date Default to the current date
   And Both request and received date should be mandatory
   When User enters incorrect Request and Received Date format "<incorrectDateFormat>"
   Then Request and Received Date Fields should show error
   When User enters Request Date in future
   Then Alert should appear if date is in future
   When User enters correct Request and Received Date format
   Then Request and Received Date Fields should not show error
   Examples:
     | incorrectDateFormat | 
     | 09-02/2019          | 

@order
Scenario Outline: Reception Time
When User enters Reception time "<entry>"
Then Field Automatically corrects "<action>" straight numeric to proper format HH:MM "<correctedTime>"
And Field validates "<status>" correct format 
Examples:
     | entry     |    action     | correctedTime | status   |
     | 1d2d77D   | auto-correct  |    12:77      | rejected |
     | 1254      | auto-correct  |    12:54      | accepted |
     | 13:54     | none          |    13:54      | accepted |

@order
Scenario Outline: Site Name
Then Site Name is mandatory
And Select Site Name from a Drop down Menu
And Select Program from a Drop down Menu


@order
Scenario Outline:  Requester's Name
Then Requester's Last Name is mandatory
And Enter Requester's Last Name "<lastName>" 
And Enter Requester's First Name "<firstName>" 
Examples:
     | firstName  | lastName  | 
     | Aliou      | SADIO     |    

@order
Scenario Outline:  Requester Phone/Fax/Email
Then Enter Telephone Number "<telephone>"
And Enter Fax "<fax>"
And Enter Email "<email>"
Examples:
|     telephone    |     fax   |      email      |
| +225-33-45-87-88 | 682737882 | uwash@gmail.com | 

         
