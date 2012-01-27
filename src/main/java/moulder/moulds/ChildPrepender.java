package moulder.moulds;

import moulder.ElementAndData;
import moulder.Moulder;
import moulder.NodeAndData;
import moulder.Value;
import moulder.values.HtmlValue;
import org.jsoup.nodes.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A moulder that prepends content to its input element's children
 * 
 * @author jawher
 * 
 */
public class ChildPrepender implements Moulder {
	private Value<Iterable<Node>> content;

	/**
	 * 
	 * @param content
	 *            the nodes that are to be prepended to the input element's
	 *            children.
	 */
	public ChildPrepender(Value<Iterable<Node>> content) {
		super();
		this.content = content;
	}

	/**
	 * a convenience version of the {@link #ChildPrepender(moulder.Value)} constructor
	 * that parses the supplied <code>html</code> string.
	 * 
	 * @param html
	 */
	public ChildPrepender(String html) {
		this(new HtmlValue(html));
	}

	public List<NodeAndData> process(ElementAndData nd) {
		content.bind(nd);
		List<NodeAndData> res = new ArrayList<NodeAndData>();
		Iterable<Node> nodes = content.get();
		List<Node> reversed = new ArrayList<Node>();
		for (Node n : nodes) {
			reversed.add(n);
		}
		Collections.reverse(reversed);
		for (Node n : reversed) {
			nd.node.prependChild(n);
		}
		res.add(nd.toNodeAndData());
		return res;
	}

}
