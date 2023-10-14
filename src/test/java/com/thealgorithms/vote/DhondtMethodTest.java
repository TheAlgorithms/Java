package com.thealgorithms.vote;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class DhondtMethodTest {

    @Test
    public void tenSeatsForThreeParties() {

        DhondtMethod.PoliticalParty a = new DhondtMethod.PoliticalParty(46600);
        DhondtMethod.PoliticalParty b = new DhondtMethod.PoliticalParty(18300);
        DhondtMethod.PoliticalParty c = new DhondtMethod.PoliticalParty(35100);

        //Will return the number of seats gained by each party
        DhondtMethod.apply(10, a,b,c);
        assertEquals(5, a.gainedSeatsNumber);
        assertEquals(2, b.gainedSeatsNumber);
        assertEquals(3, c.gainedSeatsNumber);
    }

    @Test
    public void thirteenSeatsForThirteenParties() {

        DhondtMethod.PoliticalParty a = new DhondtMethod.PoliticalParty(46550);
        DhondtMethod.PoliticalParty b = new DhondtMethod.PoliticalParty(23839);
        DhondtMethod.PoliticalParty c = new DhondtMethod.PoliticalParty(57000);
        DhondtMethod.PoliticalParty d = new DhondtMethod.PoliticalParty(13272);
        DhondtMethod.PoliticalParty e = new DhondtMethod.PoliticalParty(13407);
        DhondtMethod.PoliticalParty f = new DhondtMethod.PoliticalParty(61016);
        DhondtMethod.PoliticalParty g = new DhondtMethod.PoliticalParty(5837);
        DhondtMethod.PoliticalParty h = new DhondtMethod.PoliticalParty(94531);
        DhondtMethod.PoliticalParty i = new DhondtMethod.PoliticalParty(5393);
        DhondtMethod.PoliticalParty j = new DhondtMethod.PoliticalParty(2070);
        DhondtMethod.PoliticalParty k = new DhondtMethod.PoliticalParty(3220);
        DhondtMethod.PoliticalParty l = new DhondtMethod.PoliticalParty(3565);
        DhondtMethod.PoliticalParty m = new DhondtMethod.PoliticalParty(2784);

        //Will return the number of seats gained by each party
        DhondtMethod.apply(13, a,b,c,d,e,f,g,h,i,j,k,l,m);
        assertEquals(2, a.gainedSeatsNumber);
        assertEquals(1, b.gainedSeatsNumber);
        assertEquals(3, c.gainedSeatsNumber);
        assertEquals(0, d.gainedSeatsNumber);
        assertEquals(0, e.gainedSeatsNumber);
        assertEquals(3, f.gainedSeatsNumber);
        assertEquals(0, g.gainedSeatsNumber);
        assertEquals(4, h.gainedSeatsNumber);
        assertEquals(0, i.gainedSeatsNumber);
        assertEquals(0, j.gainedSeatsNumber);
        assertEquals(0, k.gainedSeatsNumber);
        assertEquals(0, l.gainedSeatsNumber);
    }
}
