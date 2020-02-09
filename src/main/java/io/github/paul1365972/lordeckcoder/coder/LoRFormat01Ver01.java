package io.github.paul1365972.lordeckcoder.coder;

import io.github.paul1365972.lordeckcoder.exceptions.LoRCodingException;
import io.github.paul1365972.lordeckcoder.exceptions.LoRIllegalFormatException;
import io.github.paul1365972.lordeckcoder.exceptions.LoRUnsupportedFormatException;
import io.github.paul1365972.lordeckcoder.objects.LoRCardI;
import io.github.paul1365972.lordeckcoder.objects.LoRDeckI;
import io.github.paul1365972.lordeckcoder.objects.LoRFactionI;
import io.github.paul1365972.lordeckcoder.util.CheckedSupplier;
import io.github.paul1365972.lordeckcoder.util.VarInt;

import java.math.BigInteger;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class LoRFormat01Ver01 implements LoRFormatter {
	
	public static final FormatVersion FORMAT_VERSION = new FormatVersion(1, 1);
	
	public <T, U> T decode(Supplier<Byte> bytes, LoRDecoder<T> deserializer) throws LoRCodingException {
		CheckedSupplier<BigInteger, LoRCodingException> supplier = () -> Optional
				.ofNullable(VarInt.toBigInt(bytes))
				.orElseThrow(() -> new LoRIllegalFormatException("Reached end of stream unexpectedly"));
		
		T deck = deserializer.createDeck(FORMAT_VERSION);
		try {
			for (int cards = 3; cards > 0; cards--) {
				int setFactionGroups = supplier.get().intValueExact();
				for (int i = 0; i < setFactionGroups; i++) {
					int setFactionLength = supplier.get().intValueExact();
					int set = supplier.get().intValueExact();
					int faction = supplier.get().intValueExact();
					for (int j = 0; j < setFactionLength; j++) {
						int cardNumber = supplier.get().intValueExact();
						deserializer.addCard(deck, set, faction, cardNumber, cards);
					}
				}
			}
		} catch (ArithmeticException e) {
			throw new LoRUnsupportedFormatException("VarInt-Optimization overflowed");
		}
		return deck;
	}
	
	@Override
	public <T extends LoRCardI<U>, U extends LoRFactionI> void encode(LoRDeckI<T, U> deck, Consumer<Byte> builder) {
		Consumer<BigInteger> consumer = varInt -> VarInt.toBytes(varInt, builder);
		
		Map<Integer, List<T>> amountCardsMap = new HashMap<>();
		deck.getCards().forEach((card, amount) -> amountCardsMap.computeIfAbsent(amount, k -> new ArrayList<>()).add(card));
		for (int amount = 3; amount >= 1; amount--) {
			List<T> cards = amountCardsMap.getOrDefault(amount, Collections.emptyList());
			Map<SetFaction, List<T>> setFactionCardsMap = new HashMap<>();
			cards.forEach(card -> setFactionCardsMap.computeIfAbsent(new SetFaction(card.getSet(), card.getFaction()), k -> new ArrayList<>()).add(card));
			consumer.accept(BigInteger.valueOf(setFactionCardsMap.size()));
			setFactionCardsMap.entrySet().stream()
					.sorted(Comparator.comparing(entry -> entry.getValue().size()))
					.forEach(entry -> {
						consumer.accept(BigInteger.valueOf(entry.getValue().size()));
						consumer.accept(BigInteger.valueOf(entry.getKey().getSet()));
						consumer.accept(BigInteger.valueOf(entry.getKey().getFaction().getId()));
						entry.getValue().stream()
								.sorted(Comparator.comparing(LoRCardI::getCardCode))
								.forEach(card -> consumer.accept(BigInteger.valueOf(card.getCardNumber())));
					});
		}
	}
	
	@Override
	public FormatVersion getFormatVersion() {
		return FORMAT_VERSION;
	}
	
}
