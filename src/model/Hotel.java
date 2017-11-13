package model;

public class Hotel {

    private String name;
    private String adress;
    private float priceSingle;
    private float priceDouble;
    private boolean hasWifi;
    private boolean hasBreakfast;
    private boolean hasShower;
    private float wifiPrice;
    private float breakfastPrice;
    private float showerPrice;

    public Hotel(String name, String adress, float priceSingle, float priceDouble) {
        this.name = name;
        this.adress = adress;
        this.priceSingle = priceSingle;
        this.priceDouble = priceDouble;
        this.hasWifi = false;
        this.hasBreakfast = false;
        this.hasShower = false;
    }

    public Hotel(String name, String adress, float priceSingle, float priceDouble, boolean hasWifi,
            boolean hasBreakfast, boolean hasShower, float wifiPrice, float breakfastPrice, float showerPrice) {
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

    public float getWifiPrice() {
        return wifiPrice;
    }

    public void setWifiPrice(float wifiPrice) {
        this.wifiPrice = wifiPrice;
    }

    public float getBreakfastPrice() {
        return breakfastPrice;
    }

    public void setBreakfastPrice(float breakfastPrice) {
        this.breakfastPrice = breakfastPrice;
    }

    public float getShowerPrice() {
        return showerPrice;
    }

    public void setShowerPrice(float showerPrice) {
        this.showerPrice = showerPrice;
    }
}