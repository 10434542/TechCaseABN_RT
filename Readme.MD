### INTRO

TechCase for ABN AMRO: create a recipes api where recipes can be created
and queried

### BUILD
under infrastructure/postgres is a docker compose file for just the db, run it
then run this application in intellij or use
```
./gradlew clean build -Dtest.exclude="integration"
```
With the optional flag to turn off integration testing

Once docker image of db is running, you can run some http requests under requests folder

### TODO
List of things that still need some attention:
- proper testing of endpoints by hand
- fix swagger ui since it is inaccessible atm unfortunately
- rework the data model
- have another look at the mappers and circular dependency issues
- implement proper oauth2 flow
- use keycloak instead of auth0 for even more control