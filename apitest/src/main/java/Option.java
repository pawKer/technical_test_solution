import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rares on 25.02.2019.
 */
public class Option implements Comparable<Option>{
    private String car_type;
    private Long price;

    public Option(String carType, Long price) {
        this.car_type = carType;
        this.price = price;
    }

    public Option() {
    }

    public String getCarType() {
        return car_type;
    }

    public void setCarType(String carType) {
        this.car_type = carType;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public int compareTo(Option b)
    {
        if(this.price > b.getPrice())
            return 1;
        if(this.price < b.getPrice())
            return -1;
        else return 0;
    }

}
