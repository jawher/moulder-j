package moulder.moulds;

import moulder.Moulder;
import moulder.Value;
import moulder.values.HtmlValue;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * A moulder that appends content to its input element's children
 * 
 * @author jawher
 * 
 */
public class ChildAppender implements Moulder {
	private Value<Iterable<Node>> content;

	/**
	 * 
	 * @param content
	 *            the nodes that are to be appended to the input element's
	 *            children.
	 */
	public ChildAppender(Value<Iterable<Node>> content) {
		super();
		this.content = content;
	}

	/**
	 * a convenience version of the {@link #ChildAppender(moulder.Value)} constructor
	 * that parses the supplied <code>html</code> string.
	 * 
	 * @param html
	 */
	public ChildAppender(String html) {
		this(new HtmlValue(html));
	}

    public List<Node> process(Element element) {
		List<Node> res = new ArrayList<Node>();
		Iterable<Node> nodes = content.get();
		for (Node n : nodes) {
			element.appendChild(n);
		}
		res.add(element);
		return res;
	}

}
