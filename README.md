# technical_test_solution
I'm using Maven version 3.3.9 and Java version 1.8.0_144.

* To run the first part you can either import it into an IDE, create a run configuration as a Java Application with the main class as **ApiConsoleApplication** and the Program arguments in the format latitudePickup longitudePickup latitudeDropoff longitudeDropoff noPassengers
* Alternatively, you can use this command 
    `mvn exec:java -Dexec.mainClass="ApiConsoleApplication" -Dexec.args="arg0 arg1 arg2 arg3 arg4"`
* For the second part, to run the web server you could either to the same as above, creating run configuration and selecting the main class as **Server** or run this command 
  `mvn exec:java -Dexec.mainClass="Server"`
