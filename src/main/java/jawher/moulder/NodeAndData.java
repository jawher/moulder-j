package jawher.moulder;

import org.jsoup.nodes.Node;

public class NodeAndData<N extends Node> {
	public final N node;
	public final Object data;

	public NodeAndData(N node, Object data) {
		super();
		this.node = node;
		this.data = data;
	}

	public NodeAndData(N node) {
		super();
		this.node = node;
		this.data = null;
	}

}
