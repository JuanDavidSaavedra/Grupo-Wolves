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

***Nota:*** En nuestra implementación se utilizo una compuerta Xor, esto se debe a que el comportamiento del multiplexor asegura alguna de las siguientes combinaciones: xorEntry1 = 1 y xorEntry2 = 0 o viceversa, en otras palabras ambas salidas de las **And** *nunca* serán 1 al mismo tiempo, y por ende el comportamiento de la Xor y la Or en este caso especifico es virtualmente el mismo.

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
La tabla de verdad se puede reducir de manera significativa de esta manera:

![image](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/128198245/b28daa39-ce2c-490a-8ac6-ea7205a655b3)

Si ignoramos que a, b, c y d son entradas de 16 bits (usando la compuerta Mux16 implementada anteriormente), entonces solo se debe reducir las opciones de salidas disponibles (al igual que con la compuerta Or8way), para ello usamos los bits del Selector, ya que el bit menos significativo (Sel[0]) nos permite reducir las opciones disponibles a la mitad, si sel[0] es igual a 0 el output será a o c (mux16Out1 = a y mux16Out2 = c respectivamente), por el contrario si es igual a 1 el output será b o d (mux16Out1 = b y mux16Out2 = d respectivamente) y finalmente, con el bit mas significativo (sel[1]) se decide efectivamente cual de esas 2 opciones es la que va a salir por el output al final.

### Compuerta Mux8way16:
Nuevamente se puede simplificar la tabla de verdad de la siguiente manera:

![image](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/128198245/2f377940-cf0d-4f1b-b674-b620cf44a0c2)

Ahora observemos la implementacion de la compuerta:

![image](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/128198245/c7b1f04f-86eb-4742-b504-6feb46c02d0f)

Los 2 Mux4way16 hacen su trabajo con los bits menos significativos (sel[0] y sel[1]), por lo que reducen las opciones posibles en 4 duplas diferentes: Si es 00 entonces el output puede ser a o e (siendo muxOut1 = a y muxOut2 = e), si es 01 el output puede ser b o f (siendo muxOut1 = b y muxOut2 = f), si es 10 el output puede ser c o g (siendo muxOut1 = c y muxOut2 = g), y si es 11 el output pueder ser d o h (siendo muxOut1 = d y muxOut2 = h). De modo que con el ultimo multiplexor de 16 bit y tomando el bit mas significativo (sel[2]) se decide cual debe ser el output final, en el caso de Sel siendo igual a 000, el output es a, pero si es 100 entonces el output será e y se repite este comportamiento para cada combinacion del selector posible.

### Compuerta DMux4way:
Simplificando la tabla de verdad obtenemos esto:

![image](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/128198245/9bce3828-b169-450d-a657-9770a3f93e51)

Ahora veamos la implementacion:

![image](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/128198245/789ccb37-1154-4636-9722-602e5016810d)

Graficamente se vería asi:

![Diagrama compuerta 4-way-Dmux 2](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/128198245/45b197dc-8361-41e3-98a1-2a809a366e15)

La compuerta DMux4way hace lo inverso a la Mux4way, por lo que su implementacion tambien debe ser inversa, en primer lugar tomamos el bit mas significativo sel[1] y en base a el generamos dos duplas de salida diferentes si sel[1] = 0 entonces el output puede ser a o b (dmux1out1), por el contrario si sel[1] = 1 entonces el output puede ser c o d (dmux1out2) finalmente con los 2 ultimos *Dmux* y usando el bit menos significativo (sel[0]) seleccionamos efectivamente cual queremos que sea la salida, si sel = 00 entonces es a y si es sel = 01 entonces seria b, y asi para cada combinacion de sel.

### Compuerta DMux8way:
La tabla de verdad simplificada se ve asi:

![image](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/128198245/e18e0360-e303-46ef-a75a-cb510f52217b)

y la implementacion quedó asi:

![image](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/128198245/f58490b9-4b89-4651-ac0d-a936ef84c0d3)

Nuevamente tomamos el bit mas significativo (sel[2]) generando 2 posibilidades diferentes donde dmuxOutA es igual a: a, b, c o d; por el contrario, dmuxoutB es igual a: e, f, g o h, finalmente con las 2 compuertas **DMux4way** y los dos bits menos significativos (sel[1] y sel[0]) se selecciona efectivamente cual es el output deseado.

