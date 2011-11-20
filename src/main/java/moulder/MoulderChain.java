package moulder;

import java.util.Arrays;
import java.util.List;

public class MoulderChain {
        private final List<Moulder> moulders;

        public MoulderChain(List<Moulder> moulders) {
            this.moulders = moulders;
        }

        public MoulderChain add(Moulder... moulders) {
            return add(Arrays.asList(moulders));
        }

        public MoulderChain add(List<Moulder> moulders) {
            this.moulders.addAll(moulders);
            return null;
        }

}