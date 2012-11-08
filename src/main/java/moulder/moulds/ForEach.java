package moulder.moulds;

import static moulder.moulds.helpers.JSoupHelpers.copy;

import java.util.ArrayList;
import java.util.List;

import moulder.Moulder;
import moulder.values.SeqValue;
import moulder.values.SimpleValue;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

public class ForEach<T> implements Moulder {

    private final SimpleValue<T> val;
    private final SeqValue<T> seq;
    private final Moulder body;

    public ForEach(SimpleValue<T> val, SeqValue<T> seq, Moulder body) {
        this.val = val;
        this.seq = seq;
        this.body = body;
    }

    public List<Node> process(Element element) {
        final Document shell = Document.createShell(element.ownerDocument().baseUri());
        List<Node> res = new ArrayList<Node>();
        T o;

        while ((o = seq.get()) != null) {
            val.set(o);
            final Element copy = copy(element);
            shell.appendChild(copy);
            res.addAll(body.process(copy));
        }

        return res;
    }
}
