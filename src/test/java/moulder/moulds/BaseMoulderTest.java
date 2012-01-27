package moulder.moulds;

import org.custommonkey.xmlunit.XMLUnit;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.junit.BeforeClass;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseMoulderTest {
    @BeforeClass
    public static void beforeClass() {
        XMLUnit.setIgnoreWhitespace(true);
    }

    public static Iterable<Node> parse(String s) {
        Document d = Jsoup.parseBodyFragment(s);
        return new ArrayList<Node>(d.body().childNodes());
    }

    public static Node parseNode(String s) {
        Document d = Jsoup.parseBodyFragment(s);
        return d.body().childNode(0);
    }

    public static String html(List<Node> nodes) {
        StringBuilder res = new StringBuilder("<body>");
        for (Node node : nodes) {
            res.append(node.outerHtml());
        }
        res.append("</body>");
        return res.toString();
    }

    public static String html2(Iterable<Node> nodes) {
        StringBuilder res = new StringBuilder("<body>");
        for (Node node : nodes) {
            res.append(node.outerHtml());
        }
        res.append("</body>");
        return res.toString();
    }
}
