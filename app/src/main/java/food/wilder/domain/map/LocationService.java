package food.wilder.domain.map;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import javax.inject.Inject;
import javax.inject.Singleton;

import food.wilder.R;
import food.wilder.common.ILocationService;

@Singleton
public class LocationService implements ILocationService {

    private Activity activity;
    private FusedLocationProviderClient fusedLocationClient;

    @Inject
    public LocationService(Activity activity) {
        this.activity = activity;
    }

}
