Feature: Referral Work Flow 

# Use Case 1 :I am a receptionist at the Flu Clinic
@referral
Scenario Outline: Register a patient's sample for Covid testing and send the lab request and sample to the Central Health Laboratory
When User logs in into the OpenELIS System
Then User is able to log in
When User Goes to Order tab--> Add Order
Then User turns on contact tracing configuration
And User Completes the Order section of the Order form with accesionNumber "<labNo>" 
Then Lab number "<labNo>" can be entered|scanned successfully
#And Lab Number field will not accept incorrect format "<incorectLabNumber>"
When User Completes the Sample section of the Order form
Then Sample Type list displays all needed options
And Panels and Tests appear correctly
When User Completes the Referral sub-section under Sample section of the Order form, with referral Institute "<referalInstitute>"
Then Referral details fields appear when the Refer test to a reference lab box is ticked
And Reasons for Referral shows default value 'Test not performed'
And Reasons for Referral list appears in alphabetical order
And Referrer field is autofilled with name of logged in user
And Laboratory names appear in alphabetical order under Institute
And Sent Date is autofilled with current date
And Sent Date can be modified
And Test Name defaults to original test requested for sample above
When User Enters search parameter Patient ID "<patientId>" for a known patient and click Search
Then Patient details appear in search results table
When User Enters search parameter Last Name "<lastName>" for a known patient and click Search
Then Patient details appear in search results table
When User Enters search parameter First Name "<firstName>" for a known patient and click Search
Then Patient details appear in search results table
When User Enter search parameter [Previous] Lab Number "<existinglabNo>" for a known patient and click Search
Then Patient search shows green wheel while searching internally ,OE database
And Patient details appear in search results table
And  If correct patient is selected, their information populates the Patient section of the order form
When User Reviews and completes the  Patient Information section
Then Save button is activated ,if all required fields are filled
And User Ticks the boxes for Patient notification by Email and SMS
And User Ticks the boxes for Requester notification by Email and SMS
When User Clicks Save to save form on Add Order Page
And Message 'Save Was Successful' appears at the top of the page
When User Clicks 'Print labels' Button
Then User is able to print barcode label that matches all order information
# CONFIRMATION STEPS
When User Go to Order tab --> Modify Order
And User Enters lab number "<labNo>" from test order
Then Order details are correct
Examples:
    |labNo            |incorectLabNumber|existinglabNo    |patientId|lastName|firstName  |referalInstitute |
    |20230000000008182| BETA119000047   |20230000000008423|202307D9P|musa   | muranga|Test referral lab|

@referral
Scenario Outline: Register test requests sent electronically from the Flu Clinic
When User logs in into the referral OpenELIS System
Then User is able to log in into the referral OpenELIS System
When User Goes to Order tab --> Electronic Orders
And User Enters lab number "<labNo>" in Search Test Requests, and Click Search
Then Order details appear in the table 
When User Enters Patient Last Name "<lastName>" in Search Test Requests, and Click Search
Then Order details appear in the table 
When User Enters Patient First Name "<lastName>" in Search Test Requests, and Click Search
Then Order details appear in the table 
When User Enters Patient Id "<patientId>" in Search Test Requests, and Click Search
Then Order details appear in the table 
Examples:
    |labNo            |lastName|firstName  |patientId|
    |20230000000008182|musa   | muranga|202307D9P|


