package com.hawk.plugin.devicetemperature;

import android.util.Log;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "CapacitorTemperature")
public class CapacitorTemperaturePlugin extends Plugin {

    private CapacitorTemperature implementation;

    @Override
    public void load() {
        super.load();
        implementation = new CapacitorTemperature(getContext());
//        implementation.startListening();
        CapacitorTemperature.TemperatureStatusChangeListener listener = temperature -> {
            notifyTemperatureEvent(temperature);
        };
        implementation.setStatusChangeListener(listener);
    }

    private void notifyTemperatureEvent(float temperature) {
        JSObject jsObject = new JSObject();
        jsObject.put("temperature", temperature);
        notifyListeners("temperatureUpdate", jsObject);
    }

    @Override
    protected void handleOnDestroy() {
        implementation.stopListening();
    }

    @Override
    protected void handleOnResume() {
        implementation.startListening();
    }

    @Override
    protected void handleOnPause() {
        implementation.stopListening();
    }

    @PluginMethod
    public void getTemperature(PluginCall call) {
        float temperature = CapacitorTemperature.getCurrentTemperature();
        JSObject result = new JSObject();
        result.put("temperature", temperature);
        call.resolve(result);
    }
}
