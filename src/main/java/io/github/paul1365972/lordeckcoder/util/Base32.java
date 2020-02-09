package io.github.paul1365972.lordeckcoder.util;

import io.github.paul1365972.lordeckcoder.exceptions.LoRIllegalFormatException;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Base32 {
	
	private static final char[] CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567".toCharArray();
	private static final int SHIFT = 5;
	private static final int MASK = (1 << SHIFT) - 1;
	private static final Map<Character, Integer> CHAR_ID_MAP = new HashMap<>();
	
	static {
		for (int i = 0; i < CHARS.length; i++) {
			CHAR_ID_MAP.put(CHARS[i], i);
		}
	}
	
	public static void decode(String str, Consumer<Byte> consumer) throws LoRIllegalFormatException {
		str = str.toUpperCase().replaceAll("[^A-Z2-7]", "");
		
		int buffer = 0;
		int pos = 0;
		
		for (char c : str.toCharArray()) {
			int id = CHAR_ID_MAP.getOrDefault(c, -1);
			if (id == -1)
				throw new LoRIllegalFormatException("Bad Base32 encoding");
			buffer <<= SHIFT;
			buffer |= id;
			pos += SHIFT;
			if (pos >= Byte.SIZE) {
				consumer.accept((byte) (buffer >>> (pos - Byte.SIZE)));
				pos -= Byte.SIZE;
			}
		}
		//if (pos > 0)
		//	consumer.accept((byte) buffer);
	}
	
	public static String encode(Supplier<Byte> bytes) {
		StringBuilder sb = new StringBuilder();
		
		int buffer = 0;
		int pos = 0;
		for (Byte b; (b = bytes.get()) != null; ) {
			buffer <<= Byte.SIZE;
			buffer |= b & 0xFF;
			pos += Byte.SIZE;
			while (pos >= SHIFT) {
				sb.append(CHARS[(buffer >>> (pos - SHIFT)) & MASK]);
				pos -= SHIFT;
			}
		}
		if (pos > 0)
			sb.append(CHARS[(buffer << (SHIFT - pos)) & MASK]);
		return sb.toString();
	}
	
}
