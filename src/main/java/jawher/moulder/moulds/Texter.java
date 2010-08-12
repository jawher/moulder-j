package jawher.moulder.moulds;

import java.util.ArrayList;
import java.util.List;

import jawher.moulder.MoulderUtils;
import jawher.moulder.NodeAndData;
import jawher.moulder.Moulder;
import jawher.moulder.Value;
import jawher.moulder.values.SimpleValue;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

public class Texter implements Moulder {
	private Value<String> text;

	public Texter(String text) {
		this(new SimpleValue<String>(text));
	}

	public Texter(Value<String> text) {
		this.text = text;
	}

	public List<NodeAndData<? extends Node>> process(NodeAndData<Element> nd,
			MoulderUtils f) {
		text.bind(nd);
		List<NodeAndData<? extends Node>> res = new ArrayList<NodeAndData<? extends Node>>();
		String text = this.text.get();
		nd.node.text(text);
		res.add(nd);
		return res;
	}

}
