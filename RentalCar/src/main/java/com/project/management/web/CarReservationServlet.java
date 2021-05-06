package com.project.management.web;

import com.project.management.dao.ReservationDao;
import com.project.management.dao.VehicleReservationDao;
import com.project.management.model.Reservation;
import com.project.management.model.Vehicle;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@WebServlet(name = "CarReservationServlet", value = "/CarReservationServlet")
public class CarReservationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String action = request.getParameter("action");
        System.out.println(action);
        if (action == null){
            if (session.getAttribute("actionList")!=null) {
                action = (String)session.getAttribute("actionList");
            }else {
                action = "list";
            }
        }
        System.out.println(action);

        switch (action){

            case "update":
                updateVehicleForm(request,response);
                break;

            case "create":
                createVehicleForm(request,response);
                break;

            case "listReservation":
                listReservationAdmin(request,response);
                break;


            default:
                listVehicle(request,response);
        }
    }

    private void listReservationAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReservationDao reservationDao = new ReservationDao();
        List<Reservation> reservations = reservationDao.getAllReservation();

        request.setAttribute("RESERVATION_LIST",reservations);

        RequestDispatcher dispatcher = request.getRequestDispatcher("admin/homepage-reservation-admin.jsp");
        dispatcher.forward(request,response);
    }

    private void updateVehicleForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        VehicleReservationDao getCarDao = new VehicleReservationDao();
        int id = Integer.parseInt(request.getParameter("carId"));
        Vehicle car = getCarDao.getVehicle(id);

        request.setAttribute("car",car);
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin/update-vehicle.jsp");
        dispatcher.forward(request,response);
    }

    private void createVehicleForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin/create-vehicle.jsp");
        dispatcher.forward(request,response);
    }

    public void listVehicle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Vehicle> cars = VehicleReservationDao.getAllVehicle();

        request.setAttribute("CARS_LIST", cars);


        RequestDispatcher dispatcher = request.getRequestDispatcher("admin/homepage-car-admin.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch(action){
            case "create":
            case "update":
                CreateUpdateVehicle(request,response);
                break;
            case "delete":
                deleteVehicle(request,response);
                break;
            case "updateReservation":
                updateReservation(request,response);
                break;
            case "deleteReservation":
                deleteReservation(request,response);
        }
    }

    private void updateReservation(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        ReservationDao reservationDao = new ReservationDao();
        Reservation reservation = reservationDao.getReservation(Integer.parseInt(request.getParameter("reservationId")));


        Boolean bool =  Boolean.parseBoolean(request.getParameter("reservConfirm"));
        reservation.setConfirmed(bool);

        reservationDao.updateReservation(reservation);
        session.setAttribute("actionList","listReservation");
        response.sendRedirect("CarReservationServlet");

    }

    private void deleteVehicle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        VehicleReservationDao deleteDao = new VehicleReservationDao();
        int carId = Integer.parseInt(request.getParameter("carId"));
        deleteDao.deleteVehicle(carId);
        HttpSession session = request.getSession();
        session.setAttribute("actionList","list");
        response.sendRedirect("CarReservationServlet");
    }

    private void deleteReservation(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        ReservationDao reservationDao = new ReservationDao();
        int id = Integer.parseInt(request.getParameter("reservId"));

        reservationDao.deleteReservation(id);
        session.setAttribute("actionList","listReservation");
        response.sendRedirect("CarReservationServlet");

    }

    private void CreateUpdateVehicle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        session.setAttribute("actionList","list");
        VehicleReservationDao vehicleReservationDao = new VehicleReservationDao();
        Vehicle car = null;
        if (request.getParameter("carId")!=null){
            int id = Integer.parseInt(request.getParameter("carId"));
            car = vehicleReservationDao.getVehicle(id);
        }else{
            car = new Vehicle();
        }

        System.out.println(car.getId());

        if (request.getParameter("type") != "")
            car.setType(request.getParameter("type"));

        if (request.getParameter("houseproducer") != "")
            car.setHouseProducer(request.getParameter("houseproducer"));

        if (request.getParameter("model") != "")
            car.setModel(request.getParameter("model"));

        if (request.getParameter("registrationyear") != "")
            car.setRegistrationYear(request.getParameter("registrationyear"));

        if (request.getParameter("licenseplate") != "")
            car.setLicensePlate(request.getParameter("licenseplate"));

        if (request.getParameter("carId")!=null){
            VehicleReservationDao.updateVehicle(car);
        }else{
            VehicleReservationDao.saveVehicle(car);
        }

        response.sendRedirect("CarReservationServlet");
    }

}
