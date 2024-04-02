package com.stepashka.jsptask.servlet.good;

import com.stepashka.jsptask.service.GoodService;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GoodDeleteServlet", value = "/good-delete-servlet")
public class GoodDeleteServlet extends HttpServlet {
    private final GoodService goodService = new GoodService();
    private static final Logger logger = Logger.getLogger(GoodDeleteServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  {
        try {
            Integer goodId = Integer.valueOf(request.getParameter("goodId"));
            goodService.delete(goodId);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/good-read-all-servlet");
            requestDispatcher.forward(request, response);
        } catch (IOException | ServletException | NumberFormatException exception){
            logger.error(exception.getMessage());
        }
    }
}
