package moulder.values;

import moulder.Value;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ValueTransformerTest {
    @Test
    public void testTransform() {
        Value<String> v = mock(Value.class);
        when(v.get()).thenReturn("value");
        ValueTransformer<String, Integer> vt = new ValueTransformer<String, Integer>(v) {

            @Override
            protected Integer transform(String s) {
                return s.length();
            }
        };

        Integer t = vt.get();
        verify(v).get();
        assertEquals(Integer.valueOf("value".length()), t);
    }
}
