+ `Passenger` bracht eine Referenz zu `Floors`
+ `Floors`
    - Methoden erweitern.
	- Zugriff auf das alte Floors[] Array überarbeiten.
	- `isCallInDir` (int ffpos, int dir, [int callDir])
+ `Elevator`
    - `calcDirection` implementieren
	- `isWishInDir` implementieren
+ `Simulation` soll `Passenger`, die ihr Ziel erreicht haben, löschen.
+ **Vorrang** implementieren

## bugs ##
```
rm -f LiftSim/*.class
rm -f *.class
javac LiftSim.java


------------------
java LiftSim
===== 0 =====
Pos     Des
2       4
3       6
2       1
1       5
0       1
Floor   CallState
6       0
5       0
4       0
3       0
2       0
1       0
0       0
Pos     Dir     Open    Load
0       0       false   0
Wishes
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 3
at LiftSim.Floor.delCall(Floor.java:59)
at LiftSim.Elevator.doAction(Elevator.java:85)
at LiftSim.Simulation.nextStep(Simulation.java:66)
at LiftSim.main(LiftSim.java:33)
Makefile:7: die Regel für Ziel „test“ scheiterte
make: *** [test] Fehler 1
```
