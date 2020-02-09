package io.github.paul1365972.lordeckcoder.coder;

import io.github.paul1365972.lordeckcoder.exceptions.LoRIllegalFormatException;
import io.github.paul1365972.lordeckcoder.objects.impl.LoRCard;
import io.github.paul1365972.lordeckcoder.objects.impl.LoRDeck;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TestCodeData {
	
	public static final Map<String, LoRDeck> TEST_DECKS;
	
	static {
		Map<String, LoRDeck> decks = new HashMap<>();
		
		LoRDeck current = null;
		boolean cardMode = false;
		try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/DeckCodesTestData.txt")))) {
			for (String line; (line = reader.readLine()) != null; ) {
				if (!cardMode) {
					current = new LoRDeck(FormatVersion.LIVE);
					decks.put(line, current);
					cardMode = true;
				} else {
					if (!line.isEmpty()) {
						int sepIndex = line.indexOf(':');
						int amount = Integer.parseUnsignedInt(line.substring(0, sepIndex));
						String cardCode = line.substring(sepIndex + 1);
						current.addCards(LoRCard.fromCardCode(cardCode), amount);
					} else {
						cardMode = false;
					}
				}
			}
		} catch (IOException | LoRIllegalFormatException e) {
			e.printStackTrace();
			decks = Collections.emptyMap();
		}
		
		TEST_DECKS = Collections.unmodifiableMap(decks);
	}
	
	@Test
	public void test_code_data_not_empty() {
		assert !TEST_DECKS.isEmpty();
	}
}
