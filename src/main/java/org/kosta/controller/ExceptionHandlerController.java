package org.kosta.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionHandlerController {
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView handlerError404(HttpServletRequest request, Exception ex) {
		//500 Exception
		//error해결불가 
		ModelAndView mv = new ModelAndView();
		mv.addObject("errorMessage", ex.getMessage());
		mv.addObject("url", request.getRequestURL());
		mv.setViewName("error/errorPage404");
		return mv;
	}

}
