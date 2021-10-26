
## OpenELIS-Global-2 Automated Testing Framework.
This is the new  OpenELIS-Global2 Automated testing Framework thats integrates both BDD with Cucumber and Fuctional Testing with Selenium using the Page Object Model design for high Maintainability ,Readability and Re-usability

[![Build Status](https://github.com/I-TECH-UW/openelis-qaframework/actions/workflows/qa.yml/badge.svg)](https://github.com/I-TECH-UW/openelis-qaframework/actions/workflows/qa.yml)

See [Wiki](https://github.com/I-TECH-UW/openelis-qaframework/wiki) for the Project SetUp

## Installing dependencies 

    mvn clean install -DskipTests=true

## Configuration
- Set Your test configurations in [src/test/resources/test.properties](./src/test/resources/test.properties)

- See Feature files under [src/features/openelis](./src/features/openelis)

### Running tests

1. All Test Features

        mvn test

2. To run individual Test Features ,You need to filter them by the feature filter tag.

        mvn test -Dcucumber.filter.tags='<@tag>'   

    ie , to run Login Feature tests  

         mvn test -Dcucumber.filter.tags='@login'   

 ## Test features       


| Test Feature      |Filter Tag     |Status        |
|:-----------------:|:-------------:|:-------------:|
| Login             | @login        | [![Build Status](https://github.com/I-TECH-UW/openelis-qaframework/actions/workflows/login.yml/badge.svg)](https://github.com/I-TECH-UW/openelis-qaframework/actions/workflows/login.yml)|
| Add order         | @order        | [![Build Status](https://github.com/I-TECH-UW/openelis-qaframework/actions/workflows/addOrder.yml/badge.svg)](https://github.com/I-TECH-UW/openelis-qaframework/actions/workflows/addOrder.yml)|  
| Results Entry     | @results      |[![Build Status](https://github.com/I-TECH-UW/openelis-qaframework/actions/workflows/resultEntry.yml/badge.svg)](https://github.com/I-TECH-UW/openelis-qaframework/actions/workflows/resultEntry.yml)| 
| Validation        | @validate     |[![Build Status](https://github.com/I-TECH-UW/openelis-qaframework/actions/workflows/validation.yml/badge.svg)](https://github.com/I-TECH-UW/openelis-qaframework/actions/workflows/validation.yml)|
| Patient Report    | @report       |[![Build Status](https://github.com/I-TECH-UW/openelis-qaframework/actions/workflows/patientReport.yml/badge.svg)](https://github.com/I-TECH-UW/openelis-qaframework/actions/workflows/patientReport.yml)|
| Patient Entry     | @patientEntry |[![Build Status](https://github.com/I-TECH-UW/openelis-qaframework/actions/workflows/patientEntry.yml/badge.svg)](https://github.com/I-TECH-UW/openelis-qaframework/actions/workflows/patientEntry.yml)|
| Mofidy Order      | @modifyOrder  |[![Build Status](https://github.com/I-TECH-UW/openelis-qaframework/actions/workflows/modifyOrder.yml/badge.svg)](https://github.com/I-TECH-UW/openelis-qaframework/actions/workflows/modifyOrder.yml)| 
| Referral Work Flow | @referral    |[![Build Status](https://github.com/I-TECH-UW/openelis-qaframework/actions/workflows/referalWorkFlow.yml/badge.svg)](https://github.com/I-TECH-UW/openelis-qaframework/actions/workflows/referalWorkFlow.yml)| 


 ## CI Local Tests
 [![Build Status](https://github.com/I-TECH-UW/openelis-qaframework/actions/workflows/test-local.yml/badge.svg)](https://github.com/I-TECH-UW/openelis-qaframework/actions/workflows/test-local.yml)

