
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author IsisNaomi
 */
public class DataBaseConn {
    Connection myConn = null;
    Statement myStmt = null;
    ResultSet myRs = null;
    
    public void connect() throws SQLException{
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root" , "root");
            myStmt = myConn.createStatement();
    }
    
    public ResultSet excecuteQuery(String query ) throws SQLException{
        System.out.println(query);
        myRs = myStmt.executeQuery(query);
        return myRs;
    }
    
    public void closeAll() throws SQLException{
        if (myRs != null) {
            myRs.close();
	}
			
        if (myStmt != null) {
            myStmt.close();
	}
			
	if (myConn != null) {
            myConn.close();
	}
}
}
