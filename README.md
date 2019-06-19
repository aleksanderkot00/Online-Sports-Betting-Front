# Online Sports Betting Front-End
Simple front-end in Vaadin to Online-Sports-Betting Api - https://github.com/aleksanderkot00/Online-Sports-Betting

## DEMO

[Demo of this application connected with back-end](https://osb-front.herokuapp.com/). To use it register or use user:

email: user@user
password: user

It will take a while before front-end and back-end will be loaded.

## HOW TO RUN

To run this project in your IDE you need to have lombok plugin and enable annotation processing. If there will be problems with building change 
```
compileOnly('org.projectlombok:lombok')
annotationProcessor('org.projectlombok:lombok')
```
to
```
compile('org.projectlombok:lombok')
```
in build.gradle. Project is connected to online database then you need Internet connection.

