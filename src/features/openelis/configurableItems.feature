Feature: Configurable Items Feature

  Background:
    Given User Logins,then goes to home Page

  @configurableItems
  Scenario Outline: Order Entry Configuration
    Given User Clicks Admin menu --> then Order Entry Configuration
    Then Check auto-fill collection date-time (configurable item)
    And User changes auto-fill collection date-time value and clicks save
    Examples:
      | configuration                  | configurationId | newValue |
      | auto-fill collection date/time | selectedIDs1    | true     |
#      | billingRefNumber               | selectedIDs2    |          |
#      | billingRefNumberLocalization   | selectedIDs3    |          |
#      | contactTracingEnabled          | selectedIDs4    |          |
#      | external orders                | selectedIDs5    |          |
#      | Program                        | selectedIDs6    |          |
#      | restrictFreeTextProviderEntry  | selectedIDs7    |          |
#      | restrictFreeTextRefSiteEntry   | selectedIDs8    |          |
#      | trackPayment                   | selectedIDs9    |          |
