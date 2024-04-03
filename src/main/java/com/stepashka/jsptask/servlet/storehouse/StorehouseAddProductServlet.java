package com.stepashka.jsptask.servlet.storehouse;

import com.stepashka.jsptask.entity.Good;
import com.stepashka.jsptask.entity.Storehouse;
import com.stepashka.jsptask.service.GoodService;
import com.stepashka.jsptask.service.GoodStorehouseService;
import com.stepashka.jsptask.service.StorehouseService;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "StorehouseAddProductServlet", value = "/storehouse-add-product-servlet")
public class StorehouseAddProductServlet extends HttpServlet {
    private final StorehouseService storehouseService = new StorehouseService();
    private final GoodService goodService = new GoodService();
    private final GoodStorehouseService goodStorehouseService = new GoodStorehouseService();
    private static final Logger logger = Logger.getLogger(StorehouseAddProductServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer storehouseId = Integer.valueOf(request.getParameter("storehouseId"));
            request.setAttribute("storehouseId", storehouseId);
            List<Good> goods = goodService.findAll();
            request.setAttribute("goodsList", goods);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/storehouse/storehouseAddProduct.jsp");
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException | NumberFormatException exception) {
            logger.error(exception.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            goodStorehouseService.save(buildStorehouse(request));
            Integer storehouseId = Integer.valueOf(request.getParameter("storehouseId"));
            Storehouse storehouse = storehouseService.findById(storehouseId);
            request.setAttribute("storehouse", storehouse);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/storehouse/storehouseReadOne.jsp");
            requestDispatcher.forward(request, response);
        } catch (IOException | ServletException | NumberFormatException exception) {
            logger.error(exception.getMessage());
        }
    }

    private Storehouse buildStorehouse(HttpServletRequest request) throws NumberFormatException {
        Storehouse storehouse = new Storehouse();
        storehouse.setId(Integer.valueOf(request.getParameter("storehouseId")));
        Good good = new Good();
        good.setId(Integer.valueOf(request.getParameter("goodId")));
        HashMap<Good, Integer> goods = new HashMap<>();
        goods.put(good, Integer.valueOf(request.getParameter("quantity")));
        storehouse.setGoods(goods);
        return storehouse;
    }
}
