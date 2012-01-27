package moulder.moulds;

import moulder.Moulder;
import moulder.Value;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;

import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;
import static org.mockito.Mockito.*;

public class IfMoulderTest extends BaseMoulderTest {

    @Test
    public void testThen() throws Exception {
        test(true, "<body><b>text</b>text</body>");
    }

    @Test
    public void testElse() throws Exception {
        test(false, "<body><c>content</c>text</body>");
    }

    private void test(boolean res, String expected) throws Exception {
        Value<Boolean> condition = mock(Value.class);
        when(condition.get()).thenReturn(res);

        Document document = Jsoup
                .parseBodyFragment("<html><body><outer a='v'><a>test</a></outer></body></html>");
        Element element = document.getElementsByTag("outer").first();

        Moulder thenMoulder = mock(Moulder.class);
        ArgumentCaptor<Element> ifMoulderCaptor = ArgumentCaptor
                .forClass(Element.class);
        when(
                thenMoulder.process(ifMoulderCaptor.capture())).thenReturn(
                Arrays.asList(parseNode("<b>text</b>"),
                        parseNode("text")));

        Moulder elseMoulder = mock(Moulder.class);
        ArgumentCaptor<Element> elseMoulderCaptor = ArgumentCaptor
                .forClass(Element.class);
        when(
                elseMoulder.process(elseMoulderCaptor.capture())).thenReturn(
                Arrays.asList(parseNode("<c>content</c>"),
                        parseNode("text")));

        IfMoulder a = new IfMoulder(condition, thenMoulder, elseMoulder);

        List<Node> processed = a.process(element);

        // verify that bind and get were called, in this order
        InOrder inOrder = inOrder(condition);
        inOrder.verify(condition).get();

        // check that the sub moulder called if ifMoulder and returned its
        // result
        assertXMLEqual(new StringReader(expected),
                new StringReader(html(processed)));
    }

}
