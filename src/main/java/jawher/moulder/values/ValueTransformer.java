package jawher.moulder.values;

import jawher.moulder.ElementAndData;
import jawher.moulder.Value;

public abstract class ValueTransformer<T, S> implements Value<T> {
	private Value<S> delegate;

	public ValueTransformer(Value<S> delegate) {
		this.delegate = delegate;
	}

	protected abstract T transform(S s);

	public T get() {
		return transform(delegate.get());
	}

	public void bind(ElementAndData nd) {
		delegate.bind(nd);
	}

}
