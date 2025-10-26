import { defineConfig, devices } from '@playwright/test';

export default defineConfig({
  testDir: './e2e',            // Ordner mit Tests
  timeout: 30_000,
  expect: { timeout: 5000 },
  fullyParallel: true,
  retries: 0,
  reporter: [['list'], ['html', { outputFolder: 'playwright-report' }]],
  use: {
    baseURL: 'http://localhost:4200', // passe an falls anders
    headless: true,
    trace: 'on-first-retry'
  },
  projects: [
    { name: 'chromium', use: { ...devices['Desktop Chrome'] } }
  ]
});
