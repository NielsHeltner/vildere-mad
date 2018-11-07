package food.wilder.common;

import android.app.Activity;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import food.wilder.domain.map.LocationService;

@Module
public class MapModule {

    private final Activity activity;

    public MapModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @Singleton
    public ILocationService provideLocationService() {
        return new LocationService(activity);
    }

}
