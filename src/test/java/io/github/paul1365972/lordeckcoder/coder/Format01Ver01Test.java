package io.github.paul1365972.lordeckcoder.coder;

import io.github.paul1365972.lordeckcoder.LoRDeckCoder;
import io.github.paul1365972.lordeckcoder.exceptions.LoRCodingException;
import io.github.paul1365972.lordeckcoder.objects.impl.LoRDeck;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class Format01Ver01Test {
	
	@Test
	public void reencoding_recommended_test() throws LoRCodingException {
		for (Map.Entry<String, LoRDeck> entry : TestCodeData.TEST_DECKS.entrySet()) {
			String code = entry.getKey();
			LoRDeck deck = entry.getValue();
			Assert.assertEquals(LoRDeckCoder.toDeck(code), LoRDeckCoder.toDeck(LoRDeckCoder.toCode(deck, FormatVersion.LIVE)));
		}
	}
	
	@Test
	public void decoding_recommended_test() throws LoRCodingException {
		for (Map.Entry<String, LoRDeck> entry : TestCodeData.TEST_DECKS.entrySet()) {
			String code = entry.getKey();
			LoRDeck deck = entry.getValue();
			Assert.assertEquals(deck, LoRDeckCoder.toDeck(code));
		}
	}
	
}
