package jawher.moulder.moulds;

import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.StringReader;
import java.util.List;

import jawher.moulder.ElementAndData;
import jawher.moulder.MoulderUtils;
import jawher.moulder.NodeAndData;
import jawher.moulder.Value;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.junit.Test;
import org.mockito.InOrder;

public class NopMoulderTest extends BaseMoulderTest {
	

	@Test
	public void test() throws Exception {
		NopMoulder a = new NopMoulder();
		Document document = Jsoup
				.parseBodyFragment("<html><body><outer>test</outer></body></html>");
		MoulderUtils mu = new MoulderUtils(document);
		Element element = document.getElementsByTag("outer").first();

		ElementAndData nd = new ElementAndData(element, "data");
		List<NodeAndData> processed = a.process(nd, mu);
System.out.println(html(processed));
		assertXMLEqual(new StringReader(
				"<body><outer>test</outer></body>"),
				new StringReader(html(processed)));
	}

	
}
