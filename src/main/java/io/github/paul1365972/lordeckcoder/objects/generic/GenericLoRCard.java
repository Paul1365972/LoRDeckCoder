package io.github.paul1365972.lordeckcoder.objects.generic;

import io.github.paul1365972.lordeckcoder.exceptions.LoRIllegalFormatException;
import io.github.paul1365972.lordeckcoder.objects.LoRCardI;
import io.github.paul1365972.lordeckcoder.objects.LoRFactionI;

import java.util.Objects;

public class GenericLoRCard<T extends LoRFactionI> implements LoRCardI<T> {
	
	protected final int set;
	protected final T faction;
	protected final int cardNumber;
	
	public GenericLoRCard(int set, T faction, int cardNumber) throws LoRIllegalFormatException {
		boolean invalidSet = set < 0 || set >= 100;
		boolean nullFaction = faction == null;
		boolean invalidFaction = !nullFaction && faction.getShortCode().length() != 2;
		boolean invalidId = cardNumber < 0 || cardNumber >= 1000;
		if (invalidSet || nullFaction || invalidFaction || invalidId) {
			StringBuilder sb = new StringBuilder("Invalid card Arguments");
			if (invalidSet)
				sb.append(", set out of bounds 0-99 got ").append(set);
			if (nullFaction)
				sb.append(", faction is null");
			if (invalidFaction)
				sb.append(", faction short code not two letters got ").append(faction.getShortCode());
			if (invalidId)
				sb.append(", cardNumber out of bounds 0-999 got ").append(cardNumber);
			throw new LoRIllegalFormatException(sb.toString());
		}
		this.set = set;
		this.faction = faction;
		this.cardNumber = cardNumber;
	}
	
	public String getCardCode() {
		return String.format("%02d", set) + faction.getShortCode() + String.format("%03d", cardNumber);
	}
	
	public int getSet() {
		return set;
	}
	
	public T getFaction() {
		return faction;
	}
	
	public int getCardNumber() {
		return cardNumber;
	}
	
	@Override
	public String toString() {
		return "GenericLoRCard{" +
				"set=" + set +
				", faction=" + faction +
				", cardNumber=" + cardNumber +
				'}';
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof GenericLoRCard)) return false;
		GenericLoRCard<?> that = (GenericLoRCard<?>) o;
		return getSet() == that.getSet() &&
				getCardNumber() == that.getCardNumber() &&
				getFaction().equals(that.getFaction());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getSet(), getFaction(), getCardNumber());
	}
	
}
