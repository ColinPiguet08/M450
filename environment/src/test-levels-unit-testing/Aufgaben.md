# Aufgabe 1 & 2
# JUnit 5 – Zusammenfassung

JUnit 5 ist das aktuelle Standard-Testframework für Java.
Hauptkomponenten:

* **JUnit Platform** – führt Tests aus (z. B. über IDE oder Maven)
* **JUnit Jupiter** – enthält die neuen Annotations & APIs
* **JUnit Vintage** – ermöglicht alte JUnit 4-Tests weiter zu benutzen

---

## Grundstruktur eines Tests

J Unit Test sieht so aus:
- **Testklasse** – enthält die Tests
- **Testmethoden** – werden mit `@Test` annotiert
- **Assertions** – prüfen das erwartete Verhalten

```java
// imports
class CalculatorTest {

    @Test
    void testAddition() {
        Calculator c = new Calculator();
        assertEquals(5, c.add(2, 3));
    }
}
```

## Ausführen der Tests

### In IntelliJ:
- right click auf Test und dann run Tests. Oder auf grüner Play button -> right click für mehrere Optionen.
---

## Gängigste Features
### Annotations

- @Test – markiert eine Methode als Test

- @BeforeEach – führt Code vor jedem Test aus (z. B. Setup)

- @AfterEach – führt Code nach jedem Test aus (z. B. Cleanup)

- @BeforeAll – wird einmal vor allen Tests ausgeführt (muss static sein)

- @AfterAll – wird einmal nach allen Tests ausgeführt (muss static sein)

- @Disabled – deaktiviert einen Test (z. B. vorübergehend)

### Assertions (Überprüfungen)
- assertEquals(expected, actual) – prüft Gleichheit

- assertNotEquals(a, b) – prüft Ungleichheit

- assertTrue(condition) / assertFalse(condition) – prüft Wahrheitswerte

- assertNull(object) / assertNotNull(object) – prüft Nullwerte

- assertThrows(Exception.class, () -> …) – prüft, ob Exception geworfen wird

## Referenz

*  [Offizielle JUnit 5 Dokumentation](https://junit.org/junit5/docs/current/user-guide/)
*  [Baeldung – JUnit 5 Tutorial](https://www.baeldung.com/junit-5)
*  [JUnit GitHub Repository](https://github.com/junit-team/junit5)

---

# Aufgabe 3 & 4
## Bank
### Struktur: 
Konto -> beinhaltet Daten vom Konto: name, balance etc.
- Account
    - Salary
    - Savings
        - PromoYouth (extends Savings)
     
BankUtils: Formatiert Daten wird in Bookings.java verwendet.

Bank: Kann man sich halt wie eine echte Bank vorstellen, mit allen Konten, und methoden um Konten zu erstellen

Booking: Für transaktionen.


## Code coverage
<img width="2111" height="940" alt="image" src="https://github.com/user-attachments/assets/5b9cb128-9de3-4b13-8d74-33e6d17a5ed7" />

