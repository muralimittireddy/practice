import java.io.File; // file is older version to point to given file

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.io.FileWriter;
import java.io.BufferedWriter;


import java.nio.file.Path;

public class FileHandling{
    public static void main(String[] args){
        // System.out.println("hello world");
        // Path file = Path.of("input.txt");
        // System.out.println(file.getFileName());
        // System.out.println(file.toAbsolutePath());

        try{
            // read file
            FileReader fr = new FileReader("input.txt");
            BufferedReader br = new BufferedReader(fr);

            FileWriter fw = new FileWriter("input_1.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            // process
            String line;
            while((line = br.readLine())!=null){
                System.out.println(line);
                bw.write(line);
                bw.newLine();
            }
            //close file
            br.close();
            bw.close();
        }catch(Exception ex){
            System.out.println("Exception : "+ ex.getMessage());
        }

    }
}