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

	public List<NodeAndData> process(ElementAndData nd) {
		content.bind(nd);
		List<NodeAndData> res = new ArrayList<NodeAndData>();
		Iterable<Node> nodes = content.get();
		for (Node n : nodes) {
			res.add(new NodeAndData(n, nd.data));
		}
		res.add(nd.toNodeAndData());
		return res;
	}

}
