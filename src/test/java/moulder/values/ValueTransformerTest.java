package moulder.values;

import static org.mockito.Mockito.*;
import moulder.ElementAndData;
import moulder.Value;
import static org.junit.Assert.*;

import org.junit.Test;

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
	
	@Test
	public void testBindWasCalledOnDelegate() {
		Value<String> v = mock(Value.class);
		when(v.get()).thenReturn("value");
		ValueTransformer<String, Integer> vt = new ValueTransformer<String, Integer>(v) {

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
