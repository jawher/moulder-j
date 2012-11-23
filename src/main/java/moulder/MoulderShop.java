package moulder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import moulder.Registry.TemplatorConfig;
import moulder.moulds.helpers.MouldersApplier;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

public class MoulderShop {

    private final Registry registry = new Registry();

    public MoulderChain select(String selector) {
        List<Moulder> moulders = new ArrayList<Moulder>();
        registry.register(selector, moulders);
        return new MoulderChain(moulders);
    }

    public MoulderShop register(String selector, Moulder... moulders) {
        return register(selector, Arrays.asList(moulders));
    }

    public MoulderShop register(String selector, List<Moulder> templators) {
        registry.register(selector, templators);
        return this;
    }

    public void process(Document doc) {
        for (TemplatorConfig c : registry.getConfig()) {
            Elements elements = doc.select(c.selector);
            for (Element e : elements) {
                Collection<Node> oes = MouldersApplier.applyMoulders(c.templators, Arrays.<Node>asList(e));
                // replace e with oes
                for (Node oe : oes) {
                    e.before(oe.outerHtml());
                }

                e.remove();
            }
        }
    }

    public Document process(InputStream stream) {
        Document doc;
        try {
            doc = Jsoup.parse(stream, null, "#");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        process(doc);
        return doc;
    }

}
