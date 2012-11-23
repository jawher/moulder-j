package moulder;

import java.util.ArrayList;
import java.util.List;

public class Registry {

    public static final class TemplatorConfig {
        public final String selector;
        public final List<Moulder> templators;

        public TemplatorConfig(String selector, List<Moulder> templators) {
            this.selector = selector;
            this.templators = templators;
        }
    }

    private final List<TemplatorConfig> cfg = new ArrayList<TemplatorConfig>();

    /**
     * Registers a number of moulders to be applied to its input element's
     * children returned by the supplied selector
     *
     * @param selector
     *            to selected the input element's children to be processed
     * @param templators
     *            the moulders to apply on the selected children
     */
    public void register(String selector, List<Moulder> templators) {
        cfg.add(new TemplatorConfig(selector, templators));
    }

    /**
     * Return the registered selector/moulders pairs
     *
     * @return
     */
    public List<TemplatorConfig> getConfig() {
        return cfg;
    }
}
