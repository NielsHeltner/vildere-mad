package food.wilder.domain;

import android.location.Location;

import food.wilder.common.IForageData;

public class ForageData implements IForageData {

    private Location location;
    private int plantType;

    public ForageData(Location location, int plantType) {
        this.location = location;
        this.plantType = plantType;
    }

    public Location getLocation() {
        return location;
    }

    public int getPlantType() {
        return plantType;
    }
}
