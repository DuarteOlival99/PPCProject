package ParalisedForkJoin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class ResutTests {

    public static void main(String[] args) {

       HashMap<String, Integer> listSequencial ;
        HashMap<String, Integer> listParalisedFramework ;

        File fileSequencial3 = new File("C:\\Users\\duart\\OneDrive\\Ambiente de Trabalho\\PPCProject\\src\\Sequencial\\tempo_sequencial_dim3.txt");
        File fileSequencial4 = new File("C:\\Users\\duart\\OneDrive\\Ambiente de Trabalho\\PPCProject\\src\\Sequencial\\tempo_sequencial_dim4.txt");
        File fileSequencial5 = new File("C:\\Users\\duart\\OneDrive\\Ambiente de Trabalho\\PPCProject\\src\\Sequencial\\tempo_sequencial_dim5.txt");

        File fileSuperDuperFramework3 = new File("C:\\Users\\duart\\OneDrive\\Ambiente de Trabalho\\PPCProject\\src\\ParalisedFramework\\tempo_paralised_framework.txt");

        File fileForkJoin3 = new File("C:\\Users\\duart\\OneDrive\\Ambiente de Trabalho\\PPCProject\\src\\ParalisedForkJoin\\tempo_forkJoin_dim3.txt");
        File fileForkJoin4 = new File("C:\\Users\\duart\\OneDrive\\Ambiente de Trabalho\\PPCProject\\src\\ParalisedForkJoin\\tempo_forkJoin_dim4.txt");
        File fileForkJoin5 = new File("C:\\Users\\duart\\OneDrive\\Ambiente de Trabalho\\PPCProject\\src\\ParalisedForkJoin\\tempo_forkJoin_dim5.txt");

        File fileSpecific3 = new File("C:\\Users\\duart\\OneDrive\\Ambiente de Trabalho\\PPCProject\\src\\ParalisedForkJoin\\tempo_forkJoin_Specific_dim3.txt");
        File fileSpecific4 = new File("C:\\Users\\duart\\OneDrive\\Ambiente de Trabalho\\PPCProject\\src\\ParalisedForkJoin\\tempo_forkJoin_Specific_dim4.txt");
        File fileSpecific5 = new File("C:\\Users\\duart\\OneDrive\\Ambiente de Trabalho\\PPCProject\\src\\ParalisedForkJoin\\tempo_forkJoin_Specific_dim5.txt");

        File fileMaxLevel3 = new File("C:\\Users\\duart\\OneDrive\\Ambiente de Trabalho\\PPCProject\\src\\ParalisedForkJoin\\tempo_forkJoin_MaxLevel_dim3.txt");
        File fileMaxLevel4 = new File("C:\\Users\\duart\\OneDrive\\Ambiente de Trabalho\\PPCProject\\src\\ParalisedForkJoin\\tempo_forkJoin_MaxLevel_dim4.txt");
        File fileMaxLevel5 = new File("C:\\Users\\duart\\OneDrive\\Ambiente de Trabalho\\PPCProject\\src\\ParalisedForkJoin\\tempo_forkJoin_MaxLevel_dim5.txt");

        File fileMaxTasks3 = new File("C:\\Users\\duart\\OneDrive\\Ambiente de Trabalho\\PPCProject\\src\\ParalisedForkJoin\\tempo_forkJoin_MaxTasks_dim3.txt");
        File fileMaxTasks4 = new File("C:\\Users\\duart\\OneDrive\\Ambiente de Trabalho\\PPCProject\\src\\ParalisedForkJoin\\tempo_forkJoin_MaxTasks_dim4.txt");
        File fileMaxTasks5 = new File("C:\\Users\\duart\\OneDrive\\Ambiente de Trabalho\\PPCProject\\src\\ParalisedForkJoin\\tempo_forkJoin_MaxTasks_dim5.txt");

        File fileSurplus3 = new File("C:\\Users\\duart\\OneDrive\\Ambiente de Trabalho\\PPCProject\\src\\ParalisedForkJoin\\tempo_forkJoin_Surplus_dim3.txt");
        File fileSurplus4 = new File("C:\\Users\\duart\\OneDrive\\Ambiente de Trabalho\\PPCProject\\src\\ParalisedForkJoin\\tempo_forkJoin_Surplus_dim4.txt");
        File fileSurplus5 = new File("C:\\Users\\duart\\OneDrive\\Ambiente de Trabalho\\PPCProject\\src\\ParalisedForkJoin\\tempo_forkJoin_Surplus_dim5.txt");


        HashMap<String, Integer> listParalisedForkJoin = ReadFileParalisedForkJoin(fileSurplus3);
        System.out.println("fileSurplus3");

        writeToFile(listParalisedForkJoin);

    }

    public static void writeToFile(HashMap<String, Integer> listParalisedForkJoin){
        try {

            FileWriter myWriter = new FileWriter("tempo.txt");

            List<String> listFinal = new ArrayList<>();
            AtomicReference<Integer> maiorNumero = new AtomicReference<>(0);

            List<Integer> valueSequencial = new ArrayList<>();
            List<Integer> valueParalisedFramework =  new ArrayList<>();
            List<Integer> valueParalisedForkJoin =  new ArrayList<>();
            Integer count = 0;

            listParalisedForkJoin.forEach((k, v) -> {

                String[] parts1 = k.split("_");
                //if (3 == Integer.parseInt(parts1[1])) { //so para a dimensao 3
//                    if (v > maiorNumero.get()){
//                        maiorNumero.set(v); // maior numero é = 527404
//                    }
                    //listFinal.add("Sequential;" + v + "\n");
                    //listFinal.add("Paralised Framework;" + listParalisedFramework.get(k) + "\n");
                    //listFinal.add("Paralised ForkJoin;" + listParalisedForkJoin.get(k) + "\n");

                    valueParalisedForkJoin.add(listParalisedForkJoin.get(k));

               // }

            });

            Integer finalValueParalisedForkJoin = 0;
            Integer size = 0;
            for (Integer value : valueParalisedForkJoin){
               // if (size < 466){
                    finalValueParalisedForkJoin += value;
                    size++;
                //}

            }
            System.out.println("size = " + size);
            System.out.println("media de tempo de execucao = " + (finalValueParalisedForkJoin/size)+ " Milesegundos" + "\n");
            listFinal.add("media de tempo de execucao = " + (finalValueParalisedForkJoin/size)+ " Milesegundos" + "\n");


            //System.out.println("maior numero é = " + maiorNumero);

            //Collections.sort(listFinal);

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
        File myObj = new File("C:\\Users\\duart\\OneDrive\\Ambiente de Trabalho\\PPCProject\\src\\tempo_sequencial_dim5.txt");
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
                //System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Collections.sort(list);
        //System.out.println(list);
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
               // System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Collections.sort(list);
       // System.out.println(list);
        return listParalisedFramework;

    }

    public static HashMap<String, Integer>  ReadFileParalisedForkJoin(File myObj) {
        List<Integer> list = new ArrayList<Integer>();
        HashMap<String, Integer> listParalisedForkJoin = new HashMap<String,Integer>();
        Scanner myReader;
        try {
            myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] parts = data.split(" ");
                System.out.println("parts" + Arrays.toString(parts));
                list.add(Integer.valueOf(parts[2]));
                listParalisedForkJoin.put(parts[0],Integer.valueOf(parts[2]));
               // System.out.println(data);
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
