package com.example.demo2;

public class Edge implements Comparable {
    private Integer from;
    private Integer to;
    private Integer weight;
    public Edge(Integer from, Integer to, Integer weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getTo() {
        return to;
    }

    public Integer getFrom() {
        return from;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    @Override
    public int compareTo(Object o) {
        if (this.weight < ((Edge) o).weight) {
            return -1;
        }
        else if (this.weight > ((Edge) o).weight) {
            return 1;
        }
        else {
            return 0;
        }
    }
}
