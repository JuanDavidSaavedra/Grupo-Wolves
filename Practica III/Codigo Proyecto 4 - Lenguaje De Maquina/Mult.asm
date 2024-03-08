// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/4/Mult.asm

// Multiplies R0 and R1 and stores the result in R2.
// (R0, R1, R2 refer to RAM[0], RAM[1], and RAM[2], respectively.)
// The algorithm is based on repetitive addition.

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
M=D+M //R2 ES R2+R1

@R3
M=M-1 //RESTAMOS UN CICLO

D=M //ALMACENAMOS EL CICLO ACTUALIZADO
@LOOP //LLAMAMOS DE NUEVO EL CICLO
D;JGT //VERIFICAMOS QUE EL CICLO NO SEA 0
