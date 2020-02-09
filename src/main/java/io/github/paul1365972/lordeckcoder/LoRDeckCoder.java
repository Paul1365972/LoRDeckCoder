package io.github.paul1365972.lordeckcoder;

import io.github.paul1365972.lordeckcoder.coder.FormatVersion;
import io.github.paul1365972.lordeckcoder.coder.LoRCoder;
import io.github.paul1365972.lordeckcoder.coder.LoRDecoder;
import io.github.paul1365972.lordeckcoder.exceptions.LoRCodingException;
import io.github.paul1365972.lordeckcoder.objects.impl.LoRCard;
import io.github.paul1365972.lordeckcoder.objects.impl.LoRDeck;
import io.github.paul1365972.lordeckcoder.util.Base32;

import java.io.ByteArrayOutputStream;
import java.util.function.Supplier;

public class LoRDeckCoder {
	
	private static final LoRDecoder<LoRDeck> BASIC_DECODER = LoRDecoder.from(LoRDeck::new,
			(deck, set, factionId, cardNumber, amount) ->
					deck.addCards(new LoRCard(set, factionId, cardNumber), amount)
	);
	
	public static LoRDeck toDeck(String code) throws LoRCodingException {
		return toDeck(code, BASIC_DECODER);
	}
	
	public static <T> T toDeck(String code, LoRDecoder<T> decoder) throws LoRCodingException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Base32.decode(code, baos::write);
		return toDeck(baos.toByteArray(), decoder);
	}
	
	public static LoRDeck toDeck(byte[] bytes) throws LoRCodingException {
		return toDeck(bytes, BASIC_DECODER);
	}
	
	public static <T> T toDeck(byte[] bytes, LoRDecoder<T> decoder) throws LoRCodingException {
		return LoRCoder.decode(bytesToSupplier(bytes), decoder);
	}
	
	public static String toCode(LoRDeck deck, FormatVersion formatVersion) throws LoRCodingException {
		return Base32.encode(bytesToSupplier(toCodeBytes(deck, formatVersion)));
	}
	
	public static byte[] toCodeBytes(LoRDeck deck, FormatVersion formatVersion) throws LoRCodingException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		LoRCoder.encode(deck, formatVersion, baos::write);
		return baos.toByteArray();
	}
	
	private static Supplier<Byte> bytesToSupplier(byte[] bytes) {
		return new Supplier<Byte>() {
			int index = 0;
			
			@Override
			public Byte get() {
				return index < bytes.length ? bytes[index++] : null;
			}
		};
	}
}
