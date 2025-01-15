# TOOLSHOP API TESTING FRAMEWORK

## OVERVIEW
This repository contains a Hybrid Framework designed for testing the ToolShop API,


## SWAGGER DOCUMENT LINK :-
Link :- https://api-with-bugs.practicesoftwaretesting.com/api/documentation#/

## PREREQUISITES
* Java: JDK 17 or above installed.
* Maven: Installed and configured in the system.
* Jenkins: Set up for CI/CD pipeline integration (optional).
* IDE: IntelliJ IDEA, Eclipse, or any preferred IDE.


## APPROACH:-
* Reusable REST requests.
* POJOs (Plain Old Java Objects) used for API payloads.
* Lombok used to reduce boilerplate code.
* Builder pattern applied for setter methods in POJOs.
* Data pattern used for getter methods in POJOs.
* Generated Extent Reports for detailed test results.
* Request and response details added in the Extent Reports.
* Integrated with Git for version control.
* Tests executed through Jenkins for CI/CD.


## TEST REPORT SCREENSHOTS :-
![pic_1](reportsnaps/pic_1.png)

![pic_2](reportsnaps/pic_2.png)

### Jenkins
![pic_3](reportsnaps/jenkins_1.png)

## TOOLS AND TECHNOLOGY USED :-
* Java
* Rest Assured
* TestNg
* Lombok
* ExtentReports
* JavaFaker
* Eclipse