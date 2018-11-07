package food.wilder.common;

import android.location.Location;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = StorageModule.class)
public interface StorageComponent {

    IStorage<Location> provideGpsStorage();

    IStorage<IForageData> provideForageStorage();

}
