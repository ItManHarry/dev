package com.ch.sys.biz.system.exception.handler;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.ch.sys.biz.system.exception.ServerException;
import com.ch.sys.biz.system.results.ServerResultJson;
/**
 * Exception异常处理
 * 兼容说明：
 * 	支持HTTP WEB异常页面跳转
 * 	支持AJAX JSON异常数据返回
 * @author 20112004
 *
 */
@ControllerAdvice
public class ServerExceptionHandler {
	private final static Logger logger = LoggerFactory.getLogger(ServerExceptionHandler.class);
	public static final String ERROR_PAGE = "system/common/error";
	@ExceptionHandler(value=ServerException.class)
	@ResponseBody
	public Object handle(HttpServletRequest request, Exception e){
		//获取异常页面URL地址
		String exceptionURL = null;
		Object o = request.getRequestURL();
		if(o != null)
			exceptionURL = request.getRequestURL().toString();
		//异常处理
		if(e instanceof ServerException){
			logger.error("000 自定义异常：{}", e);
			ServerException se = (ServerException)e;
			if(!isAjax(request))
				return exceptionView(se.getStatus(), se.getMessage(), exceptionURL);
			else
				return ServerResultJson.error(se.getStatus(), se.getMessage(), exceptionURL);
		}else{
			logger.error("500 系统异常", e);
			if(!isAjax(request))
				return exceptionView(500, e.toString(), exceptionURL);
			else
				return ServerResultJson.error(500, e.toString(), exceptionURL);
		}
	}
	/**
	 * 判断请求是否是异步请求
	 * @return
	 */
	private static boolean isAjax(HttpServletRequest request){
		String header = request.getHeader("x-requested-with");
		boolean ajax = "XMLHttpRequest".equals(header) ? true : false;
		return ajax;
	}
	/**
	 * 错误页面跳转
	 * @param status
	 * @param message
	 * @param exceptionURL
	 * @return
	 */
	private static ModelAndView exceptionView(Integer status, String message, String exceptionURL){
		ModelAndView mav = new ModelAndView();
		mav.addObject("status",status);
		mav.addObject("message", message);
		mav.addObject("exceptionURL", exceptionURL);
		mav.setViewName(ERROR_PAGE);
		return mav;
	}
}