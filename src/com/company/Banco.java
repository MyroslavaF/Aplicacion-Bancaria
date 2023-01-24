package com.company;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.Scanner;

public class Banco {

    private String nombreBanco;

    public Banco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    private void setNombreBanco(String nuevoNombreBanco) {
        nombreBanco = nuevoNombreBanco;
    }

    public void crearCuenta() {

        try {
            Class.forName("org.sqlite.JDBC");
            Connection conexion = DriverManager.getConnection("jdbc:sqlite:c:\\sqlite\\mibanco.db");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Introdusca Su DNI: ");
            String dni = scanner.nextLine();
            System.out.println("Introdusca Su nombre: ");
            String nombre = scanner.nextLine();
            String query = "INSERT INTO cuentas (numCuenta, nomCliente, fechaCreacion, saldo) " +
                    "VALUES ('" + dni + "', '" + nombre + "', '2020-11-07', 0) ";
            Statement stm = conexion.createStatement();
            stm.executeUpdate(query);
            System.out.println("La informacion de cuenta esta añadida");
            stm.close();
            conexion.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Cuenta> leerCuenta() {
        List<Cuenta> listadoCuentas = new ArrayList<Cuenta>();

        try {

            Class.forName("org.sqlite.JDBC");
            Connection conexion = DriverManager.getConnection("jdbc:sqlite:c:\\sqlite\\mibanco.db");
            Statement stm = conexion.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM cuentas");

            while (rs.next()) {

                String numero = rs.getString("numCuenta");
                String nombre = rs.getString("nomCliente");
                Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("fechaCreacion"));
                float saldo = rs.getFloat("saldo");
                Cuenta cuenta = new Cuenta(numero, nombre, fecha, saldo);
                listadoCuentas.add(cuenta);
                System.out.println(cuenta.getNumeroCuenta() + " " + cuenta.getNombreCliente() + " " + cuenta.getFechaCreacionCuenta() + " " + cuenta.getSaldoActual());
            }
            stm.close();
            conexion.close();
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listadoCuentas;
    }

    public static void actualizarCuenta(Cuenta cuenta) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conexion = DriverManager.getConnection("jdbc:sqlite:c:\\sqlite\\mibanco.db");
            PreparedStatement stm = conexion.prepareStatement("UPDATE cuentas SET nomCliente = ?, saldo = ? WHERE numCuenta = ? ");
            stm.setString(1, cuenta.getNombreCliente());
            stm.setFloat(2, cuenta.getSaldoActual());
            stm.setString(3, cuenta.getNumeroCuenta());
            stm.executeUpdate();
            stm.close();
            conexion.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void modificarNombre() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introducsca numero de cuenta: ");
        String numero = sc.nextLine();
        System.out.println("Nuevo nombre:");
        String nombre = sc.nextLine();
        Cuenta cuenta = obtenerCuenta(numero);
        cuenta.setNombreCliente(nombre);
        if (cuenta != null)
            actualizarCuenta(cuenta);
        else
            System.out.println("La cuenta no encontrada");
    }

    public static void ingresarDinero() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introducsca numero de cuenta: ");
        String numero = sc.nextLine();
        Cuenta cuenta = obtenerCuenta(numero);
        cuenta.ingresarDinero();

        if (cuenta != null)
            actualizarCuenta(cuenta);
        else
            System.out.println("La cuenta no encontrada");
    }

    public static void reintegrarDinero() {

        Scanner sc = new Scanner(System.in);
        System.out.println("Introducsca numero de cuenta: ");
        String numero = sc.nextLine();
        Cuenta cuenta = obtenerCuenta(numero);
        cuenta.reintegrarDinero();

        if (cuenta != null)
            actualizarCuenta(cuenta);
        else
            System.out.println("La cuenta no encontrada");
    }

    //eliminar una cuenta, que recibe un número de cuenta que utiliza para identificar qué cuenta eliminar.
    public void eliminarCuenta() {

        try {
            Class.forName("org.sqlite.JDBC");
            Connection conexion = DriverManager.getConnection("jdbc:sqlite:c:\\sqlite\\mibanco.db");
            PreparedStatement stm = conexion.prepareStatement("DELETE FROM cuentas WHERE numCuenta = ? ");
            Scanner sc = new Scanner(System.in);
            System.out.println("Numero de cuenta:");
            String num = sc.nextLine();
            stm.setString(1, num);
            stm.executeUpdate();
            System.out.println("La cuenta esta eliminada");
            stm.close();
            conexion.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static Cuenta obtenerCuenta(String numero) {
        Cuenta cuenta = null;
        try {

            Class.forName("org.sqlite.JDBC");
            Connection conexion = DriverManager.getConnection("jdbc:sqlite:c:\\sqlite\\mibanco.db");
            PreparedStatement stm = conexion.prepareStatement("SELECT * FROM cuentas WHERE numCuenta = ? ");
            stm.setString(1, numero);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {

                String nombre = rs.getString("nomCliente");
                float saldo = rs.getFloat("saldo");
                cuenta = new Cuenta(numero, nombre, new Date(), saldo);

            }
            rs.close();
            stm.close();
            conexion.close();
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cuenta;
    }

    // obtener el listado de cuentas, recibe un texto como parámetro, que
    //utilizaremos para filtrar las cuentas cuyo nombre contenga ese texto
    public List<Cuenta> filtrarCuenta() {
        List<Cuenta> listadoCuentas = new ArrayList<Cuenta>();

        try {

            Class.forName("org.sqlite.JDBC");
            Connection conexion = DriverManager.getConnection("jdbc:sqlite:c:\\sqlite\\mibanco.db");
            Scanner sc = new Scanner(System.in);
            System.out.println("Escribe un nombre : ");
            String texto = sc.nextLine();
            PreparedStatement stm = conexion.prepareStatement("SELECT * FROM cuentas WHERE nomCliente LIKE ?");
            stm.setString(1, "%" + texto + "%");
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                String numero = rs.getString("numCuenta");
                String nombre = rs.getString("nomCliente");
                Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("fechaCreacion"));
                float saldo = rs.getFloat("saldo");

                Cuenta cuenta = new Cuenta(numero, nombre, fecha, saldo);
                listadoCuentas.add(cuenta);
                System.out.println(cuenta.getNumeroCuenta() + " " + cuenta.getNombreCliente() + " " + cuenta.getFechaCreacionCuenta() + " " + cuenta.getSaldoActual());

            }
            stm.close();
            conexion.close();
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return listadoCuentas;
    }
}