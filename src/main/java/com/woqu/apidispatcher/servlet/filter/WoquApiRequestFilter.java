package com.woqu.apidispatcher.servlet.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

/**
 * 1.支持多次读取ServletRequest inputSteam
 * 
 * 2.支持m.woqu.com的跨域AJAX请求
 * 
 * @author HP
 *
 */
public class WoquApiRequestFilter implements Filter {
	
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		MultiReadHttpServletRequest multiReadRequest = new MultiReadHttpServletRequest((HttpServletRequest) request);
	    chain.doFilter(multiReadRequest, response);
	}


	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

	private class MultiReadHttpServletRequest extends HttpServletRequestWrapper {
		  private ByteArrayOutputStream cachedBytes;

		  public MultiReadHttpServletRequest(HttpServletRequest request) {
		    super(request);
		  }

		  @Override
		  public ServletInputStream getInputStream() throws IOException {
		    if (cachedBytes == null)
		      cacheInputStream();

		      return new CachedServletInputStream();
		  }

		  @Override
		  public BufferedReader getReader() throws IOException{
		    return new BufferedReader(new InputStreamReader(getInputStream()));
		  }

		  private void cacheInputStream() throws IOException {
		    /* Cache the inputstream in order to read it multiple times. For
		     * convenience, I use apache.commons IOUtils
		     */
		    cachedBytes = new ByteArrayOutputStream();
		    IOUtils.copy(super.getInputStream(), cachedBytes);
		  }

		  /* An inputstream which reads the cached request body */
		  public class CachedServletInputStream extends ServletInputStream {
		    private ByteArrayInputStream input;

		    public CachedServletInputStream() {
		      /* create a new input stream from the cached request body */
		      input = new ByteArrayInputStream(cachedBytes.toByteArray());
		    }

		    @Override
		    public int read() throws IOException {
		      return input.read();
		    }
		  }
	}

}
