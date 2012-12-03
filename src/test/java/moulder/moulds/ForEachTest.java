package moulder.moulds;

import moulder.Moulder;
import moulder.values.SeqValue;
import moulder.values.SimpleBox;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.junit.Test;

import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;

public class ForEachTest extends BaseMoulderTest {

    @Test
    public void test() throws Exception {
        List<Integer> list = Arrays.asList(4, 2);
        final SimpleBox<Integer> sb = new SimpleBox<Integer>();
        ForEach<Integer> f = new ForEach<Integer>(sb, new SeqValue<Integer>(list), new Moulder() {
            public List<Node> process(Element element) {
                return Arrays.asList(parseNode("<a>ohai n" + sb.get() + "</a>"));
            }
        });
        Document document = Jsoup
                .parseBodyFragment("<tag>text</tag>");
        Element element = document.getElementsByTag("tag").first();

        List<Node> processed = f.process(element);

        // check for correct repetition of html
        assertXMLEqual(new StringReader("<body>" + "<a>ohai n4</a><a>ohai n2</a>" + "</body>"), new StringReader(
                html(processed)));
    }

}
