package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class edit
 */
@WebServlet("/Edit")
public class Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Edit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8"); //これを入れないと文字化けする
		
		String controller = "com.mysql.jdbc.Driver";
		String db_url = "jdbc:mysql://localhost/memoschema?useSSL=false";
		String user_name = "root";
		String password = "*****";
		
		ConnectingToDB connection = new ConnectingToDB(null, controller, db_url, user_name, password);
		Connection con = connection.getCon(); //con = null;という初期化に相当する
		
		/*-----------------------------編集画面の準備-------------------------------------*/
		
		int memo_id = Integer.parseInt(request.getParameter("memo_no"));
		
		/*選択されたメモのタイトルを取得するSQL構文*/
		PreparedStatement get_memo_title = null;
		ResultSet rs_get_memo_title = null;
		
		/*選択されたメモの内容を取得するSQL構文*/
		PreparedStatement get_memo_content = null;
		ResultSet rs_get_memo_content = null;
		
		
		try {
			
			con = connection.generateConnection();
			
			/*メモのタイトルをDBから取得*/
			get_memo_title = con.prepareStatement("SELECT title FROM memoschema.test WHERE `id` = ?;");
			get_memo_title.setInt(1, memo_id);
			rs_get_memo_title = get_memo_title.executeQuery();
			
			/*メモの内容をDBから取得*/
			get_memo_content = con.prepareStatement("SELECT content FROM memoschema.test WHERE `id` = ?;");
			get_memo_content.setInt(1, memo_id);
			rs_get_memo_content = get_memo_content.executeQuery();
			
			String id_of_memo = Integer.toString(memo_id);
			String title_of_memo = "";
			String content_of_memo = "";
			
			while(rs_get_memo_title.next()) {
				title_of_memo = rs_get_memo_title.getString("title");
			}
			
			while(rs_get_memo_content.next()) {
				content_of_memo = rs_get_memo_content.getString("content");
			}
			
			get_memo_title.close();
			get_memo_content.close();
			rs_get_memo_title.close();
			rs_get_memo_content.close();
			
			request.setAttribute("memo_id", id_of_memo);
			request.setAttribute("memo_title", title_of_memo);
			request.setAttribute("memo_content", content_of_memo);
			
		}catch(Exception e) {
			
			System.out.println("error:"+e);
		
		}finally{
			
			try {
				
				get_memo_title.close();
				get_memo_content.close();
				rs_get_memo_title.close();
				rs_get_memo_content.close();
				con.close();
			
			}catch(Exception e) { }
				
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/Edit.jsp");
		dispatcher.forward(request,  response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
