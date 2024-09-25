package com.kh.community.model.vo;

public class Category {
	private int tabNumber;
	private String tabName;
	
	public Category() {
		super();
	}
	
	public Category(int tabNumber, String tabName) {
		super();
		this.tabNumber = tabNumber;
		this.tabName = tabName;
	}
	
	public int getTabNumber() {
		return tabNumber;
	}
	public void setTabNumber(int tabNumber) {
		this.tabNumber = tabNumber;
	}
	public String getTabName() {
		return tabName;
	}
	public void setTabName(String tabName) {
		this.tabName = tabName;
	}
	
	@Override
	public String toString() {
		return "Category [tabNumber=" + tabNumber + ", tabName=" + tabName + "]";
	}
	
}
