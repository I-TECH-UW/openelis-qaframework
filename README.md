
## OpenELIS-Global-2 Quality Assurance Framework.
This is the new  OpenELIS-Global2 Automated testing Framework thats integrates both BDD with Cucumber and Fuctional Testing with Selenium.

![Build Status](https://github.com/I-TECH-UW/openelis-qaframework/actions/workflows/qa.yml/badge.svg)
## Installing dependencies wthout running tests

    mvn clean install -Dcucumber.filter.tags='@null'

## Configuration
- Set Your test configurations in [src/test/resources/test.properties](./src/test/resources/test.properties)

- See Feature files under [src/features/openelis](./src/features/openelis)

### Running test projects

1. All OE-tests

        mvn test

2. login tests 

       mvn test -Dcucumber.filter.tags='@login'

3. Add order tests 

       mvn test -Dcucumber.filter.tags='@order'
