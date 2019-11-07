/**
 * 
 */
package com.qrata.enums;



public enum ReviewStatus {

	ONLINE((short) 1,"ONLINE"),
    
    APPROVEL((short) 2,"WAITING APPROVAL"),
    
    INPROGRESS((short) 3,"IN PROGRESS"),	
	
    REVISE((short) 4,"REWORK"),
	
	 NEW((short) 5,"NEW"),
	 
	 OFFLINE((short) 6, "OFFLINE");

    private final Short value;

    private String name;
    
    ReviewStatus(Short value,String name) {
    	this.value = value;
    	this.name = name;
    }

    /**
     * @return constant
     */
    public Short getValue() {
    	return value;
    }

	public String getName() {
		return name;
	}
	
	public static String getStatusByStatusId(short statusId) {
		String status = ReviewStatus.NEW.getName();
		if(statusId == ReviewStatus.ONLINE.getValue()){
			status = ReviewStatus.ONLINE.getName();
		}else if(statusId == ReviewStatus.INPROGRESS.getValue()){
			status = ReviewStatus.INPROGRESS.getName();
		}else if(statusId == ReviewStatus.APPROVEL.getValue()){
			status = ReviewStatus.APPROVEL.getName();
		}else if(statusId == ReviewStatus.REVISE.getValue()){
			status = ReviewStatus.REVISE.getName();
		}else if(statusId == ReviewStatus.OFFLINE.getValue()){
			status = ReviewStatus.OFFLINE.getName();
		}return status;
	}
}
