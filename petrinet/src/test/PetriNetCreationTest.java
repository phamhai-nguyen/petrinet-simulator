package test;

import logic.PetriNet;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

/** 
 * Tests for PetriNet creation in the section 7.2 of test plan.
 * @author Yuyan Zhao
 */
public class PetriNetCreationTest {

	/**
	 * Test the function addPlace
	 */
	@Test
	public void testAddPlace() {
		PetriNet petrinet = new PetriNet();
		/* If nTokens < 0, print error message, rejected */
		petrinet.addPlace(-1);
		assertEquals(petrinet.getPlaces().size(), 0);

		petrinet.addPlace(1);
		assertEquals(petrinet.getPlaces().size(), 1);
		
		System.out.println(petrinet);
	}

	/**
	 * Test the function getPlaceNTokens
	 */
	@Test
	public void testGetPlaceNTokens() {
		PetriNet petrinet = new PetriNet();
		petrinet.addPlace(3);

		/* If wrong id, print error message, return -1 */
		assertEquals(petrinet.getNTokens(""), -1);

		assertEquals(petrinet.getNTokens("p0"), 3);
	}

	/**
	 * Test the function setPlaceNTokens
	 */
	@Test
	public void testSetPlaceNTokens() {
		PetriNet petrinet = new PetriNet();
		petrinet.addPlace(3);

		/* If nTokens < 0, print error message, rejected */
		petrinet.setPlaceNTokens("p0", -2);
		assertEquals(petrinet.getNTokens("p0"), 3);

		/* If wrong id, print error message, rejected */
		petrinet.setPlaceNTokens("", 2);
		assertEquals(petrinet.getNTokens("p0"), 3);

		petrinet.setPlaceNTokens("p0", 2);
		assertEquals(petrinet.getNTokens("p0"), 2);
	}

	/**
	 * Test the function addPlaceTokens
	 */
	@Test
	public void testAddPlaceTokens() {
		PetriNet petrinet = new PetriNet();
		petrinet.addPlace(1);

		/* If nTokens < 0, print error message, rejected */
		petrinet.addPlaceTokens("p0", -2);
		assertEquals(petrinet.getNTokens("p0"), 1);

		/* If wrong id, print error message, rejected */
		petrinet.addPlaceTokens("0", 2);
		assertEquals(petrinet.getNTokens("p0"), 1);

		petrinet.addPlaceTokens("p0", 2);
		assertEquals(petrinet.getNTokens("p0"), 3);
	}

	/**
	 * Test the function removePlaceTokens
	 */
	@Test
	public void testRemovePlaceTokens() {
		PetriNet petrinet = new PetriNet();
		petrinet.addPlace(3);

		/* If nTokens < 0, print error message, rejected */
		petrinet.removePlaceTokens("p0", -2);
		assertEquals(petrinet.getNTokens("p0"), 3);

		/* If wrong id, print error message, rejected */
		petrinet.removePlaceTokens("", 2);
		assertEquals(petrinet.getNTokens("p0"), 3);

		petrinet.removePlaceTokens("p0", 2);
		assertEquals(petrinet.getNTokens("p0"), 1);
	}

	/**
	 * Test the function addArc
	 */
	@Test
	public void testAddArc() {
		PetriNet petrinet = new PetriNet();
		petrinet.addPlace(1);
		petrinet.addTransition();

		/* If weight < 1, print error message, don't add the arc */
		petrinet.addArc("p0", "t0", -1);
		assertEquals(petrinet.getArcs().size(), 0);

		/* If the source or target is wrong, print error message, don't add the arc */
		petrinet.addArc("p0", "p0", 1);
		assertEquals(petrinet.getArcs().size(), 0);

		petrinet.addArc("p0", "t0", 1);
		assertEquals(petrinet.getArcs().size(), 1);
		petrinet.addArc("t0", "p0", 2);
		assertEquals(petrinet.getArcs().size(), 2);

		/* If the arc has the same source and the target, print error message */
		petrinet.addArc("p0", "t0", 1);
		assertEquals(petrinet.getArcs().size(), 2);
	}
	
