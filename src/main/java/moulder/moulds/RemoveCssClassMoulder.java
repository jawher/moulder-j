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
 * Moulder, which removes a CSS class.
 */
public class RemoveCssClassMoulder implements Moulder {

    private final Value<String> value;

    /**
     * @param value
     *            a value that returns the CSS class
     */
    public RemoveCssClassMoulder(Value<String> value) {
        this.value = value;
    }

    /**
     * @param cssClass
     *            the CSS class
     */
    public RemoveCssClassMoulder(String cssClass) {
        this(new SimpleValue<String>(cssClass));
    }

    public List<Node> process(Element element) {
        if (element.hasAttr("class")) {
            final String cssClass = value.get();
            final String attr = element.attr("class");

            final String regex = "\\b" + Pattern.quote(cssClass) + "\\b";

            String[] parts = attr.split(regex);
            if (parts == null)
                parts = new String[] { "" };

            StringBuilder sb = new StringBuilder();
            for (String part: parts)
                sb.append(' ').append(part.trim());

            element.attr("class", sb.toString().trim());
        }

        List<Node> res = new ArrayList<Node>();
        res.add(element);
        return res;
    }
}
