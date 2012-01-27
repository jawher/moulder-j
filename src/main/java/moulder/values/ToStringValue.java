package moulder.values;

import moulder.Value;

public class ToStringValue<T> extends ValueTransformer<T, String> {

    public ToStringValue(Value<T> delegate) {
        super(delegate);
    }

    @Override
    protected String transform(T s) {
        return s == null ? "" : s.toString();
    }

}
