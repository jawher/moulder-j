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
}
