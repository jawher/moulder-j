package moulder.values;

import moulder.Value;

/**
 * A simple box for storing one value at a time.
 */
public class SimpleBox<T> implements Value<T> {

    private T value;

    public SimpleBox() {
        this.value = null;
    }

    public SimpleBox(T value) {
        this.value = value;
    }

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }
}