	/**
	 * Test the function setArcWeight
	 */
	@Test
	public void testSetArcWeight() {
		PetriNet petrinet = new PetriNet();
		petrinet.addPlace(1);
		petrinet.addPlace(3);
		petrinet.addTransition();
		petrinet.addZeroArc("p0", "t0");
		petrinet.addEmptyArc("p1", "t0");
		petrinet.addArc("t0", "p0", 1);

		/* If weight < 0, print error message, rejected */
		petrinet.setArcWeight("a2", -2);
		assertEquals(petrinet.getArcs().get("a2").getWeight(), 1);

		/* If wrong id, print error message, rejected */
		petrinet.setArcWeight("a0", 2);
		assertEquals(petrinet.getArcs().get("a0").getWeight(), 0);
		petrinet.setArcWeight("a1", 2);
		assertEquals(petrinet.getArcs().get("a1").getWeight(), 3);
		
		petrinet.setArcWeight("a2", 2);
		assertEquals(petrinet.getArcs().get("a2").getWeight(), 2);
	}

	/**
	 * Test the function addZeroArc
	 */
	@Test
	public void testAddZeroArc() {
		PetriNet petrinet = new PetriNet();
		petrinet.addPlace(1);
		petrinet.addTransition();

		/* If the source or target is wrong, print error message, don't add the arc */
		petrinet.addZeroArc("p0", "p0");
		assertEquals(petrinet.getArcs().size(), 0);

		petrinet.addZeroArc("p0", "t0");
		assertEquals(petrinet.getArcs().size(), 1);

		/* If the arc has the same source and the target, print error message */
		petrinet.addZeroArc("p0", "t0");
		assertEquals(petrinet.getArcs().size(), 1);
	}

	/**
	 * Test the function addEmptyArc
	 */
	@Test
	public void testAddEmptyArc() {
		PetriNet petrinet = new PetriNet();
		petrinet.addPlace(1);
		petrinet.addTransition();

		/* If the source or target is wrong, print error message, don't add the arc */
		petrinet.addEmptyArc("p0", "p0");
		assertEquals(petrinet.getArcs().size(), 0);

		petrinet.addEmptyArc("p0", "t0");
		assertEquals(petrinet.getArcs().size(), 1);

		/* If the arc has the same source and the target, print error message */
		petrinet.addEmptyArc("p0", "t0");
		assertEquals(petrinet.getArcs().size(), 1);
	}

	/**
	 * Test the function removePlace
	 */
	@Test
	public void testRemovePlace() {
		PetriNet petrinet = new PetriNet();
		petrinet.addPlace(3);
		petrinet.addPlace(1);
		petrinet.addTransition();
		petrinet.addArc("p0", "t0", 1);
		petrinet.addArc("p1", "t0", 2);
		System.out.println(petrinet);

		/* If wrong id, print error message, rejected */
		petrinet.removePlace("t0");
		assertEquals(petrinet.getPlaces().size(), 2);

		petrinet.removePlace("p0");
		assertEquals(petrinet.getPlaces().size(), 1);
		System.out.println(petrinet);
	}

	/**
	 * Test the function removeTransition
	 */
	@Test
	public void testRemoveTransition() {
		PetriNet petrinet = new PetriNet();
		petrinet.addPlace(3);
		petrinet.addTransition();
		petrinet.addTransition();
		petrinet.addArc("p0", "t0", 1);
		petrinet.addArc("p0", "t1", 2);
		System.out.println(petrinet);

		/* If wrong id, print error message, rejected */
		petrinet.removeTransition("p0");
		assertEquals(petrinet.getTransitions().size(), 2);

		petrinet.removeTransition("t0");
		assertEquals(petrinet.getTransitions().size(), 1);
		System.out.println(petrinet);
	}
	
	/**
	 * Test the function removeArc
	 */
	@Test
	public void testRemoveArc() {
		PetriNet petrinet = new PetriNet();
		petrinet.addPlace(3);
		petrinet.addPlace(1);
		petrinet.addTransition();
		petrinet.addZeroArc("p0", "t0");
		petrinet.addArc("t0", "p1", 2);
		petrinet.addEmptyArc("p1", "t0");
		System.out.println(petrinet);

		/* If wrong id, print error message, rejected */
		petrinet.removeIArc("p0");
		assertEquals(petrinet.getArcs().size(), 3);

		petrinet.removeIArc("a2");
		assertEquals(petrinet.getArcs().size(), 2);
		System.out.println(petrinet);
	}
}
