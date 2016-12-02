package com.dbproject.model;


import java.util.HashSet;
import java.util.Set;

public class AUTHORS {

	String Auth_ID;
	String Author_Name;
	Set<BOOK> books;
	
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
	   
	   public Set<BOOK> getbooks() {
		      return this.books;
		   }
		   public void setbooks( Set<BOOK> Books ) {
		      this.books=Books;
		   }

}
