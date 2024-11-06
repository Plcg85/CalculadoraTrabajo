![image](https://github.com/user-attachments/assets/b604f4d1-7288-4416-b408-ef0ef0140999)
Esta es la aplicación corriendo en el emulador dentro de android estudio. Como se puede ver consta de 3 editText, uno de ellos no es accesible ya que solo se actualiza al elegir la operación deseada en los radio botones. 
Una vez introducidos los datos del primer numero y el segundo número lo ideal es elegir la operación deseada. Tenemos disponibles 4 operaciones como son la suma, resta, multiplicación y la división. El software cuenta con detección de error en el caso de que queramos calcular una operación sin haber elegido si queremos sumar, restar… y nos avisará de que debemos elegir la operación deseada. También se detecta si en las casillas de los operandos introducimos letras etc..
Cuando ya hemos rellenado todos los campos necesarios para pulsar el botón de calcular, se realiza la operación deseada trasladando el resultado al textview que inicialmente tiene el texto Resultado. En este caso la aplicación también detecta ciertos errores como la división por cero avisándonos a través de una notificación Toast de que introduzcamos valores válidos.
Más allá de calcular la operación podemos borrar el contenido mediante el botón borrar de todos los datos introducidos incluso del resultado si hemos realizado alguna operación anteriormente.

El botón guardar hace que los datos introducidos se guarden en la memoria del dispositivo y los podamos rescatar cuando queramos con el botón “Mostrar”. Pero este botón solo funciona cuando hemos hecho una operación correctamente, es decir, si la operación da error o no hemos hecho ninguna operación con resultado, no será posible guardar el contenido avisándonos nuevamente el software.
Por último tenemos el botón mostrar que carga los datos anteriormente guardados rellenando sus respectivas casillas. Incluso después de cerrar la operación es posible recuperar los datos guardados.  
Dicho esto me gustaría mostrar ciertas partes del código: 
Al principio para poder acceder mediante código a todos los recursos de la aplicación debemos relacionarlas con unas variables.
![image](https://github.com/user-attachments/assets/fcd7027d-9272-4d56-bbf7-7da9e2676635)
Aquí se muestra como cada variable que voy a utilizar es ligada con el elemento en cuestión a través de su id.
Para detectar en la división que el segundo operando  no sea valor cero lo he hecho de la siguiente manera:
![image](https://github.com/user-attachments/assets/cf84c8bc-46d4-416d-a1e5-c40e5f6de4b5)

Cada vez que pulsamos en un botón es molesto que el teclado virtual del dispositivo se quede en la pantalla por eso he implementado este método.
![image](https://github.com/user-attachments/assets/54940af5-0b4f-48a0-aca3-750d7dca559a)
Otra característica interesante es la de controlar la operación elegida en el mismo momento que pulsamos en el radio botón a través de sus eventos, aquí muestro un ejemplo:	
![image](https://github.com/user-attachments/assets/dd0ee5e6-9c94-4716-a5c1-150e1a815de0)
Se utiliza shared preferences tanto para guardar como para cargar los datos de la operación que deseemos:
Aquí vemos como se guardan.
![image](https://github.com/user-attachments/assets/c94e7a17-d72d-4dc8-afb8-7dc2c93cd1fd)
Y aquí como se cargan de nuevo.	
![image](https://github.com/user-attachments/assets/366c6746-3ee5-46fc-a8fc-ab771f66a212)
