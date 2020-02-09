package io.github.paul1365972.lordeckcoder.objects.impl;

import io.github.paul1365972.lordeckcoder.objects.LoRFactionI;

import java.util.HashMap;
import java.util.Map;

public enum LoRFaction implements LoRFactionI {
	DEMACIA(0, "DE"),
	FRELJORD(1, "FR"),
	IONIA(2, "IO"),
	NOXUS(3, "NX"),
	PILTOVER_AND_ZAUN(4, "PZ"),
	SHADOW_ISLES(5, "SI");
	
	private static final Map<Integer, LoRFaction> idMap = new HashMap<>();
	private static final Map<String, LoRFaction> shortCodeMap = new HashMap<>();
	
	static {
		for (LoRFaction faction : values()) {
			idMap.put(faction.getId(), faction);
			shortCodeMap.put(faction.getShortCode(), faction);
		}
	}
	
	private int id;
	private String shortCode;
	
	LoRFaction(int id, String shortCode) {
		this.shortCode = shortCode;
		this.id = id;
	}
	
	public static LoRFaction fromId(int id) {
		return idMap.get(id);
	}
	
	public static LoRFaction fromShortCode(String shortCode) {
		return shortCodeMap.get(shortCode.toUpperCase());
	}
	
	public int getId() {
		return id;
	}
	
	public String getShortCode() {
		return shortCode;
	}
	
	@Override
	public String toString() {
		return "LoRFaction{" +
				"id=" + id +
				", shortCode='" + shortCode + '\'' +
				'}';
	}
}
