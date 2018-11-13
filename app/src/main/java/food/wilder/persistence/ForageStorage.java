package food.wilder.persistence;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    }

}
