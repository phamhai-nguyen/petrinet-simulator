/**
 * This package contains classes and interfaces for simulating the PetriNet.
 * 
 * The core classes in this package include:
 * - PetriNet: Represents the Petri Net itself, with places, transitions and arcs.
 * - Place: Represents a place in the Petri Net, which can hold tokens.
 * - Transition: Represents a transition in the Petri Net, which can fire and
 *              modify tokens between places.
 * - Arc: Represents a normal arc with a weight.
If the arc is from a place to a transition, the weight is the number of tokens necessary to make it firable. It¡¯ll be removed after triggering the transition. 
If the arc is from a transition to a place, the weight will be added after firing the transition 
 * - EmptyArc: Represents an empty arc that is from a place to a transition. It is active when there is a token in the source place.
and remove all the tokens of the source place when it is activated.
 * - ZeroArc: Represents a zero arc that is from a place to a transition. It is active when the source place has no tokens.
 *
 * The interfaces in this package include:
 * - IPetriNet: Defines the methods for the class PetriNet.
 * - IArc: Defines the methods for the class Arc, EmptyArc, ZeroArc.
 * 
 * Example usage:
 *  PetriNet petrinet = new PetriNet();
	
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
 */
/**
 * @author Yuyan Zhao, Hai-Nguyen Pham
 * @version 2.0
 * @since 2023-11-13
 */
package logic;