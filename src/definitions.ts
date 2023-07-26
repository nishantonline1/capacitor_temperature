import type { PluginListenerHandle } from '@capacitor/core';

export interface CapacitorTemperaturePlugin {
  getTemperature(): Promise<{ temperature: number }>;
  addListener(eventName: 'temperatureUpdate', listenerFunc: (data: { temperature: number }) => void): PluginListenerHandle;
}
