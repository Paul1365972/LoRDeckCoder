package io.github.paul1365972.lordeckcoder.coder;

import io.github.paul1365972.lordeckcoder.objects.LoRFactionI;

import java.util.Objects;

class SetFaction {
	private int set;
	private LoRFactionI faction;
	
	public SetFaction(int set, LoRFactionI faction) {
		this.set = set;
		this.faction = Objects.requireNonNull(faction);
	}
	
	public int getSet() {
		return set;
	}
	
	public LoRFactionI getFaction() {
		return faction;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SetFaction that = (SetFaction) o;
		return getSet() == that.getSet() &&
				getFaction() == that.getFaction();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getSet(), getFaction());
	}
}
