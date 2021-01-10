package ParalisedForkJoin;

import ParalisedFramework.NemhauserUllman;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class NemhauserUllmanForkJoin {
	
	public static int NDIM = 3;
	
	public static void main(String[] args) {

		NemhauserUllmanForkJoin.start();

//		long start = System.nanoTime();
//
//		String fname = args[0];
//			int[][] objects = NemhauserUllmanForkJoin.importDataObjects(fname, NDIM);
//			List<Solution> paretoFront = NemhauserUllmanForkJoin.computeParetoNH(objects);
//			NemhauserUllmanForkJoin.printPareto(paretoFront);
//
//		long end = System.nanoTime();
//		System.out.println((end - start)/1000000 + " milisegundos");
//		System.out.println((end - start)/1000000000 + " segundos");

	}

	public static void start(){
		writeToFile();
	}

	public static List<String> getAllFiles(){
		List<String> results = new ArrayList<String>();

		File[] files = new File("data/").listFiles();
		//If this pathname does not denote a directory, then listFiles() returns null.

		for (File file : files) {
			if (file.isFile()) {
				results.add(file.getName());
			}
		}
		return results;
	}

	public static void ReadFile() {
		File myObj = new File("C:\\Users\\duart\\OneDrive\\Ambiente de Trabalho\\PPCProject\\src\\com\\company\\tempo.txt");
		List<Integer> list = new ArrayList<Integer>();
		Scanner myReader;
		try {
			myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String[] parts = data.split(" ");
				list.add(Integer.valueOf(parts[2]));
				System.out.println(data);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Collections.sort(list);
		System.out.println(list);

	}


	public static void writeToFile(){
		try {
			List<String> allFIles = getAllFiles();

			FileWriter myWriter = new FileWriter("tempo.txt");
			for (String s : allFIles){
				String file = "data/" + s;
				long start = System.nanoTime();
				int[][] objects = NemhauserUllman.importDataObjects(file, NDIM);
				List<ParalisedFramework.Solution> paretoFront = NemhauserUllman.computeParetoNH(objects);
				NemhauserUllman.printPareto(paretoFront);

				long end = System.nanoTime();
				long time = (end - start)/1000000;

				System.out.println("time of file -> " + s + " is " + time + " milisegundos");

				myWriter.write(file + " -> "+ time + " milisegundos\n");
			}
			myWriter.close();
			//System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	
	public static int[][] importDataObjects(String fileName, int dim) {
        String line = null;
        int[][] arr = null;
        int size = 0;
        int i = 0;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
            	if (size == 0) {
            		size = Integer.parseInt(line.trim());
            		arr = new int[size][dim];
            	} else {
            		int[] o = new int[dim];
            		String[] parts = line.trim().replaceAll(" +", " ").split(" ");
            		for (int j=0; j<dim; j++) {
            			o[j] = Integer.parseInt(parts[1+j].trim());
            		}
            		arr[i++] = o;
            		if (i == size) break;
            	}
            }
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
		return arr;
	}
	
	public static void printPareto(List<Solution> paretoFront) {
		System.out.println("P:" + paretoFront.size());
		/*for(Solution sol: paretoFront) {
			System.out.print("sol: ");
			for (int i=0; i< NDIM; i++) {
				System.out.print(sol.getValue(i) + " , ");
			}
			System.out.println();
		}*/
	}
	
	public static List<Solution> computeParetoNH(int[][] objects) {
		List<Solution> workingSolutions = new ArrayList<Solution>();
		workingSolutions.add(new Solution(new boolean[objects.length]));
		for (int oid=0; oid <objects.length; oid++) {
			List<Solution> newWorkingSolutions = new ArrayList<Solution>();
			for (Solution sol : workingSolutions) {
				Solution s2 = sol.clone();
				s2.enable(oid, objects[oid]);
				newWorkingSolutions.add(s2);
			}
			workingSolutions.addAll(newWorkingSolutions);
			
			workingSolutions = filterNonDominated(workingSolutions);
			//System.out.println("ongoing:" + workingSolutions.size());
		}
		return workingSolutions;
	}
	
	
	
	private static List<Solution> filterNonDominated(List<Solution> workingSolutions) {
		long start = System.nanoTime();

		List<Solution> filtered = new ArrayList<>();

		ForkJoinNemhauserUllman NU = new ForkJoinNemhauserUllman(workingSolutions, workingSolutions);
		NU.fork();
		filtered = NU.join();

		long end = System.nanoTime();
		System.out.println("P= "+ filtered.size() +" Tempo: " + (end - start)/1000000 + " milisegundos");

		return filtered;
	}
}
