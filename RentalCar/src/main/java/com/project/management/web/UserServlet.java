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
        System.out.println(action);
        String name = request.getParameter("sessionEmail");
        String idSess = request.getParameter("sessionId");
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
                break;

            case "create":
                createUserForm(request,response);
                break;
            case "logout":
                logoutUser(request,response);
                break;
            case "profileUser":
                updateUserForm(request,response);

            default:
                listUser(request,response);
        }



    }

    private void logoutUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.include(request, response);
    }

    private void createUserForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin/create-form.jsp");
        dispatcher.forward(request,response);
    }

    private void authenticate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserDao loginDao = new UserDao();
        if (loginDao.loginUser(email,password) != 0){
            HttpSession session = request.getSession();
            session.setAttribute("sessionId", loginDao.loginUser(email,password));
            session.setAttribute("sessionEmail",email);
            if (loginDao.isAdmin(email,password)){
                response.sendRedirect("UserServlet");
            }else{
                response.sendRedirect("ReservationServlet");
            }
        }else {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.print("Sorry email not found");

            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.include(request, response);
            //throw new Exception("Login Failed");
        }
        /*if (UserDao.validate(email, password)) {
            //System.out.println(UserDao.isAdmin(email,password));
            if (UserDao.isAdmin(email, password)) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("UserServlet");
                dispatcher.forward(request,response);
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
        }*/
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> usr = UserDao.getAllUser();

        request.setAttribute("USER_LIST", usr);

        RequestDispatcher dispatcher = request.getRequestDispatcher("admin/homepage-admin.jsp");
        dispatcher.forward(request,response);
    }

    private void updateUserForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao getDao = new UserDao();
        if (request.getParameter("profileUpdate")!=null){
            HttpSession session = request.getSession();
            session.getAttribute("sessionId");

            int idSession = (int)session.getAttribute("sessionId");
            //int id = Integer.parseInt(idSession);
            User user = getDao.getUser(idSession);

            request.setAttribute("user",user);

            RequestDispatcher dispatcher = request.getRequestDispatcher("customer/update-profile-form.jsp");
            dispatcher.forward(request,response);
        }
        int id = Integer.parseInt(request.getParameter("userId"));
        User user = getDao.getUser(id);

        request.setAttribute("user",user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin/update-form.jsp");
        dispatcher.forward(request,response);


    }




    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action){
            case "update":
            case "create":
                createUpdateUser(request,response);
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

    private void createUpdateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        User user;
        if (request.getParameter("userId")!=null){
            int id = Integer.parseInt(request.getParameter("userId"));
            user = userDao.getUser(id);
        }else{
            user = new User();
        }


        if (request.getParameter("name") != "")
            user.setName(request.getParameter("name"));

        if (request.getParameter("surname") != "")
            user.setSurname(request.getParameter("surname"));

        if (request.getParameter("email") != "")
            user.setEmail(request.getParameter("email"));

        if (request.getParameter("password") != "")
            user.setPassword(request.getParameter("password"));

        if (request.getParameter("role")!=""){
            String str = request.getParameter("role");
            boolean bool = Boolean.parseBoolean(str);
            user.setRole(bool);
        }

        if(request.getParameter("taxcode")!=""){
            user.setTaxCode(request.getParameter("taxcode"));
        }

        if ((request.getParameter("birthday")!="") || (request.getParameter("birthday")!=null) ){
            try {
                Date date = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("birthday"));
                user.setBirthday(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (request.getParameter("userId")!=null) {
            userDao.updateUser(user);
        }else{
            UserDao.saveUser(user);
        }

        if (request.getParameter("isCustomer")!=null){
            response.sendRedirect("ReservationServlet");
        }else{
            response.sendRedirect("UserServlet");
        }

    }


}
