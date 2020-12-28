package com.company;

public class DoInParallelSuperDuperFramework {
	public static void doInParallel(ParallelizableWork pw, int nIterations) {
		int defaultNThreads = Runtime.getRuntime().availableProcessors();
		doInParallel(pw, nIterations, defaultNThreads);
	}

	public static void doInParallel(ParallelizableWork pw, int nIterations, int nthreads) {
		Thread[] threads = new Thread[nthreads];
		for (int ti = 0; ti < nthreads; ti++) {
			final int tid = ti;
			threads[ti] = new Thread( () -> {
				int start_i = tid * nIterations / nthreads;
				int end_i = (tid+1) * nIterations / nthreads;
				if (tid == nthreads-1) {
					end_i = nIterations;
				}
				pw.doIteration(start_i, end_i);
			});
			threads[ti].start();
		}
		for (Thread t : threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				System.out.println("Thread " + t + " was interrupted");
			}
		}
	}

}