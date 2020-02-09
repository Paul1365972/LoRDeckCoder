package io.github.paul1365972.lordeckcoder.coder;

import io.github.paul1365972.lordeckcoder.exceptions.LoRCodingException;
import io.github.paul1365972.lordeckcoder.exceptions.LoRUnsupportedFormatException;
import io.github.paul1365972.lordeckcoder.objects.LoRCardI;
import io.github.paul1365972.lordeckcoder.objects.LoRDeckI;
import io.github.paul1365972.lordeckcoder.objects.LoRFactionI;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class LoRCoder {
	
	private static final Map<FormatVersion, LoRFormatter> FORMATS = new HashMap<>();
	
	static {
		register(new LoRFormat01Ver01());
	}
	
	public static <T> T decode(Supplier<Byte> bytes, LoRDecoder<T> decoder) throws LoRCodingException {
		FormatVersion formatVersion = new FormatVersion(bytes.get());
		return get(formatVersion).decode(bytes, decoder);
	}
	
	public static <T extends LoRCardI<U>, U extends LoRFactionI> void encode(LoRDeckI<T, U> deck, FormatVersion formatVersion, Consumer<Byte> builder) throws LoRUnsupportedFormatException {
		builder.accept(formatVersion.getPacked());
		get(formatVersion).encode(deck, builder);
	}
	
	public static LoRFormatter get(FormatVersion formatVersion) throws LoRUnsupportedFormatException {
		LoRFormatter formatter = FORMATS.get(formatVersion);
		if (formatter == null)
			throw new LoRUnsupportedFormatException("Unsupported Deck Format/Version " + formatVersion.getFormat() + "/" + formatVersion.getVersion());
		return formatter;
	}
	
	public static void register(LoRFormatter formatter) {
		FORMATS.put(formatter.getFormatVersion(), formatter);
	}
	
}
