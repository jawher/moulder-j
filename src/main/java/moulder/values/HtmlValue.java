package moulder.values;

import moulder.ElementAndData;
import moulder.Value;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * A value that parses a supplied html string
 * 
 * @author jawher
 * 
 */
public class HtmlValue implements Value<Iterable<Node>> {
	private Value<String> html;

	public HtmlValue(String html) {
		this(new SimpleValue<String>(html));
	}

	public HtmlValue(Value<String> html) {
		super();
		this.html = html;
	}

	public Iterable<Node> get() {
		List<Node> nodes = Jsoup.parseBodyFragment(html.get()).body()
				.childNodes();
		List<Node> copy = new ArrayList<Node>(nodes);
		return copy;
	}

	public void bind(ElementAndData nd) {
		html.bind(nd);
	}

}
