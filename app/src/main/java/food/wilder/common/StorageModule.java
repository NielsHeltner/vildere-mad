package food.wilder.common;

import android.location.Location;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import food.wilder.persistence.ForageStorage;
import food.wilder.persistence.GpsStorage;

@Module
public class StorageModule {

    @Provides
    @Singleton
    public IStorage<Location> provideGpsStorage() {
        return GpsStorage.getInstance();
    }

    @Provides
    @Singleton
    public IStorage<IForageData> provideForageStorage() {
        return ForageStorage.getInstance();
    }

}
