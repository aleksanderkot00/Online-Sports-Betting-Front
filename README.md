# Online Sports Betting Front-End
Simple front-end in Vaadin to Online-Sports-Betting Api - https://github.com/aleksanderkot00/Online-Sports-Betting

## DEMO

[Demo of this application connected with back-end](https://osb-front.herokuapp.com/). To use it register or use user:

email: user@user

password: user

It will take a while before front-end and back-end will be loaded.

## HOW TO RUN

To run this project in your IDE. You need to have lombok plugin and enable annotation processing. If there will be problems with building change 
```
compileOnly('org.projectlombok:lombok')
annotationProcessor('org.projectlombok:lombok')
```
to
```
compile('org.projectlombok:lombok')
```
in build.gradle. Project is connected to back-end that have to be run first. 

Front-end address: [http://localhost:8081/](http://localhost:8081/)

If online back-end database does not working you can use local database and make the appropriate changes in back-end application.properties. 
To generate example data run ExampleData test in back-end.