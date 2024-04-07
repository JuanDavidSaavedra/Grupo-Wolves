import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 *
 * @author Ipwnkidneyz
 */
public class CodeWriter {

    private int arthJumpFlag;
    private PrintWriter outPrinter;

    /**
     * Abre un archivo de salida y está listo para escribir contenido
     * @param fileOut ¡Puede ser un directorio!
     */
    public CodeWriter(File fileOut) {

        try {

            outPrinter = new PrintWriter(fileOut);
            arthJumpFlag = 0;

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        }

    }

    /**
    * Si el argumento del programa es un nombre de directorio en lugar de un nombre de archivo,
    * el programa principal debería procesar todos los archivos .vm en este directorio.
    * Al hacerlo, debería usar un Parser separado para manejar cada archivo de entrada y un solo CodeWriter para manejar la salida.
    *
    * Informa al CodeWriter que se ha iniciado la traducción de un nuevo archivo VM
    */

    public void setFileName(File fileOut){

    }

    /**
     * Escribe el código ensamblador que es la traducción del comando aritmético dado
     * @param command
     */
    public void writeArithmetic(String command){

        if (command.equals("add")){

            outPrinter.print(arithmeticTemplate1() + "M=M+D\n");

        }else if (command.equals("sub")){

            outPrinter.print(arithmeticTemplate1() + "M=M-D\n");

        }else if (command.equals("and")){

            outPrinter.print(arithmeticTemplate1() + "M=M&D\n");

        }else if (command.equals("or")){

            outPrinter.print(arithmeticTemplate1() + "M=M|D\n");

        }else if (command.equals("gt")){

            outPrinter.print(arithmeticTemplate2("JLE"));//no <=
            arthJumpFlag++;

        }else if (command.equals("lt")){

            outPrinter.print(arithmeticTemplate2("JGE"));//no >=
            arthJumpFlag++;

        }else if (command.equals("eq")){

            outPrinter.print(arithmeticTemplate2("JNE"));//no <>
            arthJumpFlag++;

        }else if (command.equals("not")){

            outPrinter.print("@SP\nA=M-1\nM=!M\n");

        }else if (command.equals("neg")){

            outPrinter.print("D=0\n@SP\nA=M-1\nM=D-M\n");

        }else {

            throw new IllegalArgumentException("Llamar a writeArithmetic() para un comando no aritmético");

        }

    }

    /**
     * Escribe el código ensamblador que es la traducción del comando dado
     * donde el comando es PUSH o POP
     * @param command PUSH o POP
     * @param segment
     * @param index
     */
    public void writePushPop(int command, String segment, int index){

        if (command == Parser.PUSH){

            if (segment.equals("constant")){

                outPrinter.print("@" + index + "\n" + "D=A\n@SP\nA=M\nM=D\n@SP\nM=M+1\n");

            }else if (segment.equals("local")){

                outPrinter.print(pushTemplate1("LCL",index,false));

            }else if (segment.equals("argument")){

                outPrinter.print(pushTemplate1("ARG",index,false));

            }else if (segment.equals("this")){

                outPrinter.print(pushTemplate1("THIS",index,false));

            }else if (segment.equals("that")){

                outPrinter.print(pushTemplate1("THAT",index,false));

            }else if (segment.equals("temp")){

                outPrinter.print(pushTemplate1("R5", index + 5,false));

            }else if (segment.equals("pointer") && index == 0){

                outPrinter.print(pushTemplate1("THIS",index,true));

            }else if (segment.equals("pointer") && index == 1){

                outPrinter.print(pushTemplate1("THAT",index,true));

            }else if (segment.equals("static")){

                outPrinter.print(pushTemplate1(String.valueOf(16 + index),index,true));

            }

        }else if(command == Parser.POP){

            if (segment.equals("local")){

                outPrinter.print(popTemplate1("LCL",index,false));

            }else if (segment.equals("argument")){

                outPrinter.print(popTemplate1("ARG",index,false));

            }else if (segment.equals("this")){

                outPrinter.print(popTemplate1("THIS",index,false));

            }else if (segment.equals("that")){

                outPrinter.print(popTemplate1("THAT",index,false));

            }else if (segment.equals("temp")){

                outPrinter.print(popTemplate1("R5", index + 5,false));

            }else if (segment.equals("pointer") && index == 0){

                outPrinter.print(popTemplate1("THIS",index,true));

            }else if (segment.equals("pointer") && index == 1){

                outPrinter.print(popTemplate1("THAT",index,true));

            }else if (segment.equals("static")){

                outPrinter.print(popTemplate1(String.valueOf(16 + index),index,true));

            }

        }else {

            throw new IllegalArgumentException("Llamar a writePushPop() para un comando no pushpop");

        }

    }

    /**
     * Cierra el archivo de salida
     */
    public void close(){

        outPrinter.close();

    }

    /**
     * Plantilla para add, sub y or
     * @return
     */
    private String arithmeticTemplate1(){

        return "@SP\n" +
                "AM=M-1\n" +
                "D=M\n" +
                "A=A-1\n";

    }

    /**
     * Plantilla para gt, lt, eq
     * @param type JLE JGT JEQ
     * @return
     */
    private String arithmeticTemplate2(String type){

        return "@SP\n" +
                "AM=M-1\n" +
                "D=M\n" +
                "A=A-1\n" +
                "D=M-D\n" +
                "@FALSE" + arthJumpFlag + "\n" +
                "D;" + type + "\n" +
                "@SP\n" +
                "A=M-1\n" +
                "M=-1\n" +
                "@CONTINUE" + arthJumpFlag + "\n" +
                "0;JMP\n" +
                "(FALSE" + arthJumpFlag + ")\n" +
                "@SP\n" +
                "A=M-1\n" +
                "M=0\n" +
                "(CONTINUE" + arthJumpFlag + ")\n";

    }


    /**
     * Plantilla para push local,this,that,argument,temp,pointer,static
     * @param segment
     * @param index
     * @param isDirect ¿Es este comando un direccionamiento directo?
     * @return
     */
    private String pushTemplate1(String segment, int index, boolean isDirect){

        //Cuando es un puntero, simplemente lee los datos almacenados en THIS o THAT
        //Cuando es estático, simplemente lee los datos almacenados en esa dirección
        String noPointerCode = (isDirect)? "" : "@" + index + "\n" + "A=D+A\nD=M\n";

        return "@" + segment + "\n" +
                "D=M\n"+
                noPointerCode +
                "@SP\n" +
                "A=M\n" +
                "M=D\n" +
                "@SP\n" +
                "M=M+1\n";

    }

    /**
     * Plantilla para pop local,this,that,argument,temp,pointer,static
     * @param segment
     * @param index
     * @param isDirect ¿Es este comando un direccionamiento directo?
     * @return
     */
    private String popTemplate1(String segment, int index, boolean isDirect){

        //Cuando es un puntero, R13 almacenará la dirección de THIS o THAT
        //Cuando es estático, R13 almacenará la dirección de índice
        String noPointerCode = (isDirect)? "D=A\n" : "D=M\n@" + index + "\nD=D+A\n";

        return "@" + segment + "\n" +
                noPointerCode +
                "@R13\n" +
                "M=D\n" +
                "@SP\n" +
                "AM=M-1\n" +
                "D=M\n" +
                "@R13\n" +
                "A=M\n" +
                "M=D\n";

    }

}
