
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
