package it59070098.kmitl.healthy;

public class Weight {
    private String date;
    private float weight;
    private String status;

    public Weight(){

    }

    public Weight(String date, float weight, String status){
        this.date = date;
        this.weight = weight;
        this.status = status;

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
