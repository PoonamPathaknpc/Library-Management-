package libDBMgmtproject.Model;


import javax.persistence.*;
import org.hibernate.jpa.*;

import java.io.Serializable;
import java.util.List;


public class BOOK_AUTHORS {	
	 
	 
	private String ISBN;
	private AUTHORS Author;
	private BOOK book;
		
	
	public BOOK_AUTHORS( String ISBN ,AUTHORS A) {
		// TODO Auto-generated constructor stub
		this.ISBN=ISBN;
		this.Author=A;
		
		
	}
	
	public BOOK_AUTHORS() {}
		
	public String getISBN()
	{
		return this.ISBN;
	}
	
	public void setISBN(String isbn)
	{
		 this.ISBN=isbn;
	}
	
	public AUTHORS getAuthor()
	{
		return this.Author;
	}
	
	public void setAuthor(AUTHORS authors)
	{
		 this.Author=authors;
	}

	public BOOK getbook()
	{
		return this.book;
	}
	
	public void setbook(BOOK b)
	{
		 this.book=b;
	}
	
}
