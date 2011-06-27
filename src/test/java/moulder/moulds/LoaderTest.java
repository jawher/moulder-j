package moulder.moulds;

import moulder.ElementAndData;
import moulder.NodeAndData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.StringReader;
import java.util.List;

import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;

public class LoaderTest extends BaseMoulderTest {

    @Test
    public void test() throws Exception {
        String html = "<body><outer a='v'><a>test</a></outer></body>";
        Document document = Jsoup
                .parseBodyFragment(html);

        Loader loader = new Loader(document, "outer");

        List<NodeAndData> processed = loader.process(new ElementAndData(null, "data"), null);
        //check that the loader simply returns its input html
        assertXMLEqual(new StringReader(html), new StringReader(
                html(processed)));
    }

}
