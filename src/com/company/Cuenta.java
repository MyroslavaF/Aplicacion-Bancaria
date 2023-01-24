package com.company;

import java.util.Date;
import java.util.Scanner;

public class Cuenta {
    private static String numeroCuenta;
    private static String nombreCliente;
    private static Date fechaCreacionCuenta;
    private static float saldoActual;

    public Cuenta(String numero, String nombre, Date fecha, float saldo) {

        this.numeroCuenta = numero;
        this.nombreCliente = nombre;
        this.fechaCreacionCuenta = fecha;
        this.saldoActual = saldo;
    }

    public Cuenta(String dni, String nombre) {
        this.numeroCuenta = dni;
        this.nombreCliente = nombre;

    }

    public static String getNombreCliente() {
        return nombreCliente;
    }

    public static void setNombreCliente(String nuevoNombreCliente) {
        nombreCliente = nuevoNombreCliente;
    }

    public static String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public static Date getFechaCreacionCuenta() {
        return fechaCreacionCuenta;
    }

    public void setFechaCreacionCuenta(Date newFecha) {
        fechaCreacionCuenta = newFecha;
    }

    public static float getSaldoActual() {
        return saldoActual;
    }

    public static void setSaldoActual(float saldo) {
        saldoActual = saldo;
    }

    public static void mostrarInfoCuenta() {
        System.out.println("Numero de cuenta: " + getNumeroCuenta());
        System.out.println("Nombre de cliente: " + getNombreCliente());
        System.out.println("Fecha de creacion: " + getFechaCreacionCuenta());
        System.out.println("Saldo actual: " + getSaldoActual());
    }

    public static void ingresarDinero() {

        Scanner reader = new Scanner(System.in);
        System.out.println("Escribe saldo de ingresar: ");

        float saldo = reader.nextFloat();
        boolean ingreso = true;
        if (saldo < 0) {
            ingreso = false;
        } else {
            saldoActual = saldoActual + saldo;
            System.out.println("Ha ingresado  " + saldo + " euros");
            System.out.println("Su saldo actual es: " + getSaldoActual());
        }
    }

    public static void reintegrarDinero() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Escribe saldo a reintegrar: ");

        float r = reader.nextFloat();
        boolean reintegro = true;

        if (r < 0) {
            reintegro = false;
        } else if (saldoActual >= r) {

            saldoActual = saldoActual - r;
            System.out.println("Ha realizado extraciones de " + r + " euros");
            System.out.println("Su saldo actual es: " + getSaldoActual());
        } else {
            System.out.println("No esta suficiente el dinero en Su saldo");
        }
    }
}


