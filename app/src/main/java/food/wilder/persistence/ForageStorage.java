package food.wilder.persistence;

import java.util.ArrayList;

import food.wilder.common.IForageData;
import food.wilder.common.IStorage;

public class ForageStorage extends AbstractBufferedStorage<IForageData> {

    private static IStorage<IForageData> instance;

    private ForageStorage() {
        data = new ArrayList();
    }

    public static IStorage<IForageData> getInstance() {
        if(instance == null) {
            instance = new ForageStorage();
        }
        return instance;
    }

    @Override
    public void upload() {
        // upload to server / database
        data.clear();
    }
}
