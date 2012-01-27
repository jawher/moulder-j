package moulder.moulds;

import moulder.Moulder;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import java.util.ArrayList;
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


    public List<Node> process(Element element) {
        List<Node> res = new ArrayList<Node>();
        List<Element> kept = element.select(selector);

        for (Element e : kept) {
            res.add(e);
        }
        return res;

    }

}
