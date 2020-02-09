package io.github.paul1365972.lordeckcoder.objects;

import io.github.paul1365972.lordeckcoder.coder.FormatVersion;

import java.util.Map;
import java.util.Optional;

public interface LoRDeckI<E extends LoRCardI<T>, T extends LoRFactionI> {
	
	default int addCards(E card, int amount) {
		return Optional.ofNullable(getCards().merge(card, amount, (a, b) -> a + b <= 0 ? null : Math.min(3, a + b))).orElse(0);
	}
	
	default int removeCards(E card, int amount) {
		return addCards(card, -amount);
	}
	
	default boolean removeAllCards(E card) {
		return getCards().remove(card) != null;
	}
	
	default void clear() {
		getCards().clear();
	}
	
	default int countCard(E card) {
		return Optional.ofNullable(getCards().get(card)).orElse(0);
	}
	
	default int uniqueCards() {
		return getCards().size();
	}
	
	default int size() {
		return getCards().values().stream().mapToInt(v -> v).sum();
	}
	
	Map<E, Integer> getCards();
	
	FormatVersion getFormatVersion();
	
	default boolean isValid() {
		return getCards().entrySet().stream().allMatch(e -> e.getKey().isValid() && e.getValue() >= 1 && e.getValue() <= 3);
	}
}
