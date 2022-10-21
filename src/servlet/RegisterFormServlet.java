package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.RegistLogic;
import model.User;

/**
 * ユーザー登録を行うサーブレットクラス
 *
 */
@WebServlet("/Register")
public class RegisterFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registerForm.jsp");
		dispatcher.forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		User user = new User();
		user.setUserName(request.getParameter("userName"));
		user.setGenderKbn(request.getParameter("genderKbn"));
		user.setBirthDate(request.getParameter("birthDate"));
		user.setMailAddress(request.getParameter("mailAddress"));
		user.setPassword(request.getParameter("password"));

		RegistLogic rL = new RegistLogic();
		String result = rL.executeRegist(user);

		Boolean registOk = true;
		switch(result) {
		case "1":
			request.setAttribute("errorMsg", "半角英数字で入力して下さい");
			registOk = false;
			break;
		case "2":
			request.setAttribute("errorMsg", "4文字以上で入力して下さい");
			registOk = false;
			break;
		case "3":
			// メールアドレスが存在している場合、登録せずにエラーを表示する。
			request.setAttribute("errorMsg2", "このメールアドレスは既に登録されています。");
			registOk = false;
			break;
		}

		if(!registOk) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registerForm.jsp");
			dispatcher.forward(request, response);
		}

		request.setAttribute("user", user);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registerResult.jsp");
		dispatcher.forward(request, response);
	}

}