package io.github.paul1365972.lordeckcoder.objects.impl;

import io.github.paul1365972.lordeckcoder.coder.FormatVersion;
import io.github.paul1365972.lordeckcoder.objects.generic.GenericLoRDeck;

public class LoRDeck extends GenericLoRDeck<LoRCard, LoRFaction> {
	
	public LoRDeck(FormatVersion formatVersion) {
		super(formatVersion);
	}
	
}
