# Descripcion de la 3era Práctica
La tercera práctica tiene como objetivo desarrollar e implementar el cuarto y el quinto proyecto propuesto por nand2tetris.org. Estos proyectos están centrados en ejercicios sencillos en lenguaje de ensamblador y avanzar aun mas en la implementación de la arquitectura completa de un computador.

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

---

## Quinto Proyecto - Arquitectura de Computadores
El objetivo de este proyecto es integrar la ALU y la RAM que se construyeron en los proyectos 2 y 3 en un sistema informático capaz de ejecutar programas escritos en el lenguaje de máquina introducido en el proyecto 4, para ello se debían implementar los siguientes chips:
- Memory
- CPU
- Computer

### Memory
La implementación se hizo de la siguiente manera:

```Java
CHIP Memory {
    IN in[16], load, address[15];
    OUT out[16];

    PARTS:
    DMux4Way(in=load, sel=address[13..14], a=ramA, b=ramB, c=screenIn, d=null);
    Or(a=ramA, b=ramB, out=ramIn);
    
	RAM16K(in=in, load=ramIn, address=address[0..13], out=ramOut);
    Screen(in=in, load=screenIn, address=address[0..12], out=screenOut); //Provided Chip
    Keyboard(out=tecOut); //Provided Chip
    
    Mux4Way16(a=ramOut, b=ramOut, c=screenOut, d=tecOut, sel=address[13..14], out=out);
}
```
Lo importante de la implementación de este chip, son las condiciones que este utiliza para discernir a cual respectiva dirección de memoria se esta accediendo, si a la RAM, a la de la pantalla o a la del teclado. El rango para acceder a la memoria de la pantalla (Screen Memory) es de 0x4000-0x5FFF. La dirección 0x6000 es el mapa de la memoria del teclado y cualquier valor superior a ese es invalido.

0x4000 = 0000 0000 0000 0000 0100 0000 0000 0000

0x5FFF = 0000 0000 0000 0000 0101 1111 1111 1111

Al analizar los limites de esas condiciones nos damos de cuenta de que los bits mas importantes son el bit[13] y el bit[14] de la dirección (address) y que son efectivamente estos bits los que usamos en el DMux4Way para discernir si accedemos a la RAM, a la pantalla o al teclado. Luego, para el caso de la RAM, por medio de una RAM16k usando el resultado de una OR (denominado ramIn) y los 14 bits de la dirección (address[0..13]) obtenemos una salida ramOut. De igual manera en el caso de que la dirección se oriente a la memoria de la pantalla, por medio de un chip ya implementado por Nand2tetris obtenemos la respectiva salida ScreenOut. Finalmente, para el caso de que la dirección corresponda a la del teclado, por medio del chip Keyboard (también implementado por nand2tetris) recibimos su salida tecOut. Así, habiendo obtenido los datos para cualquier dirección posible, usando un multiplexor con la misma entrada de selección address[13..14] se devuelve la salida de la memoria definida como out.

### CPU
La implementación de la CPU es probablemente de las cosas mas complejas que se han desarrollado hasta ahora dado que usa la ALU (la cual también fue difícil de implementar) para procesar todas las solicitudes que le van a llegar a la computadora. En esta ocasión se opto por un diseño grafico antes de pasar a la implementación en .hdl, para ello nos guiamos del video de TeaLeaves que se encuentra en las referencias, el diseño resultante es el siguiente:

![image](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/128198245/39596a74-d728-4b0d-acd7-87a919f42e30)

En primer lugar hay que entender que la entrada mas importante es la **instruction**, que es la orden (de 16 bits) que recibe la CPU y que debe procesar, sin embargo como se vio en el proyecto anterior cada bit de esta solicitud es complejo y tiene una función diferente, es por eso que en el diagrama se divide por bits.

Expliquemos lo mas simple primero, los bits en el rango de 6-11 se conectan directamente con su respectiva entrada en la ALU, por lo que no hay perdida, el bit 13 y el bit 14 no se utilizan y el 15 nos sirve como entrada de selección para el multiplexor, el cual decide si la solicitud es una c-instruction o una a-instruction; en caso de que cType sea 1, significa que es una instrucción tipo a y la CPU funciona normalmente, pero si es 0, significa que efectivamente es una c-Instruction y que el resto de instrucciones deben ser igual a 0.

