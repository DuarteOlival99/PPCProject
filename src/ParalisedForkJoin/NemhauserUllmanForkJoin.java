package ParalisedForkJoin;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class NemhauserUllmanForkJoin {
	
	public static int NDIM = 3;
	
	public static void main(String[] args) {

		long start = System.nanoTime();

		String fname = args[0];
			int[][] objects = NemhauserUllmanForkJoin.importDataObjects(fname, NDIM);
			List<Solution> paretoFront = NemhauserUllmanForkJoin.computeParetoNH(objects);
			NemhauserUllmanForkJoin.printPareto(paretoFront);

		long end = System.nanoTime();
		System.out.println((end - start)/1000000 + " milisegundos");
		System.out.println((end - start)/1000000000 + " segundos");

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
