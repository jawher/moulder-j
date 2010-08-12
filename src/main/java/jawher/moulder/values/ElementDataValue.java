package jawher.moulder.values;

import jawher.moulder.NodeAndData;
import jawher.moulder.Value;

import org.jsoup.nodes.Element;

public class ElementDataValue<T> implements Value<T> {
	private T data;

	public T get() {
		return data;
	}

	public void bind(NodeAndData<Element> nd) {
		data = (T) nd.data;
	}

}
