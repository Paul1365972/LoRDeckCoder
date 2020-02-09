package io.github.paul1365972.lordeckcoder.coder;

import io.github.paul1365972.lordeckcoder.exceptions.LoRCodingException;
import io.github.paul1365972.lordeckcoder.objects.LoRCardI;
import io.github.paul1365972.lordeckcoder.objects.LoRDeckI;
import io.github.paul1365972.lordeckcoder.objects.LoRFactionI;

import java.util.function.Consumer;
import java.util.function.Supplier;

public interface LoRFormatter {
	
	<T, U> T decode(Supplier<Byte> bytes, LoRDecoder<T> decoder) throws LoRCodingException;
	
	<T extends LoRCardI<U>, U extends LoRFactionI> void encode(LoRDeckI<T, U> deck, Consumer<Byte> builder);
	
	FormatVersion getFormatVersion();
	
}
