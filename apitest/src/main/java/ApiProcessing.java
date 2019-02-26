import javafx.util.Pair;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;
import java.util.logging.Logger;

/**
 * Created by Rares on 26.02.2019.
 */
public class ApiProcessing {
    private static ApiService apiService;
    private static final Logger LOG = Logger.getLogger(ApiProcessing.class.getName());
    private static final String CHARSET = "UTF-8";

    public ApiProcessing(ApiService apiService)
    {
        this.apiService = apiService;
    }

    public static String prepareInput(double latitudePickup, double longitudePickup, double latitudeDropoff, double longitudeDropoff) throws IOException
    {
        String pickupCoords = latitudePickup + "," + longitudePickup;
        String dropoffCoords = latitudeDropoff + "," + longitudeDropoff;
        String query = String.format("pickup=%s&dropoff=%s",
                URLEncoder.encode(pickupCoords, CHARSET),
                URLEncoder.encode(dropoffCoords, CHARSET));
        return query;
    }

    public static List<ReturnObject> processApiData(List<Supplier> supplierResults, int noPassengers)
    {
        Map<String, List<Pair<String, Long>>> aggregatedApiData = new HashMap<String, List<Pair<String, Long>>>();
        for(Supplier sup : supplierResults) {
            for (Option option : sup.getOptions()) {
                if (noPassengers <= CarCapacity.getCarCapacity(option.getCarType()))
                    if (!aggregatedApiData.containsKey(option.getCarType())) {
                        List<Pair<String, Long>> priceList = new ArrayList<Pair<String, Long>>();
                        priceList.add(new Pair(sup.getSupplierId(), option.getPrice()));
                        aggregatedApiData.put(option.getCarType(), priceList);
                    } else
                        aggregatedApiData.get(option.getCarType()).add(new Pair(sup.getSupplierId(), option.getPrice()));
            }
        }
        return findMinAndOrder(aggregatedApiData);
    }

    public static List<ReturnObject> findMinAndOrder(Map<String, List<Pair<String, Long>>> priceMap)
    {
        List<ReturnObject> resultList = new ArrayList<>();
        for(Map.Entry<String, List<Pair<String, Long>>> entry : priceMap.entrySet())
        {
            long minPrice = Long.MAX_VALUE;
            String minSup = "";
            for(Pair<String, Long> pricePair : entry.getValue())
            {
                if(pricePair.getValue() < minPrice)
                {
                    minPrice = pricePair.getValue();
                    minSup = pricePair.getKey();
                }

            }
            resultList.add(new ReturnObject(entry.getKey(), minSup, minPrice));
        }
        Collections.sort(resultList, Collections.<ReturnObject>reverseOrder());
        return resultList;
    }


    public List<ReturnObject> sendGET(double latitudePickup, double longitudePickup, double latitudeDropoff, double longitudeDropoff, int noPassengers) throws IOException {

        String query = prepareInput(latitudePickup, longitudePickup, latitudeDropoff, longitudeDropoff);

        List<Supplier> supplierList = apiService.queryApi(query);

        List<ReturnObject> finalList = processApiData(supplierList, noPassengers);

        return finalList;
    }
}
