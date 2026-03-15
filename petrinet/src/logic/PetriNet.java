package logic;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Petri Net.
 * @author Yuyan Zhao
 */
public class PetriNet implements IPetriNet {
	private Map<String, Place> places;
	private Map<String, Transition> transitions;
	private Map<String, IArc> arcs;
	private int idxPlace;
	private int idxTransition;
	private int idxArc;

	/* constructor */
	public PetriNet() {
		places = new HashMap<String, Place>();
		transitions = new HashMap<String, Transition>();
		arcs = new HashMap<String, IArc>();
		idxPlace = 0;
		idxTransition = 0;
		idxArc = 0;
	}

	/* methods of IPetriNet */
	@Override
	public void addPlace(int n) {
		if (n < 0) {
			System.err.println("Error: n tokens should >= 0");
			return;
		}

		String id = "p" + Integer.toString(idxPlace);
		places.put(id, new Place(id, n));
		++idxPlace;
	}

	@Override
	public void removePlace(String id) {
		if (places.get(id) == null) {
			System.err.println("Error: id " + id);
			return;
		}

		// remove the arcs connected by the place
		for (Iterator<Map.Entry<String, IArc>> it = arcs.entrySet().iterator(); it.hasNext();) {
			Map.Entry<String, IArc> item = it.next();
			IArc arc = item.getValue();
			if (arc.getPlace().getId().equals(id)) {
				// remove the arc from the lists inArcs and outArcs of the transition
				arc.getTransition().removeArc(arc);
				it.remove();
			}
		}

		// remove the place
		places.remove(id);
	}

	@Override
	public void addPlaceTokens(String id, int n) {
		if (places.get(id) == null) {
			System.err.println("Error: id " + id);
			return;
		}

		places.get(id).addTokens(n);
	}

	@Override
	public void removePlaceTokens(String id, int n) {
		if (places.get(id) == null) {
			System.err.println("Error: id " + id);
			return;
		}

		places.get(id).removeTokens(n);
	}

	@Override
	public void setPlaceNTokens(String id, int n) {
		if (places.get(id) == null) {
			System.err.println("Error: id " + id);
			return;
		}

		places.get(id).setNTokens(n);
	}

	@Override
	public int getNTokens(String id) {
		if (places.get(id) == null) {
			System.err.println("Error: id " + id);
			return -1;
		}

		return places.get(id).getNTokens();
	}

	@Override
	public void addTransition() {
		String id = "t" + Integer.toString(idxTransition);
		transitions.put(id, new Transition(id));
		++idxTransition;
	}
	
	@Override
	public void removeTransition(String id) {
		if (transitions.get(id) == null) {
			System.err.println("Error: id " + id);
			return;
		}

		// remove arcs connected by transition
		for (Iterator<Map.Entry<String, IArc>> it = arcs.entrySet().iterator(); it.hasNext();) {
			Map.Entry<String, IArc> item = it.next();
			IArc arc = item.getValue();
			if (arc.getTransition().getId().equals(id)) {
				it.remove();
			}
		}

		// remove the transition
		transitions.remove(id);
	}

	@Override
	public void fireTransition(String id) {
		Transition transition = transitions.get(id);
		if (transition == null) {
			System.err.println("Error: id " + id);
			return;
		}

		if (transition.fire()) {
			System.out.println(transition.getId() + " fired");
		} else {
			System.out.println(transition.getId() + " can't fire");
		}
	}

	@Override
	public void addArc(String sourceId, String targetId, int weight) {
		if (weight < 1) {
			System.err.println("Error: weight should >= 1");
			return;
		}

		if (isExistingArc(sourceId, targetId)) {
			System.err.println("Error: existing arc from " + sourceId + " to " + targetId);
			return;
		}

		String id = "a" + Integer.toString(idxArc);
		IArc arc;

		if (places.get(sourceId) instanceof Place && transitions.get(targetId) instanceof Transition) {
			Place place = places.get(sourceId);
			Transition transition = transitions.get(targetId);
			arc = new Arc(id, place, transition, weight);
			transition.addInArc(arc);
		} else if (places.get(targetId) instanceof Place && transitions.get(sourceId) instanceof Transition) {
			Place place = places.get(targetId);
			Transition transition = transitions.get(sourceId);
			arc = new Arc(id, transition, place, weight);
			transition.addOutArc(arc);
		} else {
			System.err.println("Error: sourceId or targetId " + sourceId + " " + targetId);
			return;
		}

		arcs.put(id, arc);
		++idxArc;
	}

