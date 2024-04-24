# Descripción de la practica 06 - Proyecto 9: Programación de alto nivel 
El objetivo de la practica 6 y por ende el proyecto 9 de Nand2Tetris es el de adoptar o inventar una aplicación sencilla de un juego de computadora o cualquier programa interactivo; 
algunos ejemplos incluyen Tetris, Snake, Hangman, Space Invaders, Sokoban, Pong, etc. El objetivo era el de implementar ese software en Jack. Sin embargo, esta aplicación no tenia que 
estar completa, podía ser un prototipo con algunas funcionalidades básicas e interactivas.

En nuestro caso se decidió adaptar una version inicial del clasico juego **"Snake"**:

# Implementación
La implementación de Snake se dividió en 6 clases, donde algunas tenian mas pesos que otras, empezemos por las "menos" importantes:
1. **Random.jack**:

   Es la clase encargada de manipular y generar números aleatorios por medio de métodos como `seteed()`, `rand()`, `randRange()` entre otros. Cabe recalcar que estos números aleatorios están acotados en un rango especifico para permitir el correcto funcionamiento del software y que un ejemplo de su uso es a la hora de ubicar la comida que debe ingerir la serpiente.
     
2. **RandSeed.jack**:

   Es la clase encargada de generar la semilla aleatoria para esa partida especifica, de modo que la comida siempre se distribuya de manera diferente a lo largo de las múltiples partidas que va a jugar el usuario. Además, también es la encargada de mostrarle el mensaje de "press a key to start" el cual es el evento disparador que da inicio a la partida.
     
3. **Snake.jack**:

    Esta clase modela la serpiente del juego. Tiene campos que almacenan la *posición* de su cabeza, su *longitud*, la *dirección* en la que se mueve y su *historial* de movimientos. El constructor crea una nueva serpiente en la posición especificada, y sus métodos permiten **moverla**, hacerla **crecer**, verificar si se estrello con algo (**Game Over**) y dibujarla en la pantalla. Además, maneja la lógica para reescribir su historial y recordar su última dirección (en caso de que esta no cambie). En resúmen, La clase Snake controla el movimiento y crecimiento de la serpiente, asegurándose de que se comporte correctamente dentro del juego y se dibuje adecuadamente en la pantalla.
   
4. **SnakeGame.jack**:
  
    Esta clase maneja el juego en sí. Contiene la *serpiente*, el *grid del juego*, el *puntaje*, el *nivel*, y el *ciclo del juego*. El constructor inicializa las variables del juego, crea el grid y la serpiente, y coloca la comida en el grid. Sus métodos principales son `run()`, que controla la lógica del juego como mover la serpiente, incrementar el puntaje, y manejar el cambio de nivel, y `dispose()`, que libera los recursos al finalizar el juego. Además, la clase maneja la pausa del juego, la entrada del usuario para controlar la serpiente, y la lógica para avanzar de nivel. En resumen, SnakeGame controla la ejecución del juego, manejando la interacción entre la serpiente, el grid y el usuario para proporcionar una experiencia de juego continua y entretenida.
   
5. **SnakeGrid.jack**:
  
   Esta clase modela el *grid* del juego. Define el *tamaño* del grid, la *posición de la comida*, y un array bidimensional que representa la *ubicación de la serpiente*. En el constructor, se inicializan las variables del grid y se crea el array. Sus métodos principales son `placeFood()` para colocar la comida en una posición aleatoria, `drawFood()` para dibujar la comida en la pantalla, y `drawStatus()` para mostrar información sobre el nivel y el puntaje en la pantalla del juego. Además, maneja la lógica para marcar las posiciones ocupadas por la serpiente y limpiarlas cuando la serpiente se mueve. En resumen, SnakeGrid controla la representación y la interacción del grid del juego, asegurando que la comida se coloque correctamente y que la serpiente se dibuje y se mueva adecuadamente en la pantalla del juego.

6. **Main.jack**:

    Esta clase es la entrada principal del programa. En su método `main()`, crea una instancia de SnakeGame, lo ejecuta con `run()`, y luego, cuando finalice la partida,  libera los recursos con `dispose()`.

