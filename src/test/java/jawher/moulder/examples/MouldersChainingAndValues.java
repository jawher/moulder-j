package jawher.moulder.examples;

import static jawher.moulder.moulds.Moulds.*;

import java.util.Arrays;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

import jawher.moulder.MoulderShop;
import jawher.moulder.values.ElementDataValue;
import jawher.moulder.values.HtmlValue;
import jawher.moulder.values.ValueTransformer;
import jawher.moulder.values.Values;

public class MouldersChainingAndValues {
	private static final String HTML = "<html><body><h1>[...]</h1></body></html>";

	@Before
	public void before() {
		System.out.println("\n----------------\n");
	}

	/**
	 * Shows how to chain moulders to achieve complex transformations on
	 * elements. Output:
	 * 
	 * <pre>
	 * <code>
	 * <html>
	 *  <head>
	 *  </head>
	 *  <body> 
	 *   <h1 class="title">title's text</h1> 
	 *   <p>content</p> 
	 *   <h1 class="title">title's text</h1> 
	 *   <p>content</p> 
	 *   <h1 class="title">title's text</h1> 
	 *   <p>content</p> 
	 *   <h1 class="title">title's text</h1> 
	 *   <p>content</p>
	 *  </body>
	 * </html>
	 * </code>
	 * </pre>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testChaining() throws Exception {
		Document doc = Jsoup.parse(HTML);
		MoulderShop m = new MoulderShop();

		m.register("h1",
				repeat(Arrays.asList("Spring", "Summer", "Autumn", "Winter")),
				attr("class", "title"), text("title's text"),
				append("<p>content</p>"));

		m.process(doc);

		System.out.println(doc);
	}

	/**
	 * Shows how to a moulder can pass values between each other and how to
	 * values can be used to control the moulders behaviour. Output:
	 * 
	 * <pre>
	 * <code>
	 * <html>
	 *  <head>
	 *  </head>
	 *  <body> 
	 *   <h1 class="even">Spring</h1> 
	 *   <p>Season: <em>Spring</em></p> 
	 *   <h1 class="odd">Summer</h1> 
	 *   <p>Season: <em>Summer</em></p> 
	 *   <h1 class="even">Autumn</h1> 
	 *   <p>Season: <em>Autumn</em></p> 
	 *   <h1 class="odd">Winter</h1> 
	 *   <p>Season: <em>Winter</em></p>
	 *  </body>
	 * </html>
	 * </code>
	 * </pre>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testValues() throws Exception {
		Document doc = Jsoup.parse(HTML);
		MoulderShop m = new MoulderShop();

		m.register("h1",
				repeat(Arrays.asList("Spring", "Summer", "Autumn", "Winter")),
				attr("class", new Values<String>("even", "odd").cycle()),
				text(new ElementDataValue<String>()), append("<p>content</p>"));

		m.process(doc);

		System.out.println(doc);
	}
}
