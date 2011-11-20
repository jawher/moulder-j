package moulder;

import moulder.moulds.SubMoulder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MoulderShop {

    private SubMoulder subMoulder = new SubMoulder();

    public MoulderChain select(String selector) {
        List<Moulder> moulders = new ArrayList<Moulder>();
        subMoulder.register(selector, moulders);
        return new MoulderChain(moulders);
    }

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

    public Document process(InputStream stream) {
        Document doc;
        try {
            doc = Jsoup.parse(stream, null, "#");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MoulderUtils factory = new MoulderUtils(doc);

        subMoulder.process(new ElementAndData(doc), factory);
        return doc;
    }

}
