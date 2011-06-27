package moulder.moulds;

import moulder.ElementAndData;
import moulder.Moulder;
import moulder.MoulderUtils;
import moulder.NodeAndData;

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

	public List<NodeAndData> process(ElementAndData nd, MoulderUtils f) {
		return Arrays.asList(nd.toNodeAndData());
	}

}
