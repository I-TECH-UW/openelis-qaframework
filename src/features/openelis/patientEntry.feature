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
    When User enters known Patient national Identification Number "<patientId>"
    Then Search by Patient national Identification Number yields results for known matching names
    When User enters known Subject Number "<subjectNumber>"
    Then Search by Subject Number yields results for known matching names
    When User Selects correct patient
    Then Patient Information form populates with patient information
    When User Clicks New Patient on the Add Patient Page
    Then Patient Information form clears
    Examples:
      | lastName | firstName | patientId | subjectNumber | labNo             |
      | seruwu   | jimmy     | ug012     | oe012         | 20210000000002250 |

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
    When User Selects a Health Region from the drop-down list
    Then All Health Regions list should be visible
    When User Selects a Health District from the drop-down list
    Then All Health Districts under the Health Region selected in the previous step should be visible
    When User Fills in Date of Birth "<dateOfBirth>"
    Then Date of Birth is mandatory
    And Alert appears if DOB format "<incorrectDob>" is incorrect
    And Alert appears if date of birth is in the future
    And Automatically fills correct age when DOB "<dateOfBirth>" is filled in
    When User Deletes Date of Birth and enters Age "<age>"
    Then If DOB is left blank and Age is filled,Field generates DOB with correct year for Age "<age>"
    And Alert appears if Age is -1 , 100 and 100+
    When User Selects from drop-down list for gender
    Then Gender options are displayed form drop-down list
    Examples:
      | dateOfBirth | incorrectDob | age |
      | 24/04/1992  | wrongDate    | 20  |

  @patientEntry
  Scenario: Overall Page
    When User Leaves mandatory fields without data on Add Patient Page
    Then Save button deactivated until all mandatory fields are filled on Add Patient Page
    When User Completes all mandatory fields on Add Patient Page
    Then Save button activated when all mandatory fields are filled on Add Patient Page
    When User Clicks Cancel on Add Patient Page
    Then Pop-up message appears, `Leave Site? Changes you made may not be saved` on Add Patient Page
    When User Clicks Cancel to Dismis alert
    Then Patient Information form remains on Add Patient screen
    When User Clicks Save on Add Patient Page
    Then A clear Add|Modify Patient form appears along with the message, `Save was successful` in green
    And User Goes to the bottom of the page and click Cancel and Returns to home page


  @patientEntry
  Scenario Outline: Verification
    When User Searches for Patient on the Add Order Page for a known Patient with known last name "<lastName>" and first name "<firstName>"
    Then Correct patient information ,patient Identification no "<patientId>",subject number "<subjectNumber>" last name "<lastName>" , first name "<firstName>", address "<address>", date of birth "<dateOfBirth>" , Age "<age>", Gender "<gender>" , maritalStatus "<maritalStatus>" and nationality "<nationality>" appears when searched for
    Examples:
      | lastName | firstName | patientId | subjectNumber | address | dateOfBirth | age | gender   | maritalStatus | nationality |
      | seruwu   | jimmy     | ug012     | oe012         | Gayaza  | 09/02/2019  | 4   | 1 = Male | DNA           | uganda      |
