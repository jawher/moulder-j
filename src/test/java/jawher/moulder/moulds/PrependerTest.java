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

public class PrependerTest extends BaseMoulderTest {
	

	@Test
	public void test() throws Exception {
		Value<Iterable<Node>> content = mock(Value.class);
		when(content.get()).thenReturn(parse("<e a='v'>c</e>text"));

		Prepender a = new Prepender(content);
		Document document = Jsoup
				.parseBodyFragment("<html><body><outer>test</outer></body></html>");
		MoulderUtils mu = new MoulderUtils(document);
		Element element = document.getElementsByTag("outer").first();

		ElementAndData nd = new ElementAndData(element, "data");
		List<NodeAndData> processed = a.process(nd, mu);

		// verify that bind and get were called, in this order
		InOrder inOrder = inOrder(content);
		inOrder.verify(content).bind(nd);
		inOrder.verify(content).get();

		assertXMLEqual(new StringReader(
				"<body><e a='v'>c</e>text<outer>test</outer></body>"),
				new StringReader(html(processed)));
	}

	
}
