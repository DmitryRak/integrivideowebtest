# README #

PREREQUISITES:

* Java 8
* Maven 3

TO RUN TESTS:

* mvn clean install serenity:aggregate
* mvn clean install -Dbrowser=firefox -Dgroups=edit,upload serenity:aggregate

TO VIEW RESULTS:

* See /target/site/index.html