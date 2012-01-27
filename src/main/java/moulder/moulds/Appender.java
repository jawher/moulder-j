package moulder.moulds;

import moulder.Moulder;
import moulder.Value;
import moulder.values.HtmlValue;
import org.jsoup.nodes.Element;
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

	public List<Node> process(Element element) {
		List<Node> res = new ArrayList<Node>();
		res.add(element);
		Iterable<Node> nodes = content.get();
		for (Node n : nodes) {
			res.add(n);
		}
		return res;
	}

}
