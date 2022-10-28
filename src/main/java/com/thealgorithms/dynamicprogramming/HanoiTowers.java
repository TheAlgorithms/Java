import java.util.EnumMap;
import java.util.LinkedList;
import java.util.Queue;
 
public class HanoiTowers {
 
    enum Peg {A, B, C}
 
    private final EnumMap<Peg, Queue<Integer>> pegs
            = new EnumMap<>(Peg.class);
 
    { // Use lists as Stacks:
        pegs.put(Peg.A, asLifoQueue(new LinkedList<>()));
        pegs.put(Peg.B, asLifoQueue(new LinkedList<>()));
        pegs.put(Peg.C, asLifoQueue(new LinkedList<>()));
    }
 
    public HanoiTowers(int n) {
        if (n < 0) {
            throw new IllegalArgumentException(
                    "Pegs must not be negative. Was: " + n);
        }
        while (n > 0) {
            // Peg A always starts with all the disks:
            this.pegs.get(Peg.A).add(n--);
        }
    }
 
    // return content of selected peg
    public Integer[] peg(Peg peg) {
        Queue<Integer> p = pegs.get(peg);
        return p.toArray(new Integer[p.size()]);
    }
 
    public void solve(Peg from, Peg to) {
        solve(pegs.get(from).size(), from, to);
    }
 
    private void solve(int n, Peg from, Peg to) {
        if (n >= 1) {
            Peg spare = sparePeg(from, to);
            solve(n-1, from, spare);
            move(from, to);
            solve(n-1, spare, to);
        }
    }
 
    private Peg sparePeg(Peg from, Peg to) {
        Peg spare = null;
        switch (from) {
            case A: spare = notSelected(to, B, C); break;
            case B: spare = notSelected(to, A, C); break;
            case C: spare = notSelected(to, A, B); break;
        }
        return spare;
    }
 
    private Peg notSelected(Peg given, Peg a, Peg b) {
        return (given == a) ? b : a;
    }
 
    private void move(Peg from, Peg to) {
        pegs.get(to).add(pegs.get(from).remove());
    }
 
    @Override
    public String toString() {
        // helpful for debugging:
        return pegs.toString();
    }
}
