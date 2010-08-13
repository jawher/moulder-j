package jawher.moulder;

import java.util.List;

/**
 * A processor that takes an element and its associated data and generates a
 * list of nodes as a result. The resulting nodes will be used to replace the
 * input element in the resulting document.
 * 
 * @author jawher
 * 
 */
public interface Moulder {
	/**
	 * This is where a moulder can process the input element to generate its
	 * result.
	 * 
	 * @param nd
	 *            the element and its associated data to process
	 * @param f
	 *            A utility class that can be used to copy or create elements
	 * @return the list of nodes and their associated data that were generated
	 *         after processing the input element.
	 */
	List<NodeAndData> process(ElementAndData nd, MoulderUtils f);
}
