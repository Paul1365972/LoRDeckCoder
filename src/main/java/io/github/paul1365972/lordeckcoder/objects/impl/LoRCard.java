package io.github.paul1365972.lordeckcoder.objects.impl;

import io.github.paul1365972.lordeckcoder.exceptions.LoRIllegalFormatException;
import io.github.paul1365972.lordeckcoder.objects.LoRCardI;
import io.github.paul1365972.lordeckcoder.objects.generic.GenericLoRCard;

public class LoRCard extends GenericLoRCard<LoRFaction> {
	
	public LoRCard(int set, LoRFaction faction, int cardNumber) throws LoRIllegalFormatException {
		super(set, faction, cardNumber);
	}
	
	public LoRCard(int set, int factionId, int cardNumber) throws LoRIllegalFormatException {
		this(set, LoRFaction.fromId(factionId), cardNumber);
	}
	
	public LoRCard(int set, String faction, int cardNumber) throws LoRIllegalFormatException {
		this(set, LoRFaction.fromShortCode(faction), cardNumber);
	}
	
	// Strange mismatch between IntelliJ IDEA code completion and javac compiler
	@SuppressWarnings("RedundantTypeArguments")
	public static LoRCard fromCardCode(String cardCode) throws LoRIllegalFormatException {
		return LoRCardI.<LoRCard, LoRFaction>fromCardCode(cardCode, LoRFaction::fromShortCode, LoRCard::new);
	}
	
}
