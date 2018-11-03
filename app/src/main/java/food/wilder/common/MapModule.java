package food.wilder.common;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import food.wilder.domain.map.LocationService;

@Module
public class MapModule {

    @Provides
    @Singleton
    public static ILocationService provideLocationService() {
        return new LocationService();
    }

}
