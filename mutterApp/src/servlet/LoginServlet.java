package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.LoginLogic;
import model.Mutter;
import model.TopLogic;
import model.User;



@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * ログイン画面呼出し処理
	 * @param request リクエスト
	 * @param response レスポンス
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}


	/**
	 * ログイン画面にてボタン押下時の呼び出し処理
	 * @param request リクエスト
	 * @param response レスポンス
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		User user = new User();
		// 画面入力情報を取得する。
		user.setMailAddress(request.getParameter("mailAddress"));
		user.setPassword(request.getParameter("password"));

		// ログイン処理を呼び出す。
		LoginLogic loginLogic = new LoginLogic();
		user = loginLogic.doLogin(user);

		// 戻り値がnullの場合、ユーザー情報の登録無し。
		if(user.getUserName() == null) {
			// ユーザー情報がない処理
			request.setAttribute("errorMsg", "そのユーザーは登録されていません。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		} else {
			ServletContext application = this.getServletContext();
			application.setAttribute("user", user);
			// つぶやき情報を取得する。
			TopLogic tL = new TopLogic();
			List<Mutter> mutterList = tL.doGetMutterList(user.getMailAddress());
			request.setAttribute("mutterList", mutterList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/top.jsp");
			dispatcher.forward(request, response);
		}

	}
}