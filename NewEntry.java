package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class newentry
 */
@WebServlet("/NewEntry")
public class NewEntry extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewEntry() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8"); //これを入れないと文字化けする
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/NewEntry.jsp");
		dispatcher.forward(request, response);
		
	}
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
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
