package devmagi.simple.hms.kitfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.huawei.hms.maps.HuaweiMap;
import com.huawei.hms.maps.MapView;
import com.huawei.hms.maps.MapsInitializer;
import com.huawei.hms.maps.OnMapReadyCallback;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    // Huawei map.
    private static final String TAG = "MAPKIT";
    private HuaweiMap hMap;
    private MapView mMapView;
    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtain a MapView instance.
        mMapView = findViewById(R.id.mapView);
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }
        MapsInitializer.setApiKey("DAEDAJTvc6Zq5QO9BNFe+7WbST+4ai45Ja1CAFEAD48vMPoS6hLxyAAKogO9952zJNL1I3s00jpRXJNiwASgIhUxOss66NKX1qLeuQ==");
        mMapView.onCreate(mapViewBundle);
        mMapView.getMapAsync(this);

        // Check Location permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Log.i(TAG, "sdk >= 23 M");
            // Check whether your app has the specified permission and whether the app operation corresponding to the permission is allowed.
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // Request permissions for your app.
                String[] strings =
                        {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
                // Request permissions.
                ActivityCompat.requestPermissions(this, strings, 1);
            }
        }
    }


    // Map
    @Override
    public void onMapReady(HuaweiMap huaweiMap) {
        // Obtain a map instance from callback.
        Log.d(TAG, "onMapReady: ");
        hMap = huaweiMap;

        hMap.setMyLocationEnabled(true
        );
        // Enable the my-location icon.
        hMap.getUiSettings().setMyLocationButtonEnabled(true
        );
    }

    @Override
    protected void onStart() {
        super.onStart();
        mMapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mMapView.onStop();
    }

    @Override
    protected void onDestroy() {
        // Removed when the location update is no longer required.
        //removeLocationUpdatesWithCallback();
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

}