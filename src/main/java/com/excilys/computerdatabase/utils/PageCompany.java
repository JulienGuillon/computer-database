package com.excilys.computerdatabase.utils;

/**
 * @author Guillon Julien
 *
 *         2017-03-01
 */
public class PageCompany {

    private static int size = 10;
    private static int offset = 0;
    private static int pageIndex = 0;
    private static int numberOfPages;
    private static int numberOfCompanies;

    /**
     *
     */
    public PageCompany() {

    }

    /**
     * @param size : the size to set
     */
    public static void setSize(int size) {
        PageCompany.size = size;
        PageCompany.pageIndex = 0;
        PageCompany.offset = 0;
        PageCompany.numberOfPages = PageCompany.numberOfCompanies / PageCompany.size;
    }

    /**
     *
     */
    public static void nextPage() {
        PageCompany.pageIndex = pageIndex + 1;
        PageCompany.offset = pageIndex * size;

    }

    /**
     *
     */
    public static void previousPage() {
        PageCompany.pageIndex = (pageIndex - 1) >= 0 ? (offset - 1) * size : 0;
        PageCompany.offset = pageIndex * size;
    }

    /**
     * @return the index of the current page
     */
    public static int getPageIndex() {
        return PageCompany.pageIndex + 1;
    }

    /**
     * @param pageIndex the pageIndex to set
     */
    public static void setPageIndex(int pageIndex) {
        PageCompany.pageIndex = pageIndex;
        PageCompany.offset = pageIndex * size;
    }

    /**
     * @param offset the offset to set
     */
    public static void setOffset(int offset) {
        PageCompany.offset = offset;
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
        return numberOfPages;
    }

    /**
     * @param numberOfPages the numberOfPages to set
     */
    public static void setNumberOfPages(int numberOfPages) {
        PageCompany.numberOfPages = numberOfPages;
    }

    /**
     * @param numberOfComputers the numberOfComputers to set
     */
    public static void setNumberOfCompanies(int numberOfComputers) {
        PageCompany.numberOfCompanies = numberOfComputers;
        PageCompany.numberOfPages = PageCompany.numberOfCompanies / PageCompany.size;
    }

    /**
     * @return the numberOfComputers
     */
    public static int getNumberOfCompanies() {
        return numberOfCompanies;
    }
}