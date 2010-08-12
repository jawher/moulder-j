package jawher.moulder;

import org.jsoup.nodes.Element;

public interface Value<T> {
	T get();
	void bind(NodeAndData<Element> nd);
}
