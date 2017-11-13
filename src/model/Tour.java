package model;

public class Tour {
    private String name;
    private String description;
    private boolean lunchIncluded;
    private float price;
    private short maxParticipants;

    public Tour(String name, String description, float price, short maxParticipants) {
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

    public float getPrice() {
        return price;
    }

    public short getMaxParticipants() {
        return maxParticipants;
    }
}
