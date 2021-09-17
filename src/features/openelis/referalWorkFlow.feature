Feature: Referral Work Flow 

Background:
    Given User logs in into the OpenELIS  System
    And User is able to log in


@referral
Scenario Outline: Register a patient's sample for Covid testing and send the lab request and sample to the Central Health Laboratory
When User Goes to Order tab--> Add Order
And User Completes the Order section of the Order form with accesionNumber "<labNo>" 
Then Lab number "<labNo>" can be entered|scanned successfully
And Lab Number field will not accept incorrect format "<incorectLabNumber>"
When User Completes the Sample section of the Order form
Then Sample Type list displays all needed options 
And Panels and Tests appear correctly
When User Completes the Referral sub-section under Sample section of the Order form
Then Referral details fields appear when the Refer test to a reference lab box is ticked 
And Reasons for Referral shows default value 'Test not performed'
And Reasons for Referral list appears in alphabetical order
And Referrer field is autofilled with name of logged in user
And Laboratory names appear in alphabetical order under Institute
And Sent Date is autofilled with current date 
And Sent Date can be modified 
And Test Name defaults to original test requested for sample above
Examples:
    |labNo            |incorectLabNumber|
    |20210000000008080| BETA119000047   |

