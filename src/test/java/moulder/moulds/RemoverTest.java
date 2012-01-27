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

public class RemoverTest extends BaseMoulderTest {


    @Test
    public void test() throws Exception {
        Remover a = new Remover();
        Document document = Jsoup
                .parseBodyFragment("<html><body><outer a='v'>test</outer>text</body></html>");
        Element element = document.getElementsByTag("outer").first();

        List<Node> processed = a.process(element);

        assertXMLEqual(new StringReader(
                "<body></body>"),
                new StringReader(html(processed)));
    }


    @Test
    public void testWithValue() throws Exception {
        Value<Boolean> visibility = mock(Value.class);
        when(visibility.get()).thenReturn(Boolean.TRUE);

        Remover a = new Remover(visibility);
        Document document = Jsoup
                .parseBodyFragment("<html><body><outer a='v'>test</outer>text</body></html>");
        Element element = document.getElementsByTag("outer").first();

        List<Node> processed = a.process(element);

        // verify that bind and get were called, in this order
        InOrder inOrder = inOrder(visibility);
        inOrder.verify(visibility).get();

        assertXMLEqual(new StringReader(
                "<body></body>"),
                new StringReader(html(processed)));
    }

}
