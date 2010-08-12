package jawher.moulder;

import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

public interface Moulder {
	List<NodeAndData<? extends Node>> process(NodeAndData<Element> nd, MoulderUtils f);
}
