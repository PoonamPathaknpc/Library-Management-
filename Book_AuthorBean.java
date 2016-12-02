package com.dbproject.model;

public class Book_AuthorBean {
	
	String ISBN;
	String Authors;
	String Title;
	int Quantity;
	
	public Book_AuthorBean(){}
	
	public Book_AuthorBean(String ISBN, String Title, int Quant)
	{
		this.ISBN=ISBN;
		this.Title=Title;
		this.Quantity=Quant;
		
		
	}
	
	public Book_AuthorBean(String ISBN, String Title, int Quant, String Authors)
	{
		this.ISBN=ISBN;
		this.Title=Title;
		this.Quantity=Quant;
		this.Authors=Authors;
		
	}
	
    public String getISBN() {
	      return this.ISBN;
	   }
	public void setISBN( String ISBNID ) {
	      this.ISBN= ISBNID;
	   }
	public String getTitle() {
	      return this.Title;
	   }
	public void setTitle( String T ) {
	      this.Title = T;
	   }
	public int getQuantity() {
	      return this.Quantity;
	   }
	public void setQuantity( int Q ) {
	      this.Quantity = Q;
	   }
	
	public String getAuthor_Name() {
		      return this.Authors;
		   }
    public void setAuthor_Name( String AN ) {
		      this.Authors = AN;
		}
	

}
