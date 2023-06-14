# 2. Feladat megoldásának bemutatása

Létrehoztam a Square, Circle és TextArea osztályokat

A Circle és a Square osztályokat leszármasztattam a Shape absztrakt osztályból

A TextArea osztályt leszármaztattam a Textbox osztályból

Létrehoztam ShapeInterface-t, ami a kiíratásokhoz szükséges függvényeket tartalmazza

A Shapes és a TextArea megvalósítja a ShapeInterface-t

Létrehoztam a ShapeContainert, ami ShapeInterface-eket tárol, a konténer képes új elem fekvételére és elemek kilistázására

A Main fájlban létrehoztam egy tárolót, amit feltöltöttem alakzatokkal, majd kilistáztam azokat.