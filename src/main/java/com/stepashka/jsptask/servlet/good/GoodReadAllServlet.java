package com.stepashka.jsptask.servlet.good;

import com.stepashka.jsptask.entity.Good;
import com.stepashka.jsptask.service.GoodService;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GoodReadServlet", value = "/good-read-all-servlet")
public class GoodReadAllServlet extends HttpServlet {
    private final GoodService goodService = new GoodService();
    private static final Logger logger = Logger.getLogger(GoodReadAllServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Good> goods = goodService.findAll();
            request.setAttribute("goodsList", goods);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/good/goodReadAll.jsp");
            requestDispatcher.forward(request, response);
        } catch (IOException | ServletException exception){
            logger.error(exception.getMessage());
        }
    }
}
