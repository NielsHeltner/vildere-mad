package food.wilder.persistence.storage;

import android.content.Context;
import android.location.Location;
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
import java.util.List;

import food.wilder.R;
import food.wilder.common.AsyncPersistenceCallback;
import food.wilder.common.IStorage;

public class GpsStorage extends AbstractBufferedStorage<Location> {

    private static IStorage<Location> instance;
    private List<Location> tempDataList;

    private GpsStorage() {
        data = new ArrayList();
    }

    public static IStorage<Location> getInstance() {
        if (instance == null) {
            instance = new GpsStorage();
        }
        return instance;
    }

    @Override
    public void upload(Context context, String id) {
        // Upload gps points to database
        moveDataToTempAndClear();

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = context.getResources().getString(R.string.add_gps_end_point);

        JSONObject params = new JSONObject();
        try {
            params.put("trip_id", id);
            params.put("coordinates", createJSONOfGPSPoints());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonobj = new JsonObjectRequest(Request.Method.POST, url, params,
                response -> {
                    Log.d("FUCKING GPS", response.toString());
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
        String url = context.getResources().getString(R.string.get_trip_gps_end_point);
        if (query != null) {
            url += query;
        }

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, response -> {

                    callback.callback(createGPSDataList(response));

                }, error -> Log.d("GPS_GET_REQUEST", error.getMessage()));
        queue.add(jsonArrayRequest);
    }

    private List<Location> createGPSDataList(JSONArray jsonDataArray) {
        List<Location> gpsData = new ArrayList<>();

        for (int i = 0; i < jsonDataArray.length(); i++) {
            try {
                JSONObject jsonData = jsonDataArray.getJSONObject(i);
                Location location = new Location("Vildere Mad");
                location.setLatitude(jsonData.getDouble("lat"));
                location.setLongitude(jsonData.getDouble("lon"));
                gpsData.add(location);
                //gpsData.add(new UserData(jsonData.getString("username"), jsonData.getInt("level")));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //gpsData.sort(Comparator.comparingInt(IUserData::getLevel).reversed());
        return gpsData;
    }

    private void moveDataToTempAndClear() {
        tempDataList = new ArrayList(data);
        data.clear();
    }

    private JSONArray createJSONOfGPSPoints() {
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < tempDataList.size(); i++) {
            JSONObject obj = new JSONObject();
            try {
                obj.put("lat", tempDataList.get(i).getLatitude());
                obj.put("lon", tempDataList.get(i).getLongitude());
                obj.put("timestamp", tempDataList.get(i).getTime());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jsonArray.put(obj);

        }
        Log.d("FUCKING CREATE POINTS", jsonArray.length()+"");
        return jsonArray;
    }

}
