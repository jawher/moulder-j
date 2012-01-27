package moulder.moulds;

import moulder.Moulder;
import moulder.Value;
import moulder.values.SimpleValue;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import java.util.List;

/**
 * A moulder that depending on a boolean value will invoke one of two other
 * moulder
 * 
 * @author jawher
 * 
 */
public class IfMoulder implements Moulder {
	private Value<Boolean> condition;
	private Moulder thenMoulder;
	private Moulder elseMoulder;

	/**
	 * 
	 * @param condition
	 *            A value that returns a boolean value. <code>thenMoulder</code>
	 *            will be used to process the input element if
	 *            <code>condition</code> evaluates to true, else
	 *            <code>elseMoulder</code>
	 * @param thenMoulder
	 * @param elseMoulder
	 */
	public IfMoulder(Value<Boolean> condition, Moulder thenMoulder,
			Moulder elseMoulder) {
		super();
		this.condition = condition;
		this.thenMoulder = thenMoulder;
		this.elseMoulder = elseMoulder;
	}

	/**
	 * A variant of {@link #IfMoulder(moulder.Value, Moulder, Moulder)} that wraps the
	 * <code>condition</code> argument in a {@link SimpleValue}
	 * 
	 * @param condition
	 * @param thenMoulder
	 * @param elseMoulder
	 */
	public IfMoulder(boolean condition, Moulder thenMoulder, Moulder elseMoulder) {
		super();
		this.condition = new SimpleValue<Boolean>(condition);
		this.thenMoulder = thenMoulder;
		this.elseMoulder = elseMoulder;
	}

    public List<Node> process(Element element) {
		if (condition.get()) {
			return thenMoulder.process(element);
		} else {
			return elseMoulder.process(element);
		}
	}

}
