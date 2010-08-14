package jawher.moulder.moulds;

import java.util.ArrayList;
import java.util.List;

import jawher.moulder.ElementAndData;
import jawher.moulder.Moulder;
import jawher.moulder.MoulderUtils;
import jawher.moulder.NodeAndData;
import jawher.moulder.Value;
import jawher.moulder.values.SimpleValue;

/**
 * A moulder that adds/modifies/removes an attribute to its input element
 * 
 * @author jawher
 * 
 */
public class AttrModifier implements Moulder {
	private Value<String> attr;
	private Value<String> value;

	/**
	 * 
	 * @param attr
	 *            a value that returns the attribute name
	 * @param value
	 *            a value that returns the attribute's value. If null is
	 *            supplied, or if <code>value</code> generates a null value, the
	 *            attribute is removed from the input element
	 */
	public AttrModifier(Value<String> attr, Value<String> value) {
		this.attr = attr;
		this.value = value;
	}

	/**
	 * A variant of {@link #AttrModifier(Value, Value)} that wraps the
	 * <code>attr</code> argument in a {@link SimpleValue}
	 * 
	 * @param attr
	 * @param value
	 */
	public AttrModifier(String attr, Value<String> value) {
		this(new SimpleValue<String>(attr), value);
	}

	/**
	 * A variant of {@link #AttrModifier(Value, Value)} that wraps the
	 * <code>attr</code> and <code>value</code> arguments in {@link SimpleValue}
	 * 
	 * @param attr
	 * @param value
	 */
	public AttrModifier(String attr, String value) {
		this(new SimpleValue<String>(attr), new SimpleValue<String>(value));
	}

	public List<NodeAndData> process(ElementAndData nd, MoulderUtils f) {
		attr.bind(nd);
		if (value != null) {
			value.bind(nd);
		}
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
