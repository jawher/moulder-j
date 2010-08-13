package jawher.moulder.values;

import jawher.moulder.ElementAndData;
import jawher.moulder.Value;

public class SimpleValue<T> implements Value<T> {
	private final T value;

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
