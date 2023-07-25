import { registerPlugin } from '@capacitor/core';

import type { CapacitorTemperaturePlugin } from './definitions';

const CapacitorTemperature = registerPlugin<CapacitorTemperaturePlugin>(
  'CapacitorTemperature',
  {
    web: () => import('./web').then(m => new m.CapacitorTemperatureWeb()),
  },
);

export * from './definitions';
export { CapacitorTemperature };
