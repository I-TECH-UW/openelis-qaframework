Feature: Add Batch Orders Feature

  Background:
    Given User logins and goes to Add Batch Orders page

  @addBatchOrders
  Scenario Outline: Add Batch Orders action
    Then Configuration adding orders screen appears
    And Current date and received date set by default
    And Both Current date and Received date are mandatory
    When User enters incorrect received date "<invalidDateFormat>", Alert will appear
    And User enters Received date in the future
    Then Alert appears if the date is in the future
    And User enters Date "<invalidDateWithText>" with text then enters correct date "<validDate>" in the received date field
    Then User Clicks on change sample type select options "<sampleType>"
    Examples:
      | sampleType | invalidDateFormat | invalidDateWithText | validDate  |
      | Serum      | 24-02/2023        | 24-02/mm            | 12/01/2023 |

