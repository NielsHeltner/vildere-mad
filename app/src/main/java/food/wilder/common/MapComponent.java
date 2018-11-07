package food.wilder.common;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = MapModule.class)
public interface MapComponent {

    ILocationService locationService();

}
