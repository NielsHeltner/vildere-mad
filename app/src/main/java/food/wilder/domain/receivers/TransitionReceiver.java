package food.wilder.domain.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

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
                Intent activityIntent = new Intent("ACTIVITY_CHANGED");

                if (event.getActivityType() == 3 && event.getTransitionType() == 0) {
                    activityIntent.putExtra("activity", "still");
                    context.sendBroadcast(activityIntent);
                }
                if (event.getActivityType() == 7 && event.getTransitionType() == 0) {
                    activityIntent.putExtra("activity", "walking");
                    context.sendBroadcast(activityIntent);
                }
        }
    }
}
