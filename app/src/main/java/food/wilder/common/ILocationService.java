package food.wilder.common;

import android.Manifest;
import android.content.Context;

public interface ILocationService {

    int PERMISSION_REQUEST_CODE = 6124;
    String[] PERMISSION_REQUESTS = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

}
