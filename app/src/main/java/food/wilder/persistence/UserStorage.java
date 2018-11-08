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

import food.wilder.common.IStorage;
import food.wilder.common.Callback;
import food.wilder.domain.UserData;

public class UserStorage extends AbstractBufferedStorage<UserData> {

    private static IStorage<UserData> instance;

    private UserStorage() {
        data = new ArrayList();
    }

    public static IStorage<UserData> getInstance() {
        if(instance == null) {
            instance = new UserStorage();
        }
        return instance;
    }
    @Override
    public void upload() {

    }

    @Override
    public void get(Context context, String url, Callback callback) {
        RequestQueue queue = Volley.newRequestQueue(context);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, response -> {

                    callback.callback(createUserDataList(response));

                }, error -> Log.d("GET_REQUEST", error.getMessage()));
        queue.add(jsonArrayRequest);
    }

    private List<UserData> createUserDataList(JSONArray jsonDataArray) {
        List<UserData> usersList = new ArrayList<>();

        for(int i = 0; i < jsonDataArray.length(); i++) {
            try {
                JSONObject jsonData = jsonDataArray.getJSONObject(i);

                usersList.add(new UserData(jsonData.getString("username"), jsonData.getInt("level")));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        usersList.sort(Comparator.comparing(UserData::getLevel).reversed());
        return usersList;
    }
}
