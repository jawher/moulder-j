package jawher.moulder.moulds;

import jawher.moulder.ElementAndData;
import jawher.moulder.Moulder;
import jawher.moulder.MoulderUtils;
import jawher.moulder.NodeAndData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A moulder that replaces its input element with another document's content after
 * processing it
 *
 * @author jawher
 */
public class Loader implements Moulder {
    private final String selector;
    private final Document document;
    private final SubMoulder subMoulder;

    /**
     * @param document the document to load, process and inject
     * @param selector a selector pointing to the children to extract from the loaded document
     *                 and inject into the original document
     */
    public Loader(Document document, String selector) {
        this.selector = selector;
        this.document = document;
        this.subMoulder = new SubMoulder();
    }

    /**
     * Creates a Filterer that replaces its input element with any of its children
     * conforming to a selector
     *
     * @param stream   the input stream of the document to load, process and inject
     * @param selector a selector pointing to the children to extract from the loaded document
     *                 and inject into the original document
     */
    public Loader(InputStream stream, String selector) {
        try {
            this.document = Jsoup.parse(stream, null, "#");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.selector = selector;
        this.subMoulder = new SubMoulder();
    }

    /**
     * Registers a number of moulders to be applied to its input element's
     * children returned by the supplied selector
     *
     * @param selector to selected the input element's children to be processed
     * @param moulders the moulders to apply on the selected children
     * @return its self, so that calls to register can be chained
     */
    public Loader register(String selector, Moulder... moulders) {
        return register(selector, Arrays.asList(moulders));
    }

    /**
     * Registers a number of moulders to be applied to its input element's
     * children returned by the supplied selector
     *
     * @param selector to selected the input element's children to be processed
     * @param moulders the moulders to apply on the selected children
     * @return its self, so that calls to register can be chained
     */
    public Loader register(String selector, List<Moulder> moulders) {
        subMoulder.register(selector, moulders);
        return this;
    }


    public List<NodeAndData> process(ElementAndData nd, MoulderUtils f) {
        MoulderUtils factory = new MoulderUtils(document);

        subMoulder.process(new ElementAndData(document, nd.data), factory);

        List<NodeAndData> res = new ArrayList<NodeAndData>();
        List<Element> toInject = document.select(selector);
        for (Element e : toInject) {
            res.add(new NodeAndData(e, nd.data));
        }
        return res;
    }

}
