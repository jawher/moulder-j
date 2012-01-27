package moulder.moulds;

import moulder.Moulder;
import moulder.Value;
import moulder.values.SimpleValue;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A moulder that removes its input element from the resuling document
 *
 * @author jawher
 */
public class Remover implements Moulder {
    private Value<Boolean> remove;

    /**
     * Creates a remover that removes its input element from the resulting
     * document
     */
    public Remover() {
        this(new SimpleValue<Boolean>(true));
    }

    /**
     * Creates a remover that conditionally removes its input element from the
     * resulting document
     *
     * @param remove a value that returns a boolean indicating if the input element
     *               is to be removed or not from the resulting document
     */
    public Remover(Value<Boolean> remove) {
        super();
        this.remove = remove;
    }

    public List<Node> process(Element element) {
        if (remove.get()) {
            return Collections.EMPTY_LIST;
        } else {
            List<Node> res = new ArrayList<Node>();
            res.add(element);
            return res;
        }
    }

}
