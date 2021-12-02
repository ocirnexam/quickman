package GUI;

import Services.*;
import Entities.*;
import Entities.OfferItem.Location;
import Exceptions.IllegalInputException;

public class Main {
	public static void main(String[] args) {
		try {
			Supplier sup = new Supplier.Builder().setId("123DAS123DAS").setName("Pottery Pots").build();
			Category pp_c = new Category.Builder().setId(1).setTitle("Essential").setSupplierId(sup.getId()).build();
			Item pp_i = new Item.Builder().setId("124ASD124ASD").setTitle("Jort M").setSupplierId(sup.getId()).setCategoryId(pp_c.getId()).build();
			OfferItem pp_oi = new OfferItem.Builder().setId("125ASD125ASD").setTitle(pp_i.getTitle()).setLocation(Location.Ausstellungsraum).setAmount(10)
										   .setPrice(100.0).setSupplierId(pp_i.getSupplierId()).setCategoryId(pp_i.getCategoryId()).setReferencedItem(pp_i.getId()).build();
			
			SupplierService supservice = new SupplierService("jdbc:sqlite:db/quickman.db");
			supservice.createNewSupplierTable();
			supservice.deleteSupplier(sup.getId());
			supservice.addSupplier(sup);
			supservice.getAllSuppliers().forEach(x -> System.out.println(x.getName()));
		} catch (IllegalInputException e) {
			e.printStackTrace();
		}
	}
}
