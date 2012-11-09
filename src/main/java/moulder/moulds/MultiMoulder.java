package moulder.moulds;

import java.util.Arrays;
import java.util.List;

import moulder.Moulder;
import moulder.moulds.helpers.MouldersApplier;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

public class MultiMoulder implements Moulder {

    private final List<Moulder> moulders;

    public MultiMoulder(Moulder... moulders) {
        this.moulders = Arrays.asList(moulders);
    }

    public List<Node> process(Element element) {
        return MouldersApplier.applyMoulders(moulders, Arrays.<Node> asList(element));
    }
}
