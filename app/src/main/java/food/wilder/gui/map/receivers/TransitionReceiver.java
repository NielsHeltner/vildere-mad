package food.wilder.gui.map.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.location.ActivityTransitionEvent;
import com.google.android.gms.location.ActivityTransitionResult;

import food.wilder.R;

public class TransitionReceiver extends BroadcastReceiver {


    public TransitionReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("TRANSITION", "On Handle Intent");
        if (ActivityTransitionResult.hasResult(intent)) {
            ActivityTransitionResult result = ActivityTransitionResult.extractResult(intent);
            ActivityTransitionEvent event = result.getTransitionEvents().get(result.getTransitionEvents().size()-1);
                Intent activityIntent = new Intent(context.getString(R.string.activity_changed));

                if (event.getActivityType() == 3 && event.getTransitionType() == 0) {
                    activityIntent.putExtra(context.getString(R.string.activity_value), context.getString(R.string.activity_still));
                    context.sendBroadcast(activityIntent);
                }
                else if (event.getActivityType() == 7 && event.getTransitionType() == 0) {
                    activityIntent.putExtra(context.getString(R.string.activity_value), context.getString(R.string.activity_walking));
                    context.sendBroadcast(activityIntent);
                }
        }
    }
}
