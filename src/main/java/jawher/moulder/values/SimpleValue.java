package jawher.moulder.values;

import jawher.moulder.ElementAndData;
import jawher.moulder.Value;

/**
 * A dumb value implementation that always return a constant value
 * 
 * @author jawher
 * 
 * @param <T>
 *            the generated value type
 */
public class SimpleValue<T> implements Value<T> {
	private final T value;

	/**
	 * 
	 * @param value
	 *            the value to be returned by {@link #get()}
	 */
	public SimpleValue(T value) {
		super();
		this.value = value;
	}

	public T get() {
		return value;
	}

	public void bind(ElementAndData nd) {

	}

}
