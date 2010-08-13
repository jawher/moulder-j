package jawher.moulder.values;

import static org.mockito.Mockito.*;
import jawher.moulder.ElementAndData;
import jawher.moulder.NodeAndData;
import jawher.moulder.Value;
import static org.junit.Assert.*;

import org.jsoup.nodes.Element;
import org.junit.Test;

public class ValueTransformerTest {
	@Test
	public void testTransform() {
		Value<String> v = mock(Value.class);
		when(v.get()).thenReturn("value");
		ValueTransformer<Integer, String> vt = new ValueTransformer<Integer, String>(v) {

			@Override
			protected Integer transform(String s) {
				return s.length();
			}
		};
		
		Integer t = vt.get();
		verify(v).get();
		assertEquals(Integer.valueOf("value".length()), t);
	}
	
	@Test
	public void testBindWasCalledOnDelegate() {
		Value<String> v = mock(Value.class);
		when(v.get()).thenReturn("value");
		ValueTransformer<Integer, String> vt = new ValueTransformer<Integer, String>(v) {

			@Override
			protected Integer transform(String s) {
				return s.length();
			}
		};
		ElementAndData nd = new ElementAndData(null, "data");
		vt.bind(nd);
		verify(v).bind(nd);		
	}
}
