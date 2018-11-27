package food.wilder.gui.map;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.ActivityRecognition;
import com.google.android.gms.location.ActivityTransition;
import com.google.android.gms.location.ActivityTransitionRequest;
import com.google.android.gms.location.DetectedActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import food.wilder.R;
import food.wilder.common.DaggerStorageComponent;
import food.wilder.common.IForageData;
import food.wilder.common.IStorage;
import food.wilder.common.StorageComponent;
import food.wilder.domain.ForageData;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    public static final int INIT_PERMISSION_REQUEST_CODE = 6124;
    public static final int LAST_LCOATION_PERMISSION_REQUEST_CODE = 6125;
    public static final int PENDING_INTENT = 123;
    public static final String[] PERMISSION_REQUESTS = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    @BindView(R.id.map)
    MapView mapView;
    private GoogleMap map;

    private FusedLocationProviderClient fusedLocationClient;

    @Inject
    IStorage<Location> gpsStorage;
    @Inject
    IStorage<IForageData> forageStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);
        StorageComponent component = DaggerStorageComponent.create();
        gpsStorage = component.provideGpsStorage();
        forageStorage = component.provideForageStorage();

        initLocation();
        initTransition();
    }

    private void initTransition() {
        List<ActivityTransition> transitions = new ArrayList<>();

        transitions.add(
                new ActivityTransition.Builder()
                        .setActivityType(DetectedActivity.WALKING)
                        .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_ENTER)
                        .build());

        transitions.add(
                new ActivityTransition.Builder()
                        .setActivityType(DetectedActivity.WALKING)
                        .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_EXIT)
                        .build());

        transitions.add(
                new ActivityTransition.Builder()
                        .setActivityType(DetectedActivity.STILL)
                        .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_ENTER)
                        .build());

        transitions.add(
                new ActivityTransition.Builder()
                        .setActivityType(DetectedActivity.STILL)
                        .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_EXIT)
                        .build());

        Intent broadcast = new Intent(this, TransitionReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, PENDING_INTENT, broadcast, 0);


        ActivityTransitionRequest request = new ActivityTransitionRequest(transitions);
        Task<Void> task =
                ActivityRecognition.getClient(this).requestActivityTransitionUpdates(request, pendingIntent);

        task.addOnSuccessListener(
                result -> {
                    Log.d("TRANSITION", "Success");

                }
        );

        task.addOnFailureListener(
                e -> {
                    // Handle error
                }
        );
    }

    private void initLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) { // checks if permissions are granted (https://developer.android.com/training/permissions/requesting)
            Log.d(getString(R.string.app_name), "Requesting permissions");
            ActivityCompat.requestPermissions(this, PERMISSION_REQUESTS, INIT_PERMISSION_REQUEST_CODE);
            return;
        }

        Log.d(getString(R.string.app_name), "Starting location client");
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        initMapView(null);

        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationCallback locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    Log.d(getString(R.string.app_name), String.valueOf(location.getLatitude()));
                    Log.d(getString(R.string.app_name), String.valueOf(location.getLongitude()));

                    gpsStorage.add(location);
                }
            }
        };

        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
    }

    @SuppressLint("MissingPermission")
    @OnClick(R.id.forageBtn)
    public void forage() {
        Toast.makeText(getApplicationContext(), "Click", Toast.LENGTH_SHORT).show();

        fusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                LatLng forageLocation = new LatLng(location.getLatitude(), location.getLongitude());
                map.addMarker(new MarkerOptions().position(forageLocation).title(getTimeFormatted(location.getTime())));

                forageStorage.add(new ForageData(location, 1));
            }
        });
    }

    /**
     * Invoked when the user responds to the app's permission request
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == INIT_PERMISSION_REQUEST_CODE) { // requestCode used to link permission requests together
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) { // if the request is cancelled, the result arrays are empty
                Log.d(getString(R.string.app_name), "Permission was granted");
                initLocation();
            } else {
                Log.d(getString(R.string.app_name), "Permission was denied");
            }
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setMyLocationEnabled(true);
        map.getUiSettings().setMyLocationButtonEnabled(true);

        fusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                LatLng lastLocation = new LatLng(location.getLatitude(), location.getLongitude());
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(lastLocation, 10));
            }
        });
    }

    private void initMapView(Bundle savedInstanceState) {
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);
    }

    private String getTimeFormatted(long timeMs) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss", Locale.US);
        return simpleDateFormat.format(new Date(timeMs));
    }

}
