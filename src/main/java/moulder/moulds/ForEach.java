package moulder.moulds;

import static moulder.moulds.helpers.JsoupHelper.copy;
import static moulder.moulds.helpers.MouldersApplier.applyMoulders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import moulder.Moulder;
import moulder.values.SeqValue;
import moulder.values.SimpleBox;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

/**
 * A moulder, that will repeatedly invoke the body moulders for each item in the
 * seq. They can access the current item using the box.
 */
public class ForEach<T> implements Moulder {

    private final SimpleBox<T> box;
    private final SeqValue<T> seq;
    private final List<Moulder> body;

    public ForEach(SimpleBox<T> box, SeqValue<T> seq, Moulder... body) {
        this(box, seq, Arrays.asList(body));
    }

    public ForEach(SimpleBox<T> box, SeqValue<T> seq, List<Moulder> body) {
        this.box = box;
        this.seq = seq;
        this.body = body;
    }

    public List<Node> process(Element element) {
        List<Node> res = new ArrayList<Node>();
        T o;

        while ((o = seq.get()) != null) {
            box.set(o);
            res.addAll(applyMoulders(body, Arrays.<Node> asList(copy(element))));
        }

        return res;
    }
}
