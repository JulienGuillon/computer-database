package com.excilys.computerdatabase.utils;

/**
 * @author Guillon Julien
 *
 * 2 mars 2017
 */
public class Page {
    private int size = 10;
    private int pageIndex = 0;
    private int numberOfPages;
    private int numberOfElements;

    /**
     *
     */
    private Page() {

    }

    /**
     * @param size : the size to set
     */
    public void setSize(int size) {
        this.size = size;
        this.pageIndex = 0;
        this.numberOfPages = this.numberOfElements / this.size;
    }

    /**
     *
     */
    public void nextPage() {
        this.pageIndex = pageIndex + 1;
    }

    /**
     *
     */
    public void previousPage() {
        this.pageIndex = (pageIndex - 1) >= 0 ? (pageIndex - 1) * size : 0;
    }

    /**
     * @return the index of the current page
     */
    public int getPageIndex() {
        return this.pageIndex + 1;
    }

    /**
     * @param pageIndex the pageIndex to set
     */
    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    /**
     * @return the offset
     */
    public int getOffset() {
        return this.pageIndex * this.size;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @return the numberOfPages
     */
    public int getNumberOfPages() {
        this.numberOfPages = this.numberOfElements / this.size;
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }


    public void setNumberOfElements(int numberOfComputers) {
        this.numberOfElements = numberOfComputers;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    public static class Builder {
        private Page page;

        /**
         *
         */
        public Builder() {
            page = new Page();
        }
        
        public Builder withSize(int size) {
            this.page.size = size;
            return this;
        }
        
        public Builder withNumberOfElements(int numberOfElements) {
            this.page.numberOfElements = numberOfElements;
            this.page.numberOfPages = this.page.numberOfElements / this.page.size;
            return this;
        }
        
        public Builder withPageIndex(int pageIndex) {
            this.page.pageIndex = pageIndex;
            return this;
        }

        /**
         *
         * @return a page
         */
        public Page build() {
            return page;
        }
    }}
