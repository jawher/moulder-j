package jawher.moulder.moulds;

import java.util.ArrayList;
import java.util.List;

import jawher.moulder.MoulderUtils;
import jawher.moulder.NodeAndData;
import jawher.moulder.Moulder;
import jawher.moulder.Value;
import jawher.moulder.values.HtmlValue;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

public class Appender implements Moulder {
	private Value<Iterable<Node>> content;

	public Appender(Value<Iterable<Node>> content) {
		super();
		this.content = content;
	}

	public Appender(String html) {
		this(new HtmlValue(html));
	}

	public List<NodeAndData<? extends Node>> process(NodeAndData<Element> nd,
			MoulderUtils f) {
		content.bind(nd);
		List<NodeAndData<? extends Node>> res = new ArrayList<NodeAndData<? extends Node>>();
		res.add(nd);
		Iterable<Node> nodes = content.get();
		for (Node n : nodes) {
			res.add(new NodeAndData<Node>(n));
		}
		return res;
	}

}
