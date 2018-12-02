package food.wilder.gui.map;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import food.wilder.R;
import food.wilder.common.IForageData;
import food.wilder.common.IStorage;
import food.wilder.common.dependency_injection.DaggerStorageComponent;
import food.wilder.common.dependency_injection.StorageComponent;
import food.wilder.domain.ForageData;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    public static final int INIT_PERMISSION_REQUEST_CODE = 6124;
    public static final int LAST_LCOATION_PERMISSION_REQUEST_CODE = 6125;
    public static final String[] PERMISSION_REQUESTS = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };
    public static final long DUTY_CYCLE_INTERVAL_DEFAULT_SECONDS = 5;
    public static final long DUTY_CYCLE_INTERVAL_LOW_BATTERY_SECONDS = 15;

    @BindView(R.id.map)
    MapView mapView;
    private GoogleMap map;

    private FusedLocationProviderClient fusedLocationClient;

    @Inject
    IStorage<Location> gpsStorage;
    @Inject
    IStorage<IForageData> forageStorage;

    private long dutyCycle = DUTY_CYCLE_INTERVAL_DEFAULT_SECONDS;
    private ScheduledExecutorService executorService;
    private Future<?> locationFuture;
    private Runnable sensingTask = () -> {
        //if accelerometer crossed threshold / we detect movement, then do the following:
        getLastLocation().addOnSuccessListener(location -> {
            if (location != null) {
                Log.d(getString(R.string.app_name), String.valueOf(location.getLatitude()));
                Log.d(getString(R.string.app_name), String.valueOf(location.getLongitude()));

                gpsStorage.add(location);
            }
        });
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);
        StorageComponent component = DaggerStorageComponent.create();
        gpsStorage = component.provideGpsStorage();
        forageStorage = component.provideForageStorage();
        executorService = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());

        initLocation();
        startSensing();
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
    }

    private void startSensing() {
        locationFuture = executorService.scheduleAtFixedRate(sensingTask, 0, dutyCycle, TimeUnit.SECONDS);
    }

    private void stopSensing() {
        if (locationFuture != null) {
            locationFuture.cancel(true);
        }
    }

    private void changeDutyCycle(long dutyCycle) {
        stopSensing();
        this.dutyCycle = dutyCycle;
        startSensing();
    }

    @OnClick(R.id.forageBtn)
    public void forage() {
        Toast.makeText(getApplicationContext(), "Click", Toast.LENGTH_SHORT).show();

        getLastLocation().addOnSuccessListener(location -> {
            LatLng forageLocation = new LatLng(location.getLatitude(), location.getLongitude());
            map.addMarker(new MarkerOptions().position(forageLocation).title(getTimeFormatted(location.getTime())));

            forageStorage.add(new ForageData(location, "Placeholder"));
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

        getLastLocation().addOnSuccessListener(location -> {
            LatLng lastLocation = new LatLng(location.getLatitude(), location.getLongitude());
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(lastLocation, 10));
        });
    }

    @SuppressLint("MissingPermission")
    private Task<Location> getLastLocation() {
        return fusedLocationClient.getLastLocation();
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
