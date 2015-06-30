LiftSim.class: LiftSim.java
	javac LiftSim.java
clean: 
	rm -f LiftSim/*.class
	rm -f *.class
test: clean LiftSim.class
	@echo "------------------"
	java LiftSim
	@make clean
