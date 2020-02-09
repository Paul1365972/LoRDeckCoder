package io.github.paul1365972.lordeckcoder.util;

import io.github.paul1365972.lordeckcoder.exceptions.LoRIllegalFormatException;

import java.math.BigInteger;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class VarInt {
	
	private static final int SHIFT = 7;
	private static final int MASK = (1 << SHIFT) - 1;
	private static final BigInteger BI_MASK = BigInteger.valueOf(MASK);
	
	public static BigInteger toBigInt(Supplier<Byte> supplier) throws LoRIllegalFormatException {
		BigInteger result = BigInteger.ZERO;
		int shift = 0;
		Byte b;
		do {
			b = supplier.get();
			if (b == null) {
				if (shift == 0)
					return null;
				else
					throw new LoRIllegalFormatException("VarInt incomplete");
			}
			result = result.or(BigInteger.valueOf(b & MASK).shiftLeft(shift));
			shift += SHIFT;
		} while (b < 0);
		return result;
	}
	
	public static void toBytes(BigInteger value, Consumer<Byte> consumer) {
		for (boolean more = true; more; ) {
			BigInteger buf = value.and(BI_MASK);
			value = value.shiftRight(SHIFT);
			more = value.compareTo(BigInteger.ZERO) != 0;
			consumer.accept((more ? buf.setBit(7) : buf).byteValue());
		}
	}
	
}
