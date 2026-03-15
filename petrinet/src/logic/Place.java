package logic;

/**
 * Place for Petri Net.
 * @author Yuyan Zhao, Hai-Nguyen Pham
 */
class Place {
	private String id;
	private int nTokens; // nTokens should >= 0

	/** constructor */
	public Place(String s, int n) {
		id = s;
		
		if (n < 0) {
			nTokens = 0;
		} else {
			nTokens = n;
		}
	}

	/*** 
	 * Get the number of tokens in the place.
	 * 
	 * @return nTokens
	 */
	public int getNTokens() {
		return nTokens;
	}

	/** 
	 * Set n tokens in the place.
	 * If n < 0, print error message.
	 * 
	 * @param n: number of tokens
	 */
	public void setNTokens(int n) {
		if (n < 0) {
			System.err.println("Error: n should >= 0");
		} else {
			nTokens = n;
		}
	}

	/**
	 * Remove n tokens from the place.
	 * If n < 0, print error message.
	 * 
	 * @param n: number of tokens
	 */
	public void removeTokens(int n) {
		if (n < 0) {
			System.err.println("Error: n should >= 0");
		} else {
			nTokens -= n;
		}
	}

	/**
	 * Add n tokens to the place.
	 * If n < 0, print error message.
	 * 
	 * @param n: number of tokens
	 */
	public void addTokens(int n) {
		if (n < 0) {
			System.err.println("Error: n should >= 0");
		} else {
			nTokens += n;
		}
	}

	/**
	 * Get the id of the place.
	 * 
	 * @return id
	 */
	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		return "(nTokens=" + nTokens + ")";
	}
}