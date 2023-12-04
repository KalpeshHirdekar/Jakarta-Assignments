

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class AddStudServ1
 */
public class AddStudServ1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con;
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/bit";
			con=DriverManager.getConnection(url,"root","Kalpesh@123");
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			response.setContentType("text/html");
			PrintWriter pw=response.getWriter();
			String name=request.getParameter("name");
			int age=Integer.parseInt(request.getParameter("age").trim());
			String address=request.getParameter("address");
			PreparedStatement pst=con.prepareStatement("insert into student(name,age,address) values(?,?,?)");
			pst.setString(1,name);
			pst.setInt(2,age);
			pst.setString(3, address);
			int k=pst.executeUpdate();
			if(k>0)
			{
				pw.println("Record has been added");
			}
			else
			{
				pw.println("cannot add");
			}
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
		}
	}

}
