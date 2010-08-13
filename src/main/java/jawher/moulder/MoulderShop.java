package jawher.moulder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

public class MoulderShop {
	private static final class TemplatorConfig {
		private String selector;
		private List<Moulder> templators;

		public TemplatorConfig(String selector, List<Moulder> templators) {
			super();
			this.selector = selector;
			this.templators = templators;
		}
	}

	private List<TemplatorConfig> cfg = new ArrayList<TemplatorConfig>();

	public void register(String selector, Moulder... templators) {
		register(selector, Arrays.asList(templators));
	}

	public void register(String selector, List<Moulder> templators) {
		cfg.add(new TemplatorConfig(selector, templators));
	}

	public void process(Document doc) {
		MoulderUtils factory = new MoulderUtils(doc);
		
		for (TemplatorConfig c : cfg) {
			Elements elements = doc.select(c.selector);
			for (Element e : elements) {
				List<NodeAndData> oes = Arrays
						.asList(new NodeAndData(e));
				for (Moulder templator : c.templators) {
					List<NodeAndData> tes = new ArrayList<NodeAndData>();
					for (NodeAndData oe : oes) {
						if (oe.node instanceof Element) {
							List<NodeAndData> processed = templator
									.process(new ElementAndData(
											(Element) oe.node, oe.data), factory);
							for (NodeAndData processedNode : processed) {
								tes.add(new NodeAndData(
										processedNode.node, processedNode.data));
							}
						} else {
							tes.add(oe);
						}

					}
					oes = tes;
				}
				// replace e with oes
				for (NodeAndData oe : oes) {
					e.before(oe.node.outerHtml());
				}

				e.remove();
			}
		}
	}

}
