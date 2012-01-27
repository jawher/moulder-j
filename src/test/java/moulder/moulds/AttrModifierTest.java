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

public class AttrModifierTest extends BaseMoulderTest {


    @Test
    public void test() throws Exception {
        Value<String> attr = mock(Value.class);
        when(attr.get()).thenReturn("a");

        Value<String> value = mock(Value.class);
        when(value.get()).thenReturn("v");

        AttrModifier a = new AttrModifier(attr, value);
        Document document = Jsoup
                .parseBodyFragment("<html><body><outer>test</outer></body></html>");
        Element element = document.getElementsByTag("outer").first();

        List<Node> processed = a.process(element);

        // verify that bind and get were called, in this order
        InOrder inOrdera = inOrder(attr);
        inOrdera.verify(attr).get();

        InOrder inOrderv = inOrder(value);
        inOrderv.verify(value).get();

        assertXMLEqual(new StringReader(
                "<body><outer a='v'>test</outer></body>"),
                new StringReader(html(processed)));
    }


}
