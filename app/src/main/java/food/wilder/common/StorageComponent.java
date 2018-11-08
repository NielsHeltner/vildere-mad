package food.wilder.common;

import android.location.Location;

import javax.inject.Singleton;

import dagger.Component;
import food.wilder.domain.UserData;

@Singleton
@Component(modules = StorageModule.class)
public interface StorageComponent {

    IStorage<Location> provideGpsStorage();

    IStorage<IForageData> provideForageStorage();

    IStorage<UserData> provideUserStorage();

}
