package com.insonix.qrata.pagination;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;

/**
 * @author Gurminder Singh
 *
 */
public class PaginationUtility {

	public static final int pageSize = 10;
	
	/**
	 * 
	 * @param request
	 * @param displayTableId
	 * @return starting offset Integer
	 */
	public static int getStartOffsetByRequestAndTableId(HttpServletRequest request, String displayTableId) {
		int start = 0;
		String page = request.getParameter((new ParamEncoder(displayTableId).
				encodeParameterName(TableTagParameters.PARAMETER_PAGE)));
		if(page != null)
			start = (Integer.parseInt(page) - 1) * pageSize;
		return start;
	}
	
	/**
	 * 
	 * @param request
	 * @param displayTableId
	 * @return sort field String
	 */
	public static String getSortFieldByRequestAndTableId(HttpServletRequest request, String displayTableId) {
		String sortField = request.getParameter((new ParamEncoder(displayTableId).
				encodeParameterName(TableTagParameters.PARAMETER_SORT)));
		return sortField;
	}
	
	/**
	 * 
	 * @param request
	 * @param displayTableId
	 * @return sort order String
	 */
	public static String getSortOrderByRequestAndTableId(HttpServletRequest request, String displayTableId) {
		String sortOrder = request.getParameter((new ParamEncoder(displayTableId).
				encodeParameterName(TableTagParameters.PARAMETER_ORDER)));
		if(!StringUtils.isEmpty(sortOrder)) {
			int order = Integer.parseInt(sortOrder);
			switch(order) {
				case 1: 
					sortOrder = "asc";
					break;
				case 2: 
					sortOrder = "desc";
					break;
				default: 
					sortOrder = "asc";
			}
		}
		return sortOrder;
	}
	
	public static int getCurentPage(HttpServletRequest request, String displayTableId) {
		String pageNumber = request.getParameter((new ParamEncoder(displayTableId).
				encodeParameterName(TableTagParameters.PARAMETER_PAGE)));
		return pageNumber == null ? 0 : Integer.parseInt(pageNumber);
		
	}
	
	public static String getCurrentPageRequestParam(HttpServletRequest request, final String displayTableId){
		String currentPageCode = generateEncodedTableId(displayTableId, TableTagParameters.PARAMETER_PAGE);
		String currentPageValue = request.getParameter(currentPageCode);
		if(currentPageValue == null){
			currentPageValue = "1";
		}
		return currentPageCode+"="+currentPageValue;
	}
	
	public static String getOrderRequestParam(HttpServletRequest request, final String displayTableId) {
		String currentOrderCode = generateEncodedTableId(displayTableId, TableTagParameters.PARAMETER_ORDER);
		String currentOrderValue = request.getParameter(currentOrderCode);
		if(currentOrderValue == null){
			currentOrderValue = "1";
		}
		return currentOrderCode+"="+currentOrderValue;
	}
	
	public static String getSortFieldRequestParam(HttpServletRequest request, final String displayTableId, String sortField){
		String currentSortFieldCode = generateEncodedTableId(displayTableId, TableTagParameters.PARAMETER_SORT);
		String curretSortFieldValue = sortField;
		return currentSortFieldCode+"="+curretSortFieldValue;
	}
	
	public static String getSortUsingNameRequestParam(HttpServletRequest request, final String displayTableId) {
		String currentSortUsingNameCode = generateEncodedTableId(displayTableId, TableTagParameters.PARAMETER_SORTUSINGNAME);
		String currentSortUsingNameValue = request.getParameter(currentSortUsingNameCode);
		if(currentSortUsingNameValue == null){
			currentSortUsingNameValue = "1";
		}
		return currentSortUsingNameCode+"="+currentSortUsingNameValue;
	}
	
	private static String generateEncodedTableId(String displayTableId, String tableTagParameter){
		String encodeId = new ParamEncoder(displayTableId).encodeParameterName(tableTagParameter);
		return encodeId;
	}
}
