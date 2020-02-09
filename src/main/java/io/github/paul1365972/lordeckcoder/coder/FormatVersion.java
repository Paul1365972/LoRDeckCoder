package io.github.paul1365972.lordeckcoder.coder;

import java.util.Objects;

public class FormatVersion {
	
	public static final FormatVersion F01V01 = new FormatVersion(1, 1);
	public static final FormatVersion LIVE = F01V01, PBE = F01V01, EXPERIMENTAL = F01V01;
	
	
	private byte formatVersion;
	
	public FormatVersion(byte formatVersion) {
		this.formatVersion = formatVersion;
	}
	
	public FormatVersion(int format, int version) {
		this((byte) ((version << 4) | format));
	}
	
	public byte getPacked() {
		return formatVersion;
	}
	
	public int getFormat() {
		return formatVersion & 0xF;
	}
	
	public int getVersion() {
		return (formatVersion >> 4) & 0xF;
	}
	
	@Override
	public String toString() {
		return "FormatVersion{" +
				"format=" + getFormat() +
				", version=" + getVersion() +
				'}';
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		FormatVersion that = (FormatVersion) o;
		return getPacked() == that.getPacked();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getPacked());
	}
	
}
