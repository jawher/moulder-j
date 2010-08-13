package jawher.moulder.moulds;

import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;
import static org.mockito.Mockito.*;

import java.io.StringReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import jawher.moulder.ElementAndData;
import jawher.moulder.MoulderUtils;
import jawher.moulder.NodeAndData;
import jawher.moulder.Value;
import jawher.moulder.values.Values;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.InOrder;

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
		MoulderUtils mu = new MoulderUtils(document);
		Element element = document.getElementsByTag("outer").first();

		ElementAndData nd = new ElementAndData(element, "data");
		List<NodeAndData> processed = a.process(nd, mu);
		
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
