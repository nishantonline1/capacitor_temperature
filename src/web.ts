import { WebPlugin } from '@capacitor/core';

import type {CapacitorTemperaturePlugin } from './definitions';

export class CapacitorTemperatureWeb extends WebPlugin implements CapacitorTemperaturePlugin {
  constructor() {
    super();
  }

  async getTemperature(): Promise<{ temperature: number }> {
    // Implement the logic to get the temperature on the web platform
    // For web, since there is no ambient temperature sensor, return a placeholder value (e.g., 25.0)
    throw new Error("getTemperature: Method not implemented.");
  }
}

const CapacitorTemperature = new CapacitorTemperatureWeb();
export { CapacitorTemperature };
