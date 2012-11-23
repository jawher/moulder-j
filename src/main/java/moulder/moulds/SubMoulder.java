package moulder.moulds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import moulder.Moulder;
import moulder.MoulderChain;
import moulder.Registry;
import moulder.Registry.TemplatorConfig;
import moulder.moulds.helpers.JsoupHelper;
import moulder.moulds.helpers.MouldersApplier;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

/**
 * A moulder that can be configured to select children of its input element an
 * apply other moulders on them
 *
 * @author jawher
 */
public class SubMoulder implements Moulder {

    private final Registry registry = new Registry();

    /**
     * Registers a number of moulders to be applied to its input element's
     * children returned by the supplied selector
     *
     * @param selector to selected the input element's children to be processed
     * @param moulders the moulders to apply on the selected children
     * @return its self, so that calls to register can be chained
     */
    public SubMoulder register(String selector, Moulder... moulders) {
        return register(selector, Arrays.asList(moulders));
    }

    /**
     * Registers a number of moulders to be applied to its input element's
     * children returned by the supplied selector
     *
     * @param selector   to selected the input element's children to be processed
     * @param templators the moulders to apply on the selected children
     * @return its self, so that calls to register can be chained
     */
    public SubMoulder register(String selector, List<Moulder> templators) {
        registry.register(selector, templators);
        return this;
    }

    public MoulderChain select(String selector) {
        List<Moulder> moulders = new ArrayList<Moulder>();
        register(selector, moulders);
        return new MoulderChain(moulders);
    }

    public List<Node> process(Element element) {
        final Document doc = new Document(element.baseUri());
        final Element copy = JsoupHelper.copy(element);
        doc.appendChild(copy);

        for (TemplatorConfig c : registry.getConfig()) {
            Elements elements = copy.select(c.selector);
            for (Element e : elements) {
                Collection<Node> oes = MouldersApplier.applyMoulders(c.templators, Arrays.<Node>asList(e));
                // replace e with oes
                for (Node oe : oes) {
                    e.before(oe.outerHtml());
                }

                e.remove();
            }
        }

        return doc.childNodes();
    }

}
