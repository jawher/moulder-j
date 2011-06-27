package moulder.moulds;

import moulder.values.HtmlValue;
import moulder.*;
import org.jsoup.nodes.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * A moulder that replaces its input element with the supplied content
 * 
 * @author jawher
 * 
 */
public class Replacer implements Moulder {
	private Value<Iterable<Node>> content;

	/**
	 * 
	 * @param content
	 *            the nodes to replace the input element with
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
	 * 
	 */
	public Replacer(String html) {
		this(new HtmlValue(html));
	}

	public List<NodeAndData> process(ElementAndData nd, MoulderUtils f) {
		content.bind(nd);
		List<NodeAndData> res = new ArrayList<NodeAndData>();
		Iterable<Node> nodes = content.get();
		for (Node n : nodes) {
			res.add(new NodeAndData(n, nd.data));
		}
		return res;
	}

}
