# Descripcion de la 2da Práctica
La segunda práctica tiene como objetivo desarrollar e implementar el segundo y el tercer proyecto propuesto por nand2tetris.org. Estos proyectos están centrados en la lógica aritmetica y la lógica secuencial. Al completarlos, se habrá implementado:
 - La ALU (La unidad Aritmetica Logica).
 - Flip flops.
 - Bits.
 - Registradores.
 - Diferentes versiones de la Ram.
 - El "PC".
 - Entre otros.

## Segundo Proyecto - Lógica Aritmetica
Nand2tetris.org propone este proyecto afirmando que la parte central de la arquitectura de un computador es la CPU. A su vez, la pieza computacional fundamental para la CPU es la ALU (Unidad Aritmética Lógica). Por lo tanto, este proyecto propuso implementar todos los chips necesarios antes de la ALU para, finalmente, llevar a cabo su implementación.

### HalfAdder (Medio sumador):
![Half Adder](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/128198245/1d36ba44-a89f-4fa0-b7a7-2d6433af297d)
![Half-adder-circuit-diagram](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/102627981/68baf104-6c44-4cc9-948c-b95979919257)


Al observar su tabla de verdad podemos observar que solo son necesarias 2 compuertas para su implementacion, la **Xor** será la encargada de llevar a cabo la suma y generar la respectiva salida *sum*, mientras que la **And** será la que calculará si se da un acarreo o no, manejando la salida *carry*.

### FullAdder (Sumador Completo):
![Full Adder](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/128198245/d2961f5b-b77d-4ee6-b5b8-306a7b7fc5a4)
![Full-adder-circuit-diagram](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/102627981/03160838-dc13-4952-8295-00c1744c5095)


En el caso del sumador completo, se puede aplicar la propiedad *asociativa* de la suma: La cual propone que (a + b) + c = a + (b + c). Por ende se puede hacer la primera adicion con un medio sumador, su resultado almacenado en la variable *auxSum* será sumado nuevamente (usando otro medio sumador) a la entrada c restante y de ahi se obtiene el resultado final de la suma, sin embargo cada medio sumador utilizado tiene su propio acarreo (auxCarry1 y auxCarry2 respectivamente), por lo que usando una compuerta Xor, siempre que estos 2 sean diferentes la salida Out será igual a 1, lo que significa que llevará un acarreo.

### Add16:
![16bit Adder](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/128198245/e582bce5-580e-4576-945a-a123c4f26b98)

Este chip lo que hace es tomar 2 entradas de 16 bits (a[16] y b[16]) y sumarlas bit a bit, hay 3 observaciones a tener en cuenta para implementarlo correctamente:
- La primera suma (la de a[0] + b[0]) es sencilla, ya que el bit de acarreo por default es 0.
- Del bit número 1 al 15, hay que tener en cuenta el acarreo, por lo que deja de ser una suma sencilla (a+b) y pasa a ser una suma completa (a + b + carry).
- El bit mas significtivo (a[15] + b[15]) del acarreo es ignorado.

lo que nos llevó a la siguiente implementacion:

![image](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/128198245/8ae966a1-26c7-49c0-9aed-d17058275481)

### Inc16:
La implementacion del chip fue la siguiente:

![image](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/128198245/f76487f5-9f77-4b7d-b43d-8c095f109fcc)

Utilizando el **Add16** que se acabo de implementar, se puede utilizar la siguiente sintaxis:

**"Add16(a = in, b[0] = true, b[1..15] = false, out = out);"**

Esta sintaxis lo que quiere decir es que creamos una entrada b de 16 bits con el valor de *b = 0000000000000001* por lo que efectivamente estamos incrementando en un bit, la entrada in de 16 bits.

