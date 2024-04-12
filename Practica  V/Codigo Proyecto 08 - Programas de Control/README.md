# Cómo compilar el código de Java

1. Navegue hasta la carpeta `src_Java`.

2. Abra el terminal en la ruta actual:
   - Haga clic derecho en el directorio que contiene la carpeta `src_Java`.
   - Abra el cmd con "Windows + R, y escriba 'CMD'" para la ventana de comandos o "Escriba cmd en el directorio en donde está `src`".

3. Ejecute los siguientes comandos para compilar los archivos Java necesarios:
    ```
    javac CodeWriter.java
    javac CommandType.java
    javac Parser.java
    javac VirtualMemory.java
    javac VMtranslator.java
    ```
   Es necesario tener Java instalado en su sistema para poder compilar los archivos.

4. Después de compilar, se generarán los archivos `CodeWriter.class`, `CommandType.class`, `Parser.class`, `VirtualMemory.class` y `VMtranslator.class` en la misma carpeta.

# Cómo ejecutar el código de Java

1. El formato del comando es:
   ```
   java VMtranslator directory
   ```
   Donde `directory` es la ruta del directorio que contiene los archivos VM que se desean traducir.

### Ejemplo:

   - Para traducir todos los archivos VM en el directorio `C:\Users\Ipwnkidneyz\Documents\Nueva carpeta\Codigo Proyecto 08 - Programas de control\src_Java`, ejecute:
     ```
     java VMtranslator "C:\Users\Ipwnkidneyz\Documents\Nueva carpeta\Codigo Proyecto 08 - Programas de control\MemoryAccess\StaticsTest"
     ```

     Después de completar la traducción, se creará un archivo `.asm` con el nombre del directorio en el mismo directorio donde se ejecutó el comando (en este caso `StaticsTest.asm`). Además, se imprimirá un mensaje en el terminal indicando la creación del archivo.
---

# Cómo ejecutar el código alternativo (VMTranslator.py)
1. Navegue hasta la carpeta `Codigo Proyecto 08 - Programas de Control/`

   Puede usar el comando cd en la consola de Python, por ejemplo:
   ```
   cd "Practica  V\Codigo Proyecto 08 - Programas de Control"
   ```
   Recuerda tener en cuenta donde te encuentras y a que directorio te diriges
3. El formato del comando para ejecutar el programa es:
   ```
   python3.12 VMTranslator.py directory
   ```
   Donde `directory` es la ruta del directorio que contiene los archivos VM que se desean traducir.

### Ejemplo:
- Para traducir todos los archivos VM en el directorio:
  `U:\UIS\7mo Semestre\Arquitectura de Computadores\Practica V\Codigo Proyecto 08 - Programas de Control\FunctionCalls\FibonacciElement`, se debe ejecutar:

   ```
   cd "U:\UIS\7mo Semestre\Arquitectura de Computadores\Practica V\Codigo Proyecto 08 - Programas de Control"
   python3.12 VMTranslator.py FunctionCalls\FibonacciElement
   ```
Después de completar la traducción, se creará un archivo `.asm` con el nombre del directorio en el mismo directorio donde se ejecutó el comando (en este caso `FibonacciElement.asm`). Además, se imprimirá un mensaje en el terminal indicando la creación del archivo como el siguiente: `Guardado en: FunctionCalls\FibonacciElement\FibonacciElement.asm`
