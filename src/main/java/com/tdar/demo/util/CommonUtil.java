package com.tdar.demo.util;

public class CommonUtil {
    /**
     * Cal how many pages there are
     *
     * @param rowCount int
     * @param rowCountPerPage int
     * @return int  返
     */
    public static int getPageCount(int rowCount, int rowCountPerPage) {
        if (rowCount % rowCountPerPage == 0) {
            return rowCount/rowCountPerPage;
        }
        return (int) Math.ceil((double) rowCount/rowCountPerPage);
    }
}
