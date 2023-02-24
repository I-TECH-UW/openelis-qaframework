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
    And Sample types are displayed in drop-down list
    Then User Clicks on sample type dropdown list options "<sampleType>"
    Then Checklists for applicable Panels and Available Tests should appear for the sample type when that sample type is selected
    And User checks the box for test or panel
    Examples:
      | sampleType | invalidDateFormat | invalidDateWithText | validDate  |
      | Serum      | 24-02/2023        | 24-02/mm            | 12/01/2023 |

  @addBatchOrders
  Scenario Outline: Batch Order Entry Screen
    When User Clicks on sample type dropdown list options "<sampleType>"
    Then User checks the box for test or panel
    And In Optional Fields: User checks Site name "<siteName>"
    Then User Clicks next button
    And Batch order entry screen should contain UI options elements "<siteName>"
    Then User Clicks Generate Barcode and save button
    Examples:
      | sampleType | siteName     |
      | Serum      | CSU KONAHIRI |
