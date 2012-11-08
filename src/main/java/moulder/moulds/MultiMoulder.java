package moulder.moulds;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import moulder.Moulder;
import moulder.moulds.helpers.MouldersApplier;

public class MultiMoulder implements Moulder {

    private final List<Moulder> moulders;

    public MultiMoulder(Moulder... moulders) {
        this.moulders = Arrays.asList(moulders);
    }

    public List<Node> process(Element element) {
        final Collection<Node> oes = MouldersApplier.applyMoulders(moulders, Arrays.<Node> asList(element));
        // replace e with oes
        for (Node oe: oes) {
            element.before(oe.outerHtml());
        }

        element.remove();

        return Arrays.<Node> asList(element);
    }
}
