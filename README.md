# CoWorkingSpace API
Die Aufgabe dieser API ist es, Kunden des CoWorkingSpace die möglichkeit zu bieten ihre Buchungen bezüglich der Arbeitsplätze bequem online zu erledigen.

## Einrichtung der Entwicklungsumgebung
1. Stelle sicher, dass Docker installiert ist und läuft.
2. Stelle sicher, dass Visual Studio Code und die Erweiterung Remote Container installiert ist.
3. Klone (clone) das Projekt lokal, um damit arbeiten zu können.
4. Öffne das Projekt mit Visual Studio Code.
5. Öffne das Projekt im Entwicklungscontainer.

*Kopiert von PunchClock Repository*

## Starten der Applikation
Starte das Projekt mit dem Kommando Quarkus: Debug current Quarkus Project
Probiere die Client-Applikation unter http://localhost:8080 aus.
Schaue die API auf http://localhost:8080/q/swagger-ui/ an.

*Kopiert von PunchClock Repository*

## Laden der Testdaten
Die Testdaten werden aus der Klasse TestDataService.java geladen. Diese befindet sich im Pfad: /workspace/src/main/java/ch/justinbauer/m223/service/TestDataService.java

## Anpassungen der Planung
Folgende Anpauungen der Planung wurden vorgenommen:

### Fachklassendiagramm
Folegnde Variablen wurden der Klassen Member.java hinzugefügt:
```
private boolean isBlocked;
```

Folegnde Variablen wurden der Klassen .java hinzugefügt:
```
private Boolean withPrinter;
```

### Schnittstellen
Folgende Schnittstellen sind dazu gekommen:
| Beschreibung                          | Pfad      | http-Methode | Parameter | Erfolg                                                            |
|---------------------------------------|-----------|--------------|-----------|-------------------------------------------------------------------|
| Member Registrieren                   | /signup   | POST         | -         | 201 Created, Member wurde erstellt                                |
| Member anmelden                       | /signin   | POST         | -         | 201 Created, Member wurde erstellt                                |
| Bookings von einem Mitglied auflisten | /bookings | GET          | /{id}     | 200 OK, Bookings von einem bestimmten Mitglied wird zurückgegeben |

Folgende Schnittstellen sind entfernt worden:
| Beschreibung                | Pfad            | http-Methode | Parameter | Erfolg                                                   |
|-----------------------------|-----------------|--------------|-----------|----------------------------------------------------------|
| Member finden               | /member         | GET          | /{id}     | 200 OK, Ein bestimmter member wird ausgegeben            |
| Booking nach Status filtern | /booking/status | GET          | /{id}     | 200 OK, Booking mit bestimmten Status wird zurückgegeben |
| Booking finden              | /booking        | GET          | /{id}     | 200 OK, Ein bestimmtes Booking wird zurückgegeben        |

*Anmerkung:*
Die Pfade wurden jeweils angepasst, /member wurde zu /members und /booking wurde zu /bookings.

## Postman Tests
Sämtliche Postman Test sind in der Datei "" gespeichert.
