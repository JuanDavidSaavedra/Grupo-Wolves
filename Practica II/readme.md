# Descripcion de la 2da Práctica
La segunda práctica tiene como objetivo desarrollar e implementar el segundo y el tercer proyecto propuesto por nand2tetris.org. Estos proyectos están centrados en la aritmetica booleana y la lógica secuencial. Al completarlos, se habrá implementado:
 - La ALU (La unidad Aritmetica Logica).
 - Flip flops.
 - Bits.
 - Registradores.
 - Diferentes versiones de la Ram.
 - El "PC".
 - Entre otros.

## Segundo Proyecto - Aritmetica Booleana
Nand2tetris.org propone este proyecto afirmando que la parte central de la arquitectura de un computador es la CPU. A su vez, la pieza computacional fundamental para la CPU es la ALU (Unidad Aritmética Lógica). Por lo tanto, este proyecto propuso implementar todos los chips necesarios antes de la ALU para, finalmente, llevar a cabo su implementación.

### HalfAdder (Medio sumador):
![Half Adder](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/128198245/1d36ba44-a89f-4fa0-b7a7-2d6433af297d)

Al observar su tabla de verdad podemos observar que solo son necesarias 2 compuertas para su implementacion, la **Xor** será la encargada de llevar a cabo la suma y generar la respectiva salida *sum*, mientras que la **And** será la que calculará si se da un acarreo o no, manejando la salida *carry*.

### FullAdder (Sumador Completo):
![Full Adder](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/128198245/d2961f5b-b77d-4ee6-b5b8-306a7b7fc5a4)

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


## Tercer Proyecto - Memoria:

## ¿Cuales son las principales Diferencias entre logica Aritmetica y Logica Secuencial?

## ¿Qué tipo de unidades aritmético lógicas existen?

## Referencias
Fuentes consultadas al realizar esta practica:
- El readme fue realizado siguiendo lo propuesto en [Docs.github.com](https://docs.github.com/es/get-started/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax#links).
- Guia general de la implementacion del 2do Proyecto [Tea Leaves](https://youtu.be/Wl53tFc5WYQ?list=PLu6SHDdOToSdD4-c9nZX2Qu3ZXnNFocOH)
- Diapositivas teoricas del [2do Proyecto](https://drive.google.com/file/d/1ie9s3GjM2TrvL7PrEZJ00gEwezgNLOBm/view)
- Diapositivas teoricas del [3er Proyecto](https://drive.google.com/file/d/1boFooygPrxMX-AxzogFYIZ-8QsZiDz96/view)
