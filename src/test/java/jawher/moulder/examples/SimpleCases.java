package jawher.moulder.examples;

import static jawher.moulder.moulds.Moulds.*;

import java.util.Arrays;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

import jawher.moulder.MoulderShop;

public class SimpleCases {
	private static final String HTML = "<html><body><h1>[...]</h1></body></html>";

	@Before
	public void before() {
		System.out.println("\n----------------\n");
	}

	/**
	 * Shows how to set an element's text content. Ouput:
	 * 
	 * <pre>
	 * &lt;html&gt;
	 *  <head>
	 *  </head>
	 *  <body> 
	 *   <h1>Hello Moulder !</h1>
	 *  </body>
	 * </html>
	 * </pre>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSetText() throws Exception {
		Document doc = Jsoup.parse(HTML);
		MoulderShop m = new MoulderShop();

		m.register("h1", text("Hello Moulder !"));

		m.process(doc);

		System.out.println(doc);
	}

	/**
	 * Shows how to set element's attributes. Output:
	 * 
	 * <pre>
	 * <code>
	 * <html>
	 *  <head>
	 *  </head>
	 *  <body> 
	 *   <h1 class="title">[...]</h1>
	 *  </body>
	 * </html>
	 * </code>
	 * </pre>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAttrModifier() throws Exception {
		Document doc = Jsoup.parse(HTML);
		MoulderShop m = new MoulderShop();

		m.register("h1", attr("class", "title"));

		m.process(doc);

		System.out.println(doc);
	}

	/**
	 * Shows how to append content after an element. Output:
	 * 
	 * <pre>
	 * <code>
	 * <html>
	 *  <head>
	 *  </head>
	 *  <body> 
	 *   <h1>[...]</h1> 
	 *   <p>text</p>
	 *  </body>
	 * </html>
	 * </code>
	 * </pre>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAppender() throws Exception {
		Document doc = Jsoup.parse(HTML);
		MoulderShop m = new MoulderShop();

		m.register("h1", append("<p>text</p>"));

		m.process(doc);

		System.out.println(doc);
	}

	/**
	 * Shows how to prepend content before an element. Output:
	 * 
	 * <pre>
	 * <code>
	 * <html>
	 *  <head>
	 *  </head>
	 *  <body> 
	 *   <p>text</p> 
	 *   <h1>[...]</h1>
	 *  </body>
	 * </html>
	 * </code>
	 * </pre>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testPrepender() throws Exception {
		Document doc = Jsoup.parse(HTML);
		MoulderShop m = new MoulderShop();

		m.register("h1", prepend("<p>text</p>"));

		m.process(doc);

		System.out.println(doc);
	}

	/**
	 * Shows how to append content to an element's children. Output:
	 * 
	 * <pre>
	 * <code>
	 * <html>
	 *  <head>
	 *  </head>
	 *  <body> 
	 *   <h1>[...]<em>text</em></h1>
	 *  </body>
	 * </html>
	 * </code>
	 * </pre>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testChildAppender() throws Exception {
		Document doc = Jsoup.parse(HTML);
		MoulderShop m = new MoulderShop();

		m.register("h1", appendToChildren("<em>text</em>"));

		m.process(doc);

		System.out.println(doc);
	}

	/**
	 * Shows how to prepend content to an element's children. Output:
	 * 
	 * <pre>
	 * <code>
	 * <html>
	 *  <head>
	 *  </head>
	 *  <body> 
	 *   <h1><em>text</em>[...]</h1>
	 *  </body>
	 * </html>
	 * </code>
	 * </pre>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testChildPrepender() throws Exception {
		Document doc = Jsoup.parse(HTML);
		MoulderShop m = new MoulderShop();

		m.register("h1", prependToChildren("<em>text</em>"));

		m.process(doc);

		System.out.println(doc);
	}

	/**
	 * Shows how to remove an element. Output:
	 * 
	 * <pre>
	 * <code>
	 * <html>
	 *  <head>
	 *  </head>
	 *  <body>
	 *  </body>
	 * </html>
	 * </code>
	 * </pre>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testRemover() throws Exception {
		Document doc = Jsoup.parse(HTML);
		MoulderShop m = new MoulderShop();

		m.register("h1", remove());

		m.process(doc);

		System.out.println(doc);
	}

	/**
	 * Shows how to repeat an element. Output:
	 * 
	 * <pre>
	 * <code>
	 * <html>
	 *  <head>
	 *  </head>
	 *  <body> 
	 *   <h1>[...]</h1> 
	 *   <h1>[...]</h1> 
	 *   <h1>[...]</h1> 
	 *   <h1>[...]</h1>
	 *  </body>
	 * </html>
	 * </code>
	 * </pre>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testRepeater() throws Exception {
		Document doc = Jsoup.parse(HTML);
		MoulderShop m = new MoulderShop();

		m.register("h1", repeat(Arrays.asList(1, 2, 3, 4)));

		m.process(doc);

		System.out.println(doc);
	}
}
