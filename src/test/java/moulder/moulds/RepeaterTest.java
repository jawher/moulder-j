package moulder.moulds;

import moulder.Moulder;
import moulder.Value;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.junit.Test;
import org.mockito.InOrder;

import java.io.StringReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;
import static org.mockito.Mockito.*;

public class RepeaterTest extends BaseMoulderTest {

    @Test
    public void test() throws Exception {
        List<String> items = Arrays.asList("a", "b");
        Value<Iterable<String>> content = mock(Value.class);
        when(content.get()).thenReturn(items);

        Repeater<String> a = new Repeater<String>(content) {
            @Override
            protected Collection<Moulder> mould(String item, int index) {
                return Arrays.asList(produce("<b>"+item+"</b>", "index="+index));
            }
        };
        Document document = Jsoup
                .parseBodyFragment("<html><body><outer a='v'>test</outer></body></html>");
        Element element = document.getElementsByTag("outer").first();

        List<Node> processed = a.process(element);
        // check for correct repetition of html
        assertXMLEqual(new StringReader("<body>" + "<b>a</b>index=0<b>b</b>index=1"+ "</body>"), new StringReader(
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
