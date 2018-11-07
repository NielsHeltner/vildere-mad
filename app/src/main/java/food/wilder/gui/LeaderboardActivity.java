package food.wilder.gui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

        mAdapter = new LeaderboardRecyclerViewAdapter(testData());
        mRecyclerView.setAdapter(mAdapter);
    }

    private List<UserData> getUsers() {
        List<UserData> usersList = new ArrayList<>();

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
