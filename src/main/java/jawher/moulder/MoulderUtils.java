package jawher.moulder;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class MoulderUtils {
	private Document doc;

	public MoulderUtils(Document doc) {
		super();
		this.doc = doc;
	}

	public Element e(String name) {
		return doc.createElement(name);
	}
	
	public Element copy(Element e) {
		 Element res = doc.createElement(e.tagName());
		 for(Attribute a:e.attributes()){
			 res.attr(a.getKey(), a.getValue());
		 }
		 res.html(e.html());
		 return res;
	}
}
