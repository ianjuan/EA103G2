package filters;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.emp.model.EmployeeVO;

public class LoginFilterBackEnd implements Filter {

	private FilterConfig config;

	public void init(FilterConfig config) {
		this.config = config;
	}

	public void destroy() {
		config = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws ServletException, IOException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		Object account = session.getAttribute("empVO");
		
		
		if (account == null ) {
			session.setAttribute("location", req.getRequestURI());
			res.sendRedirect(req.getContextPath() + "/back-end/emp/login.jsp");
			return;
		}

		else {
			chain.doFilter(request, response);
		}
	}
}