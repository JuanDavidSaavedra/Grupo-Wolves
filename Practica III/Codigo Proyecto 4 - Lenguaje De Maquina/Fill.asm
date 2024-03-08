// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/4/Fill.asm

// Runs an infinite loop that listens to the keyboard input. 
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel. When no key is pressed, 
// the screen should be cleared.

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