	@Override
	public void setArcWeight(String id, int weight) {
		if (arcs.get(id) instanceof Arc) {
			arcs.get(id).setWeight(weight);
		} else {
			System.err.println("Error: id " + id);
		}
	}

	@Override
	public void addZeroArc(String sourceId, String targetId) {
		if (isExistingArc(sourceId, targetId)) {
			System.err.println("Error: existing arc from " + sourceId + " to " + targetId);
			return;
		}

		Place place = places.get(sourceId);
		Transition transition = transitions.get(targetId);
		if (place == null || transition == null) {
			System.err.println("Error: sourceId or targetId " + sourceId + " " + targetId);
			return;
		}

		String id = "a" + Integer.toString(idxArc);
		IArc arc = new ZeroArc(id, place, transition);
		transition.addInArc(arc);
		arcs.put(id, arc);
		++idxArc;
	}

	@Override
	public void addEmptyArc(String sourceId, String targetId) {
		if (isExistingArc(sourceId, targetId)) {
			System.err.println("Error: existing arc from " + sourceId + " to " + targetId);
			return;
		}

		Place place = places.get(sourceId);
		Transition transition = transitions.get(targetId);
		if (place == null || transition == null) {
			System.err.println("Error: sourceId or targetId " + sourceId + " " + targetId);
			return;
		}

		String id = "a" + Integer.toString(idxArc);
		IArc arc = new EmptyArc(id, place, transition);
		transition.addInArc(arc);
		arcs.put(id, arc);
		++idxArc;
	}

	@Override
	public void removeIArc(String id) {
		// remove the arc from the lists inArcs and outArcs of the connected transition
		IArc arc = arcs.get(id);
		for (Iterator<Map.Entry<String, Transition>> it = transitions.entrySet().iterator(); it.hasNext();) {
			Map.Entry<String, Transition> item = it.next();
			item.getValue().removeArc(arc);
		}
		
		// remove the arc
		arcs.remove(id);
	}

	@Override
	public String toString() {
		String res = "----------\nPetri Net\n";
		String placesStr = "";
		String transitionsStr = "";
		String arcsStr = "";

		res = res + "---\nplaces: " + places.size() + "\n";
		for (Map.Entry<String, Place> entry : places.entrySet()) {
			if (placesStr == "") {
				placesStr = entry.getKey() + entry.getValue().toString();
			} else {
				placesStr = placesStr + ", " + entry.getKey() + entry.getValue().toString();
			}
		}
		res = res + placesStr + "\n";

		res = res + "---\ntransitions: " + transitions.size() + "\n";
		for (Map.Entry<String, Transition> entry : transitions.entrySet()) {
			if (transitionsStr == "") {
				transitionsStr = entry.getKey() + entry.getValue().toString();
			} else {
				transitionsStr = transitionsStr + ", " + entry.getKey() + entry.getValue().toString();
			}
		}
		res = res + transitionsStr + "\n";

		res = res + "---\narcs: " + arcs.size() + "\n";
		for (Map.Entry<String, IArc> entry : arcs.entrySet()) {
			if (arcsStr == "") {
				arcsStr = entry.getKey() + entry.getValue().toString();
			} else {
				arcsStr = arcsStr + ", " + entry.getKey() + entry.getValue().toString();
			}
		}
		res = res + arcsStr + "\n";
		res = res + "----------\n";

		return res;
	}

	public Map<String, Transition> getTransitions() {
		return transitions;
	}

	public Map<String, Place> getPlaces() {
		return places;
	}

	public Map<String, IArc> getArcs() {
		return arcs;
	}

	public void draw(String title) {
		PetriNetFigure figure = new PetriNetFigure(this);
		figure.drawPetriNet(title);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean isExistingArc(String sourceId, String targetId) {
		boolean res = false;
		for (Map.Entry<String, IArc> entry : arcs.entrySet()) {
			if (entry.getValue().getSourceId().equals(sourceId) && entry.getValue().getTargetId().equals(targetId)) {
				res = true;
				break;
			}
		}
		return res;
	}
}
