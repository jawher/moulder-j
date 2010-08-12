package jawher.moulder.moulds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jawher.moulder.MoulderUtils;
import jawher.moulder.NodeAndData;
import jawher.moulder.Moulder;
import jawher.moulder.Value;
import jawher.moulder.values.HtmlValue;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

public class ChildPrepender implements Moulder {
	private Value<Iterable<Node>> content;

	public ChildPrepender(Value<Iterable<Node>> content) {
		super();
		this.content = content;
	}

	public ChildPrepender(String html) {
		this(new HtmlValue(html));
	}

	public List<NodeAndData<? extends Node>> process(NodeAndData<Element> nd,
			MoulderUtils f) {
		content.bind(nd);
		List<NodeAndData<? extends Node>> res = new ArrayList<NodeAndData<? extends Node>>();
		Iterable<Node> nodes = content.get();
		List<Node> reversed = new ArrayList<Node>();
		for(Node n: nodes){
			reversed.add(n);
		}
		Collections.reverse(reversed);
		for (Node n : reversed) {
			nd.node.prependChild(n);
		}
		res.add(nd);
		return res;
	}

}
