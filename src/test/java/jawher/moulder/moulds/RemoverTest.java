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

public class RemoverTest extends BaseMoulderTest {
	

	@Test
	public void test() throws Exception {
		Remover a = new Remover();
		Document document = Jsoup
				.parseBodyFragment("<html><body><outer a='v'>test</outer>text</body></html>");
		MoulderUtils mu = new MoulderUtils(document);
		Element element = document.getElementsByTag("outer").first();

		ElementAndData nd = new ElementAndData(element, "data");
		List<NodeAndData> processed = a.process(nd, mu);

		
		assertXMLEqual(new StringReader(
				"<body></body>"),
				new StringReader(html(processed)));
	}
	
	
	@Test
	public void testWithValue() throws Exception {
		Value<Boolean> visibility = mock(Value.class);
		when(visibility.get()).thenReturn(Boolean.TRUE);
		
		Remover a = new Remover(visibility);
		Document document = Jsoup
				.parseBodyFragment("<html><body><outer a='v'>test</outer>text</body></html>");
		MoulderUtils mu = new MoulderUtils(document);
		Element element = document.getElementsByTag("outer").first();

		ElementAndData nd = new ElementAndData(element, "data");
		List<NodeAndData> processed = a.process(nd, mu);

		// verify that bind and get were called, in this order
		InOrder inOrder = inOrder(visibility);
		inOrder.verify(visibility).bind(nd);
		inOrder.verify(visibility).get();
		
		assertXMLEqual(new StringReader(
				"<body></body>"),
				new StringReader(html(processed)));
	}
	
}
