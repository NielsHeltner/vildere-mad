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
    private TextView loggedInAsTxt;
    @BindView(R.id.profileBtn)
    private Button profileBtn;
    @BindView(R.id.leaderboardBtn)
    private Button leaderboardBtn;
    @BindView(R.id.mapBtn)
    private Button mapBtn;

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
