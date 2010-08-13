package jawher.moulder.values;

import jawher.moulder.ElementAndData;
import jawher.moulder.Value;

public class ElementDataValue<T> implements Value<T> {
	private T data;

	public T get() {
		return data;
	}

	public void bind(ElementAndData nd) {
		data = (T) nd.data;
	}

}
