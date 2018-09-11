package com.kuri.helper.input;

import java.io.*;

public class InputFileHelper {
    public static void processInputFile(String path, InputFileProcessor ifp){
        try(FileInputStream fis = new FileInputStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis))){

            String strLine;
            while ((strLine = br.readLine()) != null)   {
                ifp.processLine(strLine);
            }

        }catch (FileNotFoundException ffe){
            System.out.println("File not found");
        }catch (IOException e){
            System.out.printf("There was an error reading the file");
        }
    }
}
