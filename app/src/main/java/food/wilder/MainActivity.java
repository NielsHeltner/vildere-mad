package food.wilder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @OnClick(R.id.leaderboardBtn)
    public void leaderboard() {
        Intent leaderboardIntent = new Intent(this, LeaderboardActivity.class);
        startActivity(leaderboardIntent);
    }

}
