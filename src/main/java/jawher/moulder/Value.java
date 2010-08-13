package jawher.moulder;


public interface Value<T> {
	T get();
	void bind(ElementAndData nd);
}
