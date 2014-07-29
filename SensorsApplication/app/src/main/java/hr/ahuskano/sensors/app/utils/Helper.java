package hr.ahuskano.sensors.app.utils;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;

import java.util.List;

/**
 * Created by ahuskano on 7/29/2014.
 */
public class Helper {

    public static boolean checkSensor(int sensor, Context context) {
        SensorManager sensorManger = (SensorManager) context.getApplicationContext().getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> lista = sensorManger.getSensorList(sensor);
        return lista.size() > 0;

    }
}
