package com.exam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//import com.p1.Details;

public class AdminDao {

	public static   Connection getConnection()
	{
	Connection con=null;
	
		try
		{  
			Class.forName("com.mysql.jdbc.Driver");  
			 con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/admin","root","root"); 
		}
		catch(Exception e)
		{ 
			System.out.println(e);
	    }
		return con;
	}
	public static int createTable(Details p) throws SQLException
	{
		int id1=0;
		
		Connection con = getConnection();
		PreparedStatement ps=null;
	    ps=con.prepareStatement("insert into admindetailsr(email,name,password,company,contact) values(?,?,?,?,?)");
	    
	    ps.setString(1, p.getEmail());	
	ps.setString(2, p.getName());
		
		ps.setString(3, p.getPassword());
		ps.setString(4, p.getCompany());
		ps.setString(5, p.getContact());
		id1=ps.executeUpdate();
		System.out.println(id1);
		
		String s1 = p.getEmail();
		createDatabase(s1);
		return id1;
	}
	public static void createDatabase(String s1) throws SQLException
	{
		try
		{
		Connection con=getConnection();
		PreparedStatement ps1=null;
     System.out.println("create db worked");
		int l=s1.indexOf('@');
		String s = s1.substring(0, l);
		String query = "create database "+s;
		System.out.println(query);
	     ps1 = con.prepareStatement(query);
		 ps1.executeUpdate();
		 System.out.println("create db worked");	
		}
		catch(Exception e)
		{
			System.out.println("already created");
		}
		int i=0;
		Connection con=useDatabase(s1);
		PreparedStatement ps=null;
	    ps=con.prepareStatement("create table testsdetails(id int  primary key not null auto_increment, testname varchar(30),levels varchar(30))");
	    i=ps.executeUpdate();
	    
		
	}
	public static Connection useDatabase(String s1)
	{
		Connection con=null;
		
		PreparedStatement ps1=null;

		int l=s1.indexOf('@');
		String s = s1.substring(0, l);
		String query = "jdbc:mysql://localhost:3306/"+s;
		System.out.println(query);
		
		
		try
		{  
			Class.forName("com.mysql.jdbc.Driver");   
			 con =  DriverManager.getConnection(query,"root","root"); 
		}
		catch(Exception e)
		{ 
			System.out.println(e);
	    }
		return con;
	}
	public static boolean checkDetails(String email,String password) throws SQLException
	{
		boolean b=false;
		try{
		Connection con = getConnection();
		PreparedStatement ps1=con.prepareStatement("select password from admindetailsr where email=?");
	    ps1.setString(1, email);
	    ResultSet re = ps1.executeQuery();
	    while(re.next())
	    {
	    String opassword = re.getString(1);
       System.out.println(opassword);
	    
	    if(password.equals(opassword))
	    {
	    	b= true;
	    }
	    else
	    {
	    	b=false;
	    }
		}
		}
		catch(Exception e)
		{
			System.out.println("Error at check details");
		}
		return b;
		
	}
	public static void insertValues(String email,String name,String level) throws SQLException
	{
		Connection con=useDatabase(email);	
		PreparedStatement ps=con.prepareStatement("insert into testsdetails(testname,levels) values(?,?)");
		 ps.setString(1, name);	
		 ps.setString(2, level);
		 ps.executeUpdate();
	}
	
}
