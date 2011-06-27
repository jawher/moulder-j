package moulder;

/**
 * A poor man's closure, to enable dynamic values generation to be fed to
 * moulders
 * 
 * @author jawher
 * 
 * @param <T>
 *            the resulting value's type
 */
public interface Value<T> {
	/**
	 * 
	 * @return the generated value
	 */
	T get();

	/**
	 * Always called by moulders before {@link #get()}. Can be useful to extract
	 * and store data to be used later to generate the value
	 * 
	 * @param nd
	 *            the couple (Element, data) the moulder is going to process
	 */
	void bind(ElementAndData nd);
}
