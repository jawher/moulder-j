package moulder.values;

import moulder.Value;

import java.io.InputStream;
import java.util.Iterator;

/**
 * A class with a bunch of static factory methods to common values
 *
 * @author jawher
 */
public class Values {
    public static <T> ElementDataValue<T> elemData() {
        return new ElementDataValue<T>();
    }

    public static HtmlValue html(String html) {
        return new HtmlValue(html);
    }

    public static HtmlValue html(Value<String> html) {
        return new HtmlValue(html);
    }

    public static HtmlLoaderValue load(InputStream stream, String selector) {
        return new HtmlLoaderValue(stream, selector);
    }

    public static HtmlLoaderValue load(Value<InputStream> stream, String selector) {
        return new HtmlLoaderValue(stream, selector);
    }

    public static HtmlLoaderValue load(InputStream stream, Value<String> selector) {
        return new HtmlLoaderValue(stream, selector);
    }

    public static HtmlLoaderValue load(Value<InputStream> stream, Value<String> selector) {
        return new HtmlLoaderValue(stream, selector);
    }

    public static <T> SeqValue<T> seq(Iterator<T> values) {
        return new SeqValue<T>(values);
    }

    public static <T> SeqValue<T> seq(Iterable<T> values) {
        return new SeqValue<T>(values);
    }

    public static <T> SeqValue<T> seq(T... values) {
        return new SeqValue<T>(values);
    }

    public static <T> SimpleValue<T> val(T value) {
        return new SimpleValue<T>(value);
    }
}
