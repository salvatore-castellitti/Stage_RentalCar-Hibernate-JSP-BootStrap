package com.project.management.web;

import com.project.management.dao.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserDao loginDao;

    public void init() {
        loginDao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            authenticate(request,response);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void authenticate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if(loginDao.validate(email, password)){
            System.out.println(loginDao.isAdmin(email,password));
            if(loginDao.isAdmin(email, password)){
                RequestDispatcher dispatcher = request.getRequestDispatcher("ListUserServlet");
                dispatcher.forward(request,response);
            }else{
                RequestDispatcher dispatcher = request.getRequestDispatcher("customer/homepage-customer.jsp");
                dispatcher.forward(request,response);
            }

        }else{
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.print("Sorry email not found");

            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.include(request,response);
            //throw new Exception("Login Failed");
        }
    }
}
