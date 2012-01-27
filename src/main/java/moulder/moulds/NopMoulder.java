package moulder.moulds;

import moulder.Moulder;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import java.util.Arrays;
import java.util.List;

/**
 * A moulder that does nothing, no really ! Can be useful in conjunction with
 * the {@link IfMoulder} for example.
 * 
 * @author jawher
 * 
 */
public class NopMoulder implements Moulder {

    public List<Node> process(Element element) {
		return Arrays.<Node>asList(element);
	}

}
