package moulder.moulds;

import moulder.Moulder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.junit.Test;

import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;

public class RepeaterTest extends BaseMoulderTest {

    @Test
    public void test() throws Exception {
        Repeater a = new Repeater(2);
        Document document = Jsoup
                .parseBodyFragment("<html><body><outer a='v'>test</outer></body></html>");
        Element element = document.getElementsByTag("outer").first();

        List<Node> processed = a.process(element);
        // check for correct repetition of html
        assertXMLEqual(new StringReader("<body>" + "<outer a='v'>test</outer><outer a='v'>test</outer>"+ "</body>"), new StringReader(
                html(processed)));
    }

    private Moulder produce(final String html1, final String html2) {
        return new Moulder() {
            public List<Node> process(Element element) {
                return Arrays.asList(parseNode(html1), parseNode(html2));
            }
        };
    }

}
