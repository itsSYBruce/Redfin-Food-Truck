##FOOD TRUCK FINDER <br />

###Introduction

The program retrieves all food truck data based on criteria and
requirements specified. 

###To build and run the application
The project was built on a Mac machine inside of intelliJ, with Java 11 and Maven 3.6.0 <br />
If they have not been installed on your end, please follow instructions here: <br />
1. Java: 
    * https://medium.com/w-logs/installing-java-11-on-macos-with-homebrew-7f73c1e9fadf <br />

    * https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A
2. Maven:
    * https://formulae.brew.sh/formula/maven
    
    <br />
Afterwards, navigate to the root directory. Run command: <br />

    mvn clean install

and with the packaged jar file generated, run command: <br />

    mvn exec:java -Dexec.mainClass=FoodTruckFinder

Or, you could build and run directly from your preferred IDE. <br />

After the application has been executed, if there's any food truck open, you would see them with their name
and address printed in the terminal. You would also have a few options to navigate the terminal:
1. 'next', to move to the next page
2. 'prev', to move to the previous page
3. 'quit', to quit the application

###How to turn this into a more capable web application <br />

1.  Code base:
* We could have our code be more flexible to possibly accept dynamic parameters entered by our users, such as
    the page limit and specified time to query.
* We would add more error handling and global exception handler to make sure we cover all corner cases and 
    standardize error messages for debugging purposes.
* We could provide more options for the output to offer more flexibility in terms of how users could view it.  
* We would add automated unit and integration tests, and have CI/CD systems to deliver features. <br />
   
2.Architecture:
* Due to the limited number of calls we could make to Socrata's end points per hour, and the relatively slow 
internet I/O response time
    - We could benefit by having our own databases and storage solutions,
    which could pair up with periodic calls to the origin end points whenever Socrata's data gets updated (or have a 
    pipeline that runs overnight when traffics are low). This way, 
    we could fulfill a lot more user requests by querying our own storage with indexing and sorting in place, 
    which alleviates our dependency on the performance of third party apis.
    - We could implement a caching layer that would record requests and queries that have been made before, 
    which would significantly reduce the frequency of db and origin api visits. Only when we don't have an answer 
    from the cache, do we go proceed with more expensive and time consuming operations.
    - We could have have Kafka-like tools to collect telemetries and help us better understand user behaviors.

3.Infrastructure:
* We could add load balancers to distribute load during the most demanding hours and have databases scale to have 
backup nodes or "slave" nodes in case of data loses.
* We could add CDNs to work with caching layer and serve the nearest users to cut down the delay.