De igual manera, los bits 3-5 definen a donde debe dirigirse el resultado de la ALU (aluOut), si se dirigen al registrador A, al registrador D o si directamente el resultado debe almacenarse en memoria.

#### **Logica de los Registradores**
Iniciemos hablando del registrador D que es el mas sencillo, siguiendo las especificaciones de la pagina 87 del libro de nand2tetris:

![Aux Registradores](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/128198245/8ae18404-8ec0-4592-8dd9-7789725774a9)

Podemos resumir D como: "Devuélveme cualquier valor que puse en x, independientemente del valor de y", es justo por ese motivo que la salida del registrador D es la entrada x de la ALU, de igual manera, el bit 4 de la instrucción (cDestD) nos servirá como entrada de carga (load) para decidir si la salida de la ALU (y entrada del registrador) se debe registrar o no.

Respecto al registrador A, es un poco mas complejo, pues el bit 12 de la instrucción (cA0rM) define si el registrador debe comportarse como un índice de memoria M o como efectivamente un registrador; primero asumamos que se comporta como registrador, en ese caso se comportará de una manera similar al registrador D, donde devolverá cualquier valor de y independientemente del de x, sin embargo si efectivamente se comporta como un índice de memoria, y deberá tomar el valor de la entrada inM, por ende para solucionar ese problema y generar la entrada y en la ALU, simplemente usamos un multiplexor con entrada de selección cA0rM. respecto a la entrada In del registrador A, esta depende de la salida del multiplexor aMuxOut, de igual manera la entrada de carga load, dependerá de la salida loadA de la compuerta OR. En resumen, el comportamiento del registrador A depende de varios bits diferentes, entre ellos: cA0rM, cType y cDestA.

#### **Logica de los saltos**

La lógica de los saltos gira en torno a 3 bits principales en un rango de 0-2, estos bits son los equivalentes para 3 situaciones especificas y los vimos en el lenguaje de maquina del proyecto anterior; los casos son: cuando la salida de la ALU es menor que 0 (cJLT), cuando es igual a 0 (cJEQ) y cuando es mayor a 0 (cJGT). Para los 2 primeros casos, la ALU tiene sus propias banderas ya establecidas (zr y ng), por lo que si negamos ambas banderas y las usamos como entrada en una compuerta AND tendremos también la bandera para el caso cJGT. luego por medio de 3 compuertas AND evaluamos si se dio ese resultado y si la instrucción pide hacer el salto, para finalizar por medio de una OR concluyendo si efectivamente debe suceder un salto o no que es la entrada de carga en el pc. 

A nivel de código, como archivo .hdl la implementación se dio así:

```Java
CHIP CPU {

    IN  inM[16],         // M value input  (M = contents of RAM[A])
        instruction[16], // Instruction for execution
        reset;           // Signals whether to re-start the current
                         // program (reset==1) or continue executing
                         // the current program (reset==0).

    OUT outM[16],        // M value output
        writeM,          // Write to M? 
        addressM[15],    // Address in data memory (of M)
        pc[15];          // address of next instruction

    PARTS:
    //divide si es una "c-instruction" o una "a-instruction"
    //Permite renombrar cada bit de la instruccion para poder asemejar mas la implementacion al diagrama
    Mux16(a=false, b=instruction, sel=instruction[15], 
          out[0]=cJGT,
          out[1]=cJEQ,
          out[2]=cJLT,
          out[3]=cDestM,
          out[3]=writeM,
          out[4]=cDestD,
          out[5]=cDestA,
          out[6]=cAluNo,
          out[7]=cAluF,
          out[8]=cAluNy,
          out[9]=cAluZy,
          out[10]=cAluNx,
          out[11]=cAluZx,
          out[12]=cA0rM,
          //13, 14 unused
          out[15]=cType); 
    //Bandera para JGT
    Or(a=zerop, b=negp, out=lteq);
    Not(in=lteq, out=posp);
    
	ALU(x=xIn, y=yIn, zx=cAluZx, nx=cAluNx, zy=cAluZy, ny=cAluNy, f=cAluF, no=cAluNo, out=aluOut, out=outM, 
    zr=zerop, ng=negp);

    //Logica del registrador A
    Mux16(a=instruction, b=aluOut, sel=cType, out=aMuxOut); //mux para decidir la entrada del registrador A
    Mux16(a=aRegOut, b=inM, sel=cA0rM, out=yIn); //mux para decidir la entrada y de la ALU
    //Cargando el Registrador A
    Not(in=cType, out=notCType);
    Or(a=notCType, b=cDestA, out=loadA); //Or para decidir la entrada de carga loadA
    ARegister(in=aMuxOut, load=loadA, out=aRegOut, out[0..14]=addressM); 

    //Logica del registrador D
    DRegister(in=aluOut, load=cDestD, out=xIn);

    //Logica de Saltos
    And(a=cJEQ, b=zerop, out=JEQ);
    And(a=cJLT, b=negp, out=JLT);
    And(a=cJGT, b=posp, out=JGT);
    Or(a=JEQ, b=JLT, out=JLE);
    Or(a=JLE, b=JGT, out=jump);

    //Logica del PC
    PC(in=aRegOut, load=jump, inc=true, reset=reset, out[0..14]=pc, out[15]=false);
}
```

