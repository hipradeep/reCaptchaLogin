package signup;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.ConnectDB;
import utils.EncryptionString;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Registration() {
		super();
		// TODO Auto-generated constructor stub
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		//System.out.println(name);
		String email = request.getParameter("email");
		//System.out.println(email);
		String mobile = request.getParameter("mobile");
		//System.out.println(mobile);
		
		String password = request.getParameter("password");
		//System.out.println(password);
		
		String encodedPassword=EncryptionString.encode(password);
		

		try {
			ConnectDB db = new ConnectDB();
			Connection conn = db.getConneection();

			Statement st = db.getStatements();

			String sql = "insert into users(email, name, mobile, password) values('" + email + "', '" + name + "', '" + mobile + "', '" + encodedPassword + "')";

			boolean b = st.execute(sql);

			response.sendRedirect("index.html");

		} catch (Exception e) {
			System.out.println("Error - " + e.getMessage());
			out.println("Error - " + e.getMessage());
		}

	}

}
