import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

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
            AtomicReference<Integer> maiorNumero = new AtomicReference<>(0);

            List<Integer> valueSequencial = new ArrayList<>();
            List<Integer> valueParalisedFramework =  new ArrayList<>();
            List<Integer> valueParalisedForkJoin =  new ArrayList<>();
            Integer count = 0;
//
//            listParalisedForkJoin.forEach((k, v) -> {
//                String[] parts1 = k.split("_");
//                if (3 == Integer.parseInt(parts1[1])) { //so para a dimensao 3
//                    if (v > maiorNumero.get()){
//                        maiorNumero.set(v); // maior numero é = 85890
//                    }
//                    listFinal.add("Paralised ForkJoin;" + v + "\n");
//                }
//            });

//            listParalisedFramework.forEach((k, v) -> {
//                String[] parts1 = k.split("_");
//                if (3 == Integer.parseInt(parts1[1])) { //so para a dimensao 3
//                    if (v > maiorNumero.get()){
//                        maiorNumero.set(v); // maior numero é = 110375
//                    }
//                    listFinal.add("Paralised Framework;" + v + "\n");
//                }
//            });

            listSequencial.forEach((k, v) -> {

                String[] parts1 = k.split("_");
                if (3 == Integer.parseInt(parts1[1])) { //so para a dimensao 3
//                    if (v > maiorNumero.get()){
//                        maiorNumero.set(v); // maior numero é = 527404
//                    }
                    //listFinal.add("Sequential;" + v + "\n");
                    //listFinal.add("Paralised Framework;" + listParalisedFramework.get(k) + "\n");
                    //listFinal.add("Paralised ForkJoin;" + listParalisedForkJoin.get(k) + "\n");

                    valueSequencial.add(v);
                    valueParalisedFramework.add(listParalisedFramework.get(k));
                    valueParalisedForkJoin.add(listParalisedForkJoin.get(k));

                }

                //listFinal.add(k + " / " + v + " / " + listParalisedFramework.get(k) + " / " + listParalisedForkJoin.get(k) + "\n");

//                if (v < listParalisedFramework.get(k) && v < listParalisedForkJoin.get(k)){
//                    listFinal.add(k + " / " + v + " / " + listParalisedFramework.get(k) + " / " + listParalisedForkJoin.get(k) + "\n");
//                }
            });

            Integer finalValueSequencial = 0;
            for (Integer value : valueSequencial){
                finalValueSequencial += value;
            }
            listFinal.add("media de tempo de execucao para a versao Sequencial = " + (finalValueSequencial/valueSequencial.size())+ " Milesegundos" + "\n");

            Integer finalValueParalisedFramework = 0;
            for (Integer value : valueParalisedFramework){
                finalValueParalisedFramework += value;
            }
            listFinal.add("media de tempo de execucao para a versao paralisada framework = " + (finalValueParalisedFramework/valueParalisedFramework.size())+ " Milesegundos" + "\n");

            Integer finalValueParalisedForkJoin = 0;
            for (Integer value : valueParalisedForkJoin){
                finalValueParalisedForkJoin += value;
            }
            listFinal.add("media de tempo de execucao para a versao paralisada Fork Join = " + (finalValueParalisedForkJoin/valueParalisedForkJoin.size())+ " Milesegundos" + "\n");


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
