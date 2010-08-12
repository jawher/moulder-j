package jawher.moulder.moulds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jawher.moulder.MoulderUtils;
import jawher.moulder.NodeAndData;
import jawher.moulder.Moulder;
import jawher.moulder.Value;
import jawher.moulder.values.SimpleValue;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

public class Remover implements Moulder {
	private Value<Boolean> remove;

	public Remover() {
		this(new SimpleValue<Boolean>(true));
	}

	public Remover(Value<Boolean> remove) {
		super();
		this.remove = remove;
	}

	public List<NodeAndData<? extends Node>> process(NodeAndData<Element> nd,
			MoulderUtils f) {
		remove.bind(nd);
		if (remove.get()) {
			return Collections.EMPTY_LIST;
		} else {
			List<NodeAndData<? extends Node>> res = new ArrayList<NodeAndData<? extends Node>>();
			res.add(nd);
			return res;
		}
	}

}
