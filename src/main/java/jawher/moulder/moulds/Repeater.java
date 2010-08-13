package jawher.moulder.moulds;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jawher.moulder.ElementAndData;
import jawher.moulder.MoulderUtils;
import jawher.moulder.NodeAndData;
import jawher.moulder.Moulder;
import jawher.moulder.Value;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

public class Repeater<T> implements Moulder {
	private Value<? extends Iterable<T>> items;

	public Repeater(Value<? extends Iterable<T>> items) {
		super();
		this.items = items;		
	}

	public List<NodeAndData> process(ElementAndData nd, MoulderUtils f) {
		items.bind(nd);
		Iterator<T> it = items.get().iterator();
		List<NodeAndData> res = new ArrayList<NodeAndData>();
		while(it.hasNext()){
			res.add(new NodeAndData(f.copy(nd.node), it.next()));
		}
		return res;
	}

}
