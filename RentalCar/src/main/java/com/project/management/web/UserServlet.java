package com.project.management.web;

import com.project.management.dao.UserDao;
import com.project.management.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "UserServlet", value = "/UserServlet")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String name = request.getParameter("email");
        String idSess = request.getParameter("userId");
        if (action == null)
            action = "list";

        //System.out.println("dopo if" + action);

        switch (action){
            case "login":
                try {
                    authenticate(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "update":
                updateUserForm(request,response);

            case "create":
                createUserForm(request,response);

            default:
                listUser(request,response);
        }



    }

    private void createUserForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin/create-form.jsp");
        dispatcher.forward(request,response);
    }

    private void authenticate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        session.setAttribute("email", email);
        session.setAttribute("action", "list");
        if (UserDao.validate(email, password)) {
            //System.out.println(UserDao.isAdmin(email,password));
            if (UserDao.isAdmin(email, password)) {
                /*RequestDispatcher dispatcher = request.getRequestDispatcher("UserServlet");
                dispatcher.forward(request,response);*/
                response.sendRedirect("UserServlet");
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("customer/homepage-customer.jsp");
                dispatcher.forward(request, response);
            }

        } else {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.print("Sorry email not found");

            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.include(request, response);
            //throw new Exception("Login Failed");
        }
    }


    private void listUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> usr = UserDao.getAllUser();

        request.setAttribute("USER_LIST", usr);

        RequestDispatcher dispatcher = request.getRequestDispatcher("admin/homepage-admin.jsp");
        dispatcher.forward(request,response);
    }

    private void updateUserForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao getDao = new UserDao();
        int id = Integer.parseInt(request.getParameter("userId"));
        User user = getDao.getUser(id);

        request.setAttribute("user",user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin/update-form.jsp");
        dispatcher.forward(request,response);


    }




    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        //System.out.println(action);
        //System.out.println("AAAAAAAAAAA");

        switch (action){
            case "update":
                updateUser(request,response);
                break;
            case "create":
                createUser(request,response);
                break;

            case "delete":
                deleteUser(request,response);

        }



    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserDao userDao = new UserDao();
        int id = Integer.parseInt(request.getParameter("userId"));
        //User user = userDao.getUser(id);

        userDao.deleteUser(id);
        response.sendRedirect("UserServlet");
    }

    private void createUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = new User();
        user.setName(request.getParameter("name"));
        user.setSurname(request.getParameter("surname"));
        String str = request.getParameter("role");

        //casting String birthday to Date format
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("birthday"));
            user.setBirthday(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //Casting String role(true,false) to Boolean
        boolean bool = Boolean.parseBoolean(str);
        user.setRole(bool);
        user.setEmail(request.getParameter("email"));
        user.setTaxCode(request.getParameter("taxcode"));
        user.setPassword(request.getParameter("password"));

        //saving user
        UserDao.saveUser(user);
        response.sendRedirect("UserServlet");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        int id = Integer.parseInt(request.getParameter("userId"));
        User user = userDao.getUser(id);

        if (request.getParameter("name") != "")
            user.setName(request.getParameter("name"));

        if (request.getParameter("surname") != "")
            user.setSurname(request.getParameter("surname"));

        if (request.getParameter("email") != "")
            user.setName(request.getParameter("email"));

        if (request.getParameter("password") != "")
            user.setName(request.getParameter("password"));



        userDao.updateUser(user);

        response.sendRedirect("UserServlet");
    }


}
