package moulder.values;

import moulder.Value;

/**
 * A dumb value implementation that always return a constant value
 *
 * @param <T> the generated value type
 * @author jawher
 */
public class SimpleValue<T> implements Value<T> {
    private final T value;

    /**
     * @param value the value to be returned by {@link #get()}
     */
    public SimpleValue(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }
}
