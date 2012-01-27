package moulder.moulds;

import moulder.Moulder;
import moulder.Value;
import moulder.moulds.helpers.MouldersApplier;
import moulder.values.SimpleValue;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import java.util.*;

/**
 * A moulder that repeats its input element
 * 
 * @author jawher
 * 
 * @param <T>
 *            the type of the data to be associated with the repeated elements
 */
public abstract class Repeater<T> implements Moulder {
	private Value<? extends Iterable<T>> items;

	/**
	 * Creates a moulder that for evey item <code>e</code> in the
	 * {@link Iterable} returned by the supplied value, generates a copy of its
	 * input element with e as its associated data
	 * 
	 * @param items
	 */
	public Repeater(Value<? extends Iterable<T>> items) {
		this.items = items;
	}

    /**
     * Creates a moulder that for evey item <code>e</code> in the
     * {@link Iterable} returned by the supplied value, generates a copy of its
     * input element with e as its associated data
     *
     * @param items
     */
    public Repeater(Iterable<T> items) {
        this.items = new SimpleValue<Iterable<T>>(items);
    }
    
    protected abstract Collection<Moulder> mould(T item, int index);

    public List<Node> process(Element element) {
        List<Node> res = new ArrayList<Node>();
		Iterator<T> it = items.get().iterator();
        int index = 0;
		while (it.hasNext()) {
            T item = it.next();
            Collection<Moulder> moulds = mould(item, index);
            res.addAll(MouldersApplier.applyMoulders(moulds, Arrays.<Node>asList(copy(element))));
            index++;
		}
		return res;
	}
    
    private Element copy(Element e) {
        Element res = e.ownerDocument().createElement(e.tagName());
        for(Attribute a:e.attributes()){
            res.attr(a.getKey(), a.getValue());
        }
        res.html(e.html());
        return res;
    }

}
