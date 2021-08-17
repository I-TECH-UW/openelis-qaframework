
## OpenELIS-Global-2 Automated Testing Framework.
This is the new  OpenELIS-Global2 Automated testing Framework thats integrates both BDD with Cucumber and Fuctional Testing with Selenium using the Page Object Model design for high Maintainability ,Readability and Re-usability

[![Build Status](https://github.com/I-TECH-UW/openelis-qaframework/actions/workflows/qa.yml/badge.svg)](https://github.com/I-TECH-UW/openelis-qaframework/actions/workflows/qa.yml)

See [Wiki](https://github.com/I-TECH-UW/openelis-qaframework/wiki) for the Project SetUp

## Installing dependencies wthout running tests


    mvn clean install -Dcucumber.filter.tags='@null'

## Configuration
- Set Your test configurations in [src/test/resources/test.properties](./src/test/resources/test.properties)

- See Feature files under [src/features/openelis](./src/features/openelis)

### Running tests

1. All Test Features

        mvn test

2. To run individual Test Features/categories ,You need to filter them by the feature filter tag.

        mvn test -Dcucumber.filter.tags='<@tag>'   

    ie , to run Login Feature tests  

         mvn test -Dcucumber.filter.tags='@login'   

 See All test features bellow with their coresponding filter tags      


| Test Feature      |Filter Tag     |
|:-----------------:|:-------------:|
| Login             | @login        | 
| Add order         | @order        |  
| Results Entry     | @results      | 
| Validation        | @validate     |
| Patient Report    | @report       |
| Patient Entry     | @patientEntry |
| EMR-LIS Work Flow | @emrLis       |  
       
