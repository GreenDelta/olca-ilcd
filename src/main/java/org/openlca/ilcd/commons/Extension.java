package org.openlca.ilcd.commons;

import java.util.List;

public interface Extension {

	List<Object> getAny();

	Extension withAny(List<Object> any);

	List<Object> withAny();

}
