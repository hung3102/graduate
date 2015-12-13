package x12;

public class Context {
	private Character s;
	private Character e;
	private Character c;

	/**
	 * Default constructor.
	 */
	public Context() {

	}

	/**
	 * Constructor which takes the segment separator, element separator and
	 * composite element separator as input.
	 * 
	 * @param s
	 *            segment separator
	 * @param e
	 *            element separator
	 * @param c
	 *            composite element separator
	 */
	public Context(Character s, Character e, Character c) {
		this.s = s;
		this.e = e;
		this.c = c;
	}

	/**
	 * Returns the composite element separator.
	 * 
	 * @return composite element separator
	 */
	public Character getCompositeElementSeparator() {
		return c;
	}

	/**
	 * Returns the element separator.
	 * 
	 * @return an element separator
	 */
	public Character getElementSeparator() {
		return e;
	}

	/**
	 * Returns the segment separator.
	 * 
	 * @return a segment separator
	 */
	public Character getSegmentSeparator() {
		return s;
	}

	/**
	 * Sets the composite element separator.
	 * 
	 * @param c
	 *            the composite element separator.
	 */
	public void setCompositeElementSeparator(Character c) {
		this.c = c;
	}

	/**
	 * Sets the element separator.
	 * 
	 * @param e
	 *            the element separator.
	 */
	public void setElementSeparator(Character e) {
		this.e = e;
	}

	/**
	 * Sets the segment separator.
	 * 
	 * @param s
	 *            the segment separator
	 */
	public void setSegmentSeparator(Character s) {
		this.s = s;
	}

	/**
	 * Returns a <code>String</code> consisting of segment, element and
	 * composite element separator.
	 */
	public String toString() {
		return "[" + this.s + "," + this.e + "," + this.c + "]";
	}

}
