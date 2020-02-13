/**
 * 
 */
package com.insonix.qrata.constants;

/**
 * @author raman
 * 
 */
public enum ReadStatus {
    /**
     * constant for active status
     */
    READ((int) 1),
    /**
     * for inactive status
     */
    UNREAD((int) 0);
   
    private final Integer value;

    ReadStatus(Integer value) {
    	this.value = value;
    }

    /**
     * @return constant
     */
    public Integer getValue() {
    	return value;
    }

   
}
