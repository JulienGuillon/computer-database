package com.excilys.computerdatabase.utils;

/**
 * @author Guillon Julien
 *
 * 2017-03-01
 */
public class PageComputer {

    private static int size = 10;
    private static int offset = 0;
    private static int pageIndex = 0;
    private static int numberOfPages;
    private static int numberOfComputers;

    /**
     *
     */
    public PageComputer() {
    }

    /**
     * @param page the page to set
	 */
	public static void setSize(int size) {
		PageComputer.size = size;
		PageComputer.pageIndex = 0;
		PageComputer.offset = 0;
		PageComputer.numberOfPages = PageComputer.numberOfComputers / PageComputer.size;
	}

	/**
	 *
	 */
	public static void nextPage() {
		PageComputer.pageIndex = pageIndex + 1;
		PageComputer.offset = pageIndex * size;
	}

	/**
	 *
	 */
	public static void previousPage() {
		PageComputer.pageIndex = (pageIndex - 1) >= 0 ? (offset - 1) * size : 0;
		PageComputer.offset = pageIndex * size;
	}

	/**
	 * @return
	 */
	public static int getPageIndex() {
		return PageComputer.pageIndex + 1;
	}
	
	/**
	 * @param pageIndex the pageIndex to set
	 */
	public static void setPageIndex(int pageIndex) {
		PageComputer.pageIndex = pageIndex;
		PageComputer.offset = pageIndex * size;
	}
	
	/**
	 * @param page the page to set
	 */
	public static void setOffset(int offset) {
		PageComputer.offset = offset;
	}
	
	/**
	 * @return the offset
	 */
	public static int getOffset() {
		return offset;
	}
	
	/**
	 * @return the size
	 */
	public static int getSize() {
		return size;
	}
	
	/**
	 * @return the numberOfPages
	 */
	public static int getNumberOfPages() {
		return numberOfPages ;
	}

	/**
	 * @param numberOfPages the numberOfPages to set
	 */
	public static void setNumberOfPages(int numberOfPages) {
		PageComputer.numberOfPages = numberOfPages;
	}

	/**
	 * @param numberOfComputers the numberOfComputers to set
	 */
	public static void setNumberOfComputers(int numberOfComputers) {
		PageComputer.numberOfComputers = numberOfComputers;
		PageComputer.numberOfPages = PageComputer.numberOfComputers / PageComputer.size;
	}

	/**
	 * @return the numberOfComputers
	 */
	public static int getNumberOfComputers() {
        return numberOfComputers;
    }
}