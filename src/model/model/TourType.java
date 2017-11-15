package model.model;

public class TourType {
    private String name;
    private String description;
    private boolean lunchIncluded;
    private double price;
    private short maxParticipants;

    public TourType(String name, String description, double price, short maxParticipants) {
        this.name = name;
        this.price = price;
        this.maxParticipants = maxParticipants;
        lunchIncluded = false;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isLunchIncluded() {
        return lunchIncluded;
    }

    public void setLunchIncluded(boolean lunchIncluded) {
        this.lunchIncluded = lunchIncluded;
    }

    public double getPrice() {
        return price;
    }

    public short getMaxParticipants() {
        return maxParticipants;
    }
}
