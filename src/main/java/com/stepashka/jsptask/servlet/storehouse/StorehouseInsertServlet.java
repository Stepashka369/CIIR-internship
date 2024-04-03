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
import java.util.List;

@WebServlet(name = "StorehouseInsertServlet", value = "/storehouse-insert-servlet")
public class StorehouseInsertServlet extends HttpServlet {
    private final StorehouseService storehouseService = new StorehouseService();
    private static final Logger logger = Logger.getLogger(StorehouseInsertServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/storehouse/storehouseInsert.jsp");
            requestDispatcher.forward(request, response);
        } catch (IOException | ServletException exception){
            logger.error(exception.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            storehouseService.save(buildStorehouse(request));
            List<Storehouse> storehouses = storehouseService.findAll();
            request.setAttribute("storehouseList", storehouses);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/storehouse/storehouseReadAll.jsp");
            requestDispatcher.forward(request, response);
        } catch (IOException | ServletException | NumberFormatException exception){
            logger.error(exception.getMessage());
        }
    }

    private Storehouse buildStorehouse(HttpServletRequest request) throws NumberFormatException{
        Storehouse storehouse = new Storehouse();
        storehouse.setAddress(request.getParameter("address"));
        storehouse.setSquare(Float.valueOf(request.getParameter("square")));
        return storehouse;
    }
}
