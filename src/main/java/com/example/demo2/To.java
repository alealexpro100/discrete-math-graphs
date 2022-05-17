package com.example.demo2;

public class To implements Comparable {
    private Integer to;
    private Integer weight;
    public To(Integer to, Integer weight) {
        this.to = to;
        this.weight = weight;
    }

    public Integer getTo() {
        return to;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(Object o) {
        if (this.weight < ((To)o).getWeight()) {
            return -1;
        }
        else if (this.weight > ((To)o).getWeight()) {
            return 1;
        }
        else {
            return 0;
        }
    }
}
