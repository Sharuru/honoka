/**
 * Pager.java
 * @Package com.tzg.common 
 * 项目　　：田字格管理平台
 * 机能　　：翻页计算
 * 説明　　：传入当前页和总记录数，计算页数
 * 備考　　：このプログラムはサンプルです。
 * 作成　　：[日付] 2015/08/13 [氏名] 樊国敬
 * 履歴：
 * [NO]  [日付]　  [Ver]　    [更新者]　  [内容]
 *　1   2015/08/13 V10L1     MBP）樊国敬　初版。
 * Copyright (C) 2016, MBP All Rights Reserved.
 */
package com.honoka.common;

/**
 * 翻页工具类
 *
 * @author MBP 樊国敬
 * @see com.honoka.common
 * @version  1.0
 */
public class Pager {
	
	private int first;
	private int last;
	private int previous;
	private int next;
	private int count;
	private int size;
	private int current;
	private String pageUrl;
	private int start;
	private int end;
	
	public Pager(){
		this.current = 1;
		this.size = ContextUtil.PAGE_SIZE;
	}
	public Pager(int current, int size, int count, String pageUrl){
		if(size == 0){
			this.size = ContextUtil.PAGE_SIZE;
		}else {
			this.size = size;
		}
		this.pageUrl = pageUrl;
		this.current = current;
		this.count = count;
		if(current == 1){
			this.first = 0;
			this.previous = 0;
		}else {
			this.first = 1;
			this.previous = current - 1;
		}
		int tmp = this.count%this.size;
		int pages = this.count/this.size + (tmp == 0 ? 0 : 1);
		if(this.current == pages){
			this.last = 0;
			this.next = 0;
		}else {
			this.next = this.current + 1;
			this.last = pages;
		}
		this.start = (this.current-1) * this.size ;
		this.end = this.start + this.size;
	}
	public int getFirst() {
		return first;
	}
	public void setFirst(int first) {
		this.first = first;
	}
	public int getLast() {
		return last;
	}
	public void setLast(int last) {
		this.last = last;
	}
	public int getPrevious() {
		return previous;
	}
	public void setPrevious(int previous) {
		this.previous = previous;
	}
	public int getNext() {
		return next;
	}
	public void setNext(int next) {
		this.next = next;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getCurrent() {
		return current;
	}
	public void setCurrent(int current) {
		this.current = current;
	}
	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	@Override
	public String toString() {
		return "Pager [first=" + first + ", last=" + last + ", previous="
				+ previous + ", next=" + next + ", count=" + count + ", size="
				+ size + ", current=" + current + ", pageUrl=" + pageUrl
				+ ", start=" + start + ", end=" + end + "]";
	}
	
	public static void main(String[] args){
		for(int i = 1; i <= 10
		; i++){
			System.out.println(new Pager(i, 10, 10, null));
		}
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + count;
		result = prime * result + current;
		result = prime * result + end;
		result = prime * result + first;
		result = prime * result + last;
		result = prime * result + next;
		result = prime * result + ((pageUrl == null) ? 0 : pageUrl.hashCode());
		result = prime * result + previous;
		result = prime * result + size;
		result = prime * result + start;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pager other = (Pager) obj;
		if (count != other.count)
			return false;
		if (current != other.current)
			return false;
		if (end != other.end)
			return false;
		if (first != other.first)
			return false;
		if (last != other.last)
			return false;
		if (next != other.next)
			return false;
		if (pageUrl == null) {
			if (other.pageUrl != null)
				return false;
		} else if (!pageUrl.equals(other.pageUrl))
			return false;
		if (previous != other.previous)
			return false;
		if (size != other.size)
			return false;
		if (start != other.start)
			return false;
		return true;
	}
	
}
