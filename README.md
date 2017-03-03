# Jira Estimate
Jira Estimate is a RESTful API that serves data on issues and issue types. We can use this API to get the total amount of estimated points for each type of issues.
## Spring Boot Restful Application

### Prerequisite:

- JDK 1.8
- Maven

### Getting started using Docker
1) Ensure docker is installed: https://docs.docker.com/engine/installation/linux/ubuntulinux/
```sh
$ docker -v
Docker version 17.03.0-ce, build 3a232c8
```
2) Ensure Maven is installed: http://maven.apache.org/install.html
```sh
$ mvn --version
Apache Maven 3.3.9
```
3) Ensure Java is installed
```sh
$ java -version
openjdk version "1.8.0_121"
```
3) Download git repository:
```sh
$ git clone https://github.com/lyandrew/JE.git
```
4) Build the docker image
```sh
$ mvn package docker:build
```
5) Run the docker image using docker and a custom port
```sh
$ docker run -p 8080:8080 -t jira-estimate-1.0
```


### Getting started using localhost
1) Ensure Maven is installed: http://maven.apache.org/install.html
```sh
$ mvn --version
Apache Maven 3.3.9
```
2) Ensure Java is installed
```sh
$ java -version
openjdk version "1.8.0_121"
```
3) Clone the directory
```sh
$ git clone https://github.com/lyandrew/JE.git
```
4) Package the code into a Jar file
```sh
$ mvn package
```
5) Run the jar
```sh
$ java -jar target/jira-1.0.jar
```
This application offers the following endpoints:
- GET /issues/\<issue id>
- GET /issuetypes/\<issue type>

### Implementation Details
This was written in Java using the Spring Boot Starter kit. Spring boot was choosen because it was quick to get working out of the box and there is a large reduction of boilerpoint templates (as oppose to Spring MVC). Files/packages are separated into a 3-tier architecture: data, service, and presentation. The data layer (DAO - data access object) provides an abstract interface (IssueDao) to some type of database (IssueDaoImpl). IssueDaoImpl is a mocked up database that is served with sample issue/issuetype responses. The service layer controls the logical part of the application. It talks to the data layer to get the necessary information (issue by id/types). Because there weren't any complex logic, the service layer is small simple. The presentation layer (controller) intercepts the HTTP request and returns a response.


## Total Estimated Points
At this point whether you ran the application locally or using Docker, the REST api service should be running. Now we can walk the API to get the total estimated points.

### Prequisite
- `Requests` Library for python (http://docs.python-requests.org/en/master/)

To get the estimated points for story and bugs we can use the python script in this repository.
```sh
$ python estimate.py story
story : 4
```
Note that the accepted arguments is space delimited type of issues. 
Also note that we can pass the optional `--url <url>` argument to the script.
```sh
$ python estimate.py bug story --url http://localhost:8080
bug : 10
story : 4
```
### Implementation Detail
In a nutshell, this application makes a request call for each type of issue. Because the response contains a list of issues that are of the same type, we can then make a request call for each of these issue and sum it to get the total estimated points for each type. Using a dictionary (hashmap) data structure is the most efficient because of the O(1) lookup.
