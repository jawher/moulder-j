package jawher.moulder.moulds;

import jawher.moulder.Value;
import jawher.moulder.values.SimpleValue;

import org.jsoup.nodes.Node;

/**
 * A class with a bunch of static factory methods to common moulders
 * 
 * @author jawher
 * 
 */
public class Moulds {
	public static Appender append(Value<Iterable<Node>> html) {
		return new Appender(html);
	}

	public static Appender append(String html) {
		return new Appender(html);
	}

	public static Prepender prepend(Value<Iterable<Node>> html) {
		return new Prepender(html);
	}

	public static Prepender prepend(String html) {
		return new Prepender(html);
	}
	
	public static Replacer replace(Value<Iterable<Node>> html) {
		return new Replacer(html);
	}

	public static Replacer replace(String html) {
		return new Replacer(html);
	}

	public static ChildAppender appendToChildren(Value<Iterable<Node>> html) {
		return new ChildAppender(html);
	}

	public static ChildAppender appendToChildren(String html) {
		return new ChildAppender(html);
	}

	public static ChildAppender prependToChildren(Value<Iterable<Node>> html) {
		return new ChildAppender(html);
	}

	public static ChildPrepender prependToChildren(String html) {
		return new ChildPrepender(html);
	}

	public static Texter text(String text) {
		return new Texter(text);
	}

	public static Texter text(Value<String> text) {
		return new Texter(text);
	}

	public static <T> Repeater<T> repeat(Iterable<T> items) {
		return new Repeater<T>(new SimpleValue<Iterable<T>>(items));
	}

	public static <T> Repeater<T> repeat(Value<Iterable<T>> items) {
		return new Repeater<T>(items);
	}

	public static Remover remove() {
		return new Remover();
	}

	public static Remover remove(Value<Boolean> remove) {
		return new Remover(remove);
	}

	public static AttrModifier attr(String attr, String value) {
		return new AttrModifier(attr, value);
	}

	public static AttrModifier attr(String attr, Value<String> value) {
		return new AttrModifier(attr, value);
	}

	public static AttrModifier attr(Value<String> attr, Value<String> value) {
		return new AttrModifier(attr, value);
	}
}
