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

public class Prepender implements Moulder {
	private Value<Iterable<Node>> content;

	public Prepender(Value<Iterable<Node>> content) {
		super();
		this.content = content;
	}

	public Prepender(String html) {
		this(new HtmlValue(html));
	}

	public List<NodeAndData<? extends Node>> process(NodeAndData<Element> nd,
			MoulderUtils f) {
		content.bind(nd);
		List<NodeAndData<? extends Node>> res = new ArrayList<NodeAndData<? extends Node>>();
		Iterable<Node> nodes = content.get();
		for (Node n : nodes) {
			res.add(new NodeAndData<Node>(n));
		}
		res.add(nd);
		return res;
	}

}
