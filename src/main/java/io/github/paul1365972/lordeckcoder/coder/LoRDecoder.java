package io.github.paul1365972.lordeckcoder.coder;

import io.github.paul1365972.lordeckcoder.exceptions.LoRCodingException;

import java.util.function.Function;

public interface LoRDecoder<T> {
	
	static <T> LoRDecoder<T> from(Function<FormatVersion, T> deckFactory, CardAccumulator<T> accumulator) {
		return new LoRDecoder<T>() {
			@Override
			public T createDeck(FormatVersion formatVersion) {
				return deckFactory.apply(formatVersion);
			}
			
			@Override
			public void addCard(T deck, int set, int factionId, int cardNumber, int amount) throws LoRCodingException {
				accumulator.accept(deck, set, factionId, cardNumber, amount);
			}
		};
	}
	
	T createDeck(FormatVersion formatVersion) throws LoRCodingException;
	
	void addCard(T deck, int set, int factionId, int cardNumber, int amount) throws LoRCodingException;
	
	@FunctionalInterface
	interface CardAccumulator<T> {
		void accept(T deck, int set, int factionId, int cardNumber, int amount) throws LoRCodingException;
	}
}
