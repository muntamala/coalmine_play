package com.coalmine.connector.play.v1;

import java.io.BufferedReader;
import java.security.Principal;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import play.i18n.Lang;
import play.mvc.Http.Header;
import play.mvc.Http.Request;

public class HttpRequest implements HttpServletRequest {
    private Request request;
    
    public HttpRequest(Request request) {
        this.request = request;
    }
    
    public Object getAttribute(String name) {
        return null;
    }
    
    public Enumeration<String> getAttributeNames() {
        return null;
    }
    
    public String getCharacterEncoding() {
        return this.request.encoding;
    }
    
    public int getContentLength() {
        String contentLength = this.getHeader("content-length");
        if (contentLength != null) {
            try {
                return Integer.parseInt(contentLength);
            } catch (NumberFormatException e) {
                return 0;
            }
        } else {
            return 0;
        }
    }
    
    public String getContentType() {
        return this.request.contentType;
    }
    
    public ServletInputStream getInputStream() {
        return null;
    }
    
    public String getLocalAddr() {
        return null;
    }
    
    public Locale getLocale() {
        return Lang.getLocale();
    }
    
    public Enumeration<Locale> getLocales() {
        List<Locale> list = new LinkedList<Locale>();
        list.add(this.getLocale());
        Enumeration<Locale> locales = Collections.enumeration(list);
        return locales;
    }
    
    public String getLocalName() {
        return this.getServerName();
    }
    
    public int getLocalPort() {
        return this.request.port;
    }
    
    public String getParameter(String name) {
        return this.request.params.get(name);
    }
    
    public Map<String, String[]> getParameterMap() {
        return this.request.params.all();
    }
    
    public Enumeration<String> getParameterNames() {
        Map<String, String[]> params = this.getParameterMap();
        Enumeration<String> paramNames = Collections.enumeration(params.keySet());
        return paramNames;
    }
    
    public String[] getParameterValues(String name) {
        return this.request.params.getAll(name);
    }
    
    public String getProtocol() {
        return null;
    }
    
    public BufferedReader getReader() {
        return null;
    }
    
    @Deprecated
    public String getRealPath(String path) {
        return null;
    }
    
    public String getRemoteAddr() {
        return this.request.remoteAddress;
    }
    
    public String getRemoteHost() {
        return this.getRemoteAddr();
    }
    
    public int getRemotePort() {
        return 0;
    }
    
    public RequestDispatcher getRequestDispatcher(String path) {
        return null;
    }
    
    public String getScheme() {
        return (this.request.secure) ? "https" : "http";
    }
    
    public String getServerName() {
        String host = this.request.host;
        if (host == null) {
            return null;
        }
        String[] hostParts = this.request.host.split(":");
        return hostParts[0];
    }
    
    public int getServerPort() {
        return this.request.port;
    }
    
    public boolean isSecure() {
        return this.request.secure.booleanValue();
    }
    
    public void removeAttribute(String name) {
        // Pass
    }
    
    public void setAttribute(String name, Object o) {
        // Pass
    }
    
    public void setCharacterEncoding(String env) {
        // Pass
    }
    
    public String getAuthType() {
        return null;
    }
    
    public String getContextPath() {
        return null;
    }
    
    public Cookie[] getCookies() {
        return null;
    }
    
    public long getDateHeader(String name) {
        return 0;
    }
    
    public String getHeader(String name) {
        Header header = this.request.headers.get(name.toLowerCase());
        return (header != null) ? header.value() : null;
    }
    
    public Enumeration<String> getHeaderNames() {
        Set<String> set = this.request.headers.keySet();
        Enumeration<String> headerNames = Collections.enumeration(set);
        return headerNames;
    }
    
    public Enumeration<String> getHeaders(String name) {
        return null;
    }
    
    public int getIntHeader(String name) {
        String header = this.getHeader(name);
        try {
            return Integer.parseInt(header);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    
    public String getMethod() {
        return this.request.method;
    }
    
    public String getPathInfo() {
        return null;
    }
    
    public String getPathTranslated() {
        return null;
    }
    
    public String getQueryString() {
        return this.request.querystring;
    }
    
    public String getRemoteUser() {
        return null;
    }
    
    public String getRequestedSessionId() {
        return null;
    }
    
    public String getRequestURI() {
        return null;
    }
    
    public StringBuffer getRequestURL() {
        return new StringBuffer(this.request.url);
    }
    
    public String getServletPath() {
        return null;
    }
    
    public HttpSession getSession() {
        return null;
    }
    
    public HttpSession getSession(boolean create) {
        return null;
    }
    
    public Principal getUserPrincipal() {
        return null;
    }
    
    public boolean isRequestedSessionIdFromCookie() {
        return false;
    }
    
    @Deprecated
    public boolean isRequestedSessionIdFromUrl() {
        return isRequestedSessionIdFromURL();
    }
    
    public boolean isRequestedSessionIdFromURL() {
        return false;
    }
    
    public boolean isRequestedSessionIdValid() {
        return false;
    }
    
    public boolean isUserInRole(String role) {
        return false;
    }
}