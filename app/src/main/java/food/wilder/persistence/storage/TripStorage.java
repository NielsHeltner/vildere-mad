package food.wilder.persistence.storage;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import food.wilder.R;
import food.wilder.common.AsyncPersistenceCallback;
import food.wilder.common.IStorage;
import food.wilder.common.ITripData;
import food.wilder.persistence.model.TripData;

public class TripStorage extends AbstractBufferedStorage<ITripData> {

    private static IStorage<ITripData> instance;

    private TripStorage() {
    }

    public static IStorage<ITripData> getInstance() {
        if (instance == null) {
            instance = new TripStorage();
        }
        return instance;
    }

    @Override
    public void upload(Context context, String username, AsyncPersistenceCallback callback) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = context.getResources().getString(R.string.add_trip_end_point);

        Long tsLong = System.currentTimeMillis()/1000;
        String ts = tsLong.toString();

        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("timestamp", ts);

        JsonObjectRequest jsonobj = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params),
                response -> {
                    try {
                        Log.d("FUCKING", response.getString("id"));
                        callback.callback(response.getString("id"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    Log.d("FUCKING", error.getMessage());
                }
        );
        queue.add(jsonobj);
    }

    @Override
    public void get(Context context, String query, AsyncPersistenceCallback callback) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = context.getResources().getString(R.string.get_trips_point);
        if (query != null) {
            url += query;
        }

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, response -> {

                    callback.callback(createTripsList(response));

                }, error -> Log.d("TRIP_GET_REQUEST", error.getMessage()));
        queue.add(jsonArrayRequest);

    }

    private List<ITripData> createTripsList(JSONArray jsonDataArray) {
        List<ITripData> tripsList = new ArrayList<>();

        for (int i = 0; i < jsonDataArray.length(); i++) {
            try {
                JSONObject jsonData = jsonDataArray.getJSONObject(i);

                tripsList.add(new TripData(jsonData.getString("id"), jsonData.getInt("timestamp")));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        tripsList.sort(Comparator.comparingLong(ITripData::getStartTime).reversed());
        return tripsList;
    }
}
