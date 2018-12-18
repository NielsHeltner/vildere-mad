package food.wilder.persistence.storage;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import food.wilder.R;
import food.wilder.common.IForageData;
import food.wilder.common.IStorage;

public class ForageStorage extends AbstractBufferedStorage<IForageData> {

    private static IStorage<IForageData> instance;

    private ForageStorage() {
        data = new ArrayList();
    }

    public static IStorage<IForageData> getInstance() {
        if (instance == null) {
            instance = new ForageStorage();
        }
        return instance;
    }

    @Override
    public void upload(Context context, String tripId) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = context.getResources().getString(R.string.add_forage_end_point);

        JSONObject params = new JSONObject();
        try {
            params.put("trip_id", tripId);
            params.put("species", data.get(0).getPlantType());
            params.put("timestamp", data.get(0).getLocation().getTime());
            params.put("lon", data.get(0).getLocation().getLongitude());
            params.put("lat", data.get(0).getLocation().getLatitude());
            params.put("username", "Niclas");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonobj = new JsonObjectRequest(Request.Method.POST, url, params,
                response -> {
                    Log.d("FUCKING FORAGE", response.toString());
                },
                error -> {
                    Log.d("FUCKING", error.getMessage());
                }
        );
        queue.add(jsonobj);
        data.clear();
    }

}
