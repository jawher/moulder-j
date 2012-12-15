package moulder.moulds;

import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;

import java.io.StringReader;
import java.util.List;

import moulder.Moulder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.junit.Test;

public class AddCssClassMoulderTest extends BaseMoulderTest {

    @Test
    public void noClass() throws Exception {

        final String bodyHtml = "<tag>text</tag>";
        final String cssClass = "ss";
        final String expected = "<tag class=\"ss\">text\n</tag>";

        test(bodyHtml, cssClass, expected);
    }

    @Test
    public void withSameClass() throws Exception {

        final String bodyHtml = "<tag class=\"ss\">text</tag>";
        final String cssClass = "ss";
        final String expected = "<tag class=\"ss\">text\n</tag>";

        test(bodyHtml, cssClass, expected);
    }

    @Test
    public void withSimilarClass() throws Exception {

        final String bodyHtml = "<tag class=\"sss ss1\">text</tag>";
        final String cssClass = "ss";
        final String expected = "<tag class=\"sss ss1 ss\">text\n</tag>";

        test(bodyHtml, cssClass, expected);
    }

    private void test(String bodyHtml, String cssClass, String expected) throws Exception {
        Document document = Jsoup.parseBodyFragment(bodyHtml);
        Element element = document.getElementsByTag("tag").first();

        Moulder moulder = new AddCssClassMoulder(cssClass);
        List<Node> processed = moulder.process(element);

        assertXMLEqual(new StringReader("<body>" + expected + "</body>"),
                new StringReader(html(processed)));
    }
}
