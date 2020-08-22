package application;


import java.sql.*;

public class database
{
	
	static Connection myConn;
	static Statement myStat;
	
	static ResultSet connect()
	{
		ResultSet myRs=null;
		try
		{			
			myConn = DriverManager.getConnection("You-database-info-for-sign-up");		
			myStat =(Statement) myConn.createStatement();
		}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return myRs;	
	}
	static ResultSet show(String query)
	{
		ResultSet myRs=null;
		try
		{ 
			myRs= myStat.executeQuery(query);			
		}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return myRs;	
	}
	static void add(String sorgu)
	{
		try
		{	
			myStat.executeUpdate(sorgu);
		}
		catch(Exception e)
		{
	e.printStackTrace();	
		}	
	}	
}
