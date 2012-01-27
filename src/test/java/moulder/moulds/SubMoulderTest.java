package moulder.moulds;

import moulder.Moulder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SubMoulderTest extends BaseMoulderTest {

    @Test
    public void test() throws Exception {
        Document document = Jsoup
                .parseBodyFragment("<html><body><outer a='v'><a>test</a></outer></body></html>");

        Element element = document.getElementsByTag("outer").first();

        Element subElement = document.getElementsByTag("a").first();

        Moulder moulder = mock(Moulder.class);
        ArgumentCaptor<Element> edc = ArgumentCaptor
                .forClass(Element.class);
        when(moulder.process(edc.capture()))
                .thenReturn(
                        Arrays.asList(parseNode("<b>text</b>"), parseNode("text")));

        SubMoulder sm = new SubMoulder();
        sm.register("a", moulder);

        List<Node> processed = sm.process(element);

        //check that the sub moulder called its registered moulder with the correct params: the child element <a> and "data"
        assertEquals(subElement, edc.getValue());
        System.out.println(html(processed));
        //check that the sub moulder correctly applied its registered moulder result
        assertXMLEqual(new StringReader("<body><outer a='v'><b>text</b>text</outer></body>"), new StringReader(
                html(processed)));
    }

}
