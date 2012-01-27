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
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class RepeaterTest extends BaseMoulderTest {

	@Test
	public void test() throws Exception {
		List<String> items = Arrays.asList("Summer", "Autumn", "Winter",
				"Spring");
		Value<Iterable<String>> content = mock(Value.class);
		when(content.get()).thenReturn(items);

		Repeater<String> a = new Repeater<String>(content);
		Document document = Jsoup
				.parseBodyFragment("<html><body><outer a='v'>test</outer></body></html>");
		Element element = document.getElementsByTag("outer").first();

		ElementAndData nd = new ElementAndData(element, "data");
		List<NodeAndData> processed = a.process(nd);
		
		// verify that bind and get were called, in this order
		InOrder inOrder = inOrder(content);
		inOrder.verify(content).bind(nd);
		inOrder.verify(content).get();

		// check for correct repetition of html
		assertXMLEqual(new StringReader("<body>" + "<outer a='v'>test</outer>"
				+ "<outer a='v'>test</outer>" + "<outer a='v'>test</outer>"
				+ "<outer a='v'>test</outer>" + "</body>"), new StringReader(
				html(processed)));
		
		// check for the produced nodes data
		Iterator<String> it = items.iterator();
		for (NodeAndData nodeAndData : processed) {
			assertTrue(it.hasNext());
			assertEquals(it.next(), nodeAndData.data);
		}
	}

}
