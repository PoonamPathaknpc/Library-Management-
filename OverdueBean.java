package com.dbproject.model;

public class OverdueBean {

	String ISBN;
	BORROWER Bow;
	
public OverdueBean(){}
	
	public OverdueBean(String B,BORROWER bow)
	{		
		this.ISBN=B;
		this.Bow=bow;
		
	}	
	
    public String getB() {
	      return this.ISBN;
	   }
	public void setB( String Bow ) {
	      this.ISBN=Bow;
	   }
	
	 public BORROWER getBorrower() {
	      return this.Bow;
	   }
	public void setBorrower( BORROWER Bo) {
	      this.Bow=Bo;
	   }
}
