# AplicacionBancaria

Funcionalidades de la aplicación bancaria

Esta conectada con una base de datos de SQLite:
Con una tabla “cuenta” creada en un motor de base de datos relacionales

La tabla tiene los campos:
 numero: texto
 nombre: texto
 fechaCreacion: fecha
 saldo: numérico

La clase Banco tiene las siguientes funcionalidades:
- Toda la información de las cuentas esta accedida y modificada a través
de la base de datos
- La función de CrearCuenta almacena los datos en la base de datos.
- La función para actualizar el nombre, incrementar y decrementar saldo, se
actualiza los valores directamente en la base de datos.
- La función para obtener el listado de cuentas obtiene los valores desde
la base de datos.
- La fuccion filtrarCuenta() recibe un texto como parámetro, que
utilizaremos para filtrar las cuentas cuyo nombre contenga ese texto, es
decir: 
- Si el parámetro recibido es null o un texto vacío, se listarán
todas las cuentas
- Si se recibe un valor, por ejemplo “ari”, deberían aparecer
cuentas con nombres como “Maria”, “Ariana”, “Rosmari”, “Amarilis”, etc.
- Función para eliminar una cuenta, que recibe un número de
cuenta que utiliza para identificar qué cuenta eliminar.