### ALU:
Un posible diagrama grafico de la ALU podria ser (Tomado de [Tea Leaves](https://youtu.be/Wl53tFc5WYQ?list=PLu6SHDdOToSdD4-c9nZX2Qu3ZXnNFocOH)):

![Diagrama ALU](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/128198245/68ac922f-8b38-41f9-921e-aee8ad343d49)

Se puede observar que este diagrama se divide en **cuatro** secciones fundamentales:
 - Tratamiento de los datos de entrada con (Nx, Zx, Zy y Ny) respectivamente
 - Las operaciones suma o and dependiendo de la entrada f
 - Tratamiento de los datos de salida con no
 - Las banderas para discernir si la salida es 0 o un número negativo (zr y ng)

Para cada seccion de la Alu es necesario usar multiplexores, donde la entrada de seleccion variara dependiendo de para que sea el multiplexor, por ejemplo para negar la entrada x y la entrada y se utilizan como selectores las entradas Nx y Ny respectivamente con su respectivo inversor (compuerta Not16); para convertir las entradas en 0 se utilizan como selectores Zx y Zy y para negar la salida se utiliza como selector la entrada no.

A la hora de las operaciones como tal de la ALU, se calculara siempre la suma bit por bit como el and logico y se almacenara dentro del chip, por medio de otro multiplexor con la entrada de seleccion como f, es que se decide cual debe ser su respectiva salida.

Finalmente, para el manejo de las banderas, se tomo el bit mas significativo de la salida y si este es 1, eso significa que out es menor a 0 por lo que la salida ng dará 1; para la otra bandera, se utilizan 2 compuertas Or8Way para evaluar si el valor de out es efectivamente 0, en el caso de que lo sea con un inversor se cumplira la condicion de que si la salida es 0 zr es 1.

## Tercer Proyecto - Memoria:
Para la segunda parte de la práctica II, se implementaron los siguientes CHIPS:

- DFF
- Bit
- Register
- RAM8
- RAM64
- RAM512
- RAM4K
- RAM16K
- PC

La memoria principal de la computadora, también conocida como memoria de acceso aleatorio o RAM, consiste en una secuencia direccionable de registros, cada uno diseñado para almacenar un valor de n bits. En el contexto de este proyecto, se abordará gradualmente la construcción de una unidad RAM. Esto implica dos aspectos principales: 
1) Emplear la lógica de puertas para mantener los bits de manera persistente a lo largo del tiempo.
2) Utilizar la lógica de puertas para acceder al registro de memoria específico en el que se desea operar. 

Los únicos componentes disponibles para la construcción son aquellos listados en los Proyectos 1 y 2, así como los chips que se desarrollarán progresivamente en este proyecto. Se considera que el chip DFF es primitivo, por lo que no es necesario construirlo nuevamente.

## Bit

![image](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/159449419/315f2ad0-aa55-405a-a614-dc1736a76bf3)

En la especificación presentada, se observan dos entradas: una de carga y una de salida. Por consiguiente, se procede a examinar el diagrama correspondiente. Se ha añadido otra entrada, la cual constituye el reloj implícito en cualquier elemento relacionado con la memoria. En consecuencia, se opta por considerar un flip-flop D en este punto, el cual puede interpretarse como una entrada para el reloj. Se deja ubicado allí para su posterior consideración, con la idea de enrutarse la entrada de bit al flip-flop D. Sin embargo, surge un inconveniente con esta estrategia: la especificación del flip-flop D establece que si la carga es 1, la salida en el tiempo más 1 es igual a la entrada en ese mismo tiempo; de lo contrario, la salida permanece sin cambios. Este comportamiento implica que el flip-flop D presenta un comportamiento de retraso por un ciclo.

## Registrador

![image](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/128198245/2ad76c68-5378-4caa-8a6b-affe61114334)

El bit implementado previamente, también puede ser llamado como registrador de un solo bit, por lo que en este chip en especifico lo que se hace es generalizarlo a 16 bits, hacerlo no es muy complicado, simplemente se toman cada uno de los 16 bits de la entrada y se almacenan en su respectiva compuerta Bit, para generar un Out especifico para ese bit (observar la imagen), out[0], out[1], out[2], ... , out[15].  

