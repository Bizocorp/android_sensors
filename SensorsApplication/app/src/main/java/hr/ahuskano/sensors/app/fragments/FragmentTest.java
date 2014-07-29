package hr.ahuskano.sensors.app.fragments;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hr.ahuskano.sensors.app.R;

/**
 * Created by ahuskano on 7/29/2014.
 */
public class FragmentTest extends Fragment {

    private String TAG = "FragmentTest";

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        checkSensors();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test, null);
    }

    private void checkSensors() {
        SensorManager sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        for (Sensor sensor : sensorManager.getSensorList(Sensor.TYPE_ALL)) {
            Log.d(TAG,
                    "SENSOR: " + sensor.getName() +
                            " MIN DELAY: " + sensor.getMinDelay() +
                            " POWER:" + sensor.getPower() +
                            " TYPE: " + sensor.getType() +
                            " VENDOR: " + sensor.getVendor() +
                            " FifoMaxEventCount: " + sensor.getFifoMaxEventCount() +
                            " FifoReservedEventCount: " + sensor.getFifoReservedEventCount() +
                            " MAXIMUM RANGE: " + sensor.getMaximumRange() +
                            " VERSION: " + sensor.getVersion() +
                            " RESOLUTION: " + sensor.getResolution()
            );
        }
    }
}
