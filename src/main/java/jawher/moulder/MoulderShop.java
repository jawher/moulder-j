package jawher.moulder;

import java.util.Arrays;
import java.util.List;

import jawher.moulder.moulds.SubMoulder;

import org.jsoup.nodes.Document;

public class MoulderShop {
	private SubMoulder subMoulder = new SubMoulder();

	public MoulderShop register(String selector, Moulder... moulders) {
		subMoulder.register(selector, Arrays.asList(moulders));
		return this;
	}

	public MoulderShop register(String selector, List<Moulder> templators) {
		subMoulder.register(selector, templators);
		return this;
	}

	public void process(Document doc) {
		MoulderUtils factory = new MoulderUtils(doc);

		subMoulder.process(new ElementAndData(doc), factory);
	}

}
