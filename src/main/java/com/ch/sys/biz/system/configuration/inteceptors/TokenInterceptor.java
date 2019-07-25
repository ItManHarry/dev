package com.ch.sys.biz.system.configuration.inteceptors;
import java.io.OutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.ch.sys.biz.system.results.ServerResultObject;
import com.ch.sys.biz.system.utils.JsonUtils;
/**
 * 系统登录拦截器
 */
public class TokenInterceptor implements HandlerInterceptor {

	
	/**
	 * 在Controller执行前调用
	 */
//	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		System.out.println("令牌处理拦截器...... URL : " + request.getRequestURI());
		String user = request.getParameter("username");
//		if(user != null && user.equals("Harry")){
//			System.out.println("The user has logined ...");
//			//return true;
//		}else{
////			errorOutput(response, WebServerResultJson.error(WebServerResults.ERROR_USERNOTFOUND));
//			System.out.println("The user has not logined ...");
//			response.setContentType("text/json");
//			response.setCharacterEncoding("utf-8");
//			response.sendRedirect("/system/toLogin");
//			return false;
//		}
		return true;
	}
	/**
	 * 在Controller执行之后,视图渲染前调用
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView mav)
			throws Exception {
		// TODO Auto-generated method stub
		if(mav != null){
			System.out.println("ViewName : " + mav.getViewName() + "------------------" + request.getRequestURI());
		}
	}
	/**
	 * 在Controller执行完成,视图渲染完毕后调用
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception e)
			throws Exception {
		System.out.println("Finished the request ......" + request.getRequestURI());		
	}
	/**
	 * 拦截后的错误输出
	 * @param response
	 * @param result
	 * @throws Exception
	 */
	private void errorOutput(HttpServletResponse response, ServerResultObject<Object> result) throws Exception{
		OutputStream output = null;
		try{
			response.setContentType("text/json");
			response.setCharacterEncoding("utf-8");
			output = response.getOutputStream();
			output.write(JsonUtils.objectToJson(result).getBytes("utf-8"));
			output.flush();
		}finally{
			if(output != null)
				output.close();
		}
	}
}