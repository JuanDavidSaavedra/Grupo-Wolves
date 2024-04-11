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

---------------------------------------------------------------------------------------------------------------------------------------------------
### Dentro de la carpeta de "Codigo Proyecto 07 - Aritmética de pila" se explicará con mas detalles el funcionamiento del traductor y con ejemplos.
---------------------------------------------------------------------------------------------------------------------------------------------------

Esto generará un archivo de salida llamado "program.asm" que contiene el código ensamblador Hack correspondiente al programa VM.

## Importancia del VM Translator

El VM Translator es una herramienta esencial en el proceso de compilación de programas escritos en lenguaje de alto nivel como el VM de Nand2tetris. Permite la traducción de estos programas a código de bajo nivel compatible con la arquitectura Hack, lo que facilita su ejecución en la plataforma Hack. Sin el VM Translator, sería necesario escribir programas directamente en código ensamblador Hack, lo cual sería mucho más laborioso y propenso a errores.


## Teniendo en cuenta el marco de las máquinas virtuales. ¿Cuál cree que es el futuro de las máquinas virtuales?

El futuro de las máquinas virtuales (VM) está marcado por una evolución hacia tecnologías más avanzadas y flexibles, como contenedores y orquestación (e.j. Docker y Kubernets respectivamente), aunque las VM seguirán siendo relevantes para trabajos que precisen de aislamiento fuerte y sistemas operativos completos. Creemos que un enfoque continuo en mejorar la eficiencia, el rendimiento y la seguridad de las VM es la clave, así como su integración con arquitecturas de nube híbrida y multi-nube. La automatización y la orquestación serán clave para la gestión escalable de infraestructura, mientras que la computación en el borde (edge computing) también podría ampliar el alcance de las VM en la entrega de servicios en entornos distribuidos.


## ¿Cual es la principal similitud entre un contenedor y una máquina virtual?

La principal similitud entre un contenedor y una máquina virtual es que ambos proporcionan entornos aislados y portátiles para ejecutar aplicaciones. Tanto los contenedores como las máquinas virtuales pueden empaquetar aplicaciones junto con todas sus dependencias en una unidad independiente, lno que facilita su implementación y ejecución en diferentes entornos de manera coherente y consistente.

La diferencia radica en el nivel de abstracción: las máquinas virtuales virtualizan el hardware subyacente, incluyendo el sistema operativo, mientras que los contenedores comparten el mismo kernel del sistema operativo del host, lo que resulta en una mayor eficiencia y menor sobrecarga en comparación con las máquinas virtuales.

## ¿Cual es la ventaja del contenedor respecto a la máquina virtual?

Las principales ventajas son:
* Eficiencia en uso en uso de recursos
* Tiempos de inicio mas rapidos
* Mayor portabilidad
* Facilidad de escalabidad

Mientras las máquinas virtuales deben soportar un sistema operativo por cada aplicación a desplegar en un entorno aislado, los contenedores únicamente emulan desde las librerías en adelante, lo que reduce significativamente el tiempo de despliegue de las aplicaciones al no replicar un sistema operativo completo como si lo hacen las máquinas virtuales.
