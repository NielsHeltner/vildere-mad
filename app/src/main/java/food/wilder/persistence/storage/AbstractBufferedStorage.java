package food.wilder.persistence.storage;

import android.content.Context;
import android.telecom.Call;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import food.wilder.R;
import food.wilder.common.IStorage;
import food.wilder.common.AsyncPersistenceCallback;

public abstract class AbstractBufferedStorage<T> implements IStorage<T> {

    protected List<T> data;

    @Override
    public void add(T t) {
        data.add(t);
    }

    @Override
    public void upload(Context context, String username, AsyncPersistenceCallback callback) {}

    @Override
    public void upload(Context context, String id) {}


    @Override
    public void get(Context context, String url, AsyncPersistenceCallback callback) {
    }

}
