package moulder.moulds;

import moulder.Moulder;
import moulder.Value;
import moulder.values.SimpleValue;
import org.jsoup.nodes.Node;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * A class with a bunch of static factory methods to common moulders
 *
 * @author jawher
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

    public static Repeater repeat(int count) {
        return new Repeater(count);
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

    public static NopMoulder nop() {
        return new NopMoulder();
    }

    public static IfMoulder ifm(Value<Boolean> condition, Moulder thenMoulder, Moulder elseMoulder) {
        return new IfMoulder(condition, thenMoulder, elseMoulder);
    }

    public static IfMoulder ifm(boolean condition, Moulder thenMoulder, Moulder elseMoulder) {
        return new IfMoulder(condition, thenMoulder, elseMoulder);
    }

    public static SubMoulder sub(String selector, Moulder... moulders) {
        return new SubMoulder().register(selector, moulders);
    }

    public static SubMoulder sub(String selector, List<Moulder> moulders) {
        return new SubMoulder().register(selector, moulders);
    }

    public static Filterer filter(String selector) {
        return new Filterer(selector);
    }

}
