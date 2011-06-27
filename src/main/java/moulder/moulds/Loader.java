package moulder.moulds;

import moulder.ElementAndData;
import moulder.Moulder;
import moulder.MoulderUtils;
import moulder.NodeAndData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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

    /**
     * @param document the document to load, process and inject
     * @param selector a selector pointing to the children to extract from the loaded document
     *                 and inject into the original document
     */
    public Loader(Document document, String selector) {
        this.selector = selector;
        this.document = document;
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
    }


    public List<NodeAndData> process(ElementAndData nd, MoulderUtils f) {
        List<NodeAndData> res = new ArrayList<NodeAndData>();
        List<Element> toInject = document.select(selector);
        for (Element e : toInject) {
            res.add(new NodeAndData(e, nd.data));
        }
        return res;
    }

}
