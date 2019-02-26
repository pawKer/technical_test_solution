/**
 * Created by Rares on 25.02.2019.
 */
public class ReturnObject implements Comparable<ReturnObject>{
    private String carType;
    private String supplierName;
    private Long carPrice;

    public ReturnObject(String carType, String supplierName, Long carPrice) {
        this.carType = carType;
        this.supplierName = supplierName;
        this.carPrice = carPrice;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Long getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(Long carPrice) {
        this.carPrice = carPrice;
    }

    @Override
    public int compareTo(ReturnObject o) {
        if(this.carPrice > o.getCarPrice())
            return 1;
        if(this.carPrice < o.getCarPrice())
            return -1;
        else return 0;
    }

    @Override
    public String toString(){
        return this.carType + " - " + this.supplierName + " - " + this.carPrice;
    }

}
