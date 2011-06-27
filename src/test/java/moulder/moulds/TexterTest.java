package moulder.moulds;

import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.StringReader;
import java.util.List;

import moulder.ElementAndData;
import moulder.MoulderUtils;
import moulder.NodeAndData;

import moulder.Value;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;
import org.mockito.InOrder;

public class TexterTest extends BaseMoulderTest {

	@Test
	public void testRegularText() throws Exception {
		Value<String> text = mock(Value.class);
		when(text.get()).thenReturn("text");

		Texter a = new Texter(text);
		Document document = Jsoup
				.parseBodyFragment("<html><body><outer>test</outer></body></html>");
		MoulderUtils mu = new MoulderUtils(document);
		Element element = document.getElementsByTag("outer").first();

		ElementAndData nd = new ElementAndData(element, "data");
		List<NodeAndData> processed = a.process(nd, mu);

		// verify that bind and get were called, in this order
		InOrder inOrder = inOrder(text);
		inOrder.verify(text).bind(nd);
		inOrder.verify(text).get();

		assertXMLEqual(new StringReader("<body><outer>text</outer></body>"),
				new StringReader(html(processed)));
	}

	@Test
	public void testSpecialChars() throws Exception {
		Value<String> text = mock(Value.class);
		when(text.get()).thenReturn("text<e a='v'>hello & welcome<");

		Texter a = new Texter(text);
		Document document = Jsoup
				.parseBodyFragment("<html><body><outer>test</outer></body></html>");
		MoulderUtils mu = new MoulderUtils(document);
		Element element = document.getElementsByTag("outer").first();

		ElementAndData nd = new ElementAndData(element, "data");
		List<NodeAndData> processed = a.process(nd, mu);

		// verify that bind and get were called, in this order
		InOrder inOrder = inOrder(text);
		inOrder.verify(text).bind(nd);
		inOrder.verify(text).get();

		assertXMLEqual(
				new StringReader(
						"<body><outer>text&lt;e a='v'&gt;hello &amp; welcome&lt;</outer></body>"),
				new StringReader(html(processed)));
	}

}
