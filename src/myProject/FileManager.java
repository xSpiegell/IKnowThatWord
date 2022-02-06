package myProject;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Esta clase funciona para la lectura y escritura en documentos de txt
 * @autor Mayra Alejandra Sanchez - mayra.alejandra.sanchez@correounivalle.edu.co - 202040506
 * @autor Brayan Stiven Sanchez - brayan.sanchez.leon@correounivalle.edu.co - 202043554
 * @version v.1.0.0 date:4/02/2022
 */

public class FileManager {
    public static final String PALABRAS="src/myProject/files/palabras.txt"; //Se asigna la direccion del txt a una variable
    public static final String USUARIOS="src/myProject/files/usuarios.txt"; //Se asigna la direccion del txt a una va
    private FileReader fileReader;
    private BufferedReader input;
    private FileWriter fileWriter;
    private BufferedWriter output;

    /**
     * Sirve para leer el archivo de txt
     * @return frases
     */
    public ArrayList<String> lecturaFile(){
        ArrayList<String> frases = new ArrayList<String>();

        try {
            fileReader=new FileReader(PALABRAS);
            input = new BufferedReader(fileReader);
            String line = input.readLine();
            while(line != null){
                frases.add(line);
                line = input.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return frases;
    }

    /**
     * Sirve para escribir en el archivo
     * @param line
     */
    public void escribirFile(String line){
        try {
            fileWriter = new FileWriter(USUARIOS,true);
            output = new BufferedWriter(fileWriter);
            output.write(line);
            output.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
