package moulder.moulds;

import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

import moulder.ElementAndData;
import moulder.Moulder;
import moulder.MoulderUtils;

import moulder.NodeAndData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

public class SubMoulderTest extends BaseMoulderTest {

    @Test
    public void test() throws Exception {
        Document document = Jsoup
                .parseBodyFragment("<html><body><outer a='v'><a>test</a></outer></body></html>");
        MoulderUtils mu = new MoulderUtils(document);

        Element element = document.getElementsByTag("outer").first();
        ElementAndData nd = new ElementAndData(element, "data");

        Element subElement = document.getElementsByTag("a").first();
        ElementAndData subNd = new ElementAndData(subElement, "data");

        Moulder moulder = mock(Moulder.class);
        ArgumentCaptor<ElementAndData> edc = ArgumentCaptor
                .forClass(ElementAndData.class);
        when(moulder.process(edc.capture(), any(MoulderUtils.class)))
                .thenReturn(
                        Arrays.asList(new NodeAndData(parseNode("<b>text</b>")), new NodeAndData(parseNode("text"))));

        SubMoulder sm = new SubMoulder();
        sm.register("a", moulder);

        List<NodeAndData> processed = sm.process(nd, mu);

        //check that the sub moulder called its registered moulder with the correct params: the child element <a> and "data"
        assertEquals(subNd, edc.getValue());
        System.out.println(html(processed));
        //check that the sub moulder correctly applied its registered moulder result
        assertXMLEqual(new StringReader("<body><outer a='v'><b>text</b>text</outer></body>"), new StringReader(
                html(processed)));
    }

}
