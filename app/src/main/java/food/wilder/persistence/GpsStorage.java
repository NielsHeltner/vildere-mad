package food.wilder.persistence;

import android.content.Context;
import android.location.Location;

import java.util.ArrayList;
import java.util.List;

import food.wilder.common.Callback;
import food.wilder.common.IStorage;

public class GpsStorage extends AbstractBufferedStorage<Location> {

    private static IStorage<Location> instance;
    private List<Location> tempDataList;

    private GpsStorage() {
        data = new ArrayList();
    }

    public static IStorage<Location> getInstance() {
        if(instance == null) {
            instance = new GpsStorage();
        }
        return instance;
    }

    @Override
    public void upload(Context context, String id) {
        // Upload gps points to database
        moveDataToTempAndClear();
    }

    private void moveDataToTempAndClear() {
        tempDataList = new ArrayList(data);
        data.clear();
    }

}
