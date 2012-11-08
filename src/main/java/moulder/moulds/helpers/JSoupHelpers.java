package moulder.moulds.helpers;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;

public class JSoupHelpers {

    public static Element copy(Element e) {
        Element res = e.ownerDocument().createElement(e.tagName());
        for (Attribute a: e.attributes()) {
            res.attr(a.getKey(), a.getValue());
        }
        res.html(e.html());
        return res;
    }
}
