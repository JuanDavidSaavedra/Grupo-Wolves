# Descripción de la 4ta Práctica

Esta práctica es de suma importancia, ya que comúnmente los programas de bajo nivel **no** son escritos directamente por humanos, sino que son generados por compiladores. No obstante, la inspección de este código puede brindar lecciones valiosas sobre cómo **mejorar** la escritura de programas en alto nivel. Asimismo, el desarrollo de un ensamblador representa el **primer paso** hacia la construcción de un compilador para un lenguaje de alto nivel, similar a Java.

## Objetivo
El objetivo de esta practica es el de escribir un programa **ensamblador** que traduzca programas escritos en el lenguaje simbólico de ensamblaje Hack a código binario que pueda ejecutarse en la plataforma de hardware Hack construida en las practicas anteriores.

## Arquitectura Propuesta
La implementación del ensamblador se propuso en 5 etapas (o módulos "independientes" entre si) programados en Python las cuales tienen funciones marcadas y definidas, estas son:
1. **Limpiador**: Es una función que permite eliminar los comentarios, los espacios en blanco y los saltos de linea innecesarios del programa.
2. **Parseador**: Se implementó como una clase de objetos con el objetivo de discernir con que tipo de instrucción estamos lidiando en cada linea de código, de igual manera por medio de splits nos permite obtener los valores, las operaciones, el destino, si hay un salto o si la instrucción es una etiqueta.
3. **Codificador**: Son un conjunto de funciones que giran en torno a diccionarios; estos diccionarios a su vez se subdividen en 4, de los cuales 3 son estáticos. El 1er diccionario estático es el de destino, el 2do el de la operación a realizar y el 3ero el de los saltos. Finalmente el ultimo diccionario es dinámico y es aquel que contiene las etiquetas especificas para cada programa que se busque traducir.
4. **Main**: Haciendo el paralelismo con Java, es la clase donde se utilizan los 3 módulos mencionados anteriormente. Su flujo lógico es el siguiente:
   - Se Limpia el programa a traducir por medio de la función limpiador (`del_com_espacios(programa)`)
   - Se agrega al diccionario dinámico las etiquetas especificas para el programa
   - Se genera el archivo.hack y se empieza a iterar sobre el para escribir las instrucciones traducidas a binario
5. **Ensamblador**: Es la función en la que se carga el o los archivos.asm a traducir, se genera el diccionario dinámico con valores especifícos como "SCREEN", "KBD", etc. Y donde se llama a la main para que traduzca y almacene los programas en una ruta del drive del usuario.

## Ejemplo Funcionamiento Sencillo
```Python
#Asumamos que tenemos estas ordenes sencillas
programa = """
@R1
D=D-M
D=M
0;JMP
"""
# Donde según los diccionarios:
Ejemplo_Diccionarios = {'R1': 1, "D" : "0001100", "D-M": "1010011", "JMP" : "111"} #Si un valor es None -> 000

#Y el flujo de la main es:
with open(f"{ruta}/{nombre}.hack", "w") as archivo_hack:
    for i in lineas:
      instruccion = ""
      if(i.tipo == 'C'):
        instruccion = "111" + codigo_operacion(i.get_operacion()) + codigo_destino(i.get_destino()) + codigo_salto(i.get_salto())
        archivo_hack.write(instruccion + '\n')
      elif(i.tipo == 'A'):
        instruccion= codigo_valor(i.get_valor(), diccionario)
        instruccion = "0" +  instruccion
        archivo_hack.write(instruccion + '\n')
```
En este ejemplo sencillo, se presentan cuatro líneas de código, junto con su respectiva traducción a código binario:

1. `@R1`: Al crear una instancia de la línea mediante la clase `Parseo`, el programa identifica su tipo como 'A'. Luego, se obtiene su valor (en este caso 'R1') y se utiliza la función `codigo_valor` para obtener su representación binaria correspondiente, que en este caso es la instrucción: `0000000000000001`.
2. `D=D-M`: Nuevamente, a través de la instancia se determina que es una instrucción de tipo 'C'. Utilizando diferentes getters, se obtiene la operación (`D-M`), el destino (`D`) y el salto (`None`). Luego, utilizando las funciones adecuadas del codificador y sus respectivos diccionarios, se traducen a binario: 111 (operación) + 1010011 (destino) + 010 (salto inexistente), resultando en: `1111010011010000`.
3. `D=M`: Operación = M (1110000), Destino = D (010), Salto = None (000). Al traducir y concatenar, se obtiene: `1111110000010000`.
4. `0;JMP`: Operación = 0 (0101010), Destino = None (000), Salto = JMP (111). Al traducir y concatenar, se obtiene: `1110101010000111`.

## Verificación
Al haber ejecutado la celda del Notebook que contiene la función `ensamblador()`, se generan estos archivos en la ruta especificada:

![image](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/128198245/b159029f-5614-474d-8256-8669523c67f4)

Luego de descargarse los respectivos archivos se comprueba que la traducción sea la adecuada por medio del Ensamblador de Nand2Tetris:

![image](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/128198245/b7468c21-e69a-428d-8a66-ae6f26b03dac)

Como se muestra en el mensaje: "File compilation & comparison succeeded" La traducción fue completamente equivalente. Esta verificación se realizó para cada uno de los 7 programas entregados por Nand2Tetris para el proyecto #6.

## Teniendo en cuenta las características del ensamblador, ¿Cuál es la principal limitante que observan?

La principal limitante es su simplicidad. Como pudimos observar, cumple con su función básica de traducir código escrito en lenguaje ensamblador a código máquina. Sin embargo, no contiene todas las características que se encuentran en ensambladores más complejos. Otra restricción puede ser su memoria, ya que la memoria donde residen las instrucciones del programa **no** es ilimitada, por lo que en programas extensos y complejos, puede que no sea posible almacenar todas las instrucciones o los datos que se deben manipular de manera simultanea. Otra limitante es la de la asignación manual de las direcciones de memoria, porque aquellos sin experiencia al programar en lenguaje de ensamblador estarán propensos a cometer muchos errores en el ambito de asignación de direcciones de memoria.

## BONUS: ¿Por qué es tan importante el ensamblador?
El ensamblador cumple una función crucial al actuar como un puente entre el lenguaje de programación de alto nivel y el código máquina que puede ejecutar directamente la computadora. Sin un ensamblador, la mayoría de los programadores se encontrarían obligados a escribir código en lenguaje máquina, lo que sería extremadamente complicado y propenso a errores. Por lo tanto, el ensamblador simplifica significativamente el proceso de desarrollo de software.
# Referencias:
- El readme fue realizado siguiendo lo propuesto en [Docs.github.com](https://docs.github.com/es/get-started/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax#links).
- Arquitectura Propuesta [MakkuZjAileron](https://youtu.be/0y8JPx0ZakY)
- Lectura recomendada por Nand2Tetris.org - [The Elements of Computing Systems - Chapter 6](https://www.nand2tetris.org/_files/ugd/44046b_b73759b866b249a0b3a715bf5a18f668.pdf)
