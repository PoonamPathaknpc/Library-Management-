package libDBMgmtproject.Model;

import java.util.*;


public class BOOK_LOANS {
	
	private int Loan_ID;
	private BOOK book;
	private BORROWER borrower;
	private Date Due_Date;
	private Date Date_in;
	private Date Date_out;
	private FINES Fine;

	public BOOK_LOANS(){}
	
	public BOOK_LOANS(Date DD, Date DO,BORROWER b,BOOK bk) {
		// TODO Auto-generated constructor stub
		this.Due_Date=DD;
	    this.Date_out=DO;
		this.borrower=b;
		this.book=bk;
		
	}
	
	
	public int getLoan_ID() {
	      return this.Loan_ID;
	   }
    public void setLoan_ID( int LoanID ) {
	      this.Loan_ID= LoanID;
	   }
    
	public BOOK getBOOK() {
	      return this.book;
	   }
	
	public void setBOOK( BOOK bk ) {
	      this.book = bk;
	   }
	   
	public BORROWER getBORROWER() {
	      return this.borrower;
	   }

    public void setBORROWER( BORROWER b ) {
	      this.borrower = b;
	   }

    public Date getDue_Date() {
	      return this.Due_Date;
	   }

    public void setDue_Date( Date DD ) {
	      this.Due_Date = DD;
	   }

    public Date getDate_in() {
	      return this.Date_in;
	   }
    
    public void setDate_in( Date DD ) {
	      this.Date_in = DD;
	   }
    
    public Date getDate_out() {
	      return this.Date_out;
	   }
    
    public void setDate_out( Date DD ) {
	      this.Date_out = DD;
	   }

    public FINES getFine() {
	      return this.Fine;
	   }
  
   public void setFine( FINES Fine ) {
	      this.Fine=Fine;
	   }

	
}
