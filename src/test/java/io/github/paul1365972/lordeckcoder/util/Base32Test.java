package io.github.paul1365972.lordeckcoder.util;

import io.github.paul1365972.lordeckcoder.coder.TestCodeData;
import io.github.paul1365972.lordeckcoder.exceptions.LoRIllegalFormatException;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

public class Base32Test {
	
	@Test
	public void base32_encode_decode() throws LoRIllegalFormatException {
		for (String code : TestCodeData.TEST_DECKS.keySet()) {
			Deque<Byte> bytes = new LinkedList<>();
			Base32.decode(code, bytes::addLast);
			String reCode = Base32.encode(bytes::pollFirst);
			assert code.equals(reCode);
		}
	}
}
