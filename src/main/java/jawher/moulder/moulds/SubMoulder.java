package jawher.moulder.moulds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jawher.moulder.ElementAndData;
import jawher.moulder.MoulderUtils;
import jawher.moulder.NodeAndData;
import jawher.moulder.Moulder;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

public class SubMoulder implements Moulder {
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

	public SubMoulder register(String selector, Moulder... templators) {
		return register(selector, Arrays.asList(templators));
	}

	public SubMoulder register(String selector, List<Moulder> templators) {
		cfg.add(new TemplatorConfig(selector, templators));
		return this;
	}

	public List<NodeAndData> process(ElementAndData nd, MoulderUtils f) {
		
		for (TemplatorConfig c : cfg) {
			Elements elements = nd.node.select(c.selector);
			for (Element e : elements) {
				List<NodeAndData> oes = Arrays
						.asList(new NodeAndData(e, nd.data));
				for (Moulder templator : c.templators) {
					List<NodeAndData> tes = new ArrayList<NodeAndData>();
					for (NodeAndData oe : oes) {
						if (oe.node instanceof Element) {
							List<NodeAndData> processed = templator
									.process(new ElementAndData(
											(Element) oe.node, oe.data), f);
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

		List<NodeAndData> res = new ArrayList<NodeAndData>();
		res.add(nd.toNodeAndData());
		return res;
	}

}
