package com.thealgorithms.javaissues;


public class BreakingContractBoat {
    private String name;

    BreakingContractBoat(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BreakingContractBoat boat = (BreakingContractBoat) o;

        return !(name != null ? !name.equals(boat.name) : boat.name != null);
    }

    @Override
    public int hashCode() {
        return (int) (Math.random() * 5000);
    }
}
