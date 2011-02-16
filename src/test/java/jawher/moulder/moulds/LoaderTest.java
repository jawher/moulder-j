package jawher.moulder.moulds;

import jawher.moulder.ElementAndData;
import jawher.moulder.Moulder;
import jawher.moulder.MoulderUtils;
import jawher.moulder.NodeAndData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoaderTest extends BaseMoulderTest {

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

        Loader loader = new Loader(document, "outer");
        loader.register("a", moulder);

        List<NodeAndData> processed = loader.process(nd, mu);

        //check that the loader called its registered moulder with the correct params: the child element <a> and "data"
        assertEquals(subNd, edc.getValue());

        //check that the loader correctly applied its registered moulder result
        assertXMLEqual(new StringReader("<body><outer a='v'><b>text</b>text</outer></body>"), new StringReader(
                html(processed)));
    }

}
