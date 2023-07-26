package com.hawk.plugin.devicetemperature;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import androidx.annotation.Nullable;
import com.getcapacitor.JSObject;


public class CapacitorTemperature implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor temperatureSensor;
    private static float currentTemperature = 0.0f;

    /**
     * Interface for callbacks when network status changes.
     */
    interface TemperatureStatusChangeListener {
        void onTemperatureStatusChanged(float temperature);
    }
    @Nullable
    private TemperatureStatusChangeListener statusChangeListener;

    public CapacitorTemperature(Context context) {
        // Get the SensorManager instance
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);

        // Check if the temperature sensor is available
        temperatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // The temperature value is stored in the event.values array
        if (event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE && event.values.length > 0) {
            float new_temperature = event.values[0];
            // Check if the temperature has changed
            if (new_temperature != currentTemperature) {
                currentTemperature = new_temperature;
                // Emit the temperatureUpdate event with the new temperature value
                JSObject eventData = new JSObject();
                eventData.put("temperature", currentTemperature);
                statusChangeListener.onTemperatureStatusChanged(currentTemperature);
            }
        }
    }

    /**
     * Set the object to receive callbacks.
     * @param listener
     */
    public void setStatusChangeListener(@Nullable TemperatureStatusChangeListener listener) {
        this.statusChangeListener = listener;
    }

    /**
     * Return the object that is receiving the callbacks.
     * @return
     */
    @Nullable
    public TemperatureStatusChangeListener getStatusChangeListener() {
        return statusChangeListener;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not used in this example
    }

    public static float getCurrentTemperature() {
        return currentTemperature;
    }

    public void startListening() {
        // Register the SensorEventListener to listen for temperature sensor updates
        // Use SENSOR_DELAY_NORMAL or other suitable delay for your use case
        sensorManager.registerListener(this, temperatureSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void stopListening() {
        // Unregister the SensorEventListener when you no longer need sensor updates
        sensorManager.unregisterListener(this);
    }
}
