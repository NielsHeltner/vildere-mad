package food.wilder.persistence.model;

import food.wilder.common.ITripData;

public class TripData implements ITripData {

    private String id;
    private long startTime;

    public TripData(String id, long startTime) {
        this.id = id;
        this.startTime = startTime;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public long getStartTime() {
        return startTime;
    }

}
