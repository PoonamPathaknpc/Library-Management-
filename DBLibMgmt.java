package libDBMgmtproject.Model;

import java.util.List;
import java.util.Set;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import org.hibernate.HibernateException;
import org.hibernate.exception.*;
import org.hibernate.Query;
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.*;
import org.joda.time.*;
import java.math.*;

public class DBLibMgmt {
	public static SessionFactory factory;
	public static ServiceRegistry serviceRegistry; 
	

	public DBLibMgmt() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String ISBN="0001047973";
		String fname="Richard";
		String lname="Bailey";
		String[] Author={"Sir conan d","arthur doyle"};
		String SSN="983-87-0005";
		String addr="3108 Browning Park , Richardson , TX";
		String Title="testing book2";
		String phone="(214) 718-0392";
		int Qty=10;
		String Search_String="Lord of the Far Land";
		String Card_ID="Ri870005";
		
		try{
			Configuration configuration = new Configuration();
		    configuration.configure();
		    serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()). buildServiceRegistry();
		    factory = configuration.buildSessionFactory(serviceRegistry);
			
	       }
		catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
		
		DBLibMgmt dblib = new DBLibMgmt();		
			
		// To create a BOOK record with authors
		dblib.createBookRecord(ISBN,Title,Qty);
		dblib.setAuthors(ISBN,Author);
		
		
		//Requirement 1 - search for books
		//dblib.getSearchDetails(Search_String);	
		
		//To create BorrowerRecord requirement 4 (Register button)
		//dblib.createBorrowerRecord(fname,lname,phone,SSN,addr);
		
		
		//To create Book loan record when checking out - requirement 2 (check out button )
		//card_id and ISBN will be fetched GUI -- card_ID will asked but ISBN will fetched when a book is chosen
		//dblib.createBookLoanRecord(Card_ID,ISBN);
		
		//Checking in the book requirement 3 (check in button)
		//dblib.borrowerBookCheckin(Card_ID,ISBN);
		
		//Fines requirement 5 
		//dblib.getFineDetails(Card_ID);
		//dblib.listOverDueBooks(Card_ID);
		//dblib.payFineForBorrower(Card_ID,ISBN);  //Pay Fine button
		
	}
	
	public Integer createBookRecord(String ISBN,String Titl,int Q)
	{
		Session session = factory.openSession();
		Transaction tx = null;
		Integer BookStatus = null;
		try {
			   tx = session.beginTransaction();
			   List<BOOK_LOANS> BL=new ArrayList<BOOK_LOANS>();
			   Set<AUTHORS> Book_Auth=new HashSet<AUTHORS>();
			   	   
			   BOOK new_book = new BOOK(ISBN,Titl,Q,BL,Book_Auth);
			   //session.save(Book_Auth);
			   session.save(new_book);
			   		   
			   tx.commit();
			}
		
		catch (ConstraintViolationException e ) {
	         if (tx!=null) tx.rollback();
	         System.out.println("This Book is already registered in the system" );
	      }finally {
	         session.close(); 
	      
	      }
	      return BookStatus;
	}
	
	public Integer setAuthors(String ISBN,String[] Author)
	{
		Session session = factory.openSession();
		Transaction tx = null;
		Integer BookStatus = null;
		try {
			   tx = session.beginTransaction();
			    Query query = session.createQuery("from BOOK WHERE ISBN = :ISBN");
			   query.setString("ISBN", ISBN);
			   query.setMaxResults(1);
			   BOOK assocbook = (BOOK) query.uniqueResult();
			   
			   				
			   for (int i=0;i<Author.length;i++) 
			   {
				   //Generating Unique Author ID 
				   String Authid="";
				   Authid=Authid.concat("111");
				   Authid=Authid.concat(Integer.toString(Author[i].length()));				   
				   String a = Authid.substring(Authid.length()/2);
				   Authid=Authid.concat(a);
				  
				   AUTHORS A = new AUTHORS(Authid,Author[i]);
				   A.setbooks(assocbook);
				   System.out.println(Authid);
				   assocbook.getAuthors().add(A);				   
			     
			   }
			   
			   session.save(assocbook);
			   			   
			   tx.commit();
			}
		
		catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return BookStatus;
	}
	
	
	public Integer createBorrowerRecord(String Fname,String Lname,String Phone,String SSN,String Address)
	{
		Session session = factory.openSession();
		Transaction tx = null;
		Integer BorrowerStatus = null;
		
		
		
		try {
			   tx = session.beginTransaction();
			   String Card_id;
			   String[] ssnp =SSN.split("-");
			   String Firsthalf=ssnp[1].substring(ssnp[1].length()-2);
			   Firsthalf=Firsthalf.concat(ssnp[2]);		
			   Card_id=Fname.substring(0, 2).concat(Firsthalf);
			   List<BOOK_LOANS> Book_Loans=new ArrayList<BOOK_LOANS>();
			   
			   //Checking if SSN is already in the table....
			   Query query1 = session.createQuery("from BORROWER WHERE SSN = :SSN");
			   query1.setString("SSN", SSN);
			   query1.setMaxResults(1);
			   BORROWER borrower=(BORROWER)query1.uniqueResult();
			   			   
			   if(borrower!=null)
			   System.out.println("Borrower with SSN: " + SSN + "Already exists");
			   
			   else{
			   BORROWER Bow = new BORROWER(Card_id, SSN, Fname, Lname, Address, Phone, Book_Loans);	
			   session.save(Bow);
			   tx.commit();
			   }
		    }
		
		catch (ConstraintViolationException e ) {
	         if (tx!=null) tx.rollback();
	         System.out.println("This Borrower is already registered in the system" );
	      }finally {
	         session.close(); 
	      }
	      return BorrowerStatus;
	}
	
	
	public Integer createBookLoanRecord(String Card_id,String ISBN)
	{
		Session session = factory.openSession();
		Transaction tx = null;
		Integer BorrowerStatus = null;
		
		
		try {
			    tx = session.beginTransaction();
			    int num_activeBL=0;int fineflag=0;int overdueflag=0;
			    Query query1 = session.createQuery("from BORROWER WHERE Card_ID = :Card_id");
			    query1.setString("Card_id", Card_id);
				query1.setMaxResults(1);
				//System.out.println("The borrower is " + query1.uniqueResult().getClass());
				BORROWER borrower=(BORROWER)query1.uniqueResult();
				if(borrower==null)
			    System.out.println("The borrower with given card id does not exists. Please register the borrower first");
				
				else
				{					
				//to count number of active book loans the borrower has....	
				List<BOOK_LOANS> bookloans=borrower.getBookLoans();	
				List<BOOK_LOANS> Activebookloans = new ArrayList<BOOK_LOANS>();	
				for (int i=0;i<bookloans.size();i++)
				{
					if (bookloans.get(i).getDate_out()==null)
					{
					//collecting the active book loans for books not checked in...	
					num_activeBL++;
					Activebookloans.add(bookloans.get(i)); 
					}
					else{
				    //Checking if other book loans fines if exists are paid....
					FINES F=bookloans.get(i).getFine();
					if(F!=null && (F.getPAID()=='N'))
					fineflag++;
					}
				}
								
				if ((num_activeBL>=3)||(fineflag>0))	
				System.out.println("The borrower isnt allowed to loan the book as he/she has either 3 or more Active book loans already or hasnt paid the fine for one or more checked in books");	
				
				else
				{   Date DO = new Date();
					//Checking if Active book loans are over due 
					if(Activebookloans.size()>0)
				    {
						for (int i=0;i<Activebookloans.size();i++)
						{
							if (Activebookloans.get(i).getDue_Date().compareTo(DO)==-1)
							overdueflag++;						
						 }
					 }
				      
					if(overdueflag>0)
				      System.out.println("The borrower isnt allowed to loan the book as he/she currenlty an overdue book ");
				      else
				      {
					  Query query = session.createQuery("from BOOK WHERE ISBN = :ISBN");
					  query.setString("ISBN", ISBN);
					  query.setMaxResults(1);
					  BOOK assocbook = (BOOK) query.uniqueResult();
					   
					   if (assocbook.Quantity==0)
				       System.out.println("The book is not available to be loaned anymore");
				    
					   else{
					       			   
					       System.out.println("todays date" + DO);		   
					       
					       //Creating Book loan record and adding it to 
					       Date DD = new Date(DO.getTime()+14*24*60*60*1000);
					       System.out.println("due date" + DD);	
					       BOOK_LOANS BL = new BOOK_LOANS( DD, DO,borrower,assocbook);
					       borrower.getBookLoans().add(BL);
					     
					       //Reducing the quantity of book available
					       assocbook.Quantity-=1;
					       System.out.println("available Book qty" + assocbook.Quantity);	
					       
					       //Saving changes to DB
						   session.save(assocbook);
					       session.save(BL);
					       session.save(borrower); 
				           }
				      }
				   
				   }
				}
		  	  tx.commit();
			}
		
		catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	         }finally {
	         session.close(); 
	        }
	      return BorrowerStatus;
	}
	
	
	public Integer borrowerBookCheckin(String Card_id,String ISBN)
	{
		Session session = factory.openSession();
		Transaction tx = null;
		Integer BorrowerStatus = null;
		
		
		try {
			    tx = session.beginTransaction();
			    Date DI = new Date();
			    
			    
			    //Get borrower details
			    Query query1 = session.createQuery("from BORROWER WHERE Card_ID = :Card_id");
			    query1.setString("Card_id", Card_id);
				query1.setMaxResults(1);				
				BORROWER borrower=(BORROWER)query1.uniqueResult();
				if(borrower==null)
			    System.out.println("The borrower with given card id does not exists. Please register the borrower first");
				
				else
				{					
				//to count number of active book loans the borrower has....	
				List<BOOK_LOANS> bookloans=borrower.getBookLoans();	
				BOOK_LOANS BL;
				for (int i=0;i<bookloans.size();i++)
				{
					if (bookloans.get(i).getBOOK().ISBN==ISBN)
					{
					  BL=bookloans.get(i);
					  BL.setDate_in(DI);;
					  if(BL.getDue_Date().compareTo(DI)==-1)
					  {
						 //Setting Fine for borrower 
					    FINES FBL=this.createFine(BL);
					    BL.setFine(FBL);
					    
					    //adding quantity back to Book
					    int Qty=BL.getBOOK().Quantity+1;
					    BOOK assocbook=BL.getBOOK();
					    assocbook.setQuantity(Qty);
					    
					    //Saving changes in DB
				        session.save(BL);
				        session.save(FBL);
				        session.save(assocbook);
				        break;
					  }
				   }			    
				}
				tx.commit();			
		        }
		  }	
		  catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	         }finally {
	         session.close(); 
	        }
	      return BorrowerStatus;
	}
	
	public void getSearchDetails(String S)
	{
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			   System.out.println("Starting the search program :");   
			   tx = session.beginTransaction();
			   //Searching book based on ISBN;
			   Query query = session.createQuery("from BOOK WHERE ISBN = :ISBN");
			   query.setString("ISBN", S);
			   
			   List<?> qlist = query.list();
			   if(qlist.isEmpty())
				   System.out.println("No results for ISBN");   
			   else
				   {
				   for(int i=0; i< qlist .size(); i++)
				     {				   
					   BOOK B=(BOOK)qlist.get(i);
					   Set <AUTHORS>Authors=B.getAuthors();
					   System.out.println("BOOK details are as follows :");
					   System.out.println("ISBN -- " + B.ISBN);
					   System.out.println("Title-- " + B.Title);
					   System.out.println("Quantity-- " + B.Quantity);
					   System.out.println("Authors--");
					   for (AUTHORS s : Authors) {
					    System.out.println(s.Author_Name);
					     }
				     }					
				   }	
			   
			   
			  // Searching book based on Title...
			    int i=0;
			    String[] subsearch = S.split(" ");
			    while ( i<subsearch.length)
			    {
			     System.out.println(subsearch[i]);
			     Query queryT = session.createQuery("from BOOK as B WHERE B.Title like :Title");
			     List<?> qlistT = queryT.setString("Title", subsearch[i]+"%").list();
				   
				   //List<?> qlistT = query.list();
				    for(int j=0; j< qlistT .size(); j++)
				    {
						BOOK B1=(BOOK)qlistT.get(j);
						Set <AUTHORS>Authors1=B1.getAuthors();
						System.out.println("BOOK details from title are as follows :");
						System.out.println("ISBN -- " + B1.ISBN);
						System.out.println("Title-- " + B1.Title);
						System.out.println("Quantity-- " + B1.Quantity);
						System.out.println("Authors--");
						System.out.println(Authors1.size());
						for (AUTHORS s : Authors1) {
						    System.out.println(s.Author_Name);
						}
					}
				    i++;
			    }
			    
			    //Searching book by Author name 
			    int k=0;
			    while ( k<subsearch.length)
			    {
			     System.out.println(subsearch[k]);
			     Query queryT = session.createQuery("from AUTHORS WHERE  Author_Name like :AuthName");
			     List<?> qlistT = queryT.setString("AuthName", subsearch[k] +"%").list();
				   
				   //List<?> qlistT = query.list();
				    for(int j=0; j< qlistT .size(); j++)
				    {
						AUTHORS A1=(AUTHORS)qlistT.get(j);
						BOOK BOOKS1=A1.getbooks();
						System.out.println("BOOK details from title are as follows :");
						System.out.println("ISBN -- " + BOOKS1.ISBN);
						System.out.println("Title-- " + BOOKS1.Title);
						System.out.println("Quantity-- " + BOOKS1.Quantity);
						System.out.println("Authors--" + A1.Author_Name);
						
				    }
				    k++;
			    }
			    
			    
			    
			    
			    
			    
			    
			    
			    
			   tx.commit();
			}
			catch (Exception e) {
			   if (tx!=null) tx.rollback();
			   e.printStackTrace(); 
			}finally {
			   session.close();
			}
		
		
	}
	
	
	public List<FINES> getFineDetails(String Card_id)
	{
		Session session = factory.openSession();
		Transaction tx = null;
		List<FINES> Fines = new ArrayList<FINES>();
		
		try {
			    tx = session.beginTransaction();
			    	    
			    
			    //Get borrower details
			    Query query1 = session.createQuery("from BORROWER WHERE Card_ID = :Card_id");
			    query1.setString("Card_id", Card_id);
				query1.setMaxResults(1);				
				BORROWER borrower=(BORROWER)query1.uniqueResult();
				if(borrower==null)
			    System.out.println("The borrower with given card id does not exists. Please register the borrower first");
				
				else
				{					
				//to count number of active book loans the borrower has....	
				List<BOOK_LOANS> bookloans=borrower.getBookLoans();	
				for (int i=0;i<bookloans.size();i++)
				Fines.add(bookloans.get(i).getFine());
				}
				  
				tx.commit();			
		        
		   }	
		  catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	         }finally {
	         session.close(); 
	        }
	      return Fines;
	}
	
	
	public List<BOOK> listOverDueBooks(String Card_id)
	{
		Session session = factory.openSession();
		Transaction tx = null;
		List<BOOK> OverDueBooks = new ArrayList<BOOK>();
		
		try {
			    tx = session.beginTransaction();			    	    
			    
			    //Get borrower details
			    Query query1 = session.createQuery("from BORROWER WHERE Card_ID = :Card_id");
			    query1.setString("Card_id", Card_id);
				query1.setMaxResults(1);				
				BORROWER borrower=(BORROWER)query1.uniqueResult();
				if(borrower==null)
			    System.out.println("The borrower with given card id does not exists. Please register the borrower first");
				
				else
				{					
						
			    List<BOOK_LOANS> bookloans=borrower.getBookLoans();	
				  for (int i=0;i<bookloans.size();i++)
				  {
					if (bookloans.get(i).getDate_out()==null)
						{
						  Date DO = new Date();						
					      if (bookloans.get(i).getDue_Date().compareTo(DO)==-1)
					          OverDueBooks.add(bookloans.get(i).getBOOK());								
						}
				   }
				 }
				  
				tx.commit();			
		        
		   }	
		  catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	         }finally {
	         session.close(); 
	        }
	      return OverDueBooks;
	}
	
	
	public void payFineForBorrower(String Card_id,String ISBN)
	{
		Session session = factory.openSession();
		Transaction tx = null;
		List<BOOK> OverDueBooks = new ArrayList<BOOK>();
		FINES Fine= new FINES();
		
		try {
			    tx = session.beginTransaction();			    	    
			    
			    //Get borrower details
			    Query query1 = session.createQuery("from BORROWER WHERE Card_ID = :Card_id");
			    query1.setString("Card_id", Card_id);
				query1.setMaxResults(1);				
				BORROWER borrower=(BORROWER)query1.uniqueResult();
				if(borrower==null)
			    System.out.println("The borrower with given card id does not exists. Please register the borrower first");
				
				else
				{					
				//to count number of active book loans the borrower has....	
					
			    List<BOOK_LOANS> bookloans=borrower.getBookLoans();	
				  for (int i=0;i<bookloans.size();i++)
				  {										
					      if (bookloans.get(i).getBOOK().getISBN()==ISBN){
					    	  Fine = bookloans.get(i).getFine();
					    	  Fine.setPAID('y');
					      }
						
				   }
				 }
				
				session.save(Fine);
		        tx.commit();			
		        
		   }	
		  catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	         }finally {
	         session.close(); 
	        }
	      
	}
	
	public FINES createFine(BOOK_LOANS BL)
	{
		DateTime Datein =  new DateTime(BL.getDate_in());
		DateTime Datedue =  new DateTime(BL.getDue_Date());
		
		Interval interval = new Interval(Datein,Datedue);
		
		String Inter = (interval.toString());
		FINES F=new FINES();		
		int Temp=Integer.parseInt(Inter);
		double Fine_amt = Temp*0.25;
		F.setFine_Amt(Fine_amt);
		F.setLoan_ID(BL.getLoan_ID());
		F.setPAID('N');
		return F;			
		
	}

}
