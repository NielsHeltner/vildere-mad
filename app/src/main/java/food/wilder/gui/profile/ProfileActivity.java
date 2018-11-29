package food.wilder.gui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import food.wilder.R;
import food.wilder.common.AsyncPersistenceCallback;
import food.wilder.common.IStorage;
import food.wilder.common.ITripData;
import food.wilder.common.IUserData;
import food.wilder.common.dependency_injection.DaggerStorageComponent;
import food.wilder.common.dependency_injection.StorageComponent;

public class ProfileActivity extends AppCompatActivity {

    @BindView(R.id.profile_text_name)
    TextView username;
    @BindView(R.id.profile_text_level)
    TextView userLevel;
    @BindView(R.id.profile_image_view)
    ImageView userAvatar;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.textForages)
    TextView textForages;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Intent userIntent;
    private IStorage<IUserData> userStorage;
    private IStorage<ITripData> tripStorage;
    private IUserData userData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        StorageComponent component = DaggerStorageComponent.create();
        userStorage = component.provideUserStorage();
        tripStorage = component.provideTripStorage();
        this.userIntent = getIntent();

        getUserData();
        setUpRecyclerView();
    }

    private void getUserData() {
        String query = "?username=" + userIntent.getStringExtra("username");
        userStorage.get(this, query, (AsyncPersistenceCallback<List<IUserData>>) userDataList -> {

            if (userDataList.size() == 1) {
                userData = userDataList.get(0);
                insertUserData();
            } else {
                Log.d("PROFILE_ERROR", "Get did not return only 1 user. Size: " + userDataList.size());
            }
        });
    }

    private void insertUserData() {
        findViewById(R.id.loadingLayout).setVisibility(View.INVISIBLE);

        username.setText(userData.getName());
        userLevel.setText("Level: " + userData.getLevel());
        getUserAvatar(userData.getLevel());
    }

    private void getUserAvatar(int level) {
        String url = getResources().getString(R.string.avatar_bucket_link) + level + ".jpg";
        Glide.with(this).load(url).into(userAvatar);
    }

    private void setUpRecyclerView() {
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        populateRecyclerView();
    }

    private void populateRecyclerView() {
        String query = "?username=" + userIntent.getStringExtra("username");
        tripStorage.get(this, query, (AsyncPersistenceCallback<List<ITripData>>) tripDataList -> {
            mRecyclerView.setVisibility(View.VISIBLE);
            textForages.setVisibility(View.VISIBLE);
            mAdapter = new ProfileForagesRecyclerViewAdapter(tripDataList);
            mRecyclerView.setAdapter(mAdapter);
        });
    }

}
