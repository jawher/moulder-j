package jawher.moulder.moulds;

import java.util.Arrays;
import java.util.List;

import jawher.moulder.ElementAndData;
import jawher.moulder.Moulder;
import jawher.moulder.MoulderUtils;
import jawher.moulder.NodeAndData;

/**
 * A moulder that does nothing, no really ! Can be useful in conjunction with
 * the {@link IfMoulder} for example.
 * 
 * @author jawher
 * 
 */
public class NopMoulder implements Moulder {

	public List<NodeAndData> process(ElementAndData nd, MoulderUtils f) {
		return Arrays.asList(nd.toNodeAndData());
	}

}
