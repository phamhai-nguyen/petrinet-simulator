package org.pneditor.petrinet.models.Zhao;

/**
 * Normal arc for Petri Net.
 * @author Yuyan Zhao
 */
public class Arc implements IArc {
	private String id;
	private Place place;
	private Transition transition;
	private String direction;  // p2t(place to transition) or t2p(transition to place)
	private int weight; // weight should >= 1

	/* constructors */
	public Arc(String s, Place p, Transition t, int w) {
		id = s;
		place = p;
		transition = t;
		direction = "p2t";
		
		if (w < 1) {
			weight = 1;
		} else {
			weight = w;
		}
	}

	public Arc(String s, Transition t, Place p, int w) {
		id = s;
		place = p;
		transition = t;
		direction = "t2p";
		
		if (w < 1) {
			weight = 1;
		} else {
			weight = w;
		}
	}

	/* methods of IArc */
	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getSourceId() {
		if (direction.equals("p2t")) {
			return place.getId();
		} else {
			return transition.getId();
		}
	}

	@Override
	public String getTargetId() {
		if (direction.equals("p2t")) {
			return transition.getId();
		} else {
			return place.getId();
		}
	}

	@Override
	public Place getPlace() {
		return place;
	}

	@Override
	public Transition getTransition() {
		return transition;
	}

	@Override
	public int getWeight() {
		return weight;
	}

	@Override
	public String getDirection() {
		return direction;
	}

	@Override
	public void setWeight(int w) {
		if (w < 1) {
			System.err.println("Error: weight should >= 1");
		} else {
			weight = w;
		}
	}

	@Override
	public boolean isFirable() {
		boolean res = false;
		// when nTokens >= weight in the source place
		if (direction.equals("p2t") && place.getNTokens() >= weight) {
			res = true;
		}
		return res;
	}

	@Override
	public String toString() {
		String res;
		if (direction.equals("p2t")) {
			res = place.getId() + "->" + transition.getId();
		} else {
			res = transition.getId() + "->" + place.getId();
		}
		return "(" + res + " weight=" + Integer.toString(weight) + ")";
	}
}
