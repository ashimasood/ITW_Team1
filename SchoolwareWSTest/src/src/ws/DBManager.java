package src.ws;

import java.sql.*;
import java.util.ArrayList;

public class DBManager {
			
		public static String MySQLrootUser="root";
		public static String MySQLrootPassword="ashima";
		public static String MySQLdriver="com.mysql.jdbc.Driver";
		public static String MySQLConUrl = "jdbc:mysql://127.0.0.1:3306/schoolware";
		
		 private static Connection conn = null;
	     private static Statement statement = null;
	     private static ResultSet resultSet = null;
	
	     public static void getConn() throws Exception{
	         try 
	         {
	         	Class.forName(MySQLdriver).newInstance();
	 		    conn  = DriverManager.getConnection(MySQLConUrl, MySQLrootUser, MySQLrootPassword);
	         } 
	         catch (Exception e) 
	         {
	             System.err.println("Got an exception! ");
	             System.err.println(e.getMessage());
	         }
	         
	      }
	     	     
	     public static void closeConn() throws Exception{
	    	 try
	    	 {
	    		 conn.close();
	    	 }
	    	 catch (Exception e) 
	         {
	             System.err.println("Got an exception! ");
	             System.err.println(e.getMessage());
	         }
	     }
	     
	     public static ArrayList<Integer> getTestQuestions(int testId) throws Exception{
	    	 
	    	 ArrayList<Integer> questionIDs = new ArrayList<Integer>();
	    	    	 
	    	 getConn();
	    	 statement = conn.createStatement();
	    	 
	    	 resultSet = statement.executeQuery("select question_id from question_test where test_id = " + testId);
	    	 
	    	 while (resultSet.next()){
	        
	    		 questionIDs.add(Integer.parseInt(resultSet.getString(1)));
	    		 
	         }
	    	 
	    	closeConn();
	    	return questionIDs; 
	    	 
	     }

	     
}
