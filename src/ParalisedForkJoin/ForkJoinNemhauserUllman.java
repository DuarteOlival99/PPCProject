package ParalisedForkJoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class ForkJoinNemhauserUllman extends RecursiveTask<List<Solution>> {
    private static final long serialVersionUID = 1L;
    private List<Solution> listSolutions;
    private List<Solution> workingSolutions;

    public ForkJoinNemhauserUllman(List<Solution> listSolutions, List<Solution> workingSolutions) {
        this.listSolutions = listSolutions;
        this.workingSolutions = workingSolutions;
    }


    @Override
    protected List<Solution> compute() {
        if (listSolutions.size() == 1) {
            List<Solution> filtered = new ArrayList<>();
            boolean nonDominated = true;
            for (Solution sol2 : workingSolutions) {
                if (listSolutions.get(0).isDominatedBy(sol2)) {
                    nonDominated = false;
                    break;
                }
            }
            if (nonDominated) {
                filtered.add(listSolutions.get(0));
            }
            return filtered;
        }

     //   if (listSolutions.size() < 900) return filterNonDominatedSequencial(listSolutions, workingSolutions);

       //dividir a listSolutions ao meio

        List<Solution> left = listSolutions.subList(0, listSolutions.size() / 2); //divide a primeira metade
        List<Solution> right = listSolutions.subList(listSolutions.size() / 2, listSolutions.size()); //divide a segunda metade

        ForkJoinNemhauserUllman f1 = new ForkJoinNemhauserUllman(left, workingSolutions);
        f1.fork();
        ForkJoinNemhauserUllman f2 = new ForkJoinNemhauserUllman(right, workingSolutions);
        //f2.fork();

        List<Solution> listRight = f2.compute();
        List<Solution> listLeft = f1.join();

        listLeft.addAll(listRight);
        return listLeft;
    }

//    public static List<Solution> filterNonDominatedSequencial(List<Solution> workingSolutions, List<Solution> workingSolutions2) {
//        List<Solution> filtered = new ArrayList<>();
//        for (Solution sol : workingSolutions) {
//            boolean nonDominated = true;
//            for (Solution sol2 : workingSolutions2) {
//                if (sol.isDominatedBy(sol2)) {
//                    nonDominated = false;
//                    break;
//                }
//            }
//            if (nonDominated) {
//                filtered.add(sol);
//            }
//        }
//
//        return filtered;
//    }

}