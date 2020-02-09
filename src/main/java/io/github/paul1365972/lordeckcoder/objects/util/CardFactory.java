package io.github.paul1365972.lordeckcoder.objects.util;

import io.github.paul1365972.lordeckcoder.exceptions.LoRIllegalFormatException;
import io.github.paul1365972.lordeckcoder.objects.LoRCardI;
import io.github.paul1365972.lordeckcoder.objects.LoRFactionI;

@FunctionalInterface
public interface CardFactory<T extends LoRFactionI, R extends LoRCardI<T>> {
	R apply(int set, T t, int id) throws LoRIllegalFormatException;
}