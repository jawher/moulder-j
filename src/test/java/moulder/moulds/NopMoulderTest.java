package moulder.moulds;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.junit.Test;

import java.io.StringReader;
import java.util.List;

import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;

public class NopMoulderTest extends BaseMoulderTest {


    @Test
    public void test() throws Exception {
        NopMoulder a = new NopMoulder();
        Document document = Jsoup
                .parseBodyFragment("<html><body><outer>test</outer></body></html>");
        Element element = document.getElementsByTag("outer").first();

        List<Node> processed = a.process(element);
        assertXMLEqual(new StringReader(
                "<body><outer>test</outer></body>"),
                new StringReader(html(processed)));
    }


}
