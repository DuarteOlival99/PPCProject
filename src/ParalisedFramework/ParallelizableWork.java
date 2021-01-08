package ParalisedFramework;

@FunctionalInterface
public interface ParallelizableWork {
	void doIteration(int start, int end);
}
