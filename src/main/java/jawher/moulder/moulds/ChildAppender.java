package jawher.moulder.moulds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import jawher.moulder.ElementAndData;
import jawher.moulder.MoulderUtils;
import jawher.moulder.NodeAndData;
import jawher.moulder.Moulder;
import jawher.moulder.Value;
import jawher.moulder.values.HtmlValue;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

public class ChildAppender implements Moulder {
	private Value<Iterable<Node>> content;

	public ChildAppender(Value<Iterable<Node>> content) {
		super();
		this.content = content;
	}

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
