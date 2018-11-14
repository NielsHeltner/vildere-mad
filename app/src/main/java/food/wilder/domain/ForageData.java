package food.wilder.domain;

import android.location.Location;

import food.wilder.common.IForageData;

public class ForageData implements IForageData {

    private Location location;
    private String plantType;

    public ForageData(Location location, String plantType) {
        this.location = location;
        this.plantType = plantType;
    }

    public Location getLocation() {
        return location;
    }

    public String getPlantType() {
        return plantType;
    }
}
