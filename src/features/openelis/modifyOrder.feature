Feature: Modify Order

Background:
    Given User Logs in to Home Page and goes to Modify Order Page

@modifyOrder
Scenario Outline: Order Search
Then Search appears at top of page
And Search button deactivated until search criteria selected and text is entered
And Search text boxes display correct search criteria on the Modify Order Page