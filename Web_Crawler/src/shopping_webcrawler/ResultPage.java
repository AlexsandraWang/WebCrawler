package shopping_webcrawler;

import java.util.*;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * ResultPage is the page information generated from parsed document return by WebCrawler
 * ResultPage can parse the information of each item
 * @author Weizhi Wang
 * @version 1.0 Build Oct 12, 2014
 **/

public class ResultPage {
	/**
	 * page contains a list of items parsed from the document
	 */
	public ArrayList<ResultObject> page = null;
	/**
	 * webpage is the parsed document returned by WebCrawler
	 */
	private Document webpage;
	
	/**
	 * webpage is the parsed document returned by WebCrawler
	 */
	public ResultPage( Document Webpage){
		this.webpage = Webpage;
	}
	
	/**
	 * Parse the search results of document, get each item
	 */
	public void ParsePage(){
		Elements items = this.webpage.select( "div.gridBox" );
		if(items == null)
			return;
		else
			page = new ArrayList<ResultObject>();
		int item_num = 1;
		for( Element item: items){
			ResultObject result = new ResultObject();
			result.setTitle( ParseItemTitle( item ) );
			result.setPrice( ParseItemPrice( item ) );
			result.setShippingPrice( ParseShippingInfo( item ) );
			result.setVendors( ParseVendors( item ) );
			page.add(result);
			System.out.println("No. "+item_num++ );
			result.printObjectInfo();
			
			
		}
		
	}
	
	/**
	 * Parse the product title for one item
	 * @param e Document Element which contains the information about the item
	 * @return product title of the item
	 */
	public String ParseItemTitle( Element e){
		
		Element title = e.select("a.productName").first();
		if(title.hasAttr("title"))
		{
			return title.attr("title");
		}
		else
		{
			return title.select("span").attr("title");
		}
		
		
		
	}
	
	/**
	 * Parse the product price for one item
	 * @param e Document Element which contains the information about the item
	 * @return product price of the item
	 */
	public String ParseItemPrice( Element e ){
		
		return e.select("span.productPrice").first().text();		
		
	}
	
	/**
	 * Parse the product shipping information for one item
	 * @param e Document Element which contains the information about the item
	 * @return product shipping price of the item
	 */
	public String ParseShippingInfo(Element e){
		Element itemShipPrice = e.select(".gridItemBtm").first();
		String Shipingprice = null;
		
		if(itemShipPrice != null)
		{
			//Check if it provides freeship
			if(itemShipPrice.select(".freeShip").first()!=null)
				Shipingprice = itemShipPrice.select(".freeShip").html();
			
			//if item has a shipping fee, extract the price	
			else if(itemShipPrice.select(".calc").first()!=null){
				String[] priceinfo = itemShipPrice.select(".calc").html().split(" ");
				for( String s: priceinfo )
					if( s.startsWith("$") )
						Shipingprice = s;	
			}
			//if item has not provided ship information	
			else if(itemShipPrice.select(".missCalc").first()!=null)	
				Shipingprice = itemShipPrice.select(".missCalc").html();
			//shipping fee info is not mentioned 
			else
				Shipingprice = new String("");
		}
		return Shipingprice;
		
	}
	
	/**
	 * Parse the product vendor information for one item
	 * @param e Document Element which contains the information about the item
	 * @return product vendors of the item
	 */
	public ArrayList<String> ParseVendors( Element e ){
		
		ArrayList<String> vendors = new ArrayList<String>();
		Element merchant = e.select(".newMerchantName").first();
		if( merchant != null )
			vendors.add(merchant.text().trim());
		
		Elements stores = e.select("a.buyAtTxt");
		if( stores != null && stores.first()!= null){
			vendors.add(stores.first().select("span").first().text().trim());
		}
		return vendors;
		
	}
}
