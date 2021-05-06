package com.sandino.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CatalogServlet")
public class CatalogServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CatalogServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().append(request.getParameter("name"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String manufacturer = request.getParameter("manufacturer");
        String sku = request.getParameter("sku");

        response.setHeader("someHeader", "someHeaderValue");
        response.addCookie(new Cookie("someCookie", "someCookieValue"));

        Catalog.addItem(new CatalogItem(name, manufacturer, sku));

        request.setAttribute("items", Catalog.getItems());
        var dispatcher = request.getRequestDispatcher("list.jsp");
        dispatcher.forward(request, response);
    }
}
