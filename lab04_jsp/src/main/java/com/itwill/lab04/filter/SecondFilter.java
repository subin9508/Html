package com.itwill.lab04.filter;

import jakarta.servlet.http.HttpFilter;
import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

/**
 * Servlet Filter implementation class SecondFilter
 */
public class SecondFilter extends HttpFilter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public SecondFilter() {
      
    }

	/**
	 * @see Filter#destroy()
	 */
	@Override
    public void destroy() {
		//WAS가 종료될 때 생성된 필터 객체를 소멸시키기 위해서 호출하는 메서드.
		System.out.println("FilterEx::destroy() 호출");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("SecondFilter::doFilter() 호출 전");

		// pass the request along the filter chain
		chain.doFilter(request, response);
		
		System.out.println("SecondFilter::doFilter() 호출 후");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("FilterEx::init() 호출");
	}

}
