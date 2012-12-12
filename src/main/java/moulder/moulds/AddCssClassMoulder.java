package moulder.moulds;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import moulder.Moulder;
import moulder.Value;
import moulder.values.SimpleValue;

/**
 * Moulder, which adds a CSS class if it's not already present.
 */
public class AddCssClassMoulder implements Moulder {

    private final Value<String> value;

    /**
     * @param value
     *            a value that returns the CSS class
     */
    public AddCssClassMoulder(Value<String> value) {
        this.value = value;
    }

    /**
     * @param cssClass
     *            the CSS class
     */
    public AddCssClassMoulder(String cssClass) {
        this(new SimpleValue<String>(cssClass));
    }

    public List<Node> process(Element element) {
        final String cssClass = value.get();
        final String attr = element.attr("class");

        String regex = ".*\\b" + Pattern.quote(cssClass) + "\\b.*";
        if (!attr.matches(regex)) {
            final String val = attr.trim() + " " + cssClass;
            element.attr("class", val.trim());
        }

        List<Node> res = new ArrayList<Node>();
        res.add(element);
        return res;
    }
}
