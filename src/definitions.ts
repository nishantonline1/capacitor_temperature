export interface CapacitorTemperaturePlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
