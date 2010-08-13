package jawher.moulder.moulds;

import java.util.ArrayList;
import java.util.List;

import jawher.moulder.ElementAndData;
import jawher.moulder.MoulderUtils;
import jawher.moulder.NodeAndData;
import jawher.moulder.Moulder;
import jawher.moulder.Value;
import jawher.moulder.values.SimpleValue;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

public class AttrModifier implements Moulder {
	private Value<String> attr;
	private Value<String> value;

	public AttrModifier(Value<String> attr, Value<String> value) {
		this.attr = attr;
		this.value = value;
	}

	public AttrModifier(String attr, Value<String> value) {
		this(new SimpleValue<String>(attr), value);
	}

	public AttrModifier(String attr, String value) {
		this(new SimpleValue<String>(attr), new SimpleValue<String>(value));
	}

	public List<NodeAndData> process(ElementAndData nd, MoulderUtils f) {
		attr.bind(nd);
		value.bind(nd);
		List<NodeAndData> res = new ArrayList<NodeAndData>();
		String attr = this.attr.get();
		if (attr != null) {
			String value = this.value == null ? null : this.value.get();
			if (value == null) {
				nd.node.removeAttr(attr);
			} else {
				nd.node.attr(attr, value);
			}
		}
		res.add(nd.toNodeAndData());
		return res;
	}
}
