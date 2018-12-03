package food.wilder.gui.map;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.ActivityTransitionEvent;
import com.google.android.gms.location.ActivityTransitionResult;

public class TransitionReceiver extends BroadcastReceiver {


    public TransitionReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("TRANSITION", "On Handle Intent");
        if (ActivityTransitionResult.hasResult(intent)) {
            ActivityTransitionResult result = ActivityTransitionResult.extractResult(intent);
            ActivityTransitionEvent event = result.getTransitionEvents().get(result.getTransitionEvents().size()-1);
                Log.d("TRANSITION", event.toString());
                String transition = "";
                String activity = "";

                if (event.getTransitionType() == 0) transition = "Enter";
                if (event.getTransitionType() == 1) transition = "Exit";
                if (event.getActivityType() == 3) activity = "Still";
                if (event.getActivityType() == 7) activity = "Walking";

                Toast.makeText(context, transition + " " + activity, Toast.LENGTH_LONG).show();
        }
    }
}
