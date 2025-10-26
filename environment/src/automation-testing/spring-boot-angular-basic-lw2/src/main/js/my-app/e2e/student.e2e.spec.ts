import { test, expect } from '@playwright/test';

test.describe('Student App E2E', () => {

  test.beforeEach(async ({ page }) => {
    await page.goto('/');
  });

  test('adds a new student and verifies in the list', async ({ page }) => {
    // 1) Auf Add Students Link klicken
    await page.locator('a:has-text("Add Students")').click();

    // 2) Formular ausfüllen
    await page.fill('input#name', 'Automated Lisa');
    await page.fill('input#email', 'auto.lisa@tbz.ch');

    // 3) Submit klicken und auf Navigation warten
    await Promise.all([
      page.waitForURL('**/students'), // wartet bis Angular navigiert
      page.locator('button:has-text("Submit")').click()
    ]);

    // 4) Tabelle prüfen
    // Hinzufügen von Debugging-Informationen
    await page.waitForSelector('table tbody', { timeout: 5000 });
    const tableBodyHtml = await page.locator('table tbody').innerHTML();

        const studentRow = page.locator('table tbody tr', { hasText: 'Automated Lisa' });
        await expect(studentRow).toBeVisible({ timeout: 5000 });
      });
    });
