package test;

import logic.PetriNet;
import org.junit.jupiter.api.Test;

/** 
 * Tests for PetriNet activation in the section 7.3 of test plan.
 * @author Yuyan Zhao
 */

public class PetriNetActivationTest {
	
	@Test
	void testRI() {
		PetriNet petrinet = new PetriNet();
		petrinet.addTransition();

		System.out.println("RI before\n" + petrinet);
		petrinet.fireTransition("t0");
		System.out.println("RI after\n" + petrinet);
	}

	@Test
	void testRD0() {
		PetriNet petrinet = new PetriNet();
		petrinet.addTransition();
		petrinet.addPlace(0);
		petrinet.addArc("p0", "t0", 1);
		System.out.println("RD0 before\n" + petrinet);
		petrinet.fireTransition("t0");
		System.out.println("RD0 after\n" + petrinet);
		// petrinet.draw("Simulation RD0");
	}

	@Test
	void testRD1() {
		PetriNet petrinet = new PetriNet();
		petrinet.addTransition();

		petrinet.addPlace(5);
		petrinet.addArc("p0", "t0", 3);
		System.out.println("RD1 before\n" + petrinet);
		petrinet.fireTransition("t0");
		System.out.println("RD1 after\n" + petrinet);
	}

	@Test
	void testRG0() {
		PetriNet petrinet = new PetriNet();
		petrinet.addTransition();

		petrinet.addPlace(0);
		petrinet.addArc("t0", "p0", 1);
		System.out.println("RG0 before\n" + petrinet);
		petrinet.fireTransition("t0");
		System.out.println("RG0 after\n" + petrinet);
	}

	@Test
	void testRM0() {
		PetriNet petrinet = new PetriNet();
		petrinet.addTransition();

		petrinet.addPlace(0);
		petrinet.addArc("p0", "t0", 1);
		petrinet.addPlace(2);
		petrinet.addArc("t0", "p1", 1);
		System.out.println("RM0 before\n" + petrinet);
		petrinet.fireTransition("t0");
		System.out.println("RM0 after\n" + petrinet);
	}

	@Test
	void testRM1() {
		PetriNet petrinet = new PetriNet();
		petrinet.addTransition();

		petrinet.addPlace(4);
		petrinet.addArc("p0", "t0", 3);
		petrinet.addPlace(2);
		petrinet.addArc("t0", "p1", 1);
		System.out.println("RM1 before\n" + petrinet);
		petrinet.fireTransition("t0");
		System.out.println("RM1 after\n" + petrinet);
	}

	@Test
	void testRM2RM3() {
		PetriNet petrinet = new PetriNet();
		petrinet.addTransition();

		petrinet.addPlace(3);
		petrinet.addArc("p0", "t0", 2);
		petrinet.addPlace(2);
		petrinet.addArc("p1", "t0", 1);
		petrinet.addPlace(1);
		petrinet.addArc("t0", "p2", 1);
		System.out.println("RM2 before\n" + petrinet);
		petrinet.fireTransition("t0");
		System.out.println("RM2 after\n" + petrinet);

		petrinet.fireTransition("t0");
		System.out.println("RM3 after\n" + petrinet);
	}

	@Test
	void testRM4RM5() {
		PetriNet petrinet = new PetriNet();
		petrinet.addTransition();

		petrinet.addPlace(3);
		petrinet.addArc("p0", "t0", 2);
		petrinet.addArc("t0", "p0", 1);
		petrinet.addPlace(2);
		petrinet.addArc("p1", "t0", 1);
		petrinet.addPlace(1);
		petrinet.addArc("t0", "p2", 1);
		System.out.println("RM4 before\n" + petrinet);
		petrinet.fireTransition("t0");
		System.out.println("RM4 after\n" + petrinet);

		petrinet.fireTransition("t0");
		System.out.println("RM5 after\n" + petrinet);
	}
	
	@Test
	void testRM6() {
		PetriNet petrinet = new PetriNet();

		petrinet.addPlace(1);
		petrinet.addPlace(3);
		petrinet.addPlace(0);
		petrinet.addPlace(1);
		petrinet.addTransition();
		System.out.println(petrinet);

		petrinet.addArc("p0", "t0", 1);
		petrinet.addArc("p1", "t0", 2);
		petrinet.addArc("t0", "p2", 3);
		petrinet.addArc("t0", "p3", 1);
		System.out.println(petrinet);
		petrinet.draw("Petri Net Simulation 1");

		petrinet.fireTransition("t0");
		System.out.println(petrinet);
		petrinet.draw("Petri Net Simulation 2");

		petrinet.fireTransition("t0");

		petrinet.removeIArc("a0");
		petrinet.addZeroArc("p0", "t0");
		petrinet.setPlaceNTokens("p1", 2);
		petrinet.addPlace(4);
		petrinet.addEmptyArc("p4", "t0");
		petrinet.setArcWeight("a2", 1);
		System.out.println(petrinet);
		petrinet.draw("Petri Net Simulation 3");
		petrinet.fireTransition("t0");
		System.out.println(petrinet);
		petrinet.draw("Petri Net Simulation 4");

		petrinet.removePlace("p0");
		System.out.println(petrinet);
		petrinet.removeIArc("a1");
		System.out.println(petrinet);
		petrinet.removeTransition("t0");
		System.out.println(petrinet);
	}
}
