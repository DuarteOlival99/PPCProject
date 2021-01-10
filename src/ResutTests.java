import ParalisedFramework.NemhauserUllman;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ResutTests {

    public static void main(String[] args) {

        HashMap<String, Integer> listSequencial = ReadFileSequencial();
        HashMap<String, Integer> listParalisedFramework = ReadFileParalisedFramework();
        HashMap<String, Integer> listParalisedForkJoin = ReadFileParalisedForkJoin();

        writeToFile(listSequencial, listParalisedFramework, listParalisedForkJoin);

    }

    public static void writeToFile(HashMap<String, Integer> listSequencial, HashMap<String, Integer> listParalisedFramework, HashMap<String, Integer> listParalisedForkJoin){
        try {

            FileWriter myWriter = new FileWriter("tempo.txt");

            List<String> listFinal = new ArrayList<>();

            listSequencial.forEach((k, v) -> {
                listFinal.add(k + " / " + v + " / " + listParalisedFramework.get(k) + " / " + listParalisedForkJoin.get(k) + "\n");
            });

            Collections.sort(listFinal);

            for (String item : listFinal){
                myWriter.write(item);
            }

            myWriter.close();
            //System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static HashMap<String, Integer>  ReadFileSequencial() {
        File myObj = new File("C:\\Users\\duart\\OneDrive\\Ambiente de Trabalho\\PPCProject\\src\\tempo_sequencial.txt");
        List<Integer> list = new ArrayList<Integer>();
        HashMap<String, Integer> listSequencial = new HashMap<String,Integer>();
        Scanner myReader;
        try {
            myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] parts = data.split(" ");
                list.add(Integer.valueOf(parts[2]));
                listSequencial.put(parts[0],Integer.valueOf(parts[2]));
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Collections.sort(list);
        System.out.println(list);
        return listSequencial;

    }

    public static HashMap<String, Integer>  ReadFileParalisedFramework() {
        File myObj = new File("C:\\Users\\duart\\OneDrive\\Ambiente de Trabalho\\PPCProject\\src\\tempo_paralised_framework.txt");
        List<Integer> list = new ArrayList<Integer>();
        HashMap<String, Integer> listParalisedFramework = new HashMap<String,Integer>();
        Scanner myReader;
        try {
            myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] parts = data.split(" ");
                list.add(Integer.valueOf(parts[2]));
                listParalisedFramework.put(parts[0],Integer.valueOf(parts[2]));
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Collections.sort(list);
        System.out.println(list);
        return listParalisedFramework;

    }

    public static HashMap<String, Integer>  ReadFileParalisedForkJoin() {
        File myObj = new File("C:\\Users\\duart\\OneDrive\\Ambiente de Trabalho\\PPCProject\\src\\tempo_paralised_forkjoin.txt");
        List<Integer> list = new ArrayList<Integer>();
        HashMap<String, Integer> listParalisedForkJoin = new HashMap<String,Integer>();
        Scanner myReader;
        try {
            myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] parts = data.split(" ");
                list.add(Integer.valueOf(parts[2]));
                listParalisedForkJoin.put(parts[0],Integer.valueOf(parts[2]));
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Collections.sort(list);
        System.out.println(list);
        return listParalisedForkJoin;

    }

}
