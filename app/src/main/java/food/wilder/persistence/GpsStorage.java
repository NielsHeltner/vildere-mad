package food.wilder.persistence;

import android.location.Location;

import java.util.ArrayList;

import food.wilder.common.IStorage;

public class GpsStorage extends AbstractBufferedStorage<Location> {

    private static IStorage<Location> instance;

    private GpsStorage() {
        data = new ArrayList();
    }

    public static IStorage<Location> getInstance() {
        if (instance == null) {
            instance = new GpsStorage();
        }
        return instance;
    }

    @Override
    public void upload() {
        // upload to server / database
        data.clear();
    }

}
