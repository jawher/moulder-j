package moulder.moulds;

import moulder.ElementAndData;
import moulder.NodeAndData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import java.io.StringReader;
import java.util.List;

import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;

public class FiltererTest extends BaseMoulderTest {
	

	@Test
	public void test() throws Exception {
		Filterer a = new Filterer("keep");
		Document document = Jsoup
				.parseBodyFragment("<html><body><outer a='v'><keep>this</keep><remove>this</remove></outer>text</body></html>");
		Element element = document.getElementsByTag("outer").first();

		ElementAndData nd = new ElementAndData(element, "data");
		List<NodeAndData> processed = a.process(nd);
		
		assertXMLEqual(new StringReader(
				"<body><keep>this</keep></body>"),
				new StringReader(html(processed)));
	}
	
	

	
}
