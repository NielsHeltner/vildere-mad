package food.wilder.gui.map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import food.wilder.R;

public class ForageActivity extends AppCompatActivity {

    @BindView(R.id.plantName)
    public EditText forageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forage);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.forageSubmitButton)
    public void createForageResult() {
        Log.d("FORAGE ACTIVITY CREATE FORAGE RESULT", "Submit called");
        Intent returnIntent = new Intent();
        returnIntent.putExtra("plantName", forageName.getText().toString());
        setResult(Activity.RESULT_OK,returnIntent);
        finish();

    }
}
