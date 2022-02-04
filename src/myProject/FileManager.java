package myProject;

import java.io.*;

public class FileManager {
    private FileReader fileReader;
    private BufferedReader input;
    private FileWriter fileWriter;
    private BufferedWriter output;

    public String lecturaFile() {
        String texto = "";

        try {
            fileReader = new FileReader("src/myProject/files/fileText.txt");
            input = new BufferedReader(fileReader);
            String line = input.readLine();
            while(line!=null){
                texto+=line;
                texto+="\n";
                line=input.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return texto;
    }

    public void escribirTexto(String linea){
        try {
            fileWriter = new FileWriter("src/myProject/files/fileText.txt",true);
            output = new BufferedWriter(fileWriter);
            output.write(linea);
            output.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}