### Computer
La implementacion de este chip fue la siguiente:
```Java
CHIP Computer {

    IN reset;

    PARTS:
    CPU(inM=loadFromMem, instruction=romOut, reset=reset, outM=storeMem, writeM=writeMem, addressM=addressMem, pc=pcOut);
    Memory(in=storeMem, load=writeMem, address=addressMem, out=loadFromMem);
    ROM32K(address=pcOut, out=romOut); //Chip interno dado por Nand2tetris que almacena nuestros programas (.hack)
}
```
Donde solamente hay una entrada llamada **reset** que sirve para reiniciar la ejecucion del programa almacenado en el chip ROM32K el cual ya viene implementado por nand2tetris. En este chip efectivamente se lográ el objetivo del proyecto 5, pues combinamos los componentes de las practicas anteriores en una computadora de proposito general que puede correr programas simples en lenguaje de maquina.

---

## ¿Por qué el lenguaje de máquina es importante para definir la arquitectura computacional?
Resulta esencial en 5 puntos principales:
- Control de bajo nivel, permitiendo un control preciso sobre el hardware usado fundamental para el diseño de sistemas y la gestion optima de recursos.
- Optimización de rendimiento ya que al trabajar a bajo nivel el "facil" mejorar el rendimiento de los programas.
- Interfaz con el hardware puesto que el lenguaje de maquina se entiende directamente con la CPU.
- Compatibilidad y portabilidad siendo que el lenguaje de maquina puede mantenerse constante (relativamente) entre distintos hardware, pudiendo ejecutar las mismas instrucciones en diferente tipos de arquitectura de CPU (claramente con sus respectivas restricciones de espeficicación).
- Base para otros lenguajes de programación como C++, C, Python que finalmente se traducen a lenguaje de maquina para ser entendidos y ejecutados por la CPU. Se vuelve importante entender como funciona el lenaguaje de maquina para entender el funcionamiento de los diferentes lenguajes de programación.

## ¿Qué diferencia ven entre arquitectura computacional, arquitectura de software y arquitectura del sistema? 


## BONUS-1: Como informático o computista: ¿La arquitectura computacional o la arquitectura del sistema no tiene en cuenta igualmente la arquitectura de software?
## BONUS-2: Investigación - Pentium Bug 80x86 (Pentium Chronicles)

# Referencias:
- El readme fue realizado siguiendo lo propuesto en [Docs.github.com](https://docs.github.com/es/get-started/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax#links).
- Guia general de la implementacion del Mult.asm (4to Proyecto): [Carlos Bickford - Youtube](https://youtu.be/MI_fAbJbhRw)
- Guia general de la implementacion del Fill.asm (4to Proyecto): [msohaibalam - Github](https://github.com/msohaibalam/nand2tetris.git)
- Diapositivas teoricas 4to Proyecto [nand2tetris.org](https://drive.google.com/file/d/1HxjPmIZkFHl-BVW3qoz8eD9dqEuEyuBI/view)
- Guia General de la implementacion del 5to Proyecto: [TeaLeaves - Memory](https://youtu.be/7Np0-fUYaC8?list=PLu6SHDdOToSdD4-c9nZX2Qu3ZXnNFocOH) y [TeaLeaves - CPU and Computer](https://youtu.be/xHh2GdJl4Cs?list=PLu6SHDdOToSdD4-c9nZX2Qu3ZXnNFocOH)
