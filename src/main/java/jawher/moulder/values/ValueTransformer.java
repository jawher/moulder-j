package jawher.moulder.values;

import jawher.moulder.NodeAndData;
import jawher.moulder.Value;

import org.jsoup.nodes.Element;

public abstract class ValueTransformer<T, S> implements Value<T> {
	private Value<S> delegate;

	public ValueTransformer(Value<S> delegate) {
		this.delegate = delegate;
	}

	protected abstract T transform(S s);

	public T get() {
		return transform(delegate.get());
	}

	public void bind(NodeAndData<Element> nd){
		delegate.bind(nd);
	}

}
