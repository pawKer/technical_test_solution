import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Rares on 26.02.2019.
 */
public class CarCapacityTest {

    @Test
    public void testCapacity(){
        assertEquals(4, CarCapacity.getCarCapacity("STANDARD"));
        assertEquals(4, CarCapacity.getCarCapacity("EXECUTIVE"));
        assertEquals(4, CarCapacity.getCarCapacity("LUXURY"));
        assertEquals(6, CarCapacity.getCarCapacity("PEOPLE_CARRIER"));
        assertEquals(6, CarCapacity.getCarCapacity("LUXURY_PEOPLE_CARRIER"));
        assertEquals(16, CarCapacity.getCarCapacity("MINIBUS"));
        assertEquals(-1, CarCapacity.getCarCapacity("RANDOM_CAR_TYPE"));
    }
}
