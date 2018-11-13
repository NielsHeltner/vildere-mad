package food.wilder.persistence;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
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
import food.wilder.common.Callback;
import food.wilder.common.IStorage;
import food.wilder.domain.TripData;

public class TripStorage extends AbstractBufferedStorage<TripData> {
    private static IStorage<TripData> instance;

    private TripStorage() {
    }

    public static IStorage<TripData> getInstance() {
        if(instance == null) {
            instance = new TripStorage();
        }
        return instance;
    }

    @Override
    public void upload(Context context, String username, Callback callback) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = context.getResources().getString(R.string.add_trip_end_point);

        StringRequest postRequest = new StringRequest
                (Request.Method.POST, url, response -> {

                    //callback.callback(response);
                    Log.d("POSTREQUEST", response);
                }, error -> Log.d("ADD_TRIP", error.getMessage())) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("timestamp", "10");

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }

            @Override
            public String getBodyContentType(){
                return "application/x-www-form-urlencoded";
            }
        };
        queue.add(postRequest);
    }

    @Override
    public void get(Context context, String query, Callback callback) {
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

    private List<TripData> createTripsList(JSONArray jsonDataArray) {
        List<TripData> tripsList = new ArrayList<>();

        for(int i = 0; i < jsonDataArray.length(); i++) {
            try {
                JSONObject jsonData = jsonDataArray.getJSONObject(i);

                tripsList.add(new TripData(jsonData.getString("id_trip"), jsonData.getInt("timestamp")));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        tripsList.sort(Comparator.comparing(TripData::getTimestamp).reversed());
        return tripsList;
    }
}
