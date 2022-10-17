package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectingToDB {
	
	/*メンバ変数*/
	Connection con;
	
	String controller;
	String db_url;
	String user_name;
	String password;
	
	/*コンストラクタ*/
	public ConnectingToDB(Connection con, String controller, String db_url, String user_name, String password) {
		
		this.con = con;
		this.controller = controller;
		this.db_url = db_url;
		this.user_name = user_name;
		this.password = password;
		
	}
	
	/*DBへの接続*/
	public Connection generateConnection() throws SQLException, ClassNotFoundException{
		
		Class.forName(controller);
		this.con = DriverManager.getConnection(
				this.db_url,
				this.user_name,
				this.password
				);
				
		return this.con;
		
	}
	
	public Connection getCon() {
		
		return this.con;
		
	}
	
	public Connection setConNull() {
		
		return this.con = null;
		
	}
	
}


