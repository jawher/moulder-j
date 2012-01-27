package moulder.moulds;

import moulder.Value;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.junit.Test;
import org.mockito.InOrder;

import java.io.StringReader;
import java.util.List;

import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;
import static org.mockito.Mockito.*;

public class ChildPrependerTest extends BaseMoulderTest {


    @Test
    public void test() throws Exception {
        Value<Iterable<Node>> content = mock(Value.class);
        when(content.get()).thenReturn(parse("<e a='v'>c</e>text"));

        ChildPrepender a = new ChildPrepender(content);
        Document document = Jsoup
                .parseBodyFragment("<html><body><outer>test</outer></body></html>");
        Element element = document.getElementsByTag("outer").first();

        List<Node> processed = a.process(element);

        // verify that bind and get were called, in this order
        InOrder inOrder = inOrder(content);
        inOrder.verify(content).get();

        assertXMLEqual(new StringReader(
                "<body><outer><e a='v'>c</e>texttest</outer></body>"),
                new StringReader(html(processed)));
    }


}
