Feature: Non Conformity workflow

  Background:
    Given User Logins in to Home Page


  @nonConformity
  Scenario Outline: Search Non Conformity report
    Given User Selects Non Conformity report from main menu
    When User Selects search by --> Lab No dropdown option
    Then User enters accession number "<accessionNumber>"
    And User checks the affected specimen
    Then User clicks go to NCE reporting form button
    And Report date default is set to current date
    And Report date can be modified

    Examples:
      | accessionNumber   |
      | 20210000000003761 |

