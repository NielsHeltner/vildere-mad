package food.wilder.domain.map;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;

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

    public void a() {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) { // checks if permissions are granted (https://developer.android.com/training/permissions/requesting)
            Log.d(activity.getString(R.string.app_name), "Requesting permissions");
            ActivityCompat.requestPermissions(activity, ILocationService.PERMISSION_REQUESTS, ILocationService.PERMISSION_REQUEST_CODE);
            return;
        }
    }

}
