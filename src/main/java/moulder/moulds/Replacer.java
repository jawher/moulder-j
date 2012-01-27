package moulder.moulds;

import moulder.Moulder;
import moulder.Value;
import moulder.values.HtmlValue;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * A moulder that replaces its input element with the supplied content
 *
 * @author jawher
 */
public class Replacer implements Moulder {
    private Value<Iterable<Node>> content;

    /**
     * @param content the nodes to replace the input element with
     */
    public Replacer(Value<Iterable<Node>> content) {
        super();
        this.content = content;
    }

    /**
     * a convenience version of the {@link #Replacer(moulder.Value)} constructor that
     * parses the supplied <code>html</code> string.
     *
     * @param html
     */
    public Replacer(String html) {
        this(new HtmlValue(html));
    }

    public List<Node> process(Element element) {
        List<Node> res = new ArrayList<Node>();
        Iterable<Node> nodes = content.get();
        for (Node n : nodes) {
            res.add(n);
        }
        return res;
    }

}
