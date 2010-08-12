package jawher.moulder.moulds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jawher.moulder.MoulderUtils;
import jawher.moulder.NodeAndData;
import jawher.moulder.Moulder;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

public class SubTemplator implements Moulder {
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

	public SubTemplator register(String selector, Moulder... templators) {
		return register(selector, Arrays.asList(templators));
	}

	public SubTemplator register(String selector, List<Moulder> templators) {
		cfg.add(new TemplatorConfig(selector, templators));
		return this;
	}

	public List<NodeAndData<? extends Node>> process(NodeAndData<Element> nd,
			MoulderUtils f) {
		
		for (TemplatorConfig c : cfg) {
			Elements elements = nd.node.select(c.selector);
			for (Element e : elements) {
				List<NodeAndData<Node>> oes = Arrays
						.asList(new NodeAndData<Node>(e, nd.data));
				for (Moulder templator : c.templators) {
					List<NodeAndData<Node>> tes = new ArrayList<NodeAndData<Node>>();
					for (NodeAndData<Node> oe : oes) {
						if (oe.node instanceof Element) {
							List<NodeAndData<? extends Node>> processed = templator
									.process(new NodeAndData<Element>(
											(Element) oe.node, oe.data), f);
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

		List<NodeAndData<? extends Node>> res = new ArrayList<NodeAndData<? extends Node>>();
		res.add(nd);
		return res;
	}

}
