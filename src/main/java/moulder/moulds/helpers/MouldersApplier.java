package moulder.moulds.helpers;

import moulder.Moulder;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MouldersApplier {
    public static Collection<Node> applyMoulder(Moulder moulder, Collection<Node> nodes) {
        List<Node> res = new ArrayList<Node>();
        for(Node node: nodes) {
            if(node instanceof Element) {
                res.addAll(moulder.process((Element) node));
            } else {
                res.add(node);
            }
        }
        return res;
    }
    public static Collection<Node> applyMoulders(Collection<Moulder> moulders, Collection<Node> nodes) {
        if(moulders.isEmpty()) {
            return nodes;
        } else {
            Collection<Node> workingSet = new ArrayList<Node>(nodes);
            for(Moulder moulder: moulders) {
                workingSet = applyMoulder(moulder, workingSet);
            }
            return workingSet;
        }
    }
}
