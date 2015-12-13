package x12;

/**
 * The X12 class is the object representation of an ANSI X12
 * transaction. The building block of an X12 transaction is an element. Some
 * elements may be made of sub elements. Elements combine to form segments.
 * Segments are grouped as loops. And a set of loops form an X12 transaction.
 * 
 * @author Prasad Balan
 * 
 */

public class X12 extends Loop implements EDI {

	/**
	 * The constructor takes a context object.
	 * 
	 * @param c
	 *            a Context object
	 */
	public X12(Context c) {
		super(c, "X12");
	}
}
