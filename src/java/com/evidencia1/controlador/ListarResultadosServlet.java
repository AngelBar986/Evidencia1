package com.evidencia1.controlador;

import com.evidencia1.dao.ResultadosIacDAO;
import com.evidencia1.modelo.ResultadoIac;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListarResultadosServlet", urlPatterns = {"/listar"})
public class ListarResultadosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            List<ResultadoIac> lista = new ResultadosIacDAO().listar();
            req.setAttribute("lista", lista);
            req.getRequestDispatcher("/listar.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}
