package com.excilys.computerdatabase.pagination;

import java.util.List;

/**
 * @author jlng
 *
 * 2017-03-21
 */

public class Pagination<T> {
    private int elementsByPage = 10;
    private int page = 0;
    private int totalElements;
    private int totalPages;
    private String filter = "";
    private List<T> elements;
    
    public Pagination() {
    }

    
    public Pagination(int pElemByPage) {
        elementsByPage = pElemByPage;
    }
    
    public void setTotalElements(int totalElem) {
        this.totalElements = totalElem;
        calculateNbPages();
    }

    public void setElementsByPage(int pElemByPage) {
        elementsByPage = pElemByPage;
        calculateNbPages();
    }

    private void calculateNbPages() {
        if (totalElements != 0 && elementsByPage != 0) {
            totalPages = (totalElements + elementsByPage - 1) / elementsByPage;
        } else {
            totalPages = 1;
        }
    }

    public int getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int nbPages) {
        this.totalPages = nbPages;
    }

    public List<T> getElements() {
        return elements;
    }

    public void setElements(List<T> elems) {
        this.elements = elems;
    }

    public int getElementsByPage() {
        return elementsByPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
    
    /**
	 * @return the filter
	 */
	public String getFilter() {
		return filter;
	}
	
	/**
	 * @param filter the filter to set
	 */
	public void setFilter(String filter) {
		this.filter = filter;
	}
}