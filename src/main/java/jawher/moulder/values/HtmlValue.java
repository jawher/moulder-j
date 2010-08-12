package jawher.moulder.values;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jawher.moulder.NodeAndData;
import jawher.moulder.Value;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

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

	public void bind(NodeAndData<Element> nd) {
		html.bind(nd);
	}

}
