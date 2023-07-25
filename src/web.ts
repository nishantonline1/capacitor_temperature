import { WebPlugin } from '@capacitor/core';

import type { CapacitorTemperaturePlugin } from './definitions';

export class CapacitorTemperatureWeb
  extends WebPlugin
  implements CapacitorTemperaturePlugin
{
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
