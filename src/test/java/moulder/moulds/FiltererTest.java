package moulder.moulds;

import moulder.ElementAndData;
import moulder.MoulderUtils;
import moulder.NodeAndData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;
import org.mockito.InOrder;

import java.io.StringReader;
import java.util.List;

import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;
import static org.mockito.Mockito.*;

public class FiltererTest extends BaseMoulderTest {
	

	@Test
	public void test() throws Exception {
		Filterer a = new Filterer("keep");
		Document document = Jsoup
				.parseBodyFragment("<html><body><outer a='v'><keep>this</keep><remove>this</remove></outer>text</body></html>");
		MoulderUtils mu = new MoulderUtils(document);
		Element element = document.getElementsByTag("outer").first();

		ElementAndData nd = new ElementAndData(element, "data");
		List<NodeAndData> processed = a.process(nd, mu);
		
		assertXMLEqual(new StringReader(
				"<body><keep>this</keep></body>"),
				new StringReader(html(processed)));
	}
	
	

	
}
