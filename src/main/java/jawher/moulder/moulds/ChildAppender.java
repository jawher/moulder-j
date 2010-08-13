package jawher.moulder.moulds;

import java.util.ArrayList;
import java.util.List;

import jawher.moulder.ElementAndData;
import jawher.moulder.Moulder;
import jawher.moulder.MoulderUtils;
import jawher.moulder.NodeAndData;
import jawher.moulder.Value;
import jawher.moulder.values.HtmlValue;

import org.jsoup.nodes.Node;

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
	 * a convenience version of the {@link #ChildAppender(Value)} constructor
	 * that parses the supplied <code>html</code> string.
	 * 
	 * @param html
	 */
	public ChildAppender(String html) {
		this(new HtmlValue(html));
	}

	public List<NodeAndData> process(ElementAndData nd, MoulderUtils f) {
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
