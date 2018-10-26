package food.wilder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.loggedInAsTxt)
    private TextView loggedInAsTxt;
    @BindView(R.id.profileBtn)
    private Button profileBtn;
    @BindView(R.id.leaderboardsBtn)
    private Button leaderboardsBtn;
    @BindView(R.id.mapBtn)
    private Button mapBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
    
}
