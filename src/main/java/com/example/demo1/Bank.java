package com.example.demo1;

import java.util.Objects;

public class Bank {
    static class Task5{

        private final String[] vectors = {"1111", "00", "10101000", "1100", "10101111", "00000001", "11101011", "01011100"};
        private final Integer[] powers = {2, 1, 3, 2, 3, 3, 3, 3};
        private  StringBuilder[] essVar = new StringBuilder[8];
        private  StringBuilder[] dumVar = new StringBuilder[8];

        Task5() {
            initVars();
        }
        private void initVars() {

            for (int ind = 0; ind < 8; ++ind) {
                essVar[ind] = new StringBuilder();
                dumVar[ind] = new StringBuilder();

                String s = vectors[ind];
                int len = powers[ind];

                for (int i = 1; i <= len; ++i) {
                    if (Objects.requireNonNull(Solution.main(s, 0, i)).equals(Solution.main(s, 1, i))){
                        if (!dumVar[ind].isEmpty())
                            dumVar[ind].append(",");
                        dumVar[ind].append(i);
                    }
                    else {
                        if (!essVar[ind].isEmpty())
                            essVar[ind].append(",");
                        essVar[ind].append(i);
                    }

                }
            }
        }

        public int getPowers(int ind) {
            return powers[ind];
        }
        public String getVector(int ind) {
            return vectors[ind];
        }

        public StringBuilder getEssVar(int ind) {
            return essVar[ind];
        }

        public StringBuilder getDumVar(int ind) {
            return dumVar[ind];
        }
    }

    static class Task11{


    }
}
