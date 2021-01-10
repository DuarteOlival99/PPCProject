This folder contains the Nemhauser-Ullman algorithm for the UMCOP (unconstrained multi-objective combinatorial optimization problem) problem.

There are several data files with different inputs. Files have the following format:

data/mup_<DIMENSIONS>_<ITEMS>_<CORRELATION>_<RUN>.dat.

DIMENSIONS is the number of dimensions of the problem. Each object has that number of dimensions.
Note that we want to minimize the first dimension and maximize all others.

ITEMS is the number of random objects that can be considered.

CORRELATION varies between -0.8 and 0.8 and represents how much higher dimensions are similar to the first ones. The different values have a large impact on performance. Take this into account.

RUN is just a random seed between 0 and 30, so you have more instances to represent that particular combination.


The goal is to write a version that is faster than this implementation in any input file.