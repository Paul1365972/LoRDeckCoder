package io.github.paul1365972.lordeckcoder.objects.generic;

import io.github.paul1365972.lordeckcoder.coder.FormatVersion;
import io.github.paul1365972.lordeckcoder.objects.LoRCardI;
import io.github.paul1365972.lordeckcoder.objects.LoRDeckI;
import io.github.paul1365972.lordeckcoder.objects.LoRFactionI;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GenericLoRDeck<E extends LoRCardI<T>, T extends LoRFactionI> implements LoRDeckI<E, T> {
	
	protected FormatVersion formatVersion;
	protected Map<E, Integer> cards = new HashMap<>();
	
	public GenericLoRDeck(FormatVersion formatVersion) {
		this.formatVersion = Objects.requireNonNull(formatVersion);
	}
	
	@Override
	public Map<E, Integer> getCards() {
		return cards;
	}
	
	@Override
	public FormatVersion getFormatVersion() {
		return formatVersion;
	}
	
	@Override
	public String toString() {
		return "GenericLoRDeck{" +
				"formatVersion=" + formatVersion +
				", cards=" + cards +
				'}';
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof GenericLoRDeck)) return false;
		GenericLoRDeck<?, ?> that = (GenericLoRDeck<?, ?>) o;
		return getFormatVersion().equals(that.getFormatVersion()) &&
				getCards().equals(that.getCards());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getFormatVersion(), getCards());
	}
}