## Preguntas Adicionales

### ¿Que consideraciones importantes debe tener en cuenta para trabajar con Nand2Tetris?
Hay muchos factores importantes a tener en cuenta para poder trabajar de manera efectiva con Nand2tetris, algunos de estos son:
  - La necesidad de tener conocimientos de *electronica*, pues solo en esta primera practica se aplicaron conocimientos de logica Booleana, compuertas logicas y otros fundamentos vistos en cursos anteriores. 
  - Es fundamental que el estudiante maneje una logica de programacion basica, dado que es necesario editar los documentos .hdl que comparten ciertas similitudes con la sintaxis de Java, tambien en futuras practicas puede que necesitemos diseñar nuestros propios Scripts de prueba, por lo que tener experiencia programando puede facilitar ese proceso.
  - El Manejo de **arreglos** tambien es importante, dado que en esta practica se usaron para discernir con cual bit de los selectores se estaba trabajando, de igual manera es probable de que se sigan utilizando en futuras practicas.
  - El Manejo de la interfaz de Usuario otorgada por Nand2Tetris, ya que aunque se programen correctamente los circuitos (.hdl) se necesita comprobar que funcionan adecuadamente y para eso está el emulador de hardware, los archivos .tst, el emulador de maquina virtual, etc.
  - Nand2Tetris propviene del juego de palabras "nand to tetris", el proyecto se propone partir de una unica clase de circuito Not-And(nand) y apartir de este ir construyendo los demas hasta tener las herramientas necesarias para construir un programa completo, es importante explorar el aspecto de la creatividad para el correcto aprovechamiento de esta herramienta y como se puede utilizar de manera eficiente para diseñar circuitos mas complejos. 

### ¿Qué otras herramientas similares a Nand2Tetris existen? (De mínimo dos ejemplos)
Dado que Nand2Tetris es un sitio web centrado en el aprendizaje de las habilidades necesarias para implementar un sistema informático desde cero, donde las lecciones están respaldadas por herramientas como su [Suite](https://www.nand2tetris.org/software) (utilizada en este laboratorio), su libro (mencionado anteriormente) y los laboratorios propuestos en el mismo; pues es un hecho que no es la unica, existen múltiples herramientas en línea que cumplen un propósito similar, el cual es impartir conocimiento tanto teórico como práctico en diversas áreas. Algunos ejemplos incluyen:
  - [Platzi](https://platzi.com)
  - [Coursera](https://www.coursera.org)
  - [SkillsForAll](https://skillsforall.com/)
  - Libros y Documentos como ["Sistemas Computacionales"](https://sistemascomputacionales.readthedocs.io/_/downloads/es/stable/pdf/)
  - [Github](https://github.com/jamesleeat/TeachYourselfCS-ES/blob/main/TeachYourselfCS-ES.md)
  - etc

Estos son solo unos pocos ejemplos de herramientas de aprendizaje, que ofrecen contenido gratuito y/o de pago. Estas herramientas están diseñadas para no solo proporcionar conocimiento teórico a los estudiantes, sino también para permitirles realizar ejercicios prácticos que desafíen su ingenio y fomenten su crecimiento en el ámbito profesional. Además, no se limitan únicamente a la ciencia detrás del funcionamiento de las computadoras, sino que también ofrecen contenido sobre diversos temas como ciberseguridad, programación, economía y más.

## Referencias
Fuentes consultadas al realizar esta practica:
- El readme fue realizado siguiendo lo propuesto en [Docs.github.com](https://docs.github.com/es/get-started/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax#links).
- Guia general de la implementacion de la practica [Tea Leaves](https://www.youtube.com/watch?v=Mzy0RG9Z1Ak&t=2112s)
- Simulador usado - [masterplc.com](https://masterplc.com/simulador/)
- Libro fuente: ["The Elements of Computing Systems"](https://www.nand2tetris.org/_files/ugd/44046b_f2c9e41f0b204a34ab78be0ae4953128.pdf).
- Explicacion Mux4way16 y Mux8way16: [whostolebenfrog](https://github.com/whostolebenfrog/The-Elements-of-Computing-Systems/blob/master/project1/Mux4Way16.hdl)



