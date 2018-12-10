package food.wilder.gui.map;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;

import java.util.List;

import food.wilder.R;
import food.wilder.common.AsyncPersistenceCallback;
import food.wilder.common.IStorage;
import food.wilder.common.dependency_injection.DaggerStorageComponent;
import food.wilder.common.dependency_injection.StorageComponent;


/**
 * An activity that displays a Google map with polylines to represent paths or routes,
 * and polygons to represent areas.
 */
public class RouteActivity extends AppCompatActivity
        implements
        OnMapReadyCallback,
        GoogleMap.OnPolylineClickListener {

    private static final int COLOR_BLACK_ARGB = 0xff000000;

    private static final int POLYLINE_STROKE_WIDTH_PX = 12;

    private MapView mapView;
    IStorage<Location> gpsStorage;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Retrieve the content view that renders the map.
        setContentView(R.layout.activity_route);

        intent = getIntent();

        StorageComponent component = DaggerStorageComponent.create();
        gpsStorage = component.provideGpsStorage();

        mapView = findViewById(R.id.routeMap);
        // Get the SupportMapFragment and request notification when the map is ready to be used.
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        PolylineOptions options = new PolylineOptions()
                .clickable(true);
        String query = "?trip_id=" + intent.getStringExtra("trip_id");
        gpsStorage.get(this, query, (AsyncPersistenceCallback<List<Location>>) locationList -> {
            for (Location location : locationList) {
                options.add(new LatLng(location.getLatitude(), location.getLongitude()));
            }
            Polyline polyline1 = googleMap.addPolyline(options);
            stylePolyline(polyline1);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(locationList.get(0).getLatitude(), locationList.get(0).getLongitude()), 10));

        });

        // Position the map's camera near Alice Springs in the center of Australia,
        // and set the zoom factor so most of Australia shows on the screen.

        // Set listeners for click events.
        googleMap.setOnPolylineClickListener(this);
    }

    /**
     * Styles the polyline, based on type.
     * @param polyline The polyline object that needs styling.
     */
    private void stylePolyline(Polyline polyline) {
        polyline.setEndCap(new RoundCap());
        polyline.setWidth(POLYLINE_STROKE_WIDTH_PX);
        polyline.setColor(COLOR_BLACK_ARGB);
        polyline.setJointType(JointType.ROUND);
    }

    /**
     * Listens for clicks on a polyline.
     * @param polyline The polyline object that the user has clicked.
     */
    @Override
    public void onPolylineClick(Polyline polyline) {

        Toast.makeText(this, "Route type " + polyline.getTag().toString(),
                Toast.LENGTH_SHORT).show();
    }
}