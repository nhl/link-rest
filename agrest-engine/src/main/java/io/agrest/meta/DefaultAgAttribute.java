package io.agrest.meta;

import io.agrest.property.PropertyReader;

/**
 * @since 1.12
 */
public class DefaultAgAttribute implements AgAttribute {

	private final String name;
	private final Class<?> javaType;
	private final PropertyReader propertyReader;

	public DefaultAgAttribute(String name, Class<?> javaType, PropertyReader propertyReader) {
		this.name = name;
		this.javaType = javaType;
		this.propertyReader = propertyReader;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Class<?> getType() {
		return javaType;
	}

	/**
	 * @since 2.10
	 */
    @Override
    public PropertyReader getPropertyReader() {
        return propertyReader;
    }

    @Override
	public String toString() {
		return getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(this)) + "[" + getName() + "]";
	}
}
