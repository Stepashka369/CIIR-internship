package com.stepashka.jsptask.servlet.storehouse;

import com.stepashka.jsptask.entity.Storehouse;
import com.stepashka.jsptask.service.StorehouseService;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StorehouseReadOneServlet", value = "/storehouse-read-one-servlet")
public class StorehouseReadOneServlet extends HttpServlet {
    private final StorehouseService storehouseService = new StorehouseService();
    private static final Logger logger = Logger.getLogger(StorehouseReadOneServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer storehouseId = Integer.valueOf(request.getParameter("storehouseId"));
            Storehouse storehouse = storehouseService.findById(storehouseId);
            request.setAttribute("storehouse", storehouse);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/storehouse/storehouseReadOne.jsp");
            requestDispatcher.forward(request, response);
        } catch (IOException | ServletException | NumberFormatException exception) {
            logger.error(exception.getMessage());
        }
    }
}
