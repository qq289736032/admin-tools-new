package com.lingyu.noark.amf3.parser;

import java.util.ArrayList;
import java.util.List;

/**
 * AVM+ Serialization optimizes object serialization by serializing the traits
 * of a type once, and then sending only the values of each instance of the type
 * as it occurs in the stream.
 * 
 * @author Peter Farland
 * @exclude
 */
public class TraitsInfo {
	private final String className;
	private final boolean dynamic;
	private List<String> properties;

	public TraitsInfo(String className, boolean dynamic, boolean externalizable, int initialCount) {
		this(className, dynamic, externalizable, new ArrayList<String>(initialCount));
	}

	public TraitsInfo(String className, boolean dynamic, boolean externalizable, List<String> properties) {
		if (className == null)
			className = "";

		this.className = className;

		if (properties == null)
			properties = new ArrayList<>();

		this.properties = properties;
		this.dynamic = dynamic;
	}

	public boolean isDynamic() {
		return dynamic;
	}

	public void addProperty(String name) {
		properties.add(name);
	}

	public String getProperty(int i) {
		return properties.get(i);
	}

	public List<String> getProperties() {
		return properties;
	}

	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}

		if (obj instanceof TraitsInfo) {
			TraitsInfo other = (TraitsInfo) obj;

			if (!this.className.equals(other.className)) {
				return false;
			}

			if (!(this.dynamic == other.dynamic)) {
				return false;
			}

			List<String> thisProperties = this.properties;
			List<String> otherProperties = other.properties;

			if (thisProperties != otherProperties) {
				int thisCount = thisProperties.size();

				if (thisCount != otherProperties.size()) {
					return false;
				}

				for (int i = 0; i < thisCount; i++) {
					Object thisProp = thisProperties.get(i);
					Object otherProp = otherProperties.get(i);
					if (thisProp != null && otherProp != null) {
						if (!thisProp.equals(otherProp)) {
							return false;
						}
					}
				}
			}

			return true;
		}

		return false;
	}

	/**
	 * Instances of types with the same classname and number of properties may
	 * return the same hash code, however, an equality test will fully test
	 * whether they match exactly on individual property names.
	 */
	public int hashCode() {
		int c = className.hashCode();
		c = dynamic ? c << 2 : c << 1;
		c = c | (properties.size() << 24);
		return c;
	}
}