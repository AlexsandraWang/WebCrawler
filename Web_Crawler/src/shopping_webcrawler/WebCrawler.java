package shopping_webcrawler;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
/**
 * WebCrawler crawls web page information from specific website
 * This WebCrawler is focused on "http://www.shopping.com"
 * @author Weizhi Wang
 * @version 1.0 Build Oct 12, 2014
 **/


public class WebCrawler {
	/**
	 * website is the major website from which WebCrawler fetches results
	 */
	private final String website = "http://www.shopping.com/";
	/**
	 * url is the URL for WebCrawler to fetch
	 */
	private String url = null;
	/**
	 * doc is the parsed document fetched from url
	 */
	private Document doc = null;
	
	/**
	 * WebCrawler Constructor
	 * Construct a WebCrawler object when there is only one parameter (keyword)
	 * @param keyword the name of the object user wants to search
	 */	
	public WebCrawler( String keyword){	
		setUrl( keyword );
		setup();
	}
	
	/**
	 * WebCrawler Constructor
	 * Construct a WebCrawler object when there are two parameters(keyword and page)
	 * @param keyword the name of the object user wants to search
	 * @param page the page number of search results
	 */	
	public WebCrawler( String keyword, String page){
		setUrl( keyword, page );
		setup();
	}
	
	/**
	 * Initialize WebCrawler, fetch the parsed document from URL
	 * @exception timeout or error occurs
	 */	
	private void setup(){
		try {
			this.doc = Jsoup.connect( this.url).get();
			if ( this.doc == null){
				System.out.println("Cannot find the page");
				System.exit(0);
			}
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot find the page");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Initialize WebCrawler, fetch the parsed document from URL
	 * @param keyword name of object user searches
	 */	
	private void setUrl( String keyword){
		if( keyword == null || keyword.trim().isEmpty() ){
			System.out.println(" Please input valid keyword!");
			System.exit(0);
		}
		this.url = this.website + keyword.replace(" ", "-") + "/products?CLT=SCH&KW=" + keyword.replaceAll(" ", "+");	
	}
	/**
	 * Initialize WebCrawler, fetch the parsed document from URL
	 * @param keyword name of object user searches
	 * @param page page number of search results
	 */	
	private void setUrl( String keyword, String page){
		if( keyword == null || keyword.trim().isEmpty() ){
			System.out.println(" Please input valid keyword!");
			System.exit(0);
		}
		if( page == null || page.trim().isEmpty() || !isNumeric(page) ){
			System.out.println(" Please input valid page!");
			System.exit(0);
		}
		this.url = this.website + keyword.replace(" ", "-") + "/products~PG-"+page+"?KW=" + keyword.replaceAll(" ", "+");
	}
	/**
	 * Check if a string is a number
	 * @param str String that needs check
	 * @return true is number; false is not a number
	 */	
	public static boolean isNumeric ( String str )
	{
	    for (char c : str.toCharArray())
	    {
	        if (!Character.isDigit(c)) 
	        	return false;
	    }
	    return true;
	}
	/**
	 * Get the number of search results
	 * @return number of search results
	 */		
	public String SearchResults(){
		
		String resultsnum = this.doc.select("span.numTotalResults").first().text();
		String[] words = resultsnum.split(" ");
		return words[words.length-1];
		
	}
	/**
	 * @return this WebCrawler's parsed document
	 */	
	public Document getWebPage(){
		return this.doc;
	}
	
	public static void main(String[] args){
		if( args == null || args.length == 0){
			System.out.println("Please input keyword or keyword and page !");
		}
		else if( args.length == 1 ){
			WebCrawler shopping = new WebCrawler(args[0]);
			System.out.println("Total Number of Results: "+ shopping.SearchResults());
		}
		else if( args.length == 2 ){
			WebCrawler shopping = new WebCrawler( args[0], args[1] );
			ResultPage page = new ResultPage( shopping.getWebPage() );
			page.ParsePage();
		}
		
		
		
		
	}

}
