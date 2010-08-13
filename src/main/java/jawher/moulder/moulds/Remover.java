package jawher.moulder.moulds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jawher.moulder.ElementAndData;
import jawher.moulder.MoulderUtils;
import jawher.moulder.NodeAndData;
import jawher.moulder.Moulder;
import jawher.moulder.Value;
import jawher.moulder.values.SimpleValue;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

public class Remover implements Moulder {
	private Value<Boolean> remove;

	public Remover() {
		this(new SimpleValue<Boolean>(true));
	}

	public Remover(Value<Boolean> remove) {
		super();
		this.remove = remove;
	}

	public List<NodeAndData> process(ElementAndData nd, MoulderUtils f) {
		remove.bind(nd);
		if (remove.get()) {
			return Collections.EMPTY_LIST;
		} else {
			List<NodeAndData> res = new ArrayList<NodeAndData>();
			res.add(nd.toNodeAndData());
			return res;
		}
	}

}
