package Entities;

import Exceptions.IllegalInputException;

public class OfferItem {
	
	public static enum Location {Ausstellungsraum, Dachboden};
	
	private String id;
	private String title;
	private String description;
	private int amount;
	private double price;
	private Location location; //The Location where the item is stored
	private String supplier_id;
	private int category_id;
	private String referenced_item;
    
    private OfferItem(String id, String title, String description, int amount, 
    				  double price, Location location, String sup_id, int cat_id, String ref_item) {
    	this.id = id;
    	this.title = title;
    	this.description = description;
    	this.amount = amount;
    	this.price = price;
    	this.location = location;
    	this.supplier_id = sup_id;
    	this.category_id = cat_id;
    	this.referenced_item = ref_item;
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
	
	public void setTitle(String title_) throws IllegalInputException {
		if(!checkTitle(title_))
			throw new IllegalInputException("Title must have at least 2 characters", title_);
		this.title = title_;
	}
	
	public void setDescription(String desc) {
		this.description = desc;
	}
	
	public void decreaseAmount(int a) throws IllegalInputException {
		if(a < 0 || a > this.amount)
			throw new IllegalInputException("Amount must be greater than or equal to 0", Integer.valueOf(a).toString());
		this.amount -= a;
	}
	
	public void increaseAmount(int a) throws IllegalInputException {
		if(a < 0)
			throw new IllegalInputException("Amount must be greater than or equal to 0", Integer.valueOf(a).toString());
		this.amount += a;
	}
	
	public void setPrice(double p) throws IllegalInputException {
		if(p < 0.1)
			throw new IllegalInputException("Price must be greater than 0.1Euro", Double.valueOf(p).toString());
		this.price = p;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
	public void setSupplierId(String supplier_id) {
		this.supplier_id = supplier_id;
	}
	
	public void setCategoryId(int c_id) {
		this.category_id = c_id;
	}
	
	public void setReferencedItem(String it_id) {
		this.referenced_item = it_id;
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
	
	public int getAmount() {
		return this.amount;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public Location getLocation() {
		return this.location;
	}
	
	public String getSupplierId() {
		return this.supplier_id;
	}
	
	public int getCategoryId() {
		return this.category_id;
	}
	
	public String getReferencedItemId() {
		return this.referenced_item;
	}
	
	@Override
	public String toString() {
		return "OfferItem[" + id + "]: " + title + ", stored at " + location.toString() + "!"; 
	}
    
    public static class Builder {
    	private String id_b;
    	private String title_b;
    	private String description_b = "not set";
    	private int amount_b;
    	private double price_b;
    	private Location location_b; //The Location where the item is stored
    	private String supplier_id_b;
    	private int category_id_b;
    	private String referenced_item_b;
    	
    	public Builder() {
    		
    	}
    	
    	public Builder setId(String id_) throws IllegalInputException {
    		if(!checkId(id_))
    			throw new IllegalInputException("Invalid Id", id_);
    		this.id_b = id_;
    		return this;
    	}
    	
    	public Builder setTitle(String title_) throws IllegalInputException {
    		if(!checkTitle(title_))
    			throw new IllegalInputException("Title must have at least 2 characters", title_);
    		this.title_b = title_;
    		return this;
    	}
    	
    	public Builder setDescription(String desc) {
			this.description_b = desc;
			return this;
		}
    	
    	public Builder setAmount(int a) throws IllegalInputException {
    		if(a < 0)
    			throw new IllegalInputException("Amount must be greater than or equal to 0", Integer.valueOf(a).toString());
    		this.amount_b = a;
    		return this;
    	}
    	
    	public Builder setPrice(double p) throws IllegalInputException {
    		if(p < 0.1)
    			throw new IllegalInputException("Price must be greater than 0.1Euro", Double.valueOf(p).toString());
    		this.price_b = p;
    		return this;
    	}
    	
    	public Builder setLocation(Location location) {
    		this.location_b = location;
    		return this;
    	}
    	
    	public Builder setSupplierId(String supplier_id) {
    		this.supplier_id_b = supplier_id;
    		return this;
    	}
		
		public Builder setCategoryId(int c_id) {
			this.category_id_b = c_id;
			return this;
		}
		
		public Builder setReferencedItem(String it_id) {
			this.referenced_item_b = it_id;
			return this;
		}
		
		public OfferItem build() {
			return new OfferItem(id_b, title_b, description_b, amount_b, price_b, 
								 location_b, supplier_id_b, category_id_b, referenced_item_b);
		}
    	
    }
}
