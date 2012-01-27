package moulder.moulds;

import moulder.Moulder;
import moulder.Value;
import moulder.values.HtmlValue;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * A moulder that prepends content before its input element
 * 
 * @author jawher
 * 
 */
public class Prepender implements Moulder {
	private Value<Iterable<Node>> content;

	/**
	 * 
	 * @param content
	 *            the nodes that are to be prepended before the input element.
	 */
	public Prepender(Value<Iterable<Node>> content) {
		super();
		this.content = content;
	}

	/**
	 * a convenience version of the {@link #Prepender(moulder.Value)} constructor that
	 * parses the supplied <code>html</code> string.
	 * 
	 * @param html
	 */
	public Prepender(String html) {
		this(new HtmlValue(html));
	}

    public List<Node> process(Element element) {
        List<Node> res = new ArrayList<Node>();
		Iterable<Node> nodes = content.get();
		for (Node n : nodes) {
			res.add(n);
		}
		res.add(element);
		return res;
	}

}
