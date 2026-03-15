package org.pneditor.petrinet.adapters.Zhao;

import java.util.HashMap;
import java.util.Map;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.AbstractTransition;
import org.pneditor.petrinet.PetriNetInterface;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.UnimplementedCaseException;
import org.pneditor.petrinet.models.Zhao.IArc;
import org.pneditor.petrinet.models.Zhao.PetriNet;
import org.pneditor.petrinet.models.Zhao.Place;
import org.pneditor.petrinet.models.Zhao.Transition;

public class PetriNetAdapter extends PetriNetInterface {
	private PetriNet petrinet;
	private Map<String, ArcAdapter> arcAdpts;
	
	public PetriNetAdapter() {
		petrinet = new PetriNet();
		arcAdpts = new HashMap<String, ArcAdapter>();
	}

	@Override
	public AbstractPlace addPlace() {
		String placeId = petrinet.addPlace(0);
		Place place = petrinet.getPlaces().get(placeId);
		PlaceAdapter placeAdpt = new PlaceAdapter(placeId);
		placeAdpt.setPlace(place);
		System.out.println(petrinet);
		return placeAdpt;
	}

	@Override
	public AbstractTransition addTransition() {
		String transitionId = petrinet.addTransition();
		TransitionAdapter transitionAdpt = new TransitionAdapter(transitionId);
		System.out.println(petrinet);
		return transitionAdpt;
	}

	@Override
	public AbstractArc addRegularArc(AbstractNode source, AbstractNode destination) throws UnimplementedCaseException {
		String sourceId = source.getLabel();
		String targetId = destination.getLabel();
		String arcId = petrinet.addArc(sourceId, targetId, 1);
		IArc arc = petrinet.getArcs().get(arcId);
		ArcAdapter arcAdpt = new ArcAdapter(arcId, source, destination);
		arcAdpt.setArc(arc);
		arcAdpts.put(sourceId + targetId, arcAdpt);
		System.out.println(petrinet);
		return arcAdpt;
	}

	@Override
	public AbstractArc addInhibitoryArc(AbstractPlace place, AbstractTransition transition)
			throws UnimplementedCaseException {
		String sourceId = place.getLabel();
		String targetId = transition.getLabel();
		String arcId = petrinet.addZeroArc(sourceId, targetId);
		IArc arc = petrinet.getArcs().get(arcId);		
		ArcAdapter arcAdpt = new ArcAdapter(arcId, place, transition);
		arcAdpt.setArc(arc);
		arcAdpts.put(sourceId + targetId, arcAdpt);
		System.out.println(petrinet);
		return arcAdpt;
	}

	@Override
	public AbstractArc addResetArc(AbstractPlace place, AbstractTransition transition)
			throws UnimplementedCaseException {
		String sourceId = place.getLabel();
		String targetId = transition.getLabel();
		String arcId = petrinet.addEmptyArc(sourceId, targetId);
		IArc arc = petrinet.getArcs().get(arcId);		
		ArcAdapter arcAdpt = new ArcAdapter(arcId, place, transition);
		arcAdpt.setArc(arc);
		arcAdpts.put(sourceId + targetId, arcAdpt);
		System.out.println(petrinet);
		return arcAdpt;
	}

	@Override
	public void removePlace(AbstractPlace place) {
		petrinet.removePlace(place.getLabel());
		System.out.println(petrinet);
	}

	@Override
	public void removeTransition(AbstractTransition transition) {
		petrinet.removeTransition(transition.getLabel());
		System.out.println(petrinet);
	}

	@Override
	public void removeArc(AbstractArc arc) {
		String sourceId = arc.getSource().getLabel();
		String targetId = arc.getDestination().getLabel();
		ArcAdapter arcAdpt = arcAdpts.get(sourceId + targetId);
		String arcId = arcAdpt.getLabel();
		petrinet.removeIArc(arcId);
		System.out.println(petrinet);
	}

	@Override
	public boolean isEnabled(AbstractTransition transition) throws ResetArcMultiplicityException {
		boolean res = false;
		Transition t = petrinet.getTransitions().get(transition.getLabel());
		if (t != null)
			res =  t.isFirable();
		return res;
	}

	@Override
	public void fire(AbstractTransition transition) throws ResetArcMultiplicityException {
		Transition t = petrinet.getTransitions().get(transition.getLabel());
		if (t != null)
			t.fire();
		System.out.println(petrinet);
	}
}
