package libDBMgmtproject.Model;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import java.util.Set;


public class BOOK {
	
	String ISBN;
	String Title;
	int Quantity;
	private List<BOOK_LOANS> Book_Loans;
	private Set<AUTHORS> Authors;

	public BOOK(){}
	public BOOK(String ISBNID,String T, int Qty,List<BOOK_LOANS> BL, Set<AUTHORS> BA) {
		
		this.ISBN=ISBNID;
		this.Title=T;
		this.Quantity=Qty;
		this.Book_Loans=BL;
		this.Authors=BA;
		// TODO Auto-generated constructor stub
	}
	
	public String getISBN() {
	      return ISBN;
	   }
	   public void setISBN( String ISBNID ) {
	      this.ISBN= ISBNID;
	   }
	   public String getTitle() {
	      return Title;
	   }
	   public void setTitle( String T ) {
	      this.Title = T;
	   }
	   public int getQuantity() {
	      return Quantity;
	   }
	   public void setQuantity( int Q ) {
	      this.Quantity = Q;
	   }
	   
	   public List<BOOK_LOANS> getBOOK_LOANS() {
		      return this.Book_Loans;
		   }
	   
	   public void setBOOK_LOANS(List<BOOK_LOANS> BL) {
		      this.Book_Loans = BL;
		   }

	   public Set<AUTHORS> getAuthors() {
		      return this.Authors;
		   }
	   
	   public void setAuthors(Set<AUTHORS> BL) {
		      this.Authors=BL;
		      
		   }
}
