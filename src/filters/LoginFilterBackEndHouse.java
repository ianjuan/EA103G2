package filters;
import java.io.*;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import com.emp.model.EmployeeVO;
import com.rig.model.RightService;
import com.rig.model.RightVO;

public class LoginFilterBackEndHouse implements Filter {

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
		EmployeeVO empVO = (EmployeeVO)account;
		String emp_no =empVO.getEmp_no();
		RightService rigSvc = new RightService();
		List<RightVO> list = rigSvc.getAll(emp_no);
		for(RightVO right :list) {
			if(right.getFun_no().equals("C")) {
				chain.doFilter(request, response);
				return;
			}
		}
		res.sendRedirect(req.getContextPath() + "/back-end/emp/noright.jsp");
		return;
		
	}
}