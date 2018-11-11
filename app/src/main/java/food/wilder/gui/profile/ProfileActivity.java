package food.wilder.gui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import food.wilder.R;
import food.wilder.common.Callback;
import food.wilder.common.DaggerStorageComponent;
import food.wilder.common.IStorage;
import food.wilder.common.StorageComponent;
import food.wilder.domain.UserData;

public class ProfileActivity extends AppCompatActivity implements Callback<List<UserData>> {

    @BindView(R.id.profile_text_name)
    TextView username;
    @BindView(R.id.profile_text_level)
    TextView userLevel;
    @BindView(R.id.profile_image_view)
    ImageView userAvatar;

    private Intent userIntent;
    private IStorage<UserData> userStorage;
    private UserData userData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);

        StorageComponent component = DaggerStorageComponent.create();
        userStorage = component.provideUserStorage();
        this.userIntent = getIntent();

        getUserData();
    }

    private void getUserData() {
        String query = "?username=" + userIntent.getStringExtra("username");
        userStorage.get(this, query, this);
    }

    @Override
    public void callback(List<UserData> userDataList) {
        if (userDataList.size() == 1) {
            userData = userDataList.get(0);
            insertUserData();
        } else {
            Log.d("PROFILE_ERROR", "Get did not return only 1 user. Size: " + userDataList.size());
        }
    }

    private void insertUserData(){
        findViewById(R.id.loadingLayout).setVisibility(View.INVISIBLE);

        username.setText(userData.getName());
        userLevel.setText("Level: " + userData.getLevel());
        getUserAvatar(userData.getLevel());
    }

    private void getUserAvatar(int level) {
        String url = getResources().getString(R.string.avatar_bucket_link) + level + ".jpg";
        Glide.with(this).load(url).into(userAvatar);
    }
}
