package model.model;

public class Hotel {

    private String name;
    private String adress;
    private double priceSingle;
    private double priceDouble;
    private boolean hasWifi = false;
    private boolean hasBreakfast = false;
    private boolean hasShower = false;
    private double wifiPrice = 0;
    private double breakfastPrice = 0;
    private double showerPrice = 0;

    public Hotel(String name, String adress, double priceSingle, double priceDouble) {
        this.name = name;
        this.adress = adress;
        this.priceSingle = priceSingle;
        this.priceDouble = priceDouble;
    }

    public Hotel(String name, String adress, double priceSingle, double priceDouble, boolean hasWifi,
            boolean hasBreakfast, boolean hasShower, double wifiPrice, double breakfastPrice, double showerPrice) {
        this.name = name;
        this.adress = adress;
        this.priceSingle = priceSingle;
        this.priceDouble = priceDouble;
        this.hasWifi = hasWifi;
        this.hasBreakfast = hasBreakfast;
        this.hasShower = hasShower;
        this.wifiPrice = wifiPrice;
        this.breakfastPrice = breakfastPrice;
        this.showerPrice = showerPrice;
    }

    public boolean isHasWifi() {
        return hasWifi;
    }

    public void setHasWifi(boolean hasWifi) {
        this.hasWifi = hasWifi;
    }

    public boolean isHasBreakfast() {
        return hasBreakfast;
    }

    public void setHasBreakfast(boolean hasBreakfast) {
        this.hasBreakfast = hasBreakfast;
    }

    public boolean isHasShower() {
        return hasShower;
    }

    public void setHasShower(boolean hasShower) {
        this.hasShower = hasShower;
    }

    public double getWifiPrice() {
        return wifiPrice;
    }

    public void setWifiPrice(double wifiPrice) {
        this.wifiPrice = wifiPrice;
    }

    public double getBreakfastPrice() {
        return breakfastPrice;
    }

    public void setBreakfastPrice(double breakfastPrice) {
        this.breakfastPrice = breakfastPrice;
    }

    public double getShowerPrice() {
        return showerPrice;
    }

    public void setShowerPrice(double showerPrice) {
        this.showerPrice = showerPrice;
    }

    public double getPriceSingle() {
        return priceSingle;
    }

    public double getPriceDouble() {
        return priceDouble;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getAdress() {
        return adress;
    }

}
