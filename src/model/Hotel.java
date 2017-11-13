package model;

public class Hotel {

    private String name;
    private String adress;
    private double priceSingle;
    private double priceDouble;
    private boolean hasWifi;
    private boolean hasBreakfast;
    private boolean hasShower;
    private double wifiPrice;
    private double breakfastPrice;
    private double showerPrice;

    public Hotel(String name, String adress, float priceSingle, float priceDouble) {
        this.name = name;
        this.adress = adress;
        this.priceSingle = priceSingle;
        this.priceDouble = priceDouble;
        this.hasWifi = false;
        this.hasBreakfast = false;
        this.hasShower = false;
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
}
