package moulder.values;

import moulder.ElementAndData;
import moulder.Value;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * A value that loads a supplied html input stream and returns its children filtered by a selector
 *
 * @author jawher
 */
public class HtmlLoaderValue implements Value<Iterable<Node>> {
    private Value<InputStream> stream;
    private Value<String> selector;

    /**
     * Creates a Value that loads the specified input stream and returns its children
     * filtered by selector
     *
     * @param stream   the input stream of the html to load
     * @param selector a selector pointing to the children to extract from the loaded html
     *                 and return as the value
     */
    public HtmlLoaderValue(InputStream stream, String selector) {
        this(new SimpleValue<InputStream>(stream), new SimpleValue<String>(selector));
    }

    public HtmlLoaderValue(Value<InputStream> stream, String selector) {
        this(stream, new SimpleValue<String>(selector));
    }

    public HtmlLoaderValue(InputStream stream, Value<String> selector) {
        this(new SimpleValue<InputStream>(stream), selector);
    }

    public HtmlLoaderValue(Value<InputStream> stream, Value<String> selector) {
        this.stream = stream;
        this.selector = selector;
    }

    public Iterable<Node> get() {
        Document document;
        try {
            document = Jsoup.parse(stream.get(), null, "#");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<Element> res = document.select(selector.get());
        List<Node> copy = new ArrayList<Node>(res);
        return copy;
    }

    public void bind(ElementAndData nd) {
        stream.bind(nd);
        selector.bind(nd);
    }

}
