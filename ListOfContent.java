package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 * Servlet implementation class listofcontent
 */
@WebServlet("/ListOfContent")
public class ListOfContent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListOfContent() {
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
		
		/* 詳細表示ボタンが押下された際の処理 */
		if("true".equals(request.getParameter("detail"))) {
			
			System.out.println(request.getParameter("memo_no"));
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/DetailOfContent.jsp");
			dispatcher.forward(request, response);
			
		}
		
		/*--------詳細表示画面の削除ボタンが押下された場合の処理---------*/
		if("true".equals(request.getParameter("delete"))) {
			
			/*削除するメモのidの設定*/
			int delete_memo_id = Integer.parseInt(request.getParameter("delete_id"));
			
			/*メモをDBから削除するSQL構文*/
			PreparedStatement delete_memo = null;
			
			try {
				
				con = connection.generateConnection();
			
				/*DBからメモを削除*/
				delete_memo = con.prepareStatement("DELETE FROM memoschema.test WHERE `id` = ?;");
				delete_memo.setInt(1, delete_memo_id);
				delete_memo.executeUpdate();
				
				delete_memo.close();
				con.close();
			
			}catch(Exception e) {
		
				System.out.println("error:"+e);
	
			}finally {
		
				try {
					
					delete_memo.close();
					con.close();
		
				}catch(Exception e) { 
					
				}finally {
					
				}
				
			}
			
		}	
			
		/*----------------新規登録画面で登録ボタンが押下された際の処理----------------*/
		if("true".equals(request.getParameter("new_entry"))) {
			
			/*DBのidの最大値を取得するためのSQL構文*/
			PreparedStatement get_max_id = null;
			ResultSet rs_get_max_id = null;
			
			/*DBにメモを登録するためのSQL構文*/
			PreparedStatement insert_new_memo = null;
			
			try {
				
				con = connection.generateConnection(); /*DBへ接続*/
					
				
					String new_title = request.getParameter("title");
					String new_content= request.getParameter("content");
					
					/*エスケープ処理*/
					escapeSequencer(new_title);
					escapeSequencer(new_content);
					
					if(new_title.length() > 50 || new_content.length() > 1000) {
						
						System.out.println("文字数超過");
						JFrame jFrame = new JFrame();
						JOptionPane.showMessageDialog(jFrame,  "文字数が超過しています。やり直してください。");
					
					}else{
					
					/*DBからidの最大値を取得*/
					get_max_id = con.prepareStatement("SELECT MAX(test.id) AS max FROM memoschema.test;");
					rs_get_max_id = get_max_id.executeQuery();
					
					int max_id = 0;
					
					while(rs_get_max_id.next()) {
						
					 max_id = rs_get_max_id.getInt("max");
					 
					}
					
					/*DBへ登録*/
					insert_new_memo = con.prepareStatement("INSERT INTO memoschema.test (id, title, content) VALUES (?, ?, ?);");
					insert_new_memo .setInt(1, max_id+1);
					insert_new_memo .setString(2, new_title);
					insert_new_memo .setString(3, new_content);
					insert_new_memo .executeUpdate();
						
					get_max_id.close();
					rs_get_max_id.close();
					insert_new_memo.close();
					con.close();
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/ListOfContent.jsp");
					dispatcher.forward(request, response);
			
				}
					
			}catch(Exception e) {
				
				System.out.println("error:"+e);
				
			}finally {
				
				try {
					con.close();
					get_max_id.close();
					rs_get_max_id.close();
					insert_new_memo.close();
					}catch(Exception e) {
						System.out.println("error:"+e);
					}
				
			}
			
		}
			
		/*-----------編集画面で登録ボタンが押下された際の処理-------------*/
		if("true".equals(request.getParameter("update"))) {
		
			String edit_title = request.getParameter("edited_title");
			String edit_content = request.getParameter("edited_content");
			int edit_memo_id = Integer.parseInt(request.getParameter("edited_id"));
			
			escapeSequencer(edit_title);
			escapeSequencer(edit_content);
			
			/*編集したメモの内容をDBに登録するSQL構文*/
			PreparedStatement update_memo = null;
		
			if(edit_title.length() > 50 || edit_content.length() > 1000) {
				
				System.out.println("文字数超過");
				JFrame jFrame = new JFrame();
				JOptionPane.showMessageDialog(jFrame,  "文字数が超過しています。やり直してください。");
				
			}else{
				
				try{
					con = connection.generateConnection();
				
					/*編集内容をDBへ登録*/
					update_memo = con.prepareStatement("UPDATE memoschema.test SET title = ?, content = ? WHERE id = ?;");
					update_memo.setString(1, edit_title);
					update_memo.setString(2, edit_content);
					update_memo.setInt(3, edit_memo_id);
					update_memo.executeUpdate();
					
					update_memo.close();
					con.close();
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/ListOfContent.jsp");
					dispatcher.forward(request, response);
					
					}catch(Exception e) {
						
						System.out.println("error"+e);
						
					}finally {
						
						try {
							con.close();
							update_memo.close();
						}catch(Exception e) {
							
						}
						
					}	
				
			}
			
		}
		
		/*--------------------一覧表示画面でDBに登録されているメモをすべて表示させる処理------------------------------------*/
			
		/*DBに登録されているメモを取得するＳＱＬ構文*/
		PreparedStatement get_all_memo = null;
		ResultSet rs_get_all_memo = null;
		
		try {
			
			con = connection.generateConnection();
			
			/*DBからメモの情報を取得*/
			get_all_memo = con.prepareStatement("select * from test");
			rs_get_all_memo = get_all_memo.executeQuery();
			
			/*メモの情報を格納する配列*/
			ArrayList<MemoData> memo_list = new ArrayList<MemoData>();
	
			while(rs_get_all_memo.next()) {
				
				MemoData d = new MemoData(rs_get_all_memo.getString("id"), rs_get_all_memo.getString("title"), rs_get_all_memo.getString("content"));
				memo_list.add(d);
				
			}
			
			request.setAttribute("all_memo", memo_list);
			
			get_all_memo.close();
			rs_get_all_memo.close();
			con.close();
			
		}catch(Exception e) {
			
			System.out.println(e.getMessage());
		
		}finally {
			
			try {
				get_all_memo.close();
				rs_get_all_memo.close();
				con.close();
			}catch(Exception e) { }
				
		}
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/ListOfContent.jsp");
		dispatcher.forward(request,  response);
		
	}

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	/*シーケンス処理のメソッド*/
	public String escapeSequencer(String input_str) {
		
		input_str = input_str.replace("<", "&lt");
		input_str = input_str.replace(">", "&gt");
		input_str = input_str.replace("&", "&amp");
		input_str = input_str.replace("\"", "&quot");
		input_str = input_str.replace("'", "&#x27");
		
		return input_str;
		
	}
	
}
