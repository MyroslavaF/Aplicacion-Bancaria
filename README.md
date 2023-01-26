# Aplicación Bancaria

Esta práctica estaba realizando para aprender la asignatura de programación en Java.
## Funcionalidades de la aplicación bancaria

Aplicación está conectada con una base de datos de SQLite. Tiene la tabla “cuenta” creada en un motor de base de datos relacionales

La base de datos está situada en fichero mibanco.db. Tiene una tabla `cuentas`. El código para crear la tabla con Sqlite es siguiente: 
```SQL
 CREATE TABLE IF NOT EXISTS "cuentas" (

	"numCuenta"	TEXT NOT NULL,
	"nomCliente"	TEXT NOT NULL,
	"fechaCreacion"	TEXT NOT NULL,
	"saldo"	NUMERIC NOT NULL,
	PRIMARY KEY("numCuenta")

);
```
La aplicación tiene 3 clases: `class Start`, `class Banco` y `class Cuenta`.

La clase `class Banco` tiene las siguientes funcionalidades:

- Toda la información de las cuentas está accedida y modificada a través de la base de datos

- La función de `сrearCuenta()` almacena los datos en la base de datos

- La función `actualizarCuenta(Cuenta cuenta)` sirve para actualizar el nombre, incrementar y decrementar saldo, se  actualiza los valores directamente en la base de datos.

- La función `leerCuenta()`sirve para obtener el listado de cuentas,  obtiene los valores desde la base de datos.

- La función `filtrarCuenta()` recibe un texto como parámetro, que utilizaremos para filtrar las cuentas cuyo nombre contenga ese texto, es decir: si el parámetro recibido es null o un texto vacío, se listarán todas las cuentas; si se recibe un valor, por ejemplo “ari”, deberían aparecer cuentas con nombres como “Maria”, “Ariana”, “Rosmari”, “Amarilis”, etc.

- La función `eliminarCuenta()` sirve para eliminar una cuenta, que recibe un número de cuenta que utiliza para identificar qué cuenta hay que eliminar.

En la clase `class Start` se puede listar las opciones que proponga esta aplicación para realizar las operaciones

## Como arrancar la aplicación

Para arrancar la aplicacion en Java, deberá descargar e instalar Java Virtual Machine (JVM) en su computadora.

Para programar en Java  estaba utilizando IDE IntelliJ IDEA 2022.2.