## PC o Contador
Las diapositivas que describen la practica, proporcionadas por [Nand2Tetris](https://drive.google.com/file/d/1boFooygPrxMX-AxzogFYIZ-8QsZiDz96/view) nos proporcionan el siguiente diagrama:

![image](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/128198245/0b2c48a7-3e75-41f7-b7cf-95e113c999cb)

En el se nos da una explicación algorítmica del funcionamiento del chip, se puede observar que son puros if anidados, donde la condición principal es la del reseteo, seguida por la carga (load), el incremento (inc) y finalmente el no hacer nada; este if anidado al igual que la ALU del 2do proyecto, se puede modelar por medio de multiplexores, para ello es prudente ver una vez mas la solución proporcionada en este repositorio:

![image](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/128198245/f37dad7b-817c-4a4a-b90f-6ed005a595bd)

Las entradas de selección de los multiplexores son fáciles de observar, pues hay una variable de entrada que corresponde a cada multiplexor, sin embargo, lo que complicó la implementación de esta compuerta fue el discernir que entradas debían ir en cada multiplexor, sin embargo, guiándonos del tutorial de Tea Leaves, se llego a esta conclusión: Donde el registrador recibe como entrada la salida del multiplexor encargado del reseteo (pues es la función de mayor prioridad en el if), el incremento se le aplica a la salida de ese registrador tal como sugiere el algoritmo (out(t+1) = out(t) + 1), ahora la entrada del multiplexor de reseteo es la salida del multiplexor de carga esto tiene sentido ya que es el siguiente en la prioridad del if anidado. De igual manera la entrada del multiplexor de carga es la salida del multiplexor del incremento que es el siguiente en la lista de prioridad de los ifs y finalmente la entrada del mux de incremento es la salida del registrador, pues este es el valor que se debe incrementar según el algoritmo.

## Ram8
Respecto a la RAM en general, las diapositivas del proyecto 3 nos presentan este diagrama parcial:

![image](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/128198245/dd635f33-01a0-4c98-a2e9-3cdda6be953c)

En el ya se encuentran todos los componentes necesarios para implementar una RAM de cualquier valor, lo único que debe hacer es estudiante es hacer las conexiones correctas, para ello nuevamente seguimos la guía propuesta por Tea Leaves y se llego a la siguiente solución:

image](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/128198245/288500ae-4780-477b-b209-eb90d6ae6daf)

La solución es bastante sencilla por medio de la entrada address de tres bits, el demultiplexor sabe a que dirección de memoria queremos acceder, y le va a enviar el valor de la carga (la entrada load) a ese registrador especifico, luego cada uno de los 8 registradores de 16 bits van a almacenar la entrada in y dependiendo del demultiplexor y del valor de la entrada load, sabrá cual dirección debe cargar la nueva entrada, mientras que el resto la mantienen, finalmente con el multiplexor final se devolverá la respectiva salida usando la misma variable address para acceder a la dirección de memoria que nos interesa.

## Ram64
De aquí en adelante, hasta terminar el tercer proyecto, se va a reutilizar la estructura previamente utilizada, solo que utilizando la memoria RAM implementada anteriormente, en el caso de la memoria RAM64, simplemente se van a usar 8 memorias RAM8 resultando el .hdl de la siguiente manera:

![image](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/128198245/17e57f99-e6b9-4b21-b6de-f783693a8c84)

La estructura y el funcionamiento a nivel lógico es el mismo, el demultiplexor y el multiplexor seleccionan la dirección de memoria a interactuar con la entrada address y proceden a enviarle el valor de la entrada load, para así dependiendo de su valor que la RAM8 y los registradores internos que la componen cumplan su función dependiendo del valor de esta. Cabe destacar que los DMux y los Mux utilizan los bits menos significativos (de 0 a 2) de la variable address y las RAM8 usan los bits mas significativos (de 3 a 5)

## RAM512
Nuevamente reutilizamos la estructura de las 2 memorias RAM construidas previamente, en este caso se utilizan 8 memorias RAM64 para alcanzar el valor deseado (512) resultando la implementación de la siguiente manera:

![image](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/128198245/a3126df3-356b-48f2-ae64-ffaf1effb23a)

La estructura y el funcionamiento lógico son similares en ambos niveles: tanto para los chips RAM8 individuales como para la RAM64 que los agrupa. Tanto el demultiplexor como el multiplexor son utilizados para seleccionar la dirección de memoria a interactuar, utilizando los bits de la entrada address. Luego, el valor de la señal load se envía a esa dirección seleccionada. De esta manera, cada chip RAM8 dentro de la RAM64 realiza su función según el valor de la señal load. Es importante destacar que los demultiplexores (DMux) y multiplexores (Mux) operan con los bits menos significativos (del 0 al 2) de la variable address, mientras que los chips RAM64 utilizan los bits más significativos (del 3 al 8).

