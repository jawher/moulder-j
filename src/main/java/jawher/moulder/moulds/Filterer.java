package jawher.moulder.moulds;

import jawher.moulder.*;
import jawher.moulder.values.SimpleValue;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A moulder that replaces its input element with any of its children conforming
 * to a selector
 *
 * @author jawher
 */
public class Filterer implements Moulder {
    private final String selector;

    /**
     * Creates a Filterer that replaces its input element with any of its children
     * conforming to a selector
     *
     * @param selector a selector relative to the element. Should return the children
     *                 that are to replace it.
     */
    public Filterer(String selector) {
        this.selector = selector;
    }


    public List<NodeAndData> process(ElementAndData nd, MoulderUtils f) {
        List<Element> kept = nd.node.select(selector);

        List<NodeAndData> res = new ArrayList<NodeAndData>();
        for (Element e : kept) {
            res.add(new NodeAndData(e, nd.data));
        }
        return res;

    }

}
