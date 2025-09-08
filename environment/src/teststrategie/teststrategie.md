## Übung 1
### Abstrakte Testfälle
| ID | Eingabe (Preis)         | Erwarteter Rabatt |
| -- | ----------------------- | ----------------- |
| 1  | Preis < 15'000          | 0 %               |
| 2  | 15'000 ≤ Preis ≤ 20'000 | 5 %               |
| 3  | 20'000 < Preis < 25'000 | 7 %               |
| 4  | Preis ≥ 25'000          | 8.5 %             |

### Konkrete Testfälle
| ID | Eingabe (Preis in CHF) | Erwarteter Rabatt |
| -- | ---------------------- | ----------------- |
| 1  | 10'000                 | 0 %               |
| 2  | 15'000                 | 5 %               |
| 3  | 18'500                 | 5 %               |
| 4  | 22'000                 | 7 %               |
| 5  | 25'000                 | 8.5 %             |
| 6  | 30'000                 | 8.5 %             |

## Übung 2
#### Autoscout Webseite
| ID | Beschreibung|Erwartetes Resultat|Effektives Resultat|Status|Mögliche Ursache |
|-----------|-------------------------|-------------------------|-------------------------|-------------------------|-------------------------|
|   1     | Programm startet korrekt | Nach dem Aufruf des Programms auf der Konsole öffnet sich ein Fenster.| Programm stürzt ab mit Fehler X00234 | Fehler |Zugriff auf DB-Server evtl. nicht möglich |
|2|Funktionierende Autosuche mit Filtern|Führt Suche aus mit den Filtern|Erwartetes Resultat|

| ID | Beschreibung                                            | Erwartetes Resultat                                                                     | Effektives Resultat | Status | Mögliche Ursache                  |
| -- | ------------------------------------------------------- |-----------------------------------------------------------------------------------------| ------------------- | ------ | --------------------------------- |
| 1  | Fahrzeug-Suche nach Ort und Datum                       | Liste mit verfügbaren Fahrzeugen wird angezeigt                                         |                     |        |                                   |
| 2  | Fahrzeug-Reservierung mit gültigen Daten                | Reservierung wird bestätigt und Bestätigungs-E-Mail versendet                           |                     |        |                                   |
| 3  | Reservierung mit ungültiger Kreditkarte                 | Fehlermeldung „Zahlung fehlgeschlagen“ erscheint                                        |                     |        | Falsche Validierung / Payment-API |
| 4  | Abbruch einer Reservierung                              | Reservierung wird korrekt aus dem System entfernt                                       |                     |        | Fehlerhafte Datenbankoperation    |
| 5  | Preisberechnung mit Zusatzoptionen (z. B. Versicherung) | Endpreis enthält Fahrzeugpreis und Zusatzoptionen, wird korrekt angezeigt und berechnet |                     |        | Falsche Preislogik im Backend     |


## Übung 3
| ID | Testfall                                | Erwartetes Resultat                                     |
| -- | --------------------------------------- | ------------------------------------------------------- |
| 1  | Anmeldung mit gültigen Zugangsdaten     | Benutzer wird eingeloggt                                |
| 2  | Anmeldung mit falschem Passwort         | Fehlermeldung „Login fehlgeschlagen“                    |
| 3  | Abfrage des Kontostands                 | Aktueller Kontostand wird angezeigt                     |
| 4  | Durchführung einer Überweisung          | Betrag wird abgebucht und beim Empfänger gutgeschrieben |
| 5  | Überweisung mit unzureichendem Guthaben | Fehlermeldung „nicht genügend Guthaben“                 |

#### Wo black box tests?
Überall, ausser so die Scanner klassen oder schon von Java existierenden Code

#### Welche Methoden im Code könnten für White-Box Testfälle verwendet werden?
Sinnvoll testbare Methoden:
#### Counter.java
deposit(amount)
- Bei Zahlen über 0 wird Saldo erhöht
Betrag 0 oder negativ → Exception oder keine Änderung, macht kein sinn deposit und dann nichts einzahlen

withdraw(amount)
            Betrag wird korrekt abgezogen
            Überziehung → Exception oder Fehlermeldung

Convertcurrency:
Geld wird korrekt umgewandelt


transfer:
- Betrag wird korrekt zwischen Konten verschoben


#### Was würden Sie am Code generell verbessern, welche Best Practices fallen Ihnen ein
- Text mit user interaktion in funktion auslagern und wieder aufrufen
- weniger verschachtelte if statement
- keine tests
- comments erstellen
- komplexe methoden