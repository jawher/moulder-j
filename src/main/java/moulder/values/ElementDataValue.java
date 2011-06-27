package moulder.values;

import moulder.ElementAndData;
import moulder.Value;

/**
 * A value that returns the element it was bound to's value.
 * 
 * @author jawher
 * 
 * @param <T>
 *            the type of the generated value
 */
public class ElementDataValue<T> implements Value<T> {
	private T data;

	public T get() {
		return data;
	}

	public void bind(ElementAndData nd) {
		data = (T) nd.data;
	}

}
