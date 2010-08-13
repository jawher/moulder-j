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
 * A moulder that sets its input element text content
 * 
 * @author jawher
 * 
 */
public class Texter implements Moulder {
	private Value<?> text;

	/**
	 * 
	 * @param text
	 *            a value that returns the text to be used to set its input
	 *            element's content
	 */
	public Texter(Value<?> text) {
		this.text = text;
	}

	/**
	 * A convenience version of {@link #Texter(Value)} that wraps its argument
	 * in a {@link SimpleValue}
	 * 
	 * @param text
	 */
	public Texter(String text) {
		this(new SimpleValue<String>(text));
	}

	public List<NodeAndData> process(ElementAndData nd, MoulderUtils f) {
		text.bind(nd);
		List<NodeAndData> res = new ArrayList<NodeAndData>();
		Object t = this.text.get();
		nd.node.text(t == null ? null : t.toString());
		res.add(nd.toNodeAndData());
		return res;
	}

}
