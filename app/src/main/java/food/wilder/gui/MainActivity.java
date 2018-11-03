package food.wilder.gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import food.wilder.R;
import food.wilder.gui.LeaderboardActivity;
import food.wilder.gui.MapActivity;
import food.wilder.gui.ProfileActivity;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.loggedInAsTxt)
    TextView loggedInAsTxt;
    @BindView(R.id.profileBtn)
    Button profileBtn;
    @BindView(R.id.leaderboardBtn)
    Button leaderboardBtn;
    @BindView(R.id.mapBtn)
    Button mapBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.profileBtn)
    public void goToProfile() {
        Intent profileIntent = new Intent(this, ProfileActivity.class);
        startActivity(profileIntent);
    }

    @OnClick(R.id.leaderboardBtn)
    public void goToLeaderboard() {
        Intent leaderboardIntent = new Intent(this, LeaderboardActivity.class);
        startActivity(leaderboardIntent);
    }

    @OnClick(R.id.mapBtn)
    public void goToMap() {
        Intent mapIntent = new Intent(this, MapActivity.class);
        startActivity(mapIntent);
    }

}
