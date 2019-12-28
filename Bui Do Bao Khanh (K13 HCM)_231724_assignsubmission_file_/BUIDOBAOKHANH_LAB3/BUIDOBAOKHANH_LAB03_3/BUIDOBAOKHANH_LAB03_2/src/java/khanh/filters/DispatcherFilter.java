/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanh.filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author buido
 */
public class DispatcherFilter implements Filter {

    private static final boolean debug = true;
    private static final String LOGIN = "login.jsp";
    private static final String SEARCH_PAGE = "LoadAreaAction";
    private final List<String> guest;
    private final List<String> admin;
    private final List<String> customer;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public DispatcherFilter() {
        guest = new ArrayList<>();
        guest.add("header.jsp");
        guest.add("LoadAreaAction");
        guest.add("LoadAreaAction.action");
        guest.add("search_page.jsp");
        guest.add("LoginAction");
        guest.add("LoginAction.action");
        guest.add("login.jsp");
        guest.add("RegisterUserAction");
        guest.add("RegisterUserAction.action");
        guest.add("register_user.jsp");
        guest.add("SearchHotelNameAction");
        guest.add("SearchHotelNameAction.action");
        guest.add("list_hotel.jsp");
        guest.add("SearchHotelAction");
        guest.add("SearchHotelAction.action");

        guest.add("success.jsp");

        admin = new ArrayList<>();
        admin.add("header.jsp");
        admin.add("search_page.jsp");
        admin.add("SearchHotelNameAction");
        admin.add("SearchHotelNameAction.action");
        admin.add("list_hotel.jsp");
        admin.add("LogOutAction");
        admin.add("LogOutAction.action");

        admin.add("success.jsp");

        customer = new ArrayList<>();
        customer.add("header.jsp");
        customer.add("search_page.jsp");
        customer.add("SearchHotelNameAction");
        customer.add("SearchHotelNameAction.action");
        customer.add("list_hotel.jsp");
        customer.add("SearchHotelAction");
        customer.add("SearchHotelAction.action");
        customer.add("EditHotelAction");
        customer.add("EditHotelAction.action");
        customer.add("detail_hotel.jsp");
        customer.add("LogOutAction");
        customer.add("LogOutAction.action");
        customer.add("AddCartAction");
        customer.add("AddCartAction.action");
        customer.add("view_cart.jsp");
        customer.add("UpdateCartAction");
        customer.add("UpdateCartAction.action");
        customer.add("DeleteCartAction");
        customer.add("DeleteCartAction.action");
        customer.add("CheckSaleAction");
        customer.add("CheckSaleAction.action");
        customer.add("ProcessBookingAction");
        customer.add("ProcessBookingAction.action");
        customer.add("confirm_cart.jsp");
        customer.add("CheckEmptyRoomAction");
        customer.add("CheckEmptyRoomAction.action");
        customer.add("CreateConfirmCodeAction");
        customer.add("CreateConfirmCodeAction.action");
        customer.add("CheckConfirmCodeAction");
        customer.add("CheckConfirmCodeAction.action");
        customer.add("SearchOrderByDateAction");
        customer.add("SearchOrderByDateAction.action");
        customer.add("view_order.jsp");
        customer.add("SearchDetailOrderAction");
        customer.add("SearchDetailOrderAction.action");
        customer.add("view_detailorder.jsp");
        customer.add("CreateFeedBackAction");
        customer.add("CreateFeedBackAction.action");
        customer.add("write_feedback.jsp");
        customer.add("InsertFeedBackAction");
        customer.add("InsertFeedBackAction.action");
        customer.add("confirm_code.jsp");

        customer.add("success.jsp");

    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("DispatcherFilter:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
        /*
	for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    String values[] = request.getParameterValues(name);
	    int n = values.length;
	    StringBuffer buf = new StringBuffer();
	    buf.append(name);
	    buf.append("=");
	    for(int i=0; i < n; i++) {
	        buf.append(values[i]);
	        if (i < n-1)
	            buf.append(",");
	    }
	    log(buf.toString());
	}
         */
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("DispatcherFilter:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
        /*
	for (Enumeration en = request.getAttributeNames(); en.hasMoreElements(); ) {
	    String name = (String)en.nextElement();
	    Object value = request.getAttribute(name);
	    log("attribute: " + name + "=" + value.toString());

	}
         */
        // For example, a filter might append something to the response.
        /*
	PrintWriter respOut = new PrintWriter(response.getWriter());
	respOut.println("<P><B>This has been appended by an intrusive filter.</B>");
         */
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse res = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);

        String uri = req.getRequestURI();
        int startIndex = uri.lastIndexOf("/");
        int lastIndex = uri.lastIndexOf(";");
        String resource = "";
        if (lastIndex > 0) {
            resource = uri.substring(startIndex + 1, lastIndex);
        } else {
            resource = uri.substring(startIndex + 1);
        }
        if (uri.contains("img")) {
            chain.doFilter(request, response);
        } else {
            if (resource.length() > 0) {
                if (session == null || session.getAttribute("USER") == null) {
                    //chua dang nhap
                    if (guest.contains(resource)) {
                        chain.doFilter(request, response);
                    } else {
                        res.sendRedirect(LOGIN);
                    }
                } else {
                    String role = (String) session.getAttribute("ROLE");
//                    System.out.println("Role: " + role);
                    if (role.equals("admin") && admin.contains(resource)) {
                        chain.doFilter(request, response);
                    } else if (role.equals("customer") || customer.contains(resource)) {
                        chain.doFilter(request, response);
                    } else {
                        res.sendRedirect(LOGIN);
                    }
                }
            } else {
                res.sendRedirect(SEARCH_PAGE);
            }
        }

    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("DispatcherFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("DispatcherFilter()");
        }
        StringBuffer sb = new StringBuffer("DispatcherFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
