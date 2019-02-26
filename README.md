# technical_test_solution
I'm using Maven version 3.3.9 and Java version 1.8.0_144.

## Part 1
For part 1 of the requirements I have a main class called `ApiConsoleApplication` that takes the required input and then uses an instance of the class `ApiProcessing` to get the data from the API and do transformations on it. The `ApiProcessing` class uses an instance of the `ApiService` class to query the API. The result of the API call is a list of custom objects `ReturnObject` that has a carType, supplierName, carPrice - for each car type we keep the supplier with the lowest price. I then print each of these objects on a new line in descending price order.
* To run the first part you can either import it into an IDE, create a run configuration as a Java Application with the main class as **ApiConsoleApplication** and the Program arguments in the format `latitudePickup longitudePickup latitudeDropoff longitudeDropoff noPassengers`
* Alternatively, you can use this command 
    `mvn exec:java -Dexec.mainClass="ApiConsoleApplication" -Dexec.args="arg0 arg1 arg2 arg3 arg4"`

## Part 2
For part 2 I have used a very simple Java framework called [Spark](http://sparkjava.com/) that allowed me to create a web server very fast and having the main logic already implemented in part 1 I have just made use of those methods to get the data. The `Server` class is where the routes are defined.
* To run the web server you could either to the same as above, creating run configuration and selecting the main class as **Server** or run this command 
  `mvn exec:java -Dexec.mainClass="Server"`
* A web server will start at `localhost:4567` and the path for part 2 is `localhost:4567/getCars`. The request should look like this `http://localhost:4567/getCars?latitudePickup=51.470020&longitudePickup=-0.454295&latitudeDropoff=51.470020&longitudeDropoff=-0.454295&noPassengers=4`
