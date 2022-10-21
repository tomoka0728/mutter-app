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

import model.Mutter;
import model.TopLogic;
import model.User;



@WebServlet("/Top")
public class TopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// つぶやきリストを取得する。
		ServletContext application = this.getServletContext();
		User user = (User) application.getAttribute("user");
		TopLogic tL = new TopLogic();
		List<Mutter> mutterList = tL.doGetMutterList(user.getMailAddress());
		request.setAttribute("mutterList", mutterList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/top.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext application = this.getServletContext();
		User user = (User) application.getAttribute("user");
		TopLogic tL = new TopLogic();
		request.setCharacterEncoding("UTF-8");
		// ボタンによって処理を分岐する。
		String action = request.getParameter("button");
		Boolean result = false;
		String mutterId = request.getParameter("mutterId");
		switch(action) {
			case "♡":
				if("1".equals(request.getParameter("favOnFlg"))) {
					// イイネ押下した事がある場合
					result = tL.executeDeleteFavorite(user, mutterId);
				} else {
					// イイネボタンが押下された場合 イイネ情報を登録する。
					result = tL.executeInsertFavorite(user, mutterId);
				}
				break;
			case "コメント":
				result = tL.executeInsertComment(user, mutterId, request.getParameter("comment"));
				break;
			case "つぶやく":
				// つぶやきを登録する。
				result = tL.executeInsertMutter(user, request.getParameter("mutter"));
				break;
			case "削除":
				// つぶやきを削除する。
				result = tL.executeDeleteMutter(mutterId, request.getParameter("likeCount"),request.getParameter("commentCount"));
		}
		if(!result) {
			// TODO：失敗時の処理を記載
		}
		// つぶやきリストを取得する。
		List<Mutter> mutterList = tL.doGetMutterList(user.getMailAddress());
		request.setAttribute("mutterList", mutterList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/top.jsp");
		dispatcher.forward(request, response);




	}

}