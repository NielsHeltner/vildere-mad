package food.wilder.common.dependency_injection;

import android.location.Location;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import food.wilder.common.IForageData;
import food.wilder.common.IStorage;
import food.wilder.common.ITripData;
import food.wilder.common.IUserData;
import food.wilder.persistence.storage.ForageStorage;
import food.wilder.persistence.storage.GpsStorage;
import food.wilder.persistence.storage.TripStorage;
import food.wilder.persistence.storage.UserStorage;

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

    @Provides
    @Singleton
    public IStorage<IUserData> provideUserStorage() {
        return UserStorage.getInstance();
    }

    @Provides
    @Singleton
    public IStorage<ITripData> provideTripStorage() {
        return TripStorage.getInstance();
    }

}
