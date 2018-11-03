package food.wilder.common;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = MapModule.class)
public interface MapComponent {

    ILocationService locationService();

}
