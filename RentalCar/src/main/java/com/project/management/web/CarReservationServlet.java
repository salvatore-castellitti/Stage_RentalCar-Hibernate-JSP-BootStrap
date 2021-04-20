package com.project.management.web;

import com.project.management.dao.VehicleReservationDao;
import com.project.management.model.Vehicle;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CarReservationServlet", value = "/CarReservationServlet")
public class CarReservationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null)
            action = "list";

        switch (action){

            case "update":
                updateVehicleForm(request,response);

            case "create":
                createVehicleForm(request,response);

            default:
                listVehicle(request,response);
        }
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

    private void listVehicle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
                createVehicle(request,response);
                break;
            case "update":
                updateVehicle(request,response);
                break;
            case "delete":
                deleteVehicle(request,response);
        }
    }

    private void deleteVehicle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        VehicleReservationDao deleteDao = new VehicleReservationDao();
        int carId = Integer.parseInt(request.getParameter("carId"));
        deleteDao.deleteVehicle(carId);
        response.sendRedirect("CarReservationServlet");
    }

    private void updateVehicle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        VehicleReservationDao updateDao = new VehicleReservationDao();
        Vehicle car = new Vehicle();
        int id = Integer.parseInt(request.getParameter("carId"));
        car = updateDao.getVehicle(id);

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

        updateDao.updateVehicle(car);

        response.sendRedirect("CarReservationServlet");
    }

    private void createVehicle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        VehicleReservationDao createDao = new VehicleReservationDao();
        Vehicle vehicle = new Vehicle();

        vehicle.setType(request.getParameter("type"));
        vehicle.setHouseProducer(request.getParameter("houseproducer"));
        vehicle.setModel(request.getParameter("model"));
        vehicle.setRegistrationYear(request.getParameter("registrationyear"));
        vehicle.setLicensePlate(request.getParameter("licenseplate"));

        createDao.saveVehicle(vehicle);
        response.sendRedirect("CarReservationServlet");
    }
}
