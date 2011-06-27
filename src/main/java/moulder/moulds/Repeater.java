package moulder.moulds;

import moulder.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A moulder that repeats its input element
 * 
 * @author jawher
 * 
 * @param <T>
 *            the type of the data to be associated with the repeated elements
 */
public class Repeater<T> implements Moulder {
	private Value<? extends Iterable<T>> items;

	/**
	 * Creates a moulder that for evey item <code>e</code> in the
	 * {@link Iterable} returned by the supplied value, generates a copy of its
	 * input element with e as its associated data
	 * 
	 * @param items
	 */
	public Repeater(Value<? extends Iterable<T>> items) {
		super();
		this.items = items;
	}

	public List<NodeAndData> process(ElementAndData nd, MoulderUtils f) {
		items.bind(nd);
		Iterator<T> it = items.get().iterator();
		List<NodeAndData> res = new ArrayList<NodeAndData>();
		while (it.hasNext()) {
			res.add(new NodeAndData(f.copy(nd.node), it.next()));
		}
		return res;
	}

}
