package food.wilder.domain;

import android.location.Location;

import food.wilder.common.IForageData;

public class ForageData implements IForageData {

    private Location location;
    private String species;

    public ForageData(Location location, String species) {
        this.location = location;
        this.species = species;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public String getSpecies() {
        return species;
    }
}
