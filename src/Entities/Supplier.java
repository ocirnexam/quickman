package Entities;

import java.util.ArrayList;

import Exceptions.IllegalInputException;

public class Supplier {
	private String id;
	private String name;
	private String phoneNumber;
	private String email;
	private ArrayList<Category> categories;
	private ArrayList<Item> items;
	
	private Supplier(String id, String name, String phone, String email) {
		this.id = id;
		this.name = name;
		this.phoneNumber = phone;
		this.email = email;
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
	
	private static boolean checkName(String name_) {
		if(name_.length() < 2 || !name_.matches("\\D+")) {
			return false;
		}
		return true;
	}
	
	private static boolean checkPhoneNumber(String phone_) {
		if(!phone_.matches("\\d+"))
			return false;
		return true;
	}
	
	public void setName(String name_) throws IllegalInputException {
		if(!checkName(name_))
			throw new IllegalInputException("Name needs at least 2 Characters", name_);
		this.name = name_;
	}
	
	public void setPhoneNumber(String phone_) throws IllegalInputException {
		if(!checkPhoneNumber(phone_))
			throw new IllegalInputException("Phone Number must contain only numbers", phone_);
		this.phoneNumber = phone_;
	}
	
	public void setEmail(String email_) {
		this.email = email_; //TODO: Add Email Check
	}
	
	public void addItem(Item item) {
		
	}
	
	public void deleteItem(String itemId) {
		
	}
	
	public ArrayList<Item> getItems() {
		return items;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public ArrayList<Category> getCategories() {
		return categories;
	}

	public void addCategory(Category category) {
		this.categories.add(category);
	}
	
	public void deleteCategory(int id) {
		for(Category cat : categories) {
			if(cat.getId() == id) {
				this.categories.remove(cat);
				break;
			}
		}
		
	}

	/**
	 * Inner Builder Class for creating Supplier Objects with Error Handling for better readability
	 * @author Max Mueller
	 *
	 */
	public static class Builder {
		private String i;
		private String n;
		private String pn = "0";
		private String e = "not set";
		public Builder() {
			
		}
		
		public Builder setId(String id_) {
			if(!checkId(id_))
				throw new IllegalArgumentException("Invalid Id");
			this.i = id_;
			return this;
		}
		
		public Builder setName(String name_) throws IllegalInputException {
			if(!checkName(name_))
				throw new IllegalInputException("Name needs at least 2 Characters", name_);
			this.n = name_;
			return this;
		}
		
		public Builder setPhoneNumber(String phone_) throws IllegalInputException {
			if(!checkPhoneNumber(phone_))
				throw new IllegalInputException("Phone Number must contain only numbers", phone_);
			this.pn = phone_;
			return this;
		}
		
		public Builder setEmail(String email_) {
			this.e = email_; //TODO: Add Email Check
			return this;
		}
		
		public Supplier build() {
			return new Supplier(i, n, pn, e);
		}
	}
}
