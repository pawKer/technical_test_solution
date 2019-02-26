import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Rares on 25.02.2019.
 */

public class ApiProcessingTest {
    ApiService mockService;
    ApiProcessing apiProcessing;
    @Before
    public void setup() throws IOException{
        mockService = Mockito.mock(ApiService.class);
        apiProcessing = new ApiProcessing(mockService);

        when(mockService.queryApi("")).thenReturn(new ArrayList<>());
    }
    @Test
    public void prepareInputTest() throws IOException{
        double latitudePickup = 51.470020;
        double longitudePickup = -0.454295;
        double latitudeDropoff = 51.470020;
        double longitudeDropoff = -0.454295;
        String query = apiProcessing.prepareInput(latitudePickup,longitudePickup,latitudeDropoff,longitudeDropoff);
        assertEquals("pickup=51.47002%2C-0.454295&dropoff=51.47002%2C-0.454295",query);
        assertNotSame("pickup=51.47002,0.454295&dropoff=51.47002,-0.454295",query);
    }
    @Test
    public void processApiDataTest() throws IOException{
        List<Supplier> supplierList = new ArrayList<>();
        List<Option> carList1 = new ArrayList<>();
        List<Option> carList2 = new ArrayList<>();
        Option car1 = new Option("STANDARD", new Long(100));
        Option car2 = new Option("EXECUTIVE", new Long(900));
        Option car3 = new Option("STANDARD", new Long(900));
        Option car4 = new Option("EXECUTIVE", new Long(500));
        Option car5 = new Option("PEOPLE_CARRIER", new Long(700));
        carList1.add(car1);
        carList1.add(car2);
        carList2.add(car3);
        carList2.add(car4);
        carList2.add(car5);
        Supplier sup1 = new Supplier("DAVE", "", "", carList1);
        Supplier sup2 = new Supplier("JEFF", "", "", carList2);
        supplierList.add(sup1);
        supplierList.add(sup2);

        ReturnObject r1 = new ReturnObject("STANDARD", "DAVE", new Long(100));
        ReturnObject r2 = new ReturnObject("EXECUTIVE", "JEFF", new Long(500));
        ReturnObject r3 = new ReturnObject("PEOPLE_CARRIER", "JEFF", new Long(700));

        List<ReturnObject> expectedResultList1 = new ArrayList<>();
        expectedResultList1.add(r3);
        expectedResultList1.add(r2);
        expectedResultList1.add(r1);

        List<ReturnObject> actualList = apiProcessing.processApiData(supplierList,4);
        assertEquals(expectedResultList1.toString(), actualList.toString());

        List<ReturnObject> actualList1 = apiProcessing.processApiData(supplierList,5);
        List<ReturnObject> expectedResultList2 = new ArrayList<>();
        expectedResultList2.add(r3);
        assertEquals(expectedResultList2.toString(), actualList1.toString());

        List<ReturnObject> actualList2 = apiProcessing.processApiData(supplierList,25);
        assertEquals("[]", actualList2.toString());

        List<ReturnObject> actualList3 = apiProcessing.processApiData(new ArrayList<>(),4);
        assertEquals("[]", actualList3.toString());

    }
    @Test
    public void sendGETTest() throws IOException
    {
        List<ReturnObject> list = apiProcessing.sendGET(51.4700,-0.454295,51.4700,-0.454295,4);
        verify(mockService).queryApi("pickup=51.47%2C-0.454295&dropoff=51.47%2C-0.454295");
    }
}
