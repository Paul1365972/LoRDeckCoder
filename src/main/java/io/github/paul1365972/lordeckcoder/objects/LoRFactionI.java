package io.github.paul1365972.lordeckcoder.objects;

public interface LoRFactionI {
	
	int getId();
	
	String getShortCode();
	
	default boolean isValid() {
		return getShortCode().length() == 2;
	}
	
}
