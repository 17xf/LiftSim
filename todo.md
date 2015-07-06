## Do ##
+ `Floors`
    - Methoden erweitern.
+ `Elevator`
    - `calcDirection` implementieren
+ `Simulation` soll `Passenger`, die ihr Ziel erreicht haben, löschen.
+ **Vorrang** implementieren
+ Richtungen und Positionen vereinheitlichen. Scheiß auf C-Eleganz!

# isCallInDir RichtungLift und RichtungCall berücksichtigen

## Done ##
+ `Passenger` bracht eine Referenz zu `Floors`
+ `Floors`
	- Zugriff auf das alte Floors[] Array überarbeiten.
	- `isCallInDir` (int ffpos, int dir, [int callDir]) TESTEN!
+ `Elevator`
	- `isWishInDir` implementieren
