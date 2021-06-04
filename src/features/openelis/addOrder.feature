Feature: Add Order

Background:
    Given User logs in and visits Home page
   
@order
Scenario Outline: Order Number
    When User clicks add order and goes to Add order Page
    Then Order form should appear
    Then Accesion Number should be mandatory
    When User enters Accesion Number "<accesionNumber>"
    Then Assert AccesionNumber Entered "<accesionNumber>"
    When User clicks Generate Button
    Then Generated Accesion Number should be a digit
    Examples:
     | accesionNumber |
     | 1234519000029  |

         
