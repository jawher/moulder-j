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
 * A moulder that appends content to its input element
 * 
 * @author jawher
 * 
 */
public class Appender implements Moulder {
	private Value<Iterable<Node>> content;

	/**
	 * 
	 * @param content
	 *            the nodes that are to be appended after the input element.
	 */
	public Appender(Value<Iterable<Node>> content) {
		super();
		this.content = content;
	}

	/**
	 * a convenience version of the {@link #Appender(moulder.Value)} constructor that
	 * parses the supplied <code>html</code> string.
	 * 
	 * @param html
	 * 
	 */
	public Appender(String html) {
		this(new HtmlValue(html));
	}

	public List<NodeAndData> process(ElementAndData nd) {
		content.bind(nd);
		List<NodeAndData> res = new ArrayList<NodeAndData>();
		res.add(nd.toNodeAndData());
		Iterable<Node> nodes = content.get();
		for (Node n : nodes) {
			res.add(new NodeAndData(n, nd.data));
		}
		return res;
	}

}
