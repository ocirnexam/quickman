package Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Entities.Supplier;
import Exceptions.IllegalInputException;

public class SupplierService {
    String url = null;
	public SupplierService(String db) {
	    url = "jdbc:sqlite:db/quickman.db";
	}
	
	public void createNewSupplierTable() {
        String sql = "CREATE TABLE IF NOT EXISTS Supplier (\n"
                + "	id text PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "	phone text,\n"
                + " email text\n"
                + ");";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
	public void addSupplier(Supplier sup) {
		String sql = "INSERT INTO Supplier(id, name, phone, email) VALUES (?,?,?,?);";

        try (Connection conn = DriverManager.getConnection(url);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, sup.getId());
            pstmt.setString(2, sup.getName());
            pstmt.setString(3, sup.getPhoneNumber());
            pstmt.setString(4, sup.getEmail());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
	
	public void deleteSupplier(String id) {
		String sql = "DELETE FROM Supplier WHERE id = ?;";
		
		try (Connection conn = DriverManager.getConnection(url); PreparedStatement stmt  = conn.prepareStatement(sql)){
				stmt.setString(1, id);
				stmt.execute();
		} catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
			
	}
	
	public Supplier getSupplier(String id) {
		String sql = "SELECT * FROM Supplier WHERE id = ?;";
		
		try (Connection conn = DriverManager.getConnection(url); PreparedStatement stmt  = conn.prepareStatement(sql)){
				stmt.setString(1, id);
				ResultSet rs    = stmt.executeQuery();
				Supplier result = null;
	            // loop through the result set
	            while (rs.next()) {
	                result = new Supplier.Builder().setId(rs.getString("id")).setName(rs.getString("name"))
	                		.setPhoneNumber(rs.getString("phone")).setEmail(rs.getString("email")).build();
	            }
	            return result;
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        } catch (IllegalInputException ex) {
	        	System.out.println(ex.getMessage());
	        }
		return null;
	}
	
	public ArrayList<Supplier> getAllSuppliers() {
		String sql = "SELECT * FROM Supplier;";
		
		ArrayList<Supplier> result = new ArrayList<Supplier>();
		
		try (Connection conn = DriverManager.getConnection(url); Statement stmt  = conn.createStatement()){
			
			ResultSet rs    = stmt.executeQuery(sql);
			Supplier temp = null;
            // loop through the result set
            while (rs.next()) {
                temp = new Supplier.Builder().setId(rs.getString("id")).setName(rs.getString("name"))
                		.setPhoneNumber(rs.getString("phone")).setEmail(rs.getString("email")).build();
                result.add(temp);
            }
            return result;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (IllegalInputException ex) {
        	System.out.println(ex.getMessage());
        }
		return null;
	}
}
