package com.company;

import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Start {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Banco mibanco = new Banco("MIBANCO");
        ListarOpciones();
        Scanner scanner = new Scanner(System.in);
        String opcion = scanner.nextLine();

        while (!opcion.equals("8")) {
            switch (opcion) {
                case "Datos de Cuenta":
                case "1":
                    mibanco.leerCuenta();
                    break;
                case "Filtrar cuentas por nombre":
                case "2":
                    mibanco.filtrarCuenta();
                    break;
                case "Modificar  nombre":
                case "3":
                    mibanco.modificarNombre();
                    break;
                case "Crear una cuenta":
                case "4":
                    mibanco.crearCuenta();
                    break;
                case "Ingresar dinero":
                case "5":
                    mibanco.ingresarDinero();
                    break;
                case "Reintegrar dinero":
                case "6":
                    mibanco.reintegrarDinero();
                    break;
                case "Eliminar cuenta":
                case "7":
                    mibanco.eliminarCuenta();
                    break;
            }
            ListarOpciones();
            opcion = scanner.nextLine();
        }
    }

    private static void ListarOpciones() {
        System.out.println("");
        System.out.println("Seleccione una opción:");
        System.out.println("1 / Datos de Cuenta");
        System.out.println("2 / Filtrar cuentas por nombre");
        System.out.println("3 / Modificar  nombre");
        System.out.println("4 / Crear una cuenta");
        System.out.println("5 / Ingresar dinero");
        System.out.println("6 / Reintegrar dinero");
        System.out.println("7 / Eliminar cuenta");
        System.out.println("8 / Salir");
    }
}