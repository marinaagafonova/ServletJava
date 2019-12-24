package app.servlets;

import app.domain.Department;
import app.service.ClinicService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListDepartmentServlet extends HttpServlet {
    ClinicService clinicService = new ClinicService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Department> departments = clinicService.getDepartments();

        List<String> names = new ArrayList<>();
        if(departments != null){
            for(Department department : departments) {
                names.add(department.toString());
            }
            req.setAttribute("departments", departments);
        }


        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/list.jsp");
        requestDispatcher.forward(req, resp);
    }
}
