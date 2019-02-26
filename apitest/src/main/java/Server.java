/**
 * Created by Rares on 25.02.2019.
 */
import com.google.gson.Gson;

import java.util.List;
import java.util.logging.Logger;

import static spark.Spark.*;
public class Server {
    private static final Logger LOG = Logger.getLogger(Server.class.getName());
    public static void main(String[] args)
    {
        initExceptionHandler((e) -> {
            System.err.println("Server failed to initialize - another instance might be already running");
            System.exit(100);

        });
        // Using string/html
        internalServerError("<html><body><h1>Internal Server Error 500 - Failed processing the request</h1></body></html>");
        notFound("<html><body><h1>Error 404 - route not found - try the route /getCars</h1></body></html>");
        Gson gson = new Gson();
        ApiService service = new ApiService();
        ApiProcessing apiProcessing = new ApiProcessing(service);
        get("/getCars", "application/json",(req, res) -> {

            try {
                double latitudePickup = Double.parseDouble(req.queryParams("latitudePickup"));
                double longitudePickup = Double.parseDouble(req.queryParams("longitudePickup"));
                double latitudeDropoff = Double.parseDouble(req.queryParams("latitudeDropoff"));
                double longitudeDropoff = Double.parseDouble(req.queryParams("longitudeDropoff"));
                int noPassengers = Integer.parseInt(req.queryParams("noPassengers"));
                if(noPassengers <= 0)
                    return "No. of passengers needs to be a positive number greater than 0";
                List<ReturnObject> returnObjects = apiProcessing.sendGET(latitudePickup,longitudePickup,latitudeDropoff,longitudeDropoff,noPassengers);
                if(returnObjects.size() == 0)
                    return "No results found for this query.";
                else
                    return returnObjects;
            }
            catch(NumberFormatException e)
            {
                LOG.info("Query parameter types are wrong");
                return "Query parameters are wrong. Input should look like : latitudePickup longitudePickup latitudeDropoff longitudeDropoff noPassengers";
            }

        }, gson::toJson);

    }
    public static void stopServer()
    {
        stop();
    }
}
