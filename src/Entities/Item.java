package Entities;

import Exceptions.IllegalInputException;

public class Item {
	private String id;
	private String title;
	private String description;
	private String supplierId;
	private int categoryId;
	
	private Item(String id, String title, String desc, int c_id, String supplierId) {
		this.id = id;
		this.title = title;
		this.description = desc;
		this.categoryId = c_id;
		this.supplierId = supplierId;
	}
	
	private static boolean checkId(String id_) {
		if(id_.length() != 12) {
			return false;
		}
		if(!id_.matches("\\w+")) {
			return false;
		}
		return true;
	}
	
	private static boolean checkTitle(String name_) {
		if(name_.length() < 2 || !name_.matches("\\D+")) {
			return false;
		}
		return true;
	}
	
	public void setTitle(String name_) throws IllegalInputException {
		if(!checkTitle(name_))
			throw new IllegalInputException("Title needs at least 2 Characters", name_);
		this.title = name_;
	}
	
	public void setDescription(String desc) {
		this.description = desc;
	}
	
	public void setCategoryId(int category) {
		this.categoryId = category;
	}
	
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public int getCategoryId() {
		return this.categoryId;
	}
	
	public String getSupplierId() {
		return this.supplierId;
	}
	
	@Override 
	public String toString() {
		return "ID: " + this.id + ", Title: " + this.title;
	}
	
	/**
	 * Inner Builder Class for creating Supplier Objects with Error Handling for better readability
	 * @author Max Mueller
	 *
	 */
	public static class Builder {
		private String i;
		private String t;
		private String desc = "not set";
		private String supplier_id_b;
		private int c_id;
		public Builder() {
			
		}
		
		public Builder setId(String id_) {
			if(!checkId(id_))
				throw new IllegalArgumentException("Invalid Id");
			this.i = id_;
			return this;
		}
		
		public Builder setTitle(String name_) throws IllegalInputException {
			if(!checkTitle(name_))
				throw new IllegalInputException("Title needs at least 2 Characters", name_);
			this.t = name_;
			return this;
		}
		
		public Builder setDescription(String desc) {
			this.desc = desc;
			return this;
		}
		
		public Builder setCategoryId(int c_id) {
			this.c_id = c_id; //TODO: Add Email Check
			return this;
		}
		
		public Builder setSupplierId(String supplier_id) {
    		this.supplier_id_b = supplier_id;
    		return this;
    	}
		
		public Item build() {
			return new Item(i, t, desc, c_id, supplier_id_b);
		}
	}
}
