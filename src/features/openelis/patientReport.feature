Feature: Patient Report

Background:
    Given User Vists Home Page and goes to Add Order Page

@report
Scenario: Patient Report With no result yet
When User Enters order on Order Entry page, Complete ALL fields, and does not enter results for this order
And User Generates the Patient Report for this order without Results
Then Verify the generated Report

@report
Scenario: Patient Report With Partial result reported
When User Goes to Modify Order for the same order from Use Case 1 and cancel one test
And User Looks up Results Entry for the same order, Complete some but not all results
And User Validates all results but one for this order, Send the last result back to Retest			
And User Generates the Patient Report for this order with Results
Then Verify the generated Report			
		