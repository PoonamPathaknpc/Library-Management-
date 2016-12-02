package com.dbproject.model;

public class FINES {
	
	int Loan_id;
	private BOOK_LOANS Bookloan;
	private float Fine_Amt;
	private char PAID;

	public FINES() {
		// TODO Auto-generated constructor stub
	}

	
	public BOOK_LOANS getBOOK_LOANS() {
	      return this.Bookloan;
	   }
    public void setBOOK_LOANS( BOOK_LOANS bl ) {
	      this.Bookloan=bl;
	   }
  
    public float getFine_Amt() {
	      return this.Fine_Amt;
	   }
    public void setFine_Amt( float FA ) {
	      this.Fine_Amt= FA;
	   }
	public char getPAID() {
	      return this.PAID;
	   }
	
	public void setPAID( char paid ) {
	      this.PAID = paid;
	   }
	
	public int getLoan_ID() {
	      return this.Loan_id;
	   }
   public void setLoan_ID( int LoanID ) {
	      this.Loan_id=LoanID;
	   }
}
