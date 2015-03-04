package shopping_webcrawler;

import java.util.*;

/**
 * ResultObject is the item listed on the web page
 * ResultObject describes the details about the item 
 * @author Weizhi Wang
 * @version 1.0 Build Oct 12, 2014
 **/

public class ResultObject {
	/**
	 * ProdectTitle is the name of this item
	 */
	public String ProductTitle = null;
	
	/**
	 * ProdectPrice is the price of this item
	 */
	public String ProductPrice = null;
	
	/**
	 * ShippingPrice is the shipping price for this item 
	 */
	public String ShippingPrice = null;
	
	/**
	 * Vendors contains info about vendors for this item
	 */
	public ArrayList<String> Vendors = null;
	
	/**
	 * set the ProductTitle for this item
	 * @param title name of this item
	 */
	public void setTitle( String title ){
		this.ProductTitle = title;
	}
	
	/**
	 * set the ProductPrice for this item
	 * @param price price of this item
	 */
	public void setPrice( String price ){
		this.ProductPrice = price;
	}
	
	/**
	 * set the ShippingPrice for this item
	 * @param price Shipping price of this item
	 */
	public void setShippingPrice( String price ){
		this.ShippingPrice = price;
	}
	
	/**
	 * set the Vendors info for this item
	 * @param vendors vendors for this item
	 */
	public void setVendors( ArrayList<String> vendors){
		this.Vendors = vendors;
	}
	
	/**
	 * Get the ProductTitle of this item
	 * @return Product name for this item
	 */
	public String getTitle(){
		return this.ProductTitle;
	}
	
	/**
	 * Get the ProductPrice of this item
	 * @return Product price for this item
	 */
	public String getPrice(){
		return this.ProductPrice;
	}
	
	/**
	 * Get the ShippingPrice of this item
	 * @return Product shipping info for this item
	 */
	public String getShippingPrice(){
		return this.ShippingPrice;
	}
	
	/**
	 * Get the Vendors of this item
	 * @return Product vendor info for this item
	 */
	public ArrayList<String> getVendors(){
		return this.Vendors;
	}
	
	/**
	 * Print product information
	 */
	public void printObjectInfo(){
		System.out.println("Product Name:	"+this.ProductTitle);
		System.out.println("Product Price:	"+this.ProductPrice);
		System.out.println("Shipping Price:	"+this.ShippingPrice);
		System.out.print("Vendor:	");
		for(String s: this.Vendors)
			System.out.print(s+"\t");
		
		System.out.println();
		
	}
}

