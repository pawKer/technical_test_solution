import java.security.KeyException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rares on 25.02.2019.
 */
public class CarCapacity{
    private static final Map<String, Integer> carCapacityMap = new HashMap<String, Integer>()
    {
        {
            put("STANDARD", 4);
            put("EXECUTIVE", 4);
            put("LUXURY", 4);
            put("PEOPLE_CARRIER", 6);
            put("LUXURY_PEOPLE_CARRIER", 6);
            put("MINIBUS", 16);
        }
    };

    public static int getCarCapacity(String carType)
    {
        if(carCapacityMap.containsKey(carType))
            return carCapacityMap.get(carType);
        else
            return -1;
    }
}
