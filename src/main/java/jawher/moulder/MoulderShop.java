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
				List<NodeAndData<Node>> oes = Arrays
						.asList(new NodeAndData<Node>(e));
				for (Moulder templator : c.templators) {
					List<NodeAndData<Node>> tes = new ArrayList<NodeAndData<Node>>();
					for (NodeAndData<Node> oe : oes) {
						if (oe.node instanceof Element) {
							List<NodeAndData<? extends Node>> processed = templator
									.process(new NodeAndData<Element>(
											(Element) oe.node, oe.data), factory);
							for (NodeAndData<? extends Node> processedNode : processed) {
								tes.add(new NodeAndData<Node>(
										processedNode.node, processedNode.data));
							}
						} else {
							tes.add(oe);
						}

					}
					oes = tes;
				}
				// replace e with oes
				for (NodeAndData<Node> oe : oes) {
					e.before(oe.node.outerHtml());
				}

				e.remove();
			}
		}
	}

}
