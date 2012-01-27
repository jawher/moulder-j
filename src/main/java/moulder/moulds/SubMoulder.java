package moulder.moulds;

import moulder.Moulder;
import moulder.MoulderChain;
import moulder.moulds.helpers.MouldersApplier;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * A moulder that can be configured to select children of its input element an
 * apply other moulders on them
 *
 * @author jawher
 */
public class SubMoulder implements Moulder {
    private static final class TemplatorConfig {
        private String selector;
        private List<Moulder> templators;

        public TemplatorConfig(String selector, List<Moulder> templators) {
            super();
            this.selector = selector;
            this.templators = templators;
        }
    }

    private List<TemplatorConfig> cfg = new ArrayList<TemplatorConfig>();

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
        cfg.add(new TemplatorConfig(selector, templators));
        return this;
    }

    public MoulderChain select(String selector) {
        List<Moulder> moulders = new ArrayList<Moulder>();
        register(selector, moulders);
        return new MoulderChain(moulders);
    }

    public List<Node> process(Element element) {
        for (TemplatorConfig c : cfg) {
            Elements elements = element.select(c.selector);
            for (Element e : elements) {
                Collection<Node> oes = MouldersApplier.applyMoulders(c.templators, Arrays.<Node>asList(e));
                // replace e with oes
                for (Node oe : oes) {
                    e.before(oe.outerHtml());
                }

                e.remove();
            }
        }

        List<Node> res = new ArrayList<Node>();
        res.add(element);
        return res;
    }

}
