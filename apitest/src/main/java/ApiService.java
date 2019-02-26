import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Created by Rares on 26.02.2019.
 */
public class ApiService {
    private static final String GET_URL = "https://techtest.rideways.com/";
    private static final String CHARSET = "UTF-8";
    private static final String[] SUPPLIERS = {"DAVE" , "ERIC", "JEFF"};
    private static final Logger LOG = Logger.getLogger(ApiService.class.getName());

    public List<Supplier> queryApi(String query) throws IOException
    {       List<Supplier> supplierList = new ArrayList<>();
        for(String supplierName : SUPPLIERS)
        {
            URL url = new URL(GET_URL + supplierName + "?" + query);
            try
            {
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setConnectTimeout(2000);
                con.setRequestMethod("GET");
                con.setRequestProperty("Accept-Charset", CHARSET);

                if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream response = con.getInputStream();
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    Scanner scanner = new Scanner(response);
                    String responseBody = scanner.next();
                    Supplier sup = gson.fromJson(responseBody, Supplier.class);
                    LOG.info("FULL RESPONSE TEXT: " + responseBody);
                    supplierList.add(sup);
                } else {
                    LOG.warning("HTTP RETURNED STATUS CODE " + con.getResponseCode());
                    LOG.warning("Skipped supplier " + supplierName);
                }
            } catch (SocketTimeoutException e) {
                LOG.warning("Http request timed out!");
                LOG.warning("Skipped supplier " + supplierName);
            }
        }
        return supplierList;
    }
}
