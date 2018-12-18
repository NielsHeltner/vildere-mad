package food.wilder.gui.map.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import food.wilder.common.AsyncCallback;

public class BatteryReceiver extends BroadcastReceiver {

    private AsyncCallback callback;

    public BatteryReceiver(AsyncCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        this.callback.callback(intent.getAction());
    }
}