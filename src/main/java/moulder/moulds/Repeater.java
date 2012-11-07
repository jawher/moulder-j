package moulder.moulds;

import moulder.Moulder;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * A moulder that repeats its input element
 *
 * @author jawher
 */
public class Repeater implements Moulder {

    private final int count;

    /**
     * Creates a moulder that duplicates its input element <code>count</code> times
     *
     * @param count the number of duplicates to produce
     */
    public Repeater(int count) {
        this.count = count;
    }


    public List<Node> process(Element element) {
        List<Node> res = new ArrayList<Node>();
        for (int index = 0; index < count; index++) {
            res.add(copy(element));
        }
        return res;
    }

    private Element copy(Element e) {
        Element res = e.ownerDocument().createElement(e.tagName());
        for (Attribute a : e.attributes()) {
            res.attr(a.getKey(), a.getValue());
        }
        res.html(e.html());
        return res;
    }

}
