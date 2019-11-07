package com.qrata.enums;

public enum Status {

    /**
     * constant for active status
     */
    ACTIVE((short) 1),
    /**
     * for inactive status
     */
    INACTIVE((short) 2),
    /**
     * for status deleted
     */
    DELETED((short) 3);

    private final Short value;

    Status(Short value) {
    	this.value = value;
    }

    /**
     * @return constant
     */
    public Short getValue() {
	return value;
    }
}
