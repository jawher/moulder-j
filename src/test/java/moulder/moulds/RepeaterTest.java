package moulder.moulds;

import moulder.Value;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.junit.Test;
import org.mockito.InOrder;

import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;
import static org.mockito.Mockito.*;

public class RepeaterTest extends BaseMoulderTest {

    @Test
    public void test() throws Exception {
        List<String> items = Arrays.asList("Summer", "Autumn", "Winter",
                "Spring");
        Value<Iterable<String>> content = mock(Value.class);
        when(content.get()).thenReturn(items);

        Repeater<String> a = new Repeater<String>(content);
        Document document = Jsoup
                .parseBodyFragment("<html><body><outer a='v'>test</outer></body></html>");
        Element element = document.getElementsByTag("outer").first();

        List<Node> processed = a.process(element);

        // check for correct repetition of html
        assertXMLEqual(new StringReader("<body>" + "<outer a='v'>test</outer>"
                + "<outer a='v'>test</outer>" + "<outer a='v'>test</outer>"
                + "<outer a='v'>test</outer>" + "</body>"), new StringReader(
                html(processed)));
    }

}
