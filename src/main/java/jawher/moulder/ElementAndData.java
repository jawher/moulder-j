package jawher.moulder;

import org.jsoup.nodes.Element;

public class ElementAndData {
	public final Element node;
	public final Object data;

	public ElementAndData(Element node, Object data) {
		super();
		this.node = node;
		this.data = data;
	}

	public ElementAndData(Element node) {
		super();
		this.node = node;
		this.data = null;
	}

	public NodeAndData toNodeAndData() {
		return new NodeAndData(node, data);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((node == null) ? 0 : node.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ElementAndData other = (ElementAndData) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (node == null) {
			if (other.node != null)
				return false;
		} else if (!node.equals(other.node))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ElementAndData [node=" + node + ", data=" + data + "]";
	}

}
