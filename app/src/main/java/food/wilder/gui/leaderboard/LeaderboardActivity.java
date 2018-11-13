package food.wilder.gui.leaderboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.ButterKnife;
import food.wilder.R;
import food.wilder.common.DaggerStorageComponent;
import food.wilder.common.IStorage;
import food.wilder.common.Callback;
import food.wilder.common.StorageComponent;
import food.wilder.domain.UserData;

public class LeaderboardActivity extends AppCompatActivity implements Callback<List<UserData>> {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private IStorage<UserData> userStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        ButterKnife.bind(this);
        StorageComponent component = DaggerStorageComponent.create();
        userStorage = component.provideUserStorage();

        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        populateRecyclerView();
    }

    private void populateRecyclerView() {
        userStorage.get(this, null, this);
    }

    @Override
    public void callback(List<UserData> list) {
        findViewById(R.id.loadingLayout).setVisibility(View.INVISIBLE);
        mAdapter = new LeaderboardRecyclerViewAdapter(list);
        mRecyclerView.setAdapter(mAdapter);
    }
}
