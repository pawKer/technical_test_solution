/**
 * Created by Rares on 25.02.2019.
 */
import com.google.gson.*;
import javafx.util.Pair;
import java.io.IOException;
import java.net.*;
import java.util.*;
import java.io.InputStream;
import java.util.logging.Logger;

public class ApiConsoleApplication {

        private static final Logger LOG = Logger.getLogger(ApiConsoleApplication.class.getName());

        public static void main(String[] args) throws IOException
        {
            if(args.length != 5)
                throw new IllegalArgumentException("Input should look like : latitudePickup longitudePickup latitudeDropoff longitudeDropoff noPassengers");
            try {
                double latitudePickup = Double.parseDouble(args[0]);
                double longitudePickup = Double.parseDouble(args[1]);
                double latitudeDropoff = Double.parseDouble(args[2]);
                double longitudeDropoff = Double.parseDouble(args[3]);
                int noPassengers = Integer.parseInt(args[4]);

                if(noPassengers <= 0)
                {
                    throw new IllegalArgumentException("No. of passengers needs to be a positive number > 0.");
                }

                ApiService service = new ApiService();
                ApiProcessing apiProcessing = new ApiProcessing(service);

                List<ReturnObject> resultList = apiProcessing.sendGET(latitudePickup, longitudePickup, latitudeDropoff, longitudeDropoff, noPassengers);
                for(ReturnObject entry : resultList)
                {
                    System.out.println(entry);
                }
            }
            catch(NumberFormatException e)
            {
                throw new NumberFormatException("Latitude and longitude should be double and no of passengers int");
            }

        }



}
