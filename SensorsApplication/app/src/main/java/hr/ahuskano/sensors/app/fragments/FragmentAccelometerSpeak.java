package hr.ahuskano.sensors.app.fragments;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Locale;

import hr.ahuskano.sensors.app.R;

/**
 * Created by ahuskano on 7/29/2014.
 */
public class FragmentAccelometerSpeak extends Fragment implements SensorEventListener, TextToSpeech.OnInitListener {

    private String TAG = "FragmentTest";

    private SensorManager sensorManager;
    private Sensor sensor;

    private long counter = 0;

    private TextToSpeech textToSpeech;
    private int SPEAK_FLAG = 1;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        init();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_accelometer_speak, null);
    }

    private void init() {
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        textToSpeech = new TextToSpeech(getActivity().getBaseContext(), this);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        switch (sensorEvent.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                speakBaseOnAsscelometer(sensorEvent);
                break;
        }
    }

    private void speakBaseOnAsscelometer(SensorEvent sensorEvent) {
        float[] values = sensorEvent.values.clone();
        if (counter == 0) {
            if (values[2] > 6 && values[2] < 12) {
                if (values[0] < -2) {
                    textToSpeech.speak(getString(R.string.right),
                            TextToSpeech.QUEUE_FLUSH,
                            null);
                } else if (values[0] > 2) {
                    textToSpeech.speak(getString(R.string.left),
                            TextToSpeech.QUEUE_FLUSH,
                            null);
                } else if (values[1] > 2) {
                    textToSpeech.speak(getString(R.string.up),
                            TextToSpeech.QUEUE_FLUSH,
                            null);
                } else if (values[1] < -2) {
                    textToSpeech.speak(getString(R.string.down),
                            TextToSpeech.QUEUE_FLUSH,
                            null);
                }
            } else {
                if (values[2] < 0) {
                    textToSpeech.speak(getString(R.string.faceDown),
                            TextToSpeech.QUEUE_FLUSH,
                            null);
                }

            }
            counter = 1;

        } else {
            counter--;
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        Log.d(TAG, "onAccuracyChanged");

    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
        textToSpeech.shutdown();
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            Log.d(TAG, "Initilization SUCCESS!");
            int result = textToSpeech.setLanguage(Locale.ENGLISH);
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e(TAG, "This Language is not supported");
                SPEAK_FLAG = 0;
            } else {
                SPEAK_FLAG = 1;
            }
        } else {
            Log.e(TAG, "Initilization Failed!");
            SPEAK_FLAG = 0;
        }
    }
}
