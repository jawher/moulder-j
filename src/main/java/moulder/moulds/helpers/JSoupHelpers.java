package moulder.moulds.helpers;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

public class JSoupHelpers {

    public static Element copy(Element e) {
        Element res = new Element(Tag.valueOf(e.tagName()), e.baseUri());
        for (Attribute a: e.attributes()) {
            res.attr(a.getKey(), a.getValue());
        }
        res.html(e.html());
        return res;
    }
}
