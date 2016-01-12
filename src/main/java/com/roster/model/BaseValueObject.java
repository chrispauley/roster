package com.roster.model;

import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;

@XmlAccessorType(XmlAccessType.FIELD)
public class BaseValueObject {
	 @XmlTransient
	private long valueObjectId;

	/**
	 * @return the valueObjectId
	 */
	public long getValueObjectId() {
		return valueObjectId;
	}

	/**
	 * @param valueObjectId the valueObjectId to set
	 */
	public void setValueObjectId(long valueObjectId) {
		this.valueObjectId = valueObjectId;
	}
}
