package moulder.moulds;

import moulder.ElementAndData;
import moulder.NodeAndData;
import moulder.Value;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;
import org.mockito.InOrder;

import java.io.StringReader;
import java.util.List;

import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;
import static org.mockito.Mockito.*;

public class RemoverTest extends BaseMoulderTest {
	

	@Test
	public void test() throws Exception {
		Remover a = new Remover();
		Document document = Jsoup
				.parseBodyFragment("<html><body><outer a='v'>test</outer>text</body></html>");
		Element element = document.getElementsByTag("outer").first();

		ElementAndData nd = new ElementAndData(element, "data");
		List<NodeAndData> processed = a.process(nd);

		
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
		Element element = document.getElementsByTag("outer").first();

		ElementAndData nd = new ElementAndData(element, "data");
		List<NodeAndData> processed = a.process(nd);

		// verify that bind and get were called, in this order
		InOrder inOrder = inOrder(visibility);
		inOrder.verify(visibility).bind(nd);
		inOrder.verify(visibility).get();
		
		assertXMLEqual(new StringReader(
				"<body></body>"),
				new StringReader(html(processed)));
	}
	
}
