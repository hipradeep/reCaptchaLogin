package signup;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import database.ConnectDB;
import utils.EncryptionString;
import utils.VerifyRecaptcha;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		// out.println("Email - " + email);
		// out.println("Password - " + password);

		// get reCAPTCHA request param
		//String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
		//System.out.println(gRecaptchaResponse);
		//boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);

		String encodedPassword = EncryptionString.encode(password);

		try {
			ConnectDB db = new ConnectDB();
			Connection conn = db.getConneection();

			Statement st = db.getStatements();

			String sql2 = "select email, password from users where email='" + email + "' and password='"
					+ encodedPassword + "'";

			ResultSet rs = st.executeQuery(sql2);
			if (rs.next()) {
				// out.println("Email - " + rs.getString(1) + " Passworrd - " +
				// rs.getString(2));

				String decodePassword = EncryptionString.deccode(rs.getString(2));
				System.out.println(decodePassword);

				//add && verify boolean
				if (email.equals(rs.getString(1)) && password.equals(decodePassword) ) {
					response.sendRedirect("welcome.html");
				} else {
					response.sendRedirect("login.html");
				}

			} else {
				out.println("Recard Not Found!!");
			}

		} catch (Exception e) {
			System.out.println("Error - " + e.getMessage());
		}
	}

}
