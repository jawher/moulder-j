package moulder.values;

import moulder.ElementAndData;
import moulder.Value;

import java.util.*;

/**
 * A value that sequentially generates the items it was supplied with
 * 
 * @author jawher
 * 
 * @param <T>
 *            the resulting values type
 */
public class SeqValue<T> implements Value<T> {
	private Iterator<T> values;

	public SeqValue(Iterator<T> values) {
		super();
		this.values = values;
	}

	public SeqValue(Iterable<T> values) {
		this.values = values.iterator();
	}

	public SeqValue(T... values) {
		this.values = Arrays.asList(values).iterator();
	}

	public T get() {
		return values.hasNext() ? values.next() : null;
	}

	public SeqValue<T> cycle() {
		return new SeqValue<T>(new CyclingIterator<T>(values));
	}

	public void bind(ElementAndData nd) {

	}

	private static final class CyclingIterator<S> implements Iterator<S> {
		private Iterator<S> it;
		private final boolean itHasValues;
		private final List<S> values = new ArrayList<S>();
		private boolean cycled = false;

		public CyclingIterator(Iterator<S> it) {
			this.it = it;
			itHasValues = it.hasNext();
		}

		public boolean hasNext() {
			return itHasValues;
		}

		public S next() {
			if (!itHasValues) {
				throw new NoSuchElementException();
			}
			if (it.hasNext()) {
				S res = it.next();
				if (!cycled) {
					values.add(res);
				}
				return res;
			} else {
				cycled = true;
				it = values.iterator();
				return next();
			}
		}

		public void remove() {

		}

	}

	public SeqValue<T> repeatLast() {
		return new SeqValue<T>(new RepeatLastIterator<T>(values));
	}

	private static final class RepeatLastIterator<S> implements Iterator<S> {
		private Iterator<S> it;
		private final boolean itHasValues;
		private S last;

		public RepeatLastIterator(Iterator<S> it) {
			this.it = it;
			itHasValues = it.hasNext();
		}

		public boolean hasNext() {
			return itHasValues;
		}

		public S next() {
			if (!itHasValues) {
				throw new NoSuchElementException();
			}
			if (it.hasNext()) {
				last = it.next();
			}
			return last;
		}

		public void remove() {

		}

	}
}
