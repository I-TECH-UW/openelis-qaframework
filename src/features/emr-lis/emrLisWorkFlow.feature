Feature: EMR-LIS Work Flows

Background:
    Given The EMR Supports FHIR R4
    And The LIS Supports FHIR R4
    And The EMR and LIS are trusted applications known by the IOL
    And The SHR Supports FHIR R4

@emrLis
Scenario: Lab Odering
    When EMR Creates Lab Order
    Then The order save generates a FHIR R4 Task Resource
    When EMR Sends New Lab Order
    Then FHIR Task bundled order is sent to the IOL 
    And Bundled order is routed through the IOL to both the SHR and the LIS
    When LIS Saves Order and Update Order Status
    Then FHIR R4 Task Resource Status is updated locally 
    When LIS Sends Order Update
    Then Updated Task Resource Status is sent to the IOL
    And IOL routes the updated FHIR R4 Tasks to the SHR and the EMR
    When EMR Updates FHIR Task Status
    Then FHIR task status updated locally

@emrLis
Scenario:  Report Lab Results
    When LIS Saves Result and Updates FHIR Task 
    Then The Results save generates a FHIR R4 DiagnosticReport Resource 
    When LIS EMR Sends Lab Results
    Then Diagnositc Report Resource is sent to the IOL
    And Diagnostic Report Resource is routed through the IOL to both the SHR and the EMR
    And EMR saves Results
