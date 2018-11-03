package dz.cdta.smartbin.model;

public class SmartBin {

    private Long smartBinId;
    private double latitude;
    private double longitude;
    private double quantite;
    private String addressBin;
    private int filter;

    public SmartBin(double latitude, double longitude, double quantite, String addressBin,int filter) {
        super();
        this.latitude = latitude;
        this.longitude = longitude;
        this.quantite = quantite;
        this.addressBin = addressBin;
        this.filter = filter;
    }

    public Long getSmartBinId() {
        return smartBinId;
    }

    public void setSmartBinId(Long smartBinId) {
        this.smartBinId = smartBinId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public String getAddressBin() {
        return addressBin;
    }

    public void setAddressBin(String addressBin) {
        this.addressBin = addressBin;
    }

    public int getFilter() {
        return filter;
    }

    public void setFilter(int filter) {
        this.filter = filter;
    }
}
