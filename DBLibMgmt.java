package com.dbproject.model;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import org.hibernate.HibernateException;
import org.hibernate.exception.*;
import org.hibernate.Query;
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.*;
import org.joda.time.*;



public class DBLibMgmt {
	
	public static SessionFactory factory;
	public static ServiceRegistry serviceRegistry; 
	
	public DBLibMgmt() {
		// TODO Auto-generated constructor stub
		
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
		
	}

	/*public static void main(String[] args) {
		// TODO Auto-generated method stub

		String ISBN="0001047973";
		String fname="Richard";
		String lname="Bailey";
		String Author="Sir conan d";
		String SSN="983-87-0005";
		String addr="3108 Browning Park , Richardson , TX";
		String Title="testing book2";
		String phone="(214) 718-0392";
		int Qty=10;
		String Search_String="Lord of the Far Land";
		String Card_ID="Ri870005";
		
		
		DBLibMgmt dblib = new DBLibMgmt();		
			
		// To create a BOOK record with authors
		//dblib.createBookRecord(ISBN,Title,Qty,Author);
		//dblib.setAuthors(ISBN,Author);
		
		
		//Requirement 1 - search for books
		dblib.getSearchDetails(Search_String);	
		
		//To create BorrowerRecord requirement 4 (Register button)
		//dblib.createBorrowerRecord(fname,lname,phone,SSN,addr);
		dblib.ViewBorrowerDetails(Card_ID);
		
		
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
	
	*/
	public Integer createBookRecord(String ISBN,String Titl,int Q,String Authors)
	{
		Session session = factory.openSession();
		Transaction tx = null;
		Integer BookStatus = null;
		try {
			   tx = session.beginTransaction();
			   
			   String Authid="";
			   Authid=Authid.concat("111");
			   Authid=Authid.concat(Integer.toString(Authors.length()));				   
			   String a = Authors.substring(Authid.length()/2);
			   Authid=Authid.concat(a);
			   
			   AUTHORS A = new AUTHORS(Authid,Authors);			   	
			   List<BOOK_LOANS> BL=new ArrayList<BOOK_LOANS>();	 
			     
			   BOOK new_book = new BOOK(ISBN,Titl,Q,BL,A);			   
			   new_book.setBOOK_LOANS(BL);
			   			   
			   Set<BOOK> Book=new HashSet<BOOK>();
			   Book.add(new_book);
			   A.setbooks(Book);
			   session.save(A);
			   System.out.println(a);
			   session.save(new_book);
			   System.out.println(a);
			   		   
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
	
	/*public Integer setAuthors(String ISBN,String[] Author)
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
	
	*/
	public String createBorrowerRecord(String Fname,String Lname,String Phone,String SSN,String Address)
	{
		Session session = factory.openSession();
		Transaction tx = null;
		Integer BorrowerStatus = null;
		
		String Card_id=null;
		
		try {
			   tx = session.beginTransaction();
			   
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
			   //System.out.println("Borrower with SSN: " + SSN + "Already exists");
			   return "false";
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
	      return Card_id;
	}
	
	
	public BORROWER ViewBorrowerDetails(String CardorSSN)
	{
		Session session = factory.openSession();
		Transaction tx = null;
		BORROWER borrowerFinal=null;
		
		try{
			Query query1 = session.createQuery("from BORROWER WHERE SSN = :SSN");
			   query1.setString("SSN", CardorSSN);
			   query1.setMaxResults(1);
			   BORROWER borrower=(BORROWER)query1.uniqueResult();
			   			   
			   if(borrower==null)
			   {
				   
				   Query query2 = session.createQuery("from BORROWER WHERE Card_ID = :Card_ID");
				   query2.setString("Card_ID", CardorSSN);
				   query2.setMaxResults(1);
				   BORROWER borrowerCardId =(BORROWER)query2.uniqueResult();
				   if(borrowerCardId==null)
				   {
				   System.out.println("No borrower exists in the database with given SSN or card id  " + CardorSSN);
				   borrowerFinal=null;
				   }
				   else
					   {
					   System.out.println("SSN is found:  " +CardorSSN);
					   borrowerFinal=borrowerCardId;   
					   }
			   }
			   else
				   
			   {
				   borrowerFinal = borrower; 
				   
			   }
		 //tx.commit();
		 
		}
	
	catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
         }finally {
         session.close(); 
        
        }
      
		 return borrowerFinal;
		
	}
	
	
	
	public int createBookLoanRecord(String Card_id,String ISBN)
	{
		Session session = factory.openSession();
		Transaction tx = null;
		Integer BorrowerStatus = null;
		
		
		try {
			    int flag=0; 
			    tx = session.beginTransaction();
			    int num_activeBL=0;int fineflag=0;int overdueflag=0;
			    Query query1 = session.createQuery("from BORROWER WHERE Card_ID = :Card_id");
			    query1.setString("Card_id", Card_id);
				query1.setMaxResults(1);
				//System.out.println("The borrower is " + query1.uniqueResult().getClass());
				BORROWER borrower=(BORROWER)query1.uniqueResult();
				if(borrower==null)
			    {System.out.println("The borrower with given card id does not exists. Please register the borrower first");
				return 1;}
				else
				{					
				//to count number of active book loans the borrower has....	
				List<BOOK_LOANS> bookloans=borrower.getBookLoans();	
				List<BOOK_LOANS> Activebookloans = new ArrayList<BOOK_LOANS>();	
				
				for (int i=0;i<bookloans.size();i++)
				{
					System.out.println("the che " + bookloans.get(i).getDate_in());
					if (bookloans.get(i).getDate_in()==null)
					{
					//collecting the active book loans for books not checked in...	
					num_activeBL++;
					Activebookloans.add(bookloans.get(i)); 
					System.out.println("no book loans " + num_activeBL);
					}
					else{
				     System.out.println("no book loans " + num_activeBL);		
				    //Checking if other book loans fines if exists are paid....
					FINES F=bookloans.get(i).getFine();
					if(F!=null && (F.getPAID()=='N'))
					fineflag++;
					}
				}
								
				if ((num_activeBL>=3)||(fineflag>0))	
				//System.out.println("The borrower isnt allowed to loan the book as he/she has either 3 or more Active book loans already or hasnt paid the fine for one or more checked in books");	
				return 2;
				else
				{   Date DO = new Date();
					//Checking if Active book loans are over due 
					if(Activebookloans.size()>0)
				    {
						System.out.println("Books available");
						for (int i=0;i<Activebookloans.size();i++)
						{
							if (DO.compareTo(Activebookloans.get(i).getDue_Date())==-1)
							   overdueflag++;
							else
								System.out.println("flag for overdue ..." + overdueflag);
							
						 }
					 }
					else
						System.out.println("there are no associated book loans");
						
					if(overdueflag>0)
				      //System.out.println("The borrower isnt allowed to loan the book as he/she currenlty an overdue book ");
						return 3;
				      else
				      {
					  Query query = session.createQuery("from BOOK WHERE ISBN = :ISBN");
					  query.setString("ISBN", ISBN);
					  query.setMaxResults(1);
					  BOOK assocbook = (BOOK) query.uniqueResult();
					   if (assocbook==null)
						   System.out.println("the book is not found" + ISBN);
					   else
					   {
						   System.out.println("the book is found" + assocbook.ISBN);
					   if (assocbook.Quantity==0)
				       //System.out.println("The book is not available to be loaned anymore");
				       return 4;
					   else{
					       			   
					       System.out.println("todays date" + DO);		   
					       
					       //Creating Book loan record and adding it to 
					       Date DD = new Date(DO.getTime()+14*24*60*60*1000);
					       System.out.println("due date" + DD);	
					      
					       BOOK_LOANS BL = new BOOK_LOANS( DD, DO,borrower,assocbook);
					       borrower.getBookLoans().add(BL);
					       //System.out.println("the book loan book is " + borrower.getBookLoans().get(1).getBOOK().Authors);
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
				}
		  	  tx.commit();
			}
		
		catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	         }finally {
	         session.close(); 
	        }
	      return 0;
	}
	
	
	public boolean borrowerBookCheckin(String Card_id,String ISBN)
	{
		Session session = factory.openSession();
		Transaction tx = null;
		Integer BorrowerStatus = null;
			
		try {
			    tx = session.beginTransaction();
			    Date DI = new Date();
			    
			    
			    //Get borrower details
			    Query query1 = session.createQuery("from BOOK_LOANS WHERE Card_ID = :Card_id AND ISBN = :ISBN");
			    query1.setString("Card_id", Card_id);
			    query1.setString("ISBN", ISBN);
			    query1.setMaxResults(1);
			    
							
			 		//System.out.println("the isbn is " + query1);		
				    //to count number of active book loans the borrower has....	
					//for(int i=0; i< qlist1 .size(); i++)
				    //{				   
						BOOK_LOANS B=(BOOK_LOANS)query1.uniqueResult();
					     B.setDate_in(DI);
					    if(B.getDue_Date().compareTo(DI)==-1)
					     {
						 //Setting Fine for borrower 
					      FINES FBL=this.createFine(B);
					      B.setFine(FBL);
					      FBL.setBOOK_LOANS(B);
					      
					    
					       //adding quantity back to Book
					       int Qty=B.getBOOK().Quantity+1;
					       BOOK assocbook=B.getBOOK();
					       assocbook.setQuantity(Qty);
					    
					    //Saving changes in DB
				           session.save(B);
				           session.save(FBL);
				           session.save(assocbook);
				       
					     }		   
	   				     
				tx.commit();		
	  }	
		  catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	         }finally {
	         session.close(); 
	        }
	      return true;
	}
	
	public List<Book_AuthorBean> getSearchDetails(String S)
	{
		List<Book_AuthorBean> BookAList = new ArrayList<Book_AuthorBean>();
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			
			   Book_AuthorBean Bean ;
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
					   AUTHORS Authors=B.getAuthors();
					   System.out.println("BOOK details are as follows :");
					   System.out.println("ISBN -- " + B.ISBN);
					   System.out.println("Title-- " + B.Title);
					   System.out.println("Quantity-- " + B.Quantity);
					   
					   if(B.Authors!=null)
						{
						    String isbnid1 = "'" + B.ISBN + "'";
							List resultst = session.createSQLQuery("select Author_ID from BOOK_AUTHORS WHERE ISBN =" + isbnid1).list();	
							String Authorid1 = "'" + (String) resultst.get(0)+ "'";
							List resultA1 = session.createSQLQuery("select Auth_Name from AUTHORS WHERE AUTHOR_ID =" + Authorid1).list();	
							String AuthorName = (String)resultA1.get(0);
						
						System.out.println("Authors--");
						System.out.println(AuthorName);						
						Bean = new Book_AuthorBean(B.ISBN,B.Title,B.Quantity,AuthorName);
						}
						else
					    {
						Bean = new Book_AuthorBean(B.ISBN,B.Title,B.Quantity);	}
						
					    BookAList.add(Bean);
					   					   
				     }					
				   }	
			   
			   
			  // Searching book based on Title...
			    int i=0;
			    String[] subsearch = S.split(" ");
			    while ( i<subsearch.length)
			    {
			     System.out.println(subsearch[i]);
			     Query queryT = session.createQuery("from BOOK WHERE Title like :Title");
			     List<?> qlistT = queryT.setString("Title", "%" + subsearch[i]+"%").list();
			     if(qlist.isEmpty())
					   System.out.println("No results for Tite");    
				   //List<?> qlistT = query.list();
				    for(int j=0; j< qlistT .size(); j++)
				    {
						BOOK B1=(BOOK)qlistT.get(j);						
						System.out.println("BOOK details from title are as follows :");
						System.out.println("ISBN -- " + B1.ISBN);
						System.out.println("Title-- " + B1.Title);
						System.out.println("Quantity-- " + B1.Quantity);
						if(B1.Authors!=null)
						{   
							String isbnid = "'" + B1.ISBN + "'";
							List results = session.createSQLQuery("select Author_ID from BOOK_AUTHORS WHERE ISBN =" + isbnid).list();	
							String Authorid = "'" + (String) results.get(0)+ "'";
							List resultA = session.createSQLQuery("select Auth_Name from AUTHORS WHERE AUTHOR_ID =" + Authorid).list();	
							String AuthorName = (String)resultA.get(0);
						System.out.println("Authors--");
						System.out.println(AuthorName );						
						Bean = new Book_AuthorBean(B1.ISBN,B1.Title,B1.Quantity,AuthorName );
						}
						else
					    {
						Bean = new Book_AuthorBean(B1.ISBN,B1.Title,B1.Quantity);	}
						
						BookAList.add(Bean);
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
						Set<BOOK> BOOKS1=A1.getbooks();
						for (BOOK s : BOOKS1) {					    
						     
						System.out.println("BOOK details from title are as follows :");
						System.out.println("ISBN -- " + s.ISBN);
						System.out.println("Title-- " + s.Title);
						System.out.println("Quantity-- " + s.Quantity);
						System.out.println("Authors--" + A1.Author_Name);
						Bean = new Book_AuthorBean(s.ISBN,s.Title,s.Quantity,A1.Author_Name);
						BookAList.add(Bean);
						}
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
		return BookAList;
		
	}
	
	
	public List<FINES> getFineDetails(String Card_id)
	{
		Session session = factory.openSession();
		Transaction tx = null;
		List<FINES> Fines = new ArrayList<FINES>();
		
		try {
			    tx = session.beginTransaction();
			    	    
			    
			    //Get borrower details
			    Query query1 = session.createQuery("from BOOK_LOANS WHERE Card_ID = :Card_id");
			    query1.setString("Card_id", Card_id);
								
			    List<?> list=query1.list();			
				
				
				for (int i=0;i<list.size();i++)
				{BOOK_LOANS BL = (BOOK_LOANS) list.get(i);
				System.out.println(BL.getLoan_ID());
				Query query2 = session.createQuery("from FINES WHERE Loan_ID= :Loanid");
			    query2.setInteger("Loanid", BL.getLoan_ID());			    
				query2.setMaxResults(1);
				System.out.println(BL.getLoan_ID());
				FINES F=(FINES)query2.uniqueResult();
				if(F==null)
				System.out.println("There is no fine for the borrower");
				Fines.add(F);
						
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
	
	
	public List<OverdueBean> listOverDueBooks(String Card_id)
	{
		Session session = factory.openSession();
		Transaction tx = null;
		List<BOOK> OverDueBooks = new ArrayList<BOOK>();
		//OverdueBean ODBean= new OverdueBean();
		List<OverdueBean> ODBeans = new ArrayList<OverdueBean>();
		try {
			    tx = session.beginTransaction();
			    
			    int flag=0;
			    //Get borrower details
			    Query query1 = session.createQuery("from BOOK_LOANS WHERE Card_ID = :Card_id");
			    query1.setString("Card_id", Card_id);
				List<?>Qlist1=query1.list();	
				System.out.println("no of loans are "  + Qlist1.size()); 
				for(int i=0; i< Qlist1 .size(); i++)
					{
					BOOK_LOANS B=(BOOK_LOANS)Qlist1.get(i);			
					System.out.println("loan--- "  + B.getLoan_ID() + B.getDate_in()); 
			         if (B.getDate_in()==null)
						{
			        	 System.out.println(" over dued loan books are--- "  + B.getBORROWER().getCard_ID());
						  Date DO = new Date();						
					      if (B.getDue_Date().compareTo(DO)==-1)
					      {   
					      //System.out.println("the borrower is "  + B.getBORROWER().getCard_ID()); 
					      List results = session.createSQLQuery("select ISBN from BOOK_LOANS WHERE Loan_ID =" + B.getLoan_ID()).list();	
					      //List results1 = session.createSQLQuery("select ISBN ,from BOOK WHERE ISBN =" + results.get(0).toString()).list();
					      List results2 = session.createSQLQuery("select Title from BOOK WHERE ISBN =" + results.get(0).toString()).list();
					      List results3 = session.createSQLQuery("select  Quantity from BOOK WHERE ISBN =" + results.get(0).toString()).list();
					     				      				    	   
					      
					      //System.out.println("the book is "  + results.get(0).toString() + " " + results2.get(0).toString() +  " " + results3.get(0).toString() );					      
					      OverdueBean ODBean= new OverdueBean();
					      BOOK Book = new BOOK();
					      Book.setISBN(results.get(0).toString());
					      Book.setTitle(results2.get(0).toString());
					      Book.setQuantity(Integer.parseInt(results3.get(0).toString()));
					      ODBean.setB(results.get(0).toString());
					      ODBean.setBorrower(B.getBORROWER());
					      ODBeans.add(ODBean);
					       
					     
					      }
						}
				   }
				for(int i=0;i<ODBeans.size();i++){

					
					System.out.println(ODBeans.get(i).getB());
					}
				  tx.commit();			
		        
		      }	
		  catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	         }finally {
	         session.close(); 
	        }
	      return ODBeans;
	}
	
	
	public boolean payFineForBorrower(String Card_id,String ISBN)
	{
		Session session = factory.openSession();
		Transaction tx = null;
		List<BOOK> OverDueBooks = new ArrayList<BOOK>();
		FINES Fine= new FINES();
		
		try {
			    tx = session.beginTransaction();			    	    
			    
			    //Get borrower details
			    Query query1 = session.createQuery("from BOOK_LOANS WHERE Card_ID = :Card_id AND ISBN = :ISBN");
			    query1.setString("Card_id", Card_id);
			    query1.setString("ISBN", ISBN);
				query1.setMaxResults(1);				
				BOOK_LOANS book_loan=(BOOK_LOANS)query1.uniqueResult();
				if(book_loan==null)
				{
			    System.out.println("The borrower with given card id does not exists. Please register the borrower first");
				return false;
				}
				else
				{
					
				Query query2 = session.createQuery("from FINES WHERE Loan_ID = :Loanid");
			    query2.setInteger("Loanid", book_loan.getLoan_ID());
			   
				query2.setMaxResults(1);
				Fine=(FINES)query2.uniqueResult();
				//count number of active book loans the borrower has....	
				
					    	  Fine.setPAID('y');
					    	  System.out.println(" the card id is " + Fine.getBOOK_LOANS().getBORROWER().getCard_ID());
					    	  //bookloans.get(i).setFine(Fine);
					    	  //Fine.setBOOK_LOANS(bookloans.get(i));
					
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
		return true;  
		
	}
	
	public FINES createFine(BOOK_LOANS BL)
	{
		long diff = BL.getDate_in().getTime() - BL.getDue_Date().getTime();
	    
		DateTime Datein =  new DateTime(BL.getDate_in());
		DateTime Datedue=  new DateTime(BL.getDue_Date());
		int days = (int)TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		
		FINES F=new FINES();		
		
		float Fine_amt = (float) (days*0.25);
		System.out.println(Fine_amt  +  "days..." + days);
		F.setFine_Amt(Fine_amt);
		F.setLoan_ID(BL.getLoan_ID());
		F.setPAID('N');
		return F;			
		
	}
	
	public void UpdateFines()
	{
		Session session = factory.openSession();
		Transaction tx = null;
	   
		try {
			tx = session.beginTransaction();
		    Query queryF = session.createQuery("from FINES");		
		    List<?>Qlist1=queryF.list();	
			
			for(int i=0; i< Qlist1 .size(); i++)
				{
				FINES Fine =(FINES)Qlist1.get(i);	
		        float UpdatedFine_amt = (float) ((float)(Fine.getFine_Amt()) + 0.25);		
		        Fine.setFine_Amt(UpdatedFine_amt);
		        System.out.println(UpdatedFine_amt +  " .... amount nw ");
		        session.save(Fine);
		        			
				}
			tx.commit();
		 }
		catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	         }finally {
	         session.close(); 
	        }
	
	}
	
}
