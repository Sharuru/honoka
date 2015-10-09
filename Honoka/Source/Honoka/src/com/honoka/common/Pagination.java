/**
 * Pagination.java
 * @Package com.tzg.common 
 * 项目　　：田字格管理平台
 * 机能　　：田娃管理
 * 説明　　：田娃情报更新
 * 備考　　：このプログラムはサンプルです。
 * 作成　　：[日付] 2015/08/28 [氏名] 樊国敬
 * 履歴：
 * [NO]  [日付]　  [Ver]　    [更新者]　  [内容]
 *　1   2015/08/28 V10L1     MBP）樊国敬　初版。
 * Copyright (C) 2016, MBP All Rights Reserved.
 */
package com.honoka.common;

import java.util.List;

/**
 * 田娃情报更新.
 *
 * @author MBP 樊国敬
 * @see com.honoka.common
 * @version  1.0
 */
public class Pagination<E> {
	 
    public final static int PAGESIZE = 5;
    public final static int ELLIPSE_QUANTITY = 3;
    public final static int ENTRIES_QUANTITY = 5;
    public final static int HEAD_QUANTITY = 2;
    public final static int TAIL_QUANTITY = 2;
 
    private int page = 1; // 当前页码
    private int pagesize = PAGESIZE; // 每页大小
    private int offset = 0; // 偏移量
    private int previousPage = 1; // 前一页页码
    private int nextPage = 1; // 下一页页码
    private int totalPages = 1; // 总页码
    private int ellipseQuantity = ELLIPSE_QUANTITY; // 页码分隔符数量
    private int entriesQuantity = ENTRIES_QUANTITY; // 页码连续显示数量
    private int headQuantity = HEAD_QUANTITY; // 页首显示数量
    private int tailQuantity = TAIL_QUANTITY; // 页尾显示数量
    private int startPage = 1; // 连续显示页码起始页码
    private int endPage = 1; // 连续显示页码结束页码
    private int totalRecords = 0;    // 总记录数
    private List<E> data;    // 数据
 
    public Pagination() {
    }
 
    public Pagination(int page, int pagesize) {
    }
 
    public Pagination(int page, int pagesize, int totalRecords) {
        setPagesize(pagesize);
        setTotalRecord(totalRecords);
        setPage(page);
    }
 
    public Pagination(int page, int pagesize, int totalRecords, int ellipseQuantity) {
        this(page, pagesize, totalRecords);
        setEllipseQuantity(ellipseQuantity);
    }
 
    public int getPage() {
        return page;
    }
 
    public void setPage(int page) {
        this.page = page < 1 ? 1 : (this.totalPages > 1 && page > this.totalPages ? this.totalPages
                : page);
        parseStartPageAndEndPage();
 
        if (page > 1) {
            setOffset((this.page - 1) * getPagesize());
        }
    }
 
    public int getPagesize() {
        return pagesize;
    }
 
    public void setPagesize(int pagesize) {
        this.pagesize = pagesize < 1 ? PAGESIZE : pagesize;
 
        if (page > 1) {
            setOffset((page - 1) * this.pagesize);
        }
    }
 
    public int getOffset() {
        return offset;
    }
 
    public void setOffset(int offset) {
        this.offset = offset < 0 ? 0 : offset;
    }
 
    public int getRange() {
        return pagesize;
    }
 
    public void setRange(int range) {
        setPagesize(range);
    }
 
    public int getPreviousPage() {
        return previousPage;
    }
 
    public void setPreviousPage(int previousPage) {
        this.previousPage = (page <= 1 ? 1 : previousPage);
    }
 
    public int getNextPage() {
        return nextPage;
    }
 
    public void setNextPage(int nextPage) {
        this.nextPage = totalPages >= page ? page : nextPage;
    }
 
    public int getTotalPages() {
        return totalPages;
    }
 
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages < 1 ? 1 : totalPages;
        parseStartPageAndEndPage();
    }
 
    public int getEllipseQuantity() {
        return ellipseQuantity;
    }
 
    public void setEllipseQuantity(int ellipseQuantity) {
        this.ellipseQuantity = ellipseQuantity < 0 ? 0 : ellipseQuantity;
    }
 
    public int getEntriesQuantity() {
        return entriesQuantity;
    }
 
    public void setEntriesQuantity(int entriesQuantity) {
        this.entriesQuantity = entriesQuantity < 1 ? ENTRIES_QUANTITY : entriesQuantity;
    }
 
    public int getHeadQuantity() {
        return headQuantity;
    }
 
    public void setHeadQuantity(int headQuantity) {
        this.headQuantity = headQuantity < 1 ? HEAD_QUANTITY : headQuantity;
    }
 
    public int getTailQuantity() {
        return tailQuantity;
    }
 
    public void setTailQuantity(int tailQuantity) {
        this.tailQuantity = tailQuantity < 1 ? TAIL_QUANTITY : tailQuantity;
    }
 
    public int getStartPage() {
        return startPage;
    }
 
    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }
 
    public int getEndPage() {
        return endPage;
    }
 
    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }
 
    public int getTotalRecords() {
        return totalRecords;
    }
 
    public void setTotalRecord(int totalRecords) {
        this.totalRecords = totalRecords < 0 ? 0 : totalRecords;
        setTotalPages((int) Math.ceil((double) this.totalRecords / pagesize));
        setPreviousPage(page - 1);
        setNextPage(page + 1);
    }
 
    public List<E> getData() {
        return data;
    }
 
    public void setData(List<E> data) {
        this.data = data;
    }
 
    private void parseStartPageAndEndPage() {
        if (entriesQuantity > totalPages) {
            entriesQuantity = totalPages;
        }
 
        if (page < entriesQuantity) {
            startPage = 1;
            endPage = entriesQuantity;
        } else if (page > totalPages - entriesQuantity) {
            startPage = totalPages - entriesQuantity - 1;
            endPage = totalPages;
        } else {
            int i = (int) Math.ceil(entriesQuantity / 2);
 
            startPage = page - i;
            endPage = page + i;
        }
    }
 
}