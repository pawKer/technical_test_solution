import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Rares on 25.02.2019.
 */
public class Supplier {
    private String supplier_id;
    private String pickup;
    private String dropoff;
    private List<Option> options;


    public Supplier(String supplierId, String pickup, String dropoff, List<Option> options) {
        this.supplier_id = supplierId;
        this.pickup = pickup;
        this.dropoff = dropoff;
        this.options = options;
    }

    public Supplier() {
    }

    public String getSupplierId() {
        return supplier_id;
    }

    public void setSupplierId(String supplierId) {
        this.supplier_id = supplierId;
    }

    public String getPickup() {
        return pickup;
    }

    public void setPickup(String pickup) {
        this.pickup = pickup;
    }

    public String getDropoff() {
        return dropoff;
    }

    public void setDropoff(String dropoff) {
        this.dropoff = dropoff;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }
}
