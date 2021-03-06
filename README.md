# Rmm Services

Rmm Services is a REST web application which associates hardware devices with software services.  
Furthermore it calculates the monthly bill of services usage, based on the services per device.  
It uses basic authentication as security

## Software configuration

The rmm-service application is developed in:

	Spring
	Spring boot -> (version 2.0.5)
	Spring security
	Java -> (version 1.8)
	PostgreSQL -> (version 11)
	Gradle

This project was forked from:
 
	https://github.com/NinjaMSP/rmm-services-serverapp	

The implementation of the services described are hosted in  
(markers should access to this repository to evaluate the code):

	https://github.com/ksaweryn/rmm-services-serverapp

## Project organization

The project has the basic package structure _'com.javier'_

	* rmmservices.model -> It has the entities mappings
	* rmmservices.dao -> It has the access to the data stored in the model classes
	* rmmservices -> It has the main method and the beans which provides different
	* rmmservices.controller -> It provides de URL access to the REST services
	* rmmservices.secutiry -> It handles the security of the REST services

## Compilation & Run

* Connect to your PostgreSQL server and create a database called rmm-db
	
	- In order to connect to the database you should have PostgreSQL 11 installed
	- Then, create role rmm-db with password Admin2019
	- The last configuration is for the application.properties file (where the database connection is declared)
	- This project supposes that PostgreSQL and the rmm-service application run in the same server
		* If your database is in other sever, please update the application.properties file as required.

### Create or restore database

It is possible to restore
	
* You have two options to create or re-create the model of the database:

	1. Restore from rmm-services/extras/rmm_backup.bak
	2. After running the application it will create the required model with empty data.
	
#### Create database

To create a fresh database:

	1. Change in the application.properties file the option spring.jpa.hibernate.ddl-auto from none to create.
	2. In the SecurityConfiguration class change uncomment the code found and comment the immediate next section.  

#### Restore database

To restore the database it must be used the backup file located in the _extras_ folder.  
The file rmm_backup.bak has the backup database.  
It was created through _pg__dump_ command.  
To use this backup please connect to the database with the user rmm-db and execute (as it was created in the _Compilation & Run_ section).  

```console
$ psql rmm-db < $PATH_TO_BACKUP/rmm_backup.bak 
```

### Run the application 
	 
* Download the project
* Build the project with Gradle
* To generate a bootable jar run the gradle task:

```console
$ gradle ask booJar
```
	
* To run the project:

```console  
$ java -jar $PROJECT_PATH/build/libs/rmm-services-1.0.0.jar  
```
	
If the project is correctly compiled and executed you will access to page localhost:8080 and it will prompt a login page.  
After the application has deployed please restore the database.  
There are two default users:

	user with password userPass
	admin with password adminPass
	
Use _user_ to test the REST services.   
_admin_ is for "houserkeeping" tasks, which are not implemented. 

## Rest services

This section describes the REST services and their URLs.  
It is possible to test these services in you web browser.  
Although it is recommended to test these services with a REST client.  
In this guide it will be tested with SOAPUI software and curl (to be run in the command line)

### Device Services

The services for device are located at  
_localhost:8080/device/_

The services are:

* _add_  
It creates a new device
It is a POST http method  
 
* _addRMMServices_  
It adds a rmm service to the device and it could also update the device  
It is a PUT http method  

* _deleteRMMService_  
It removes a rmm service from the device  
It is a PUT http method  
  
* _delete_  
It deletes the device from the database  
It is a DELETE http method  
  
* _findBySystemName_  
It finds a device bases on the system name parameter  
It is a GET http method    
  
* _findById_  
It finds a device by its id (which is the primary key)  
It is a GET http method    
  
* findAll  
It retrieves all devices  
It is a GET http method    

### RMM Services

The services for device are located at  
_localhost:8080/rmmService/_

The services are:

* _add_  
It creates a new rmm service
It is a POST http method  
 
* _update_  
It updates a rmm service  
It is a PUT http method  

* _delete_  
It deletes the rmm service from the database  
It is a DELETE http method  
  
* _findById_  
It finds a rmm service by its id (which is the primary key)  
It is a GET http method    
  
* findAll  
It retrieves all rmm services  
It is a GET http method  

### Customer Services

The services for device are located at  
_localhost:8080/customer/_

The services are:

* _bill_  
It calculates the bill of the devices and services
It is a GET http method  

### Working examples

In the extras folder there is the file _RMM-Services-soapui-project.xml_ which is a working example of the use of REST services to be run in SOAPUI software.  
SOAPUI can be downloaded from [here](https://www.soapui.org/downloads/latest-release.html)  
It works for Windows, Linux and Mac OS.  

## References
The project was based on the next web pages as a reference of implementation

	1. https://www.baeldung.com/securing-a-restful-web-service-with-spring-security
	2. https://dzone.com/articles/thoughts-and-ideas-related-to-java-programming
	3. https://docs.spring.io/spring-boot/docs/current/reference/html/howto-database-initialization.html#howto-initialize-a-database-using-spring-jdbc
	4. https://docs.spring.io/spring-boot/docs/current/reference/html/deployment-install.html
	5. https://docs.spring.io/spring-security/site/docs/3.0.x/reference/appendix-schema.html
	6. https://www.programcreek.com/java-api-examples/?code=PacktPublishing/Spring-5.0-Cookbook/Spring-5.0-Cookbook-master/Chapter13/ch04/src/main/java/org/packt/secured/mvc/core/AppSecurityModelC.java
	7. https://www.baeldung.com/authentication-against-a-third-party-service
	8. https://www.baeldung.com/basic-and-digest-authentication-for-a-rest-api-with-spring-security
	9. https://www.baeldung.com/exception-handling-for-rest-with-spring
	10. https://spring.io/guides/gs/spring-boot/
	11. https://www.baeldung.com/java-config-spring-security
	12. https://docs.spring.io/spring-boot/docs/current-SNAPSHOT/gradle-plugin/reference/html/#packaging-executable-configuring-launch-script
	13. https://confluence.atlassian.com/bitbucketserver/markdown-syntax-guide-776639995.html
