package moulder.moulds;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import moulder.Moulder;
import moulder.moulds.helpers.JSoupHelpers;
import moulder.moulds.helpers.MouldersApplier;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

public class MySubMoulder implements Moulder {

    private final String selector;
    private final List<Moulder> body;

    public MySubMoulder(String selector, List<Moulder> body) {
        this.selector = selector;
        this.body = body;
    }

    public MySubMoulder(String selector, Moulder... body) {
        this(selector, Arrays.asList(body));
    }

    public List<Node> process(Element element) {
        Document doc = new Document("");
        final Element copy = JSoupHelpers.copy(element);
        doc.appendChild(copy);
        Elements elements = copy.select(selector);
        for (Element e: elements) {
            Collection<Node> oes = MouldersApplier.applyMoulders(body, Arrays.<Node> asList(e));
            // replace e with oes
            for (Node oe: oes) {
                e.before(oe.outerHtml());
            }

            e.remove();
        }

        return doc.childNodes();
    }
}
