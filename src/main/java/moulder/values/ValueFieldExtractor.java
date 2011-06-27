package moulder.values;

import moulder.Value;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ValueFieldExtractor<T, S> extends ValueTransformer<S, T> {

	private Method m;

	public ValueFieldExtractor(Value<S> delegate, String field,
			Class<S> delegateResultClass) {
		super(delegate);

		try {
			BeanInfo bi = Introspector.getBeanInfo(delegateResultClass);
			PropertyDescriptor[] pds = bi.getPropertyDescriptors();
			for (PropertyDescriptor propertyDescriptor : pds) {
				if (field.equals(propertyDescriptor.getName())) {
					m = propertyDescriptor.getReadMethod();
					break;
				}
			}
			if (m == null) {
				throw new IllegalArgumentException("No property '" + field
						+ "' in " + delegateResultClass);
			}

		} catch (IntrospectionException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected T transform(S s) {
		try {
			return (T) m.invoke(s);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

}
