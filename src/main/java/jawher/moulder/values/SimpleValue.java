package jawher.moulder.values;

import jawher.moulder.NodeAndData;
import jawher.moulder.Value;

import org.jsoup.nodes.Element;

public class SimpleValue<T> implements Value<T> {
	private final T value;

	public SimpleValue(T value) {
		super();
		this.value = value;
	}

	public T get() {
		return value;
	}

	public void bind(NodeAndData<Element> nd){
		
	}

}
