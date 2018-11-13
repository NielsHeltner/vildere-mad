package food.wilder.persistence;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import food.wilder.R;
import food.wilder.common.AsyncPersistenceCallback;
import food.wilder.common.IStorage;
import food.wilder.common.ITripData;
import food.wilder.domain.TripData;

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
    public void upload() {

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

                tripsList.add(new TripData(jsonData.getString("id_trip"), jsonData.getInt("timestamp")));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        tripsList.sort(Comparator.comparingLong(ITripData::getStartTime).reversed());
        return tripsList;
    }

}
