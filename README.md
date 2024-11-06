1. Introduccion.
	Trabajo de enfoque de programación multimedia y dispositivos móviles consistente en realizar una aplicación de una calculadora básica en android estudio. 
	La aplicación debe ser capaz de realizar operaciones básicas como sumar, restar, multiplicar y dividir usando los diferentes objetos de la paleta que nos brinda android tales como textview, botones, radiobotones, etc. 
	También debe ser posible borrar todos los datos introducidos dejando la aplicación con todos los campos vacíos al pulsar un botón.
	Otra funcionalidad que debe tener es la de poder guardar los datos de una operación en la memoria del dispositivo  y recuperarla cuando sea necesario, todo esto a través de 2 botones más.
	Para que completar la funcionalidad de la aplicación había que realizar un layout para cuando el dispositivo esté en vertical y otro para cuando esté en horizontal.
2. Recorrido y funcionalidades.
  Para empezar a describir la aplicación es necesario echar un vistazo a la interfaz.
	Esta es la aplicación corriendo en el emulador dentro de android estudio. Como se puede ver consta de 3 editText, uno de ellos no es accesible ya que solo se actualiza al elegir la operación deseada en los radio botones. 
	Una vez introducidos los datos del primer numero y el segundo número lo ideal es elegir la operación deseada. Tenemos disponibles 4 operaciones como son la suma, resta, multiplicación y la división. El software cuenta con detección de error en el caso de que queramos calcular una operación sin haber elegido si queremos sumar, restar… y nos avisará de que debemos elegir la operación deseada. También se detecta si en las casillas de los operandos introducimos letras etc..
	Cuando ya hemos rellenado todos los campos necesarios para pulsar el botón de calcular, se realiza la operación deseada trasladando el resultado al textview que inicialmente tiene el texto Resultado. En este caso la aplicación también detecta
ciertos errores como la división por cero avisándonos a través de una notificación Toast de que introduzcamos valores válidos.
	Más allá de calcular la operación podemos borrar el contenido mediante el botón borrar de todos los datos introducidos incluso del resultado si hemos realizado alguna operación anteriormente.

	El botón guardar hace que los datos introducidos se guarden en la memoria del dispositivo y los podamos rescatar cuando queramos con el botón “Mostrar”. Pero este botón solo funciona cuando hemos hecho una operación correctamente, es decir, si la operación da error o no hemos hecho ninguna operación con resultado, no será posible guardar el contenido avisándonos nuevamente el software.
	Por último tenemos el botón mostrar que carga los datos anteriormente guardados rellenando sus respectivas casillas. Incluso después de cerrar la operación es posible recuperar los datos guardados.  
	Dicho esto me gustaría mostrar ciertas partes del código: 
	Al principio para poder acceder mediante código a todos los recursos de la aplicación debemos relacionarlas con unas variables.
   
   Aquí se muestra como cada variable que voy a utilizar es ligada con el elemento en cuestión a través de su id.

Para detectar en la división que el segundo operando  no sea valor cero lo he hecho de la siguiente manera:
Cada vez que pulsamos en un botón es molesto que el teclado virtual del dispositivo se quede en la pantalla por eso he implementado este método.
Otra característica interesante es la de controlar la operación elegida en el mismo momento que pulsamos en el radio botón a través de sus eventos, aquí muestro un ejemplo:
Se utiliza shared preferences tanto para guardar como para cargar los datos de la operación que deseemos:
Aquí vemos como se guardan.
Y aquí como se cargan de nuevo.
![image](https://github.com/user-attachments/assets/f2519c78-f7e5-4144-aa13-fc7b6b174b21)




	

	
