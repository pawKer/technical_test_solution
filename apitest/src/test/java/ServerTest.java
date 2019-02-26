import org.junit.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/**
 * Created by Rares on 26.02.2019.
 */
public class ServerTest {
    private static final String URL_PATH = "http://localhost:4567/getCars";
    private static final String WRONG_URL_PATH = "http://localhost:4567/hello";
    @BeforeClass
    public static void setup()
    {
        Server.main(new String[0]);
    }
    @Test
    public void goodRequestTest() throws IOException
    {

        String query = "latitudePickup=5&longitudePickup=-0.45&latitudeDropoff=5&longitudeDropoff=-0.45&noPassengers=4";
        URL url = new URL( URL_PATH + "?" + query);

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setConnectTimeout(2000);
        con.setRequestMethod("GET");
        assertEquals(HttpURLConnection.HTTP_OK, con.getResponseCode());

    }
    @Test
    public void wrongRouteTest() throws IOException
    {

        String query = "latitudePickup=5&longitudePickup=-0.45&latitudeDropoff=5&longitudeDropoff=-0.45&noPassengers=-5";
        URL url = new URL( WRONG_URL_PATH + "?" + query);

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setConnectTimeout(2000);
        con.setRequestMethod("GET");
        assertEquals(HttpURLConnection.HTTP_NOT_FOUND, con.getResponseCode());

    }
    @Test
    public void badRequestTest() throws IOException
    {

        String query = "latitudePickup=5&longitudePickup=-0.45&latitudeDropoff=5&longitudeDropoff=-0.45&noPassengers=-5";
        URL url = new URL( URL_PATH + "?" + query);

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setConnectTimeout(2000);
        con.setRequestMethod("GET");
        assertEquals("\"No. of passengers needs to be a positive number greater than 0\"", new Scanner(con.getInputStream()).nextLine());

    }
    @AfterClass
    public static void stopServer()
    {
        Server.stopServer();
    }
}
