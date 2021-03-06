package com.project.management.dao;

import com.project.management.model.Vehicle;
import com.project.management.utl.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


//CRUD Vehicle and Reservation
public class VehicleReservationDao {

    //add Vehicle in the DB
    public static void saveVehicle(Vehicle vehicle) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the Vehicle object
            session.save(vehicle);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    //update Vehicle NB the blank field == "" (NOT null)
    public static void updateVehicle(Vehicle vehicle) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the Vehicle object
            session.update(vehicle);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    //delete Vehicle by id NB need to Integer.parseInt()
    public void deleteVehicle(int id) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Delete a vehicle object
            Vehicle vehicle = session.get(Vehicle.class, id);
            if (vehicle != null) {
                session.delete(vehicle);
                System.out.println("Vehicle is deleted");
            }

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    //return Vehicle passing his id NB need to Integer.parseInt()
    public Vehicle getVehicle(int id) {

        Transaction transaction = null;
        Vehicle vehicle = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an VEHICLE object
            vehicle = session.get(Vehicle.class, id);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return vehicle;
    }

    //return all Vehicles
    @SuppressWarnings("unchecked")
    public static List < Vehicle > getAllVehicle() {
        Transaction transaction = null;
        List < Vehicle > listOfVehicle = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            //get a list of Vehicle
            listOfVehicle = session.createQuery(" from Vehicle").getResultList();

            transaction.commit();

        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfVehicle;
    }

    //return all Vehicles that are free in chosen period
    @SuppressWarnings("unchecked")
    public static List < Vehicle > getFreeVehicle(Date date) throws ParseException {
        Transaction transaction = null;
        List < Vehicle > listOfVehicle = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            //get a list of Vehicle
            listOfVehicle = session.createQuery(" from Vehicle where id not in (select ve.id from Vehicle as ve join ve.reservation as verRes  where verRes.endDate > :startDate)").setParameter("startDate",date).getResultList();

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfVehicle;
    }

}