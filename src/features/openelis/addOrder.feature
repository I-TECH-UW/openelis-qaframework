Feature: Add Order

Background:
    Given User logs in and visits Home page
    And User clicks add order and goes to Add order Page

@order
Scenario Outline: Order Number
    Then Order form should appear
    And Remember site and requester checkbox exists
    When User enters Accesion Number "<accesionNumber>"
    Then Validate "<status>" AccesionNumber Entered "<accesionNumber>"
    When User clicks Generate Button
    Then Generated Accesion Number should be a digit
    Examples:
     | accesionNumber     | status |
     | 20230000000001290  | valid  |
     | BETA119000047      |invalid |

@order
Scenario Outline: Request and Received Date
   Then Turn off accession number validation from the previous stage
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
When User enters site name suggestion text "6666 - TESTING ARV"
Then User Selects Site Name from a Drop down Menu
And User Selects Program from a Drop down Menu


@order
Scenario Outline: Requester's Name
Then Requester's Name is mandatory
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
  Scenario: Payment fields and Sampling performed for analysis
  Then User selects patient payment status drop down list
    When User selects others from Sampling performed for analysis drop down list
    Then Others sampling performed text box appears
    And Others sampling performed text field accepts text "other"

@order
Scenario: Add samples
Then Sample addition is mandatory
When User Clicks on + Button next to Sample
Then Sample Selection Field appears
And Sample types display in drop-down list
And User Selects Sample Type from Drop down menu
Then User Checks the Reject checkbox
And User Selects Sample Conditions from Drop down menu
And User Clicks reject checkBox to remove added Sample Conditions
And User Clicks remove button to remove added Sample
And User Re-adds Samples
#And User Clicks to Remove all

@order
Scenario Outline: Collection Date
When User Clicks on + Button next to Sample
And User Selects Sample Type from Drop down menu
And User enters Incorrect Date format "<incorrectDateValue>"
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
When User Clicks on + Button next to Sample
And User Selects Sample Type from Drop down menu
And User enters Collection time "<entry>"
Then Field Automatically corrects "<action>" straight numeric to proper Collection Time format HH:MM "<correctedTime>"
And Field validates "<status>" Collection Time
Examples:
     | entry     |    action     | correctedTime | status   |
     | 1d2d77D   | auto-correct  |    12:77      | invalid  |
     | 1254      | auto-correct  |    12:54      | valid    |
     | 13:54     | none          |    13:54      | valid    |


@order
Scenario Outline: Collector
When User Clicks on + Button next to Sample
And User Selects Sample Type from Drop down menu
And User Enters Collector "<collectorName>"
Then Field Acceps text "<collectorName>"
Examples:
     | collectorName   |
     | Aliou  SADIO    |

@order
Scenario: Available Tests and Panels
When User Clicks on + Button next to Sample
And User Selects Sample Type from Drop down menu
Then Tests entry is marked mandatory
And Available Tests exists
When User Checks checkbox next to test name
Then Checkbox sticks, test name appears under Tests box
When User unChecks checkbox next to test name
Then Name disappears from Tests box
When User Checks checkbox next to Panel name
Then All applicable panel tests apear in the Testsbox
When User unChecks checkbox next to Panel name
Then All Test Names disappears from Tests box
When User enters Text in Tests Box
Then Text cannot be entered in Tests Box
When User deletes Text in Tests Box
Then Text cannot be cleared in Tests Box

@order
Scenario Outline: Patient Search
Then Patient information form is marked mandatory
When User Expands Patient information form by clicking the + button next to Patient
Then Patient Search appears
Then Search button deactivated until search criteria selected and text entered
And User Searches by Accesion Number "<accesionNumber>"
And User Searches by Patient Id "<patientId>"
And User Searches by Last Name "<lastName>"
And User Searches by First Name "<firstName>"
And User Searches by Date of Birth "<dateOfBirth>"
Examples:
     | accesionNumber    | patientId  | lastName | firstName  | dateOfBirth |
     | 20230000000001290 | 201507D33  |   Aliou  | SADIO      | 09/02/2019  |

@order
Scenario Outline: Patient Information
When User Expands Patient information form by clicking the + button next to Patient
And User Clicks New Patient Button
And User Enters Subject Number "<subjectNumber>"
And User Enters National ID "<nationalId>"
And User Enters Patient Last Name "<pLastName>"
And User Enters Patient First Name "<pFirstName>"
And User Enters Contact Last Name "<cLastName>"
And User Enters Contact First Name "<cFirstName>"
And User Enters Contact Email "<cEmail>"
Then Field validates "<status>" Contact Email
And User Enters Contact Phone "<cPhone>"
And User Enters Patient Street "<street>"
And User Enters Patient Commune "<commune>"
And User Enters Patient Town "<town>"
And User Enters Patient Phone "<pPhone>"
Then Field validates "<status>" Patient Phone
And User Enters Patient Email "<pEmail>"
Then Field validates "<status>" Patient Email
And User Selects Patient Health Region
And User Selects Patient Health District from the drop down
And User Enters Patient Date of Birth "<dateOfBirth>"
Then Field validates "<status>" Patient Date Of Birth
When User Enters Patient Date of Birth in future
Then Alert appears if Date is in the Future
When Date of Birth is left blank and Age "<age>" is filled
Then Field generates Date of Birth with correct year for the Age "<age>"
And Alert Appears if Age entered is -1 , 100 and >100
And User Selects Patient Gender
And User Selects Patient Education
And User Selects Patient Marital Status
And User Enters Patient Other Nationality "<nationality>"
Examples:
     |subjectNumber|nationalId |pLastName|pFirstName|cLastName|cFirstName|  cEmail         | cPhone        |        street              |commune   |town  |pPhone       |pEmail        |dateOfBirth |nationality|age|status  |
     | 202307D9P   | 201507D35 |   Aliou | SADIO    |  musa  | mutes    | wrongEmail      | +23063458788  | New York city, street 3334 |Grand yoff| Dakar|ff223377     |wrongEmail    |09/02/dd    |American   |20 |invalid |
     | 202307D9P   | 201507D35 |   Aliou | SADIO    |  musa  | mutes    |contact@gmail.com| +23063458788  | New York city, street 3334 |Grand yoff| Dakar|+23063458788 |pat@gmail.com |09/02/2019  |American   |20 |valid   |

@order
Scenario: Overall Page
Then Save button deactivated until all mandatory fields are completed
When User Completes all mandatory fields
Then Save button is Activated when all mandatory fields are completed
When User Clicks Cancel
And User Click Stay on Page
Then Patient Information form remains on screen
When User Clicks Save
Then New blank Add Order form appears with green Save was successful message on the top
And User Clicks Cancel and Returns to Home Page
