# Házi feladat specifikáció

Információk [itt](https://viauac00.github.io/laborok/hf)

## Mobil- és webes szoftverek
### 2023.10.21.                     
### Sport tracker alkalmazás        
### Glocker Endre - (KBC838)        
### glocker.endre@edu.bme.hu        
### Laborvezető: Szénássy Márton    

## Bemutatás

Az alkalmazás célja, hogy a felhasználók nyomon követhessék a sportolásukat, illetve a sportolásukkal kapcsolatos adatokat tárolhassák. Az alkalmazás lehetőséget nyújt a felhasználók tevékenységeinek rögzítésére, illetve az azokhoz tartozó adatok és statisztikák megtekintésére. Az alkalmazás célközönsége bárki, aki szeretné részletesen tárolni és nyomon követeni azt, hogy mikor, milyen sportot, mennyi ideig űzött és ez a korábbi teljesítményeihez képest hogyan viszonyul.

## Főbb funkciók

#### Felhasználói adatok megadása / módosítása
Az alkalmazásban lehetőség van felhasználót, és annak személyes adatainak felvételére vagy módosítására (pl.: testmagasság, életkor, testsúly stb.)

#### Tevékenység rögzítése
A felhasználónak lehetősége van rögzíteni, hogy mit sportolt, mikor, mennyi ideig, milyen távolságot tett meg.

#### History
Egy nézet, ahol lehetőség van a korábbi tevékenységek megtekintésére, illetve azok törlésére.

#### Statisztika
Vizuálizált eredményeket mutat a felhasználó tevékenységeiről (pl.: átlag megtett távolság, tevékenység gyakorisága, legnagyobb megtett távolság stb.)

#### Save
A felvett adatokat perzisztensen elmenti, és az alkalmazás újraindításakor is elérhetővé teszi.


## Választott technológiák:

- fragmentek
- RecyclerView: history megvalósításához 
- perzisztens adattárolás: adatok mentéséhez
- room: adatbázis megvalósításához
- intent: adatok megosztásához
