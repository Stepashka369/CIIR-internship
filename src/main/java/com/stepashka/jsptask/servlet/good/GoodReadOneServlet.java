package com.stepashka.jsptask.servlet.good;

import com.stepashka.jsptask.entity.Good;
import com.stepashka.jsptask.service.GoodService;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GoodReadOneServlet", value = "/good-read-one-servlet")
public class GoodReadOneServlet extends HttpServlet {
    private final GoodService goodService = new GoodService();
    private static final Logger logger = Logger.getLogger(GoodReadOneServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer goodId = Integer.valueOf(request.getParameter("goodId"));
            Good good = goodService.findById(goodId);
            request.setAttribute("good", good);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/good/goodReadOne.jsp");
            requestDispatcher.forward(request, response);
        } catch (IOException | ServletException | NumberFormatException exception){
            logger.error(exception.getMessage());
        }
    }
}
