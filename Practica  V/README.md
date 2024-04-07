# Descripción del Proyecto 07 - VM I: Aritmética de pila

El séptimo proyecto de la plataforma Nand2tetris.org tiene como objetivo principal crear un traductor de código de máquina virtual (VM) a código ensamblador Hack. Este proyecto es una continuación del proyecto 06, que consistía en implementar una máquina virtual que ejecutara programas escritos en un lenguaje de alto nivel llamado VM.

## VM Translator

El traductor de VM a Hack es una herramienta fundamental para la construcción de un compilador completo, ya que permite traducir programas escritos en un lenguaje de alto nivel como el VM de Nand2tetris a código ensamblador de bajo nivel compatible con la arquitectura Hack. El objetivo final es poder ejecutar estos programas en la plataforma Hack, que es una arquitectura de computadora diseñada en los proyectos anteriores de Nand2tetris.

### Estructura del Traductor

El traductor de VM a Hack se divide en varias etapas que realizan diferentes tareas:

1. **Parser**: Esta etapa se encarga de leer el archivo de código fuente en formato VM y dividirlo en tokens significativos. Identifica y clasifica cada instrucción VM y extrae los argumentos necesarios para generar el código ensamblador correspondiente.

2. **Code Writer**: Una vez que el parser ha procesado las instrucciones VM, el code writer se encarga de traducirlas a código ensamblador Hack. Cada instrucción VM se traduce a una secuencia de instrucciones Hack que realiza la misma operación.

3. **Main**: Esta es la etapa principal que coordina el proceso de traducción. Lee el archivo VM de entrada, pasa las instrucciones al parser, y luego envía los resultados al code writer para generar el código ensamblador Hack.

### Funcionamiento del Traductor

El traductor recibe como entrada un archivo de código fuente en formato VM, que contiene programas escritos en el lenguaje de máquina virtual de Nand2tetris. El parser se encarga de leer este archivo línea por línea, identificando cada instrucción VM y sus argumentos. Luego, el code writer traduce estas instrucciones a código ensamblador Hack, generando un archivo de salida con extensión .asm que contiene el programa traducido.

### Ejemplo de Uso

Supongamos que tenemos un archivo de código fuente llamado "program.vm" que contiene un programa escrito en el lenguaje de máquina virtual de Nand2tetris. Para traducir este programa a código ensamblador Hack, podemos ejecutar el traductor de la siguiente manera:

```
$ VMTranslator program.vm
```

Esto generará un archivo de salida llamado "program.asm" que contiene el código ensamblador Hack correspondiente al programa VM.

## Importancia del VM Translator

El VM Translator es una herramienta esencial en el proceso de compilación de programas escritos en lenguaje de alto nivel como el VM de Nand2tetris. Permite la traducción de estos programas a código de bajo nivel compatible con la arquitectura Hack, lo que facilita su ejecución en la plataforma Hack. Sin el VM Translator, sería necesario escribir programas directamente en código ensamblador Hack, lo cual sería mucho más laborioso y propenso a errores.


