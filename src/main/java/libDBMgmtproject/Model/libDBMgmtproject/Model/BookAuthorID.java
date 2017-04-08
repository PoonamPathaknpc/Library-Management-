package libDBMgmtproject.Model;




import java.io.Serializable;

public class BookAuthorID implements Serializable {
  
	String ISBN;
	String AuthID;
	
	public BookAuthorID() {
		// TODO Auto-generated constructor stub
	}
	
	public String getAuthID() {
	      return this.AuthID;
	   }
	   public void setAuthID( String Auth ) {
	      this.AuthID=Auth;
	   }
	   
	public String getISBN() {
		      return ISBN;
		   }
    public void setISBN( String ISBNID ) {
		      this.ISBN= ISBNID;
		   }
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
	        return true;
	    if ((obj == null) || (obj.getClass() != this.getClass()))
	        return false;

	    BookAuthorID test = (BookAuthorID) obj;
	    boolean flagCrop = ISBN == test.ISBN;
	    boolean flagMarket = AuthID == test.AuthID;

	    return flagCrop && flagMarket;
	}

	@Override
	public int hashCode() {
	    int hash = 7;
	    hash = 31 * hash + ISBN.hashCode();
	    hash = 31 * hash + ISBN.hashCode();
	    return hash;
	}

}
