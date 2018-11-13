package food.wilder.common.dependency_injection;

import android.location.Location;

import javax.inject.Singleton;

import dagger.Component;
import food.wilder.common.IForageData;
import food.wilder.common.IStorage;
import food.wilder.common.ITripData;
import food.wilder.common.IUserData;

@Singleton
@Component(modules = StorageModule.class)
public interface StorageComponent {

    IStorage<Location> provideGpsStorage();

    IStorage<IForageData> provideForageStorage();

    IStorage<IUserData> provideUserStorage();

    IStorage<ITripData> provideTripStorage();

}
