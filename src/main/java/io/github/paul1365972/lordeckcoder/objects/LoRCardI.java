package io.github.paul1365972.lordeckcoder.objects;

import io.github.paul1365972.lordeckcoder.exceptions.LoRIllegalFormatException;
import io.github.paul1365972.lordeckcoder.objects.util.CardFactory;

import java.util.function.Function;

public interface LoRCardI<T extends LoRFactionI> {
	
	static <R extends LoRCardI<T>, T extends LoRFactionI> R fromCardCode(String cardCode, Function<String, T> factionParser, CardFactory<T, R> factory) throws LoRIllegalFormatException {
		if (cardCode.length() != 7)
			throw new LoRIllegalFormatException("Card codes must be 7 characters long");
		int set = Integer.parseUnsignedInt(cardCode.substring(0, 2));
		T faction = factionParser.apply(cardCode.substring(2, 4));
		if (faction == null)
			throw new LoRIllegalFormatException("Unknown Faction Short Code: \"" + cardCode.substring(2, 4) + "\"");
		int id = Integer.parseUnsignedInt(cardCode.substring(4, 7));
		return factory.apply(set, faction, id);
	}
	
	int getSet();
	
	T getFaction();
	
	int getCardNumber();
	
	default String getCardCode() {
		return String.format("%02d", getSet()) + getFaction().getShortCode() + String.format("%03d", getCardNumber());
	}
	
	default boolean isValid() {
		int set = getSet();
		T faction = getFaction();
		int id = getCardNumber();
		return set >= 0 && set < 100 && faction != null && faction.isValid() && id >= 0 && id < 1000;
	}
	
}
