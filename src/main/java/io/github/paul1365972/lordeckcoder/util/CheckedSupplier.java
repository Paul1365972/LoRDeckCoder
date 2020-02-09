package io.github.paul1365972.lordeckcoder.util;

@FunctionalInterface
public interface CheckedSupplier<T, E extends Exception> {
	T get() throws E;
}