## RAM4K 
Nuevamente reutilizamos la estructura de las 3 memorias RAM construidas previamente, en este caso se utilizan 8 memorias RAM512 para alcanzar el valor deseado (4000) resultando la implementación de la siguiente manera:

![image](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/128198245/2f56aa0f-7393-46f9-81b0-17ff28341fbe)

La estructura y el funcionamiento lógico son similares en ambos niveles: tanto para los chips RAM64 individuales como para la RAM512 que los agrupa. Tanto el demultiplexor como el multiplexor son utilizados para seleccionar la dirección de memoria a interactuar, utilizando los bits de la entrada address. Luego, el valor de la señal load se envía a esa dirección seleccionada. De esta manera, cada chip RAM64 dentro de la RAM512 realiza su función según el valor de la señal load. Es importante destacar que los demultiplexores (DMux) y multiplexores (Mux) operan con los bits menos significativos (del 0 al 2) de la variable address, mientras que los chips RAM512 utilizan los bits más significativos (del 3 al 11).

## RAM16K
En este caso, se reutiliza la estructura de las 4 memorias RAM construidas anteriormente, pero se simplifica levemente, pues solo se utilizan 4 memorias RAM4K para alcanzar el valor deseado de 16k, quedando la implementación de la siguiente manera:

![image](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/128198245/80dfabc3-5e6f-478f-8185-de5db6eee354)

La estructura y el funcionamiento lógico son similares en ambos niveles: tanto para los chips RAM512 individuales como para la RAM4k que los agrupa. Tanto el demultiplexor como el multiplexor (simplificados, siendo un DMux4Way y un Mux4Way16 en este caso) son utilizados para seleccionar la dirección de memoria a interactuar, utilizando los bits de la entrada address. Luego, el valor de la señal load se envía a esa dirección seleccionada. De esta manera, cada chip RAM512 dentro de la RAM4K realiza su función según el valor de la señal load. Es importante destacar que los demultiplexores (DMux) y multiplexores (Mux) operan con los bits menos significativos (en este caso solo necesitan 2, el address[0] y el address[1]) de la variable address, mientras que los chips RAM4K utilizan los bits más significativos (del 2 al 13).

## ¿Cuales son las principales Diferencias entre logica Aritmetica y Logica Secuencial?
### Logica Aritmética
* Depende unicamente de las entradas
* El proceso corre en un solo ciclo o paso
* Generalmente no presentan capacidad de memoria o memorizacion

### Logica Secuencial
* Depende de las entradas y de los estados anteriores o una variable t
* El proceso de calculo corre en multiples pasos
* Presentan capacidad de memorizacion

## ¿Qué tipo de unidades aritmético lógicas existen?
Más conocidas como ALU (Unidad aritmético logica / Arithmetic Logic Unit ) suelen ser chips con la capacidad de realizar operaciones aritmeticas de numeros enteros (como complemento 2 a 2 o BCD) pero tambien existen los FPU (Unidad de Punto Flotante).
Las unidades aritmetico logicas se pueden clasificar tambien por tipo de dato:
* ALU de enteros: Opera con números enteros binarios.
* ALU de coma flotante (Punto flotante): Opera con números reales en formato de coma flotante.
* ALU vectoriales: Opera con vectores de datos, permitiendo realizar operaciones simultáneas en varios datos.

## Referencias
Fuentes consultadas al realizar esta practica:
- El readme fue realizado siguiendo lo propuesto en [Docs.github.com](https://docs.github.com/es/get-started/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax#links).
- Guia general de la implementacion del 2do Proyecto [Tea Leaves](https://youtu.be/Wl53tFc5WYQ?list=PLu6SHDdOToSdD4-c9nZX2Qu3ZXnNFocOH)
- Diapositivas teoricas del [2do Proyecto](https://drive.google.com/file/d/1ie9s3GjM2TrvL7PrEZJ00gEwezgNLOBm/view)
- Diapositivas teoricas del [3er Proyecto](https://drive.google.com/file/d/1boFooygPrxMX-AxzogFYIZ-8QsZiDz96/view)
- Guia general de la implenmentacion del tercer proyecto: https://drive.google.com/file/d/1ArUW8mkh4Kax-2TXGRpjPWuHf70u6_TJ/view 
