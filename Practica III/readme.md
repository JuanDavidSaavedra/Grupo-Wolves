# Descripcion de la 3era Práctica
La tercera práctica tiene como objetivo desarrollar e implementar el cuarto y el quinto proyecto propuesto por nand2tetris.org. Estos proyectos están centrados en ejercicios sencillos en lenguaje de ensamblador y la arquitectura de un computador.

## Cuarto Proyecto - Lenguaje de Maquina
El objetivo de este proyecto es el de obtener una experiencia en programación de bajo nivel en el lenguaje de máquina, y el de familiarizarse con la plataforma de computadora Hack. En este proyecto, se buscó que los estudiantes se familiarizaran con el lenguaje de ensamblador **(Assembly)** y de que obtuvieran nociones básicas a la hora de traducir de un lenguaje simbólico al lenguaje de la máquina. También se buscó que apreciaran visualmente cómo se ejecuta el código binario nativo en la plataforma de hardware otorgada por Nand2Tetris. Todo esto se logró al escribir y probar dos programas de bajo nivel, los cuales fueron los siguientes:

### Mult.asm
Este es un programa simple que recibe dos posiciones de memoria RAM (R0 y R1) de modo que se obtenga el producto de ambos números y se almacene en la posición de memoria R2, la implementación fue la siguiente:

```Assembly
@R2 //INICIALIZAMOS R2 EN 0
M=0

@R0 //CARGAMOS R0
D=M
@END
D;JEQ //VERIFICAR QUE NO SEA 0

@R1 //CARGAMOS R1
D=M
@END
D;JEQ //VERIFICAR QUE NO SEA 0

@R0 //VOLVEMOS A CARGAR R0
D=M 
@R3 //CREAMOS UN AUXILIAR R3 
M=D //TOMA EL VALOR DE R0 QUE SON LOS CICLOS RESTANTES

(LOOP) //INICIAMOS EL CICLO
@R1
D=M //CARGAMOS R1
@R2
M=D+M //R2 ES R1+R2
@R3
M=M-1 //RESTAMOS UN CICLO

D=M //ALMACENAMOS EL CICLO ACTUALIZADO
@LOOP //LLAMAMOS DE NUEVO EL CICLO
D;JGT //VERIFICAMOS QUE EL CICLO NO SEA 0
```
El funcionamiento del programa es sumamente sencillo, primero se leen los valores R0 y R1, Se inicializa R2 = 0 y se inicia una cuenta regresiva delos ciclos restantes (R3) luego simplemente se hace la multiplicación por medio de un ciclo de sumas, por ejemplo 2*5 = 2 + 2 + 2 + 2 + 2 = 10; cada iteración de la suma se almacena en R2 que es nuestro resultado del producto y de igual manera se decrementa en uno el valor de R3. Al finalizar cada iteración, por medio de D;JGT preguntamos si los ciclos restantes son 0, en caso de que no sea así, se repite el bucle, de lo contrario sale del Loop y termina la ejecución del programa. 

### Fill.asm
Este es un programa simple que permite una interacción del usuario con la maquina, de modo que esta constantemente leyendo si alguna tecla del Keyboard esta siendo presionada y en caso de que así lo sea pinta la pantalla de negro, de lo contrario la pinta de blanco, su implementación fue la siguiente:
```Assembly
(BEGIN)      // Inicio del programa infinito

@KBD //Recibe el registro del Teclado
D=M
@BLACK
D;JNE //Si una tecla esta oprimida pasa al label @BLACK
@WHITE
D;JEQ //Si ninguna tecla esta oprimida pasa al label @White

(BLACK)
@counter     //Inicializa un contador con el valor de -1
M=-1
(LOOP)       //Inicia un bucle 
@counter
M=M+1        //Incremento del valor del contador
D=M
@SCREEN
A=A+D //Selecciona el Pixel
M=-1  //Lo establece en negro
@8191 //constante con el tamaño de la pantalla
D=D-A //resta del pixel actual (posicion actual) vs el tamaño de la pantalla
@LOOP
D;JNE  //Si el contador no ha pintado toda la pantalla, vuelve al inicio del LOOP
@BEGIN
0;JMP  //Al pintar toda la pantalla de negro vuelve al inicio del programa

(WHITE)
@counterwhite  //variable contadora para pintar la pantalla de blanco
M=-1          //se inicializa en -1
(LOOPWHITE)  //Inicia el bucle para pintar la pantalla de blanco
@counterwhite
M=M+1  //incremento del contador
D=M    //almacenamos el valor del contador en el pixel actual
@SCREEN
A=A+D   //seleccionamos el pixel de la pantalla
M=0     //lo pintamos de blanco
@8191
D=D-A   //hacemos la misma verificacion de posicion - tamaño de la pantalla
@LOOPWHITE
D;JNE       // Si la resta no da cero (que recorrio toda la pantalla) vuelve al inicio del bucle
@BEGIN      
0;JMP       // vuelve al inicio del programa
```
En primer lugar accede al registro del teclado (@KBD) y por medio de un if simple selecciona a que sección del código debe saltar, si hay una tecla oprimida gracias a D;JNE procederá a saltar al label BLACK, lo que hace esta "función" es simplemente recorrer todos los pixeles de la pantalla e ir pintándolos de negro (-1); en el caso de que no haya ninguna tecla oprimida, gracias a D;JEQ, el programa salta al label (WHITE) que también recorre todos los pixeles de la pantalla solo que los pinta de blanco (0), este proceso se repite de manera infinita pues en ambos labels esta la linea de código 0;JMP, que lo que hace es volver a la linea 0 del programa, de modo que lo mantiene en un bucle infinito.


## Quinto Proyecto - Arquitectura de Computadores

## ¿Por qué el lenguaje de máquina es importante para definir la arquitectura computacional?
## ¿Qué diferencia ven entre arquitectura computacional, arquitectura de software y arquitectura del sistema? 
## BONUS-1: Como informático o computista: ¿La arquitectura computacional o la arquitectura del sistema no tiene en cuenta igualmente la arquitectura de software?
## BONUS-2: Investigación - Pentium Bug 80x86 (Pentium Chronicles)

# Referencias:
- El readme fue realizado siguiendo lo propuesto en [Docs.github.com](https://docs.github.com/es/get-started/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax#links).
- Guia general de la implementacion del Mult.asm (4to Proyecto): [Carlos Bickford - Youtube](https://youtu.be/MI_fAbJbhRw)
- Guia general de la implementacion del Fill.asm (4to Proyecto): [msohaibalam - Github](https://github.com/msohaibalam/nand2tetris.git)
- Diapositivas teoricas 4to Proyecto [nand2tetris.org](https://drive.google.com/file/d/1HxjPmIZkFHl-BVW3qoz8eD9dqEuEyuBI/view)
