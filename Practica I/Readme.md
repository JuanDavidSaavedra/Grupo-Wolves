# Descripcion de la 1era Practica
La 1era practica se desarrolló con dos objetivos principales:
  - Familializarse con el emulador de hardware proporcionado por [nand2tetris.org](https://drive.google.com/file/d/1xZzcMIUETv3u3sdpM_oTJSTetpVee3KZ/view).
  - Implementar las compuertas basicas propuestas en el 1er capitulo del libro ["The Elements of Computing Systems"](https://www.nand2tetris.org/_files/ugd/44046b_f2c9e41f0b204a34ab78be0ae4953128.pdf).

## Emulador de Hardware
![Interfaz Usuario](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/128198245/aeb4700b-635e-450c-8244-229d848c81a1)

El funcionamiento de este software (desarrollado principalmente en Java) puede ser descrito de la siguiente manera:
  1.  **Tipos de Archivos**: Este emulador se divide en 4 tipos de archivos principales:
      - Los ***.HDL*** que son los que contienen la codificacion de las compuertas y circuitos que se iran construyendo a lo largo de las practicas.
      - Los ***.tst*** que son scripst diseñados para comprobar el correcto funcionamiento de los chips y los circuitos diseñados por los estudiantes. 
      - Los ***.cmp*** y los ***.out*** que son usados para evaluar en los ***.tst*** si los chips funcionan adecuadamente, en el caso del 1er laboratorio, ambos contienen las tablas de verdad de las compuertas a implementar.
  2. **Interfaz de Usuario**: La interfaz es sumamente simple e intuitiva, en general, el estudiante cargará sus compuertas *.hdl* y evaluará su funcionamiento con los archivos *.tst*, si la ejecución del script es exitosa, en la parte inferior se imprimirá el mensaje **"End of script - comparison ended succesfully"**, de lo contrario se mostrara un error.
  3. **Personificacion**: El emulador le permite al usuario cierto grado de personificación, desde elegir si desea observar el script o el output esperado por la compuerta, hasta la velocidad de ejecución de los scripts y si desea observar animaciones para guiarse durante la ejecución de los scripts.

## Implementacion De las Compuertas

### Compuerta Not:
El emulador de hardware viene con la compuerta Nand ya implementada, por lo que podemos usarla para Implementar nuestra compuerta Not de la siguiente manera:

![image](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/128198245/015aab8c-6045-426b-95e7-e5a5dd5e2029)

Dado que ambas entradas de la compuerta Nand (a y b respectivamente) son la misma entrada In, entonces la salida de la compuerta siempre sera **Not In** gracias a la Nand.

### Compuerta And:
Al haber programado la compuerta Not, podemos utilizarla para invertir el resultado de la compuerta Nand, por ende convirtiendola en una compuerta And común y corriente, el circuito se vería de la siguiente manera:

![image](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/128198245/e92c7b81-4fbd-48bb-9662-2dcb2f998b32)

### Compuerta Or:
Al observar la tabla de verdad de la compuerta *Nand*:

![image](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/128198245/146b4be2-12b9-4387-82fd-ea7d9fd5d9ea)

Se puede observar que es practicamente igual a la or, solamente que invirtiendo los resultados del 0 binario y el 4 binario, por ende al usar compuertas Not en las entradas a y b se puede lograr el comportamiento deseado ya que:
- a = 0 -> not --> notA = 1 ; b = 0 --> not --> notB = 1 y al pasar por la Nand obtenemos out = 0
- a = 1 -> not --> notA = 0 ; b = 1 --> not --> notB = 0 y al pasar por la Nand obtenemos out = 1
Resultando el circuito final de la siguiente manera:

![Captura de pantalla 2024-02-10 123235](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/128198245/697149c6-a8b3-4402-9852-95bb27f03b65)

### Compuerta Xor:
La compuerta Xor se puede implementar de multiples maneras diferentes, en nuestro caso se implementó siguiendo el siguiente diagrama:

![Diagrama Compuerta XOR](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/128198245/a658f8ef-41bd-40c6-9ac5-9544dc32f85c)

### Compuerta Mux:
Nuevamente, la compuerta Mux que es un multiplexor con 2 entradas a y b (p0 y p1 respectivamente) junto con su entrada de seleccion sel puede ser implementada de muchas maneras diferentes, en nuestro caso se siguió el siguiente diagrama (el cual se encontró en la web):

![Diagrama Compuerta Mux](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/128198245/1c9f857a-76ef-4b73-b6a9-e3c9ebe86e9f)

### Compuerta DMux:
En el caso de la compuerta Dmux, la cual es un demultiplexor de 1 entrada (in), una entrada de seleccion sel y 2 salidas puede ser implementado de la siguiente manera:

![Diagrama Compuerta Dmux](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/128198245/b49c4012-a588-4ea6-a3a5-18386b1f3608)

### Compuerta Not16:
La compuerta Not16 tiene la particularidad de que no recibe un input de 1 bit (0 o 1), sino uno de 16 bits, por ejemplo *1001000011010110*, por lo que la compuerta debe ser capaz de invertir esa entrada retornando *0110111100101001*, para ello la señal de entrada debe dividirse en 16 entradas de un bit que se almacenan en un arreglo **in[16]**, luego cada entrada in[0], in[1], ... , in[15] es negada con la compuerta Not programada anteriormente y su respectivo output se "condensa" en una sola salida de 16 bits, a nivel grafico se veria aproximadamente asi:

![image](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/128198245/1af00ac9-c32e-4616-9a8b-b3ee73f861fe)

Cabe resaltar que esa imagen es solamente una Not de 2 bits, pero la logica se mantiene aunque se aumente el número de bits que la compuerta maneje.

### Compuerta And16
La logica de la compuerta And16 es similar a la de la Not16, limitemos la cantidad de bits para hacer el ejemplo mas digerible, supongamos que tenemos una entrada *a = 0011* y una entrada *b = 1111*, al igual que con la Not16 se va a tomar cada bit de ambas entradas (a[0] and b[0], a[1] and b[1], ... , a[3] and b[3]) y se va a condensar en un solo output, obteniendo en este caso *out = 0011*

A nivel grafico, se vería de la siguiente manera:
![image](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/128198245/390ea25b-23a3-4279-9e90-fbe8c16feede)

### Compuerta Or16:
La logica de la compuerta Or16 es similar a la de la And16, hagamos uso de las entradas del ejemplo anterior, *a = 0011* y *b = 1111*, al igual que con la And16 se va a tomar cada bit de ambas entradas (a[0] Or b[0], a[1] Or b[1], ... , a[3] Or b[3]) y se va a condensar en un solo output, obteniendo en este caso *out = 1111*

A nivel grafico se veria de la siguiente manera:
![image](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/128198245/5c8a8efe-baf4-4f8d-b2a9-a8f3ee07ce2e)

### Compuerta Mux16:
La logica del Multiplexor de 16 bits es similar a las descritas previamente, observemos la tabla de pruebas propuesta por nand2tetris.org:

![image](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/128198245/0e5b727a-276c-4983-8993-4931c9d14532)

Ahí se puede observar que las entradas tanto a como b son de 16 bits, el selector es de 1 bit y que para cada bit individual en la entrada, la compuerta sigue el comportamiento de un multiplexor de 1 bit, es por eso que cuando sel sea igual a 0, el output de la compuerta será a, y a su vez cuando sel sea igual a 1, el output va a ser b.

### Compuerta Or8way:
Tomando como base el output propuesto por nand2tetris.org:

![image](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/128198245/b6ab4c5f-bae3-4f01-953a-2cb7a97b1958)

La compuerta Or8way toma 1 entrada de 8 bits, pero al contrario de la Or16, la salida es de un solo bit, por lo que simplificando su funcionamiento, se puede decir que mientras alguno de los 8 bits de la entrada sean de NA (Nivel alto), entonces el output de la Or8way será de NA tambien, por lo que la implementacion se resume en compuertas *Or* anidadas que iran disminuyendo los bits de entrada hasta 1 que es el esperado para el output, graficamente se vería asi:

![Diagrama Compuerta or8way](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/128198245/e070c258-5f01-40bb-bac1-e40a87c610b6)

### Compuerta Mux4way16:
### Compuerta Mux8way16:
### Compuerta DMux4way:
### Compuerta DMux8way:

## Referencias
Fuentes consultadas al realizar esta practica:
- El readme fue realizado siguiendo lo propuesto en [Docs.github.com](https://docs.github.com/es/get-started/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax#links).
- Guia general de la implementacion de la practica [Tea Leaves](https://www.youtube.com/watch?v=Mzy0RG9Z1Ak&t=2112s)
- Simulador usado - [masterplc.com](https://masterplc.com/simulador/)
- Libro fuente: ["The Elements of Computing Systems"](https://www.nand2tetris.org/_files/ugd/44046b_f2c9e41f0b204a34ab78be0ae4953128.pdf).



