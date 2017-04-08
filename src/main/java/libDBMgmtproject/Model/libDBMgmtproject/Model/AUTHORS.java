package libDBMgmtproject.Model;


import java.util.HashSet;

public class AUTHORS {

	String Auth_ID;
	String Author_Name;
	BOOK books;
	
	public AUTHORS(){}
	
	public AUTHORS(String AID, String AN) {
		// TODO Auto-generated constructor stub
		this.Auth_ID=AID;
		this.Author_Name=AN;
		
	}
	
	public String getAuth_ID() {
	      return Auth_ID;
	   }
	   public void setAuth_ID( String Auth ) {
	      this.Auth_ID= Auth;
	   }
	   public String getAuthor_Name() {
	      return Author_Name;
	   }
	   public void setAuthor_Name( String AN ) {
	      this.Author_Name = AN;
	   }
	   
	   public BOOK getbooks() {
		      return this.books;
		   }
		   public void setbooks( BOOK Book ) {
		      this.books=Book;
		   }

}
