package food.wilder.domain;

public class TripData {

    private String id;
    private int timestamp;

    public TripData(String id, int timestamp) {
        this.id = id;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public int getTimestamp() {
        return timestamp;
    }
}
