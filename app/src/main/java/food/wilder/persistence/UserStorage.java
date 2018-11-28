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
import food.wilder.common.IStorage;
import food.wilder.common.AsyncPersistenceCallback;
import food.wilder.common.IUserData;
import food.wilder.domain.UserData;

public class UserStorage extends AbstractBufferedStorage<IUserData> {

    private static IStorage<IUserData> instance;

    private UserStorage() {
        data = new ArrayList();
    }

    public static IStorage<IUserData> getInstance() {
        if (instance == null) {
            instance = new UserStorage();
        }
        return instance;
    }

    @Override
    public void upload() {

    }

    @Override
    public void get(Context context, String query, AsyncPersistenceCallback callback) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = context.getResources().getString(R.string.get_users_end_point);
        if (query != null) {
            url += query;
        }

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, response -> {

                    callback.callback(createUserDataList(response));

                }, error -> Log.d("USER_GET_REQUEST", error.getMessage()));
        queue.add(jsonArrayRequest);
    }

    private List<IUserData> createUserDataList(JSONArray jsonDataArray) {
        List<IUserData> usersList = new ArrayList<>();

        for (int i = 0; i < jsonDataArray.length(); i++) {
            try {
                JSONObject jsonData = jsonDataArray.getJSONObject(i);

                usersList.add(new UserData(jsonData.getString("username"), jsonData.getInt("level")));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        usersList.sort(Comparator.comparingInt(IUserData::getLevel).reversed());
        return usersList;
    }

}
