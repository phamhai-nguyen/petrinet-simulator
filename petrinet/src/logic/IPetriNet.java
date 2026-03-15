package logic;

/**
 * Interface for the class PetriNet.
 * @author Yuyan Zhao
 */
public interface IPetriNet {
	/**
	 * Add a place in the Petri Net with n tokens.
	 * If n is negative, print error message.
	 * 
	 * @param n: number of tokens in the place
	 */
	public void addPlace(int n);

	/**
	 * Remove the selected place by id.
	 * 
	 * @param id: input id
	 */
	public void removePlace(String id);

	/**
	 * Add n tokens to the selected place.
	 * If the selected place is null, print error message.
	 * If n is negative, print error message.
	 * 
	 * @param id: input id
	 * @param n: number of tokens
	 */
	public void addPlaceTokens(String id, int n);

	/**
	 * Remove n tokens from the selected place.
	 * If the selected place is null, print error message.
	 * If n is negative, print error message.
	 * 
	 * @param id: input id
	 * @param n: number of tokens
	 */
	public void removePlaceTokens(String id, int n);

	/**
	 * Set n tokens in the selected place.
	 * If the selected place is null, print error message.
	 * If n is negative, print error message.
	 * 
	 * @param id: input id
	 * @param n: number of tokens
	 */
	public void setPlaceNTokens(String id, int n);

	/**
	 * Get nTokens in the selected place.
	 * If the selected place is null, print error message.
	 * 
	 * @param id: input id
	 * 
	 * @return nTokens: number of tokens of the place
	 */
	public int getNTokens(String id);

	/** 
	 * Add a transition.
	 */
	public void addTransition();

	/**
	 * Remove the selected transition by id.
	 * If the selected transition is null, print error message.
	 * 
	 * @param id: input id
	 */
	public void removeTransition(String id);

	/**
	 * Fire the selected transition by id.
	 * If the selected transition is null, print error message.
	 * 
	 * @param id: input id
	 */
	public void fireTransition(String id);

	/**
	 * Add an arc by sourceId, targetId, weight.
	 * If weight is not positive, print error message.
	 * If the corresponding place or transition doesn't exist, print error message.
	 * If there's already an arc in the same direction between the place and the transition, print error message.
	 * 
	 * @param sourceId: id of the source object
	 * @param targetId: id of the target object
	 * @param weight: input weight
	 */
	public void addArc(String sourceId, String targetId, int weight);
	
	/**
	 * Set the weight for the selected arc.
	 * If weight is not positive, print error message.
	 * If the selected object isn't a instance of the class Arc, print error message.
	 * 
	 * @param id: input id
	 * @param weight: input weight
	 */
	public void setArcWeight(String id, int weight);

	/**
	 * Add an empty arc by sourceId, targetId.
	 * If the corresponding place or transition doesn't exist, print error message.
	 * 
	 * @param sourceId: id of the source object
	 * @param targetId: id of the target object
	 */
	public void addEmptyArc(String sourceId, String targetId);

	/**
	 * Add a zero arc by sourceId, targetId.
	 * If the corresponding place or transition doesn't exist, print error message.
	 * 
	 * @param sourceId: id of the source object
	 * @param targetId: id of the target object
	 */
	public void addZeroArc(String sourceId, String targetId);

	/**
	 * Remove the selected arc/empty arc/zero arc.
	 * If the selected arc/empty arc/zero arc is null, print error message.
	 * 
	 * @param id: input id
	 */
	public void removeIArc(String id);

	/** 
	 * To string. 
	 */
	public String toString();
}