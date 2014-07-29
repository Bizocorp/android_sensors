package hr.ahuskano.sensors.app.fragments;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import hr.ahuskano.sensors.app.R;



/**
 * Created by ahuskano on 7/27/2014.
 */
public class FragmentLocation extends Fragment implements LocationListener, View.OnClickListener {

    private LocationManager locationManager;
    private Button btLocation;
    private TextView tvLatitude;
    private TextView tvLongitude;
    private TextView tvAccuracy;
    private TextView tvProvider;
    private TextView tvEnabledProviders;


    private void init(View view) {
        btLocation = (Button) view.findViewById(R.id.btLocation);
        btLocation.setOnClickListener(this);
        tvLatitude = (TextView) view.findViewById(R.id.tvLatitude);
        tvLongitude = (TextView) view.findViewById(R.id.tvLongitude);
        tvAccuracy = (TextView) view.findViewById(R.id.tvAccuracy);
        tvProvider = (TextView) view.findViewById(R.id.tvProvider);
        tvEnabledProviders = (TextView) view.findViewById(R.id.tvProvidersEnabled);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        return inflater.inflate(R.layout.fragment_location, null);
    }


    private void settingManager() {
        StringBuffer stringBuffer = new StringBuffer();
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);

        List<String> providers = locationManager.getProviders(criteria, true);
        if (providers.isEmpty()) {
            tvEnabledProviders.setText("");
            Log.d("location_debug", "Providers is empty");
        } else {
            for (String provider : providers) {
                Log.d("location_debug", "Provider: " + provider);

                stringBuffer.append(provider).append(" ");
                locationManager.requestSingleUpdate(provider, this, null);

            }
            tvEnabledProviders.setText(stringBuffer);
        }
        tvLatitude.setText("");
        tvLongitude.setText("");
        tvAccuracy.setText("");
        tvProvider.setText("");

    }

    @Override
    public void onResume() {
        super.onResume();
        settingManager();
        Log.d("location_debug", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
        Log.d("location_debug", "onPause");
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d("location_debug", "onLocationChanged");
        setData(location);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        Log.d("location_debug", "onStatusChanged");

    }

    @Override
    public void onProviderEnabled(String s) {
        Log.d("location_debug", "onProviderEnabled");

    }

    @Override
    public void onProviderDisabled(String s) {
        Log.d("location_debug", "onProviderDisabled");

    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btLocation:
                Log.d("location_debug","Button pressed");
                getLastKnowLocation();
                break;
        }
    }

    private void getLastKnowLocation(){
        Location location=locationManager.getLastKnownLocation("gps");
        setData(location);
    }

    private void setData(Location location){
        tvLongitude.setText(String.valueOf(location.getLongitude()));
        tvLatitude.setText(String.valueOf(location.getLatitude()));
        tvAccuracy.setText(String.valueOf(location.getAccuracy()));
        tvProvider.setText(String.valueOf(location.getProvider()));
    }
}
