package moulder.moulds;

import moulder.ElementAndData;
import moulder.Moulder;
import moulder.NodeAndData;
import moulder.Value;
import moulder.values.HtmlValue;
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

	public List<NodeAndData> process(ElementAndData nd) {
		content.bind(nd);
		List<NodeAndData> res = new ArrayList<NodeAndData>();
		Iterable<Node> nodes = content.get();
		for (Node n : nodes) {
			nd.node.appendChild(n);
		}
		res.add(nd.toNodeAndData());
		return res;
	}

}