## Traducción de `.jack` a `.vm` por medio del `JackCompiler`
Siguiendo lo planteado por el proyecto 9 de Nand2tetris, el objetivo era diseñar e implementar un software interactivo siguiendo el paradigma de programación orientado a objetos (POO) en el lenguaje Jack. Luego, al tener las clases listas, estas se debían compilar por medio del `JackCompiler` que venia incluido en la suite de [Nand2tetris](https://drive.google.com/file/d/1xZzcMIUETv3u3sdpM_oTJSTetpVee3KZ/view), para hacer esto de manera exitosa se recomienda seguir la siguiente metodología:
1. Descargar y descomprimir la suite en la ruta que considere adecuada
2. Luego acceder a la carpeta **tools**:
   
   ```
   rutaInstalación\nand2tetris\nand2tetris\tools
   ```
3. En el caso de tener multiples clases como con nuestra implementación de Snake, se recomienda agruparlas en una carpeta y ubicarla en la misma carpeta tools:

    ![image](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/128198245/d7e68ce7-2a38-4ebb-bf69-5bdd597cbff8)

4. Luego, se debe abrir la consola y ubicarse en la carpeta por medio del comando `cd`:
   ```
   cd rutaInstalación\nand2tetris\nand2tetris\tools
   ```
    En nuestro caso:

    ![image](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/128198245/9df9d1b3-2815-4b2b-9911-10f40eeb76cf)

5. Finalmente se realiza la traducción de los archivos.jack a .vm de con el siguiente comando:
   ```
   JackCompiler nombreCarpetaACompilar
   ```
    En nuestro caso:

    ![Captura de pantalla 2024-04-23 100340](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/128198245/78f5e57a-8247-4f2e-b1ce-94db012953c7)

6. Verificar que no hayan habido errores al traducir los archivos:
7. En caso de que todo haya salido bien, por medio del **VMEmulator** en la misma carpeta, se puede ejecutar el software:

   ![image](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/128198245/1b3812da-bafb-4742-a969-478ac7367e23)

**Nota**: Se recomienda durante la ejecución del programa, quitar las animaciones (No Animation) en el VMEmulator.

## Limitaciones
Las limitaciones asociadas a la implementación del prototipo del clásico juego Snake, debido a restricciones de tiempo, pueden enunciarse de la siguiente manera:
- **Dificultad constante**: Con el paso de los niveles la dificultad se mantiene constante (ignorando la lonigtud creciente de la serpiente), se deseaba implementar una funcionalidad donde la comida se fuera ubicando mas cerca de los bordes con el paso de los niveles.
- **Grid Constante**: Al igual que con la dificultad, el grid no cambia con el paso de los niveles, sin embargo en implementaciones mas avanzadas de este juego se podrían implementar cambios en el grid (obstaculos) por cada 10 niveles que se avance.
- **Menú de inicio inexistente**: A la hora de iniciar una partida, no existe ningun menú lo mas cercano es el mensaje de "presione una tecla para iniciar", pero sería agradable en siguientes versiones, que se muestre un menú con los mejores 5 puntajes que se hayan logrado (almacenados de manera local).
- **Nueva partida**: Como no existe un menú de inicio, si se da un **game Over** la unica forma de iniciar una nueva partida es reiniciando la ejecución del software desde el VMEmulator; sería agradable que al mostrarse la alerta de Game Over el usuario pudiera decidir si iniciar una nueva partida en el instante, o dirigirse al menú de inicio para ver los mejores puntajes, personalizar la paleta de colores (invertirla), etc.
  
---
# Preguntas Complementarias
## Desarrolle más el concepto de lenguaje de alto nivel, teniendo en cuenta la diferencia entre lenguajes de programación propiamente dichos e interpretadores.
Un lenguaje de alto nivel es aquel diseñado para ser más comprensible para los humanos, en contraste con los lenguajes de bajo nivel que están más cerca del lenguaje de la máquina. Estos lenguajes de alto nivel utilizan una sintaxis y estructuras más cercanas al lenguaje natural, lo que facilita a los programadores expresar sus ideas de manera más clara y concisa.

Los lenguajes de alto nivel están diseñados para ser independientes de la máquina, lo que significa que son menos dependientes de la arquitectura del hardware subyacente. Esto permite que los programas escritos en un lenguaje de alto nivel sean más portables, es decir, pueden ejecutarse en diferentes tipos de computadoras con poca o ninguna modificación.

## ¿Qué lenguajes interpretadores ademas del Python existen?
### Lenguajes de Programación: 
Son sistemas formales que estan diseñados para expresar procesos de computación de manera clara y concisa. Ejemplos incluyen Python, Java, C++, entre muchos otros. Estos lenguajes requieren un compilador o un intérprete para ser ejecutados. Los compiladores traducen el código fuente a un lenguaje de bajo nivel (como el lenguaje de máquina) antes de la ejecución, mientras que los intérpretes traducen y ejecutan el código línea por línea en tiempo real.
![image](https://github.com/JuanDavidSaavedra/WolfPack-Devs/assets/159449419/189b9c7e-1ef0-40db-b2ec-55ca644c0fa1)

### Intérpretes: 
Estos son programas informáticos que leen y ejecutan instrucciones escritas en un lenguaje de programación de alto nivel. Actúan como una capa intermedia entre el código fuente y la máquina, interpretando las instrucciones y ejecutándolas directamente en el sistema operativo o en una máquina virtual. Los intérpretes son útiles para la depuración y la experimentación rápida, ya que permiten ejecutar y probar el código de inmediato sin necesidad de compilar.
C, C++ y Go son lenguajes de programación compilados. JavaScript, Python y Ruby son lenguajes interpretados.

La distinción principal entre un lenguaje compilado y uno interpretado radica en el proceso necesario antes de la ejecución. En un lenguaje compilado, como C o Java, se requiere un paso previo denominado compilación. Durante este proceso, el código escrito se traduce completamente a lenguaje de máquina, que es el código entendido directamente por la computadora. Por otro lado, en un lenguaje interpretado, como Python o JavaScript, el código se traduce a medida que se ejecuta, sin necesidad de una compilación anticipada.

Por ejemplo, en el caso del lenguaje C, es necesario compilar el código antes de ejecutarlo. En cambio, con Python, el código puede ejecutarse directamente sin necesidad de una compilación previa.


---

### Ejemplo de lenguaje interpretado: 

Ruby es un lenguaje interpretado, lo que significa que se puede crear un archivo llamado `hello.rb` con el siguiente código, o cualquier otro código válido en Ruby (a esto se le conoce como el código fuente):

```ruby
puts "Hola Mundo"
```

Para ejecutarlo, simplemente ingresa el siguiente comando en la consola:

```bash
$ ruby hello.rb
```

Debería aparecer la cadena `"Hola Mundo"` en la salida de la consola. Si lo deseas, puedes modificar el código fuente y volver a ejecutarlo.

---

# Referencias:
- El readme fue realizado siguiendo lo propuesto en [Docs.github.com](https://docs.github.com/es/get-started/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax#links).
- Guia Implementación proyecto 9: [Grupo Aval - Github](https://github.com/JuanSepu18/Grupo-Aval/tree/main/Practica_6)
