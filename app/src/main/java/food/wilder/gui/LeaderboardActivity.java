package food.wilder.gui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import food.wilder.R;
import food.wilder.domain.UserData;

public class LeaderboardActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        ButterKnife.bind(this);


        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

//        mAdapter = new LeaderboardRecyclerViewAdapter(testData());
//        mRecyclerView.setAdapter(mAdapter);

        populateRecyclerView();
    }

    private void populateRecyclerView() {
        String url = getResources().getString(R.string.get_users_end_point);
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                       //createUserDataList(response);
                       mAdapter = new LeaderboardRecyclerViewAdapter(createUserDataList(response));
                       mRecyclerView.setAdapter(mAdapter);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("GET_REQUEST", error.getMessage());
                    }
                });
        queue.add(jsonArrayRequest);
    }

    private List<UserData> createUserDataList(JSONArray jsonDataArray) {
        List<UserData> usersList = new ArrayList<>();

        for(int i = 0; i < jsonDataArray.length(); i++) {
            try {
                JSONObject jsonData = jsonDataArray.getJSONObject(i);

                String name = jsonData.getString("username");
                int level = jsonData.getInt("level");

                usersList.add(new UserData().setName(name).setLevel(level));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return usersList;
    }

    private List<UserData> testData() {
        List<UserData> d = new ArrayList<>();

        d.add(new UserData().setName("Niclas").setLevel(2));
        d.add(new UserData().setName("Niels").setLevel(3));
        d.add(new UserData().setName("Jakub").setLevel(10));

        d.add(new UserData().setName("Niclas").setLevel(20));
        d.add(new UserData().setName("Niels").setLevel(30));
        d.add(new UserData().setName("Jakub").setLevel(100));

        d.add(new UserData().setName("Niclas").setLevel(200));
        d.add(new UserData().setName("Niels").setLevel(300));
        d.add(new UserData().setName("Jakub").setLevel(1000));

        d.add(new UserData().setName("Niclas").setLevel(2000));
        d.add(new UserData().setName("Niels").setLevel(3000));
        d.add(new UserData().setName("Jakub").setLevel(10000));

        d.add(new UserData().setName("Niclas").setLevel(20000));
        d.add(new UserData().setName("Niels").setLevel(30000));
        d.add(new UserData().setName("Jakub").setLevel(100000));

        d.add(new UserData().setName("Niclas").setLevel(200000));
        d.add(new UserData().setName("Niels").setLevel(300000));
        d.add(new UserData().setName("Jakub").setLevel(1000000));

        d.add(new UserData().setName("Niclas").setLevel(200000000));
        d.add(new UserData().setName("Niels").setLevel(300000000));
        d.add(new UserData().setName("Jakub").setLevel(10000000));
        d.add(new UserData().setName("Niclas").setLevel(2));
        d.add(new UserData().setName("Niels").setLevel(3));
        d.add(new UserData().setName("Jakub").setLevel(10));

        d.add(new UserData().setName("Niclas").setLevel(20));
        d.add(new UserData().setName("Niels").setLevel(30));
        d.add(new UserData().setName("Jakub").setLevel(100));

        d.add(new UserData().setName("Niclas").setLevel(200));
        d.add(new UserData().setName("Niels").setLevel(300));
        d.add(new UserData().setName("Jakub").setLevel(1000));

        d.add(new UserData().setName("Niclas").setLevel(2000));
        d.add(new UserData().setName("Niels").setLevel(3000));
        d.add(new UserData().setName("Jakub").setLevel(10000));

        d.add(new UserData().setName("Niclas").setLevel(20000));
        d.add(new UserData().setName("Niels").setLevel(30000));
        d.add(new UserData().setName("Jakub").setLevel(100000));

        d.add(new UserData().setName("Niclas").setLevel(200000));
        d.add(new UserData().setName("Niels").setLevel(300000));
        d.add(new UserData().setName("Jakub").setLevel(1000000));

        d.add(new UserData().setName("Niclas").setLevel(200000000));
        d.add(new UserData().setName("Niels").setLevel(300000000));
        d.add(new UserData().setName("Jakub").setLevel(10000000));
        d.add(new UserData().setName("Niclas").setLevel(2));
        d.add(new UserData().setName("Niels").setLevel(3));
        d.add(new UserData().setName("Jakub").setLevel(10));

        d.add(new UserData().setName("Niclas").setLevel(20));
        d.add(new UserData().setName("Niels").setLevel(30));
        d.add(new UserData().setName("Jakub").setLevel(100));

        d.add(new UserData().setName("Niclas").setLevel(200));
        d.add(new UserData().setName("Niels").setLevel(300));
        d.add(new UserData().setName("Jakub").setLevel(1000));

        d.add(new UserData().setName("Niclas").setLevel(2000));
        d.add(new UserData().setName("Niels").setLevel(3000));
        d.add(new UserData().setName("Jakub").setLevel(10000));

        d.add(new UserData().setName("Niclas").setLevel(20000));
        d.add(new UserData().setName("Niels").setLevel(30000));
        d.add(new UserData().setName("Jakub").setLevel(100000));

        d.add(new UserData().setName("Niclas").setLevel(200000));
        d.add(new UserData().setName("Niels").setLevel(300000));
        d.add(new UserData().setName("Jakub").setLevel(1000000));

        d.add(new UserData().setName("Niclas").setLevel(200000000));
        d.add(new UserData().setName("Niels").setLevel(300000000));
        d.add(new UserData().setName("Jakub").setLevel(10000000));
        d.add(new UserData().setName("Niclas").setLevel(2));
        d.add(new UserData().setName("Niels").setLevel(3));
        d.add(new UserData().setName("Jakub").setLevel(10));

        d.add(new UserData().setName("Niclas").setLevel(20));
        d.add(new UserData().setName("Niels").setLevel(30));
        d.add(new UserData().setName("Jakub").setLevel(100));

        d.add(new UserData().setName("Niclas").setLevel(200));
        d.add(new UserData().setName("Niels").setLevel(300));
        d.add(new UserData().setName("Jakub").setLevel(1000));

        d.add(new UserData().setName("Niclas").setLevel(2000));
        d.add(new UserData().setName("Niels").setLevel(3000));
        d.add(new UserData().setName("Jakub").setLevel(10000));

        d.add(new UserData().setName("Niclas").setLevel(20000));
        d.add(new UserData().setName("Niels").setLevel(30000));
        d.add(new UserData().setName("Jakub").setLevel(100000));

        d.add(new UserData().setName("Niclas").setLevel(200000));
        d.add(new UserData().setName("Niels").setLevel(300000));
        d.add(new UserData().setName("Jakub").setLevel(1000000));

        d.add(new UserData().setName("Niclas").setLevel(200000000));
        d.add(new UserData().setName("Niels").setLevel(300000000));
        d.add(new UserData().setName("Jakub").setLevel(10000000));

        return d;
    }

}
