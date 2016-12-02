package com.dbproject.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class BORROWER {

	
	private String Card_ID ;
	private String SSN ;
	private String Fname ;
	private String LName ;
	private String Address ;
	private String Phone;
	private List<BOOK_LOANS> Book_Loans;
	
	public BORROWER(){}
	
	public BORROWER(String cd,String ssn,String fn,String ln,String Addr,String Ph, List<BOOK_LOANS> Book_Loans) {
		// TODO Auto-generated constructor stub
		this.Card_ID=cd;
		this.SSN=ssn;
		this.Fname=fn;
		this.LName=ln;
		this.Address=Addr;
		this.Phone=Ph;
		this.Book_Loans=Book_Loans;
		}
	
	public String getCard_ID() {
	      return this.Card_ID;
	   }
	
	   public void setCard_ID( String cardID ) {
	      this.Card_ID= cardID;
	   }
	   
	   public String getSSN() {
	      return this.SSN;
	   }
	   
	   public void setSSN( String ssn ) {
	      this.SSN = ssn;
	   }
	   
	  	   
	   public void setPhone( String phone ) {
	      this.Phone = phone;
	   }
	   
	   public String getPhone() {
		      return this.Phone;
	   }
	   
	   public String getFname() {
		      return this.Fname;
		   }
   
	   public void setFname(String Fname) {
		      this.Fname=Fname;
		   }

	   public String getLname() {
		      return this.LName;
		   }
	   
	   public void setLname(String Lname) {
		      this.LName=Lname;
		   }

	   
	   public String getAddress() {
		      return this.Address;
		   }
	   
	   public void setAddress(String Addr) {
		      this.Address=Addr;
		   }
	   
	   public List<BOOK_LOANS> getBookLoans() {
		      return this.Book_Loans;
		   }
	   
	   public void setBookLoans(List<BOOK_LOANS> BL) {
		      this.Book_Loans=BL;
		   }

}
