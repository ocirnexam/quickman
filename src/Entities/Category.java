package Entities;

import Exceptions.IllegalInputException;

public class Category {
	private int id;
	private String title;
	private String supplierId;
	
	private Category(int id, String title, String supplierId) {
		this.id = id;
		this.title = title;
		this.supplierId = supplierId;
	}
	
	private static boolean checkId(int id_) {
		if(id_ < 0)
			return false;
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
	
	public void setCategoryId(String sup_id) {
		this.supplierId = sup_id;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getSupplierId() {
		return this.supplierId;
	}
	
	/**
	 * Inner Builder Class for creating Supplier Objects with Error Handling for better readability
	 * @author Max Mueller
	 *
	 */
	public static class Builder {
		private int i;
		private String t;
		private String s_id;
		public Builder() {
			
		}
		
		public Builder setId(int id_) {
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
		
		public Builder setSupplierId(String s_id) {
			this.s_id = s_id; //TODO: Add Email Check
			return this;
		}
		
		public Category build() {
			return new Category(i, t, s_id);
		}
	}
}
