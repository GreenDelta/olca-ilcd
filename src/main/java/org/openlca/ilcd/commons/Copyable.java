package org.openlca.ilcd.commons;

public interface Copyable<T extends Copyable<T>> {

	T copy();

}
