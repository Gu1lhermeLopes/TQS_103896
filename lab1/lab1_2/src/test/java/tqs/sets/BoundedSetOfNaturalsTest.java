/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tqs.sets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import tqs.sets.BoundedSetOfNaturals;

/**
 * @author ico0
 */
class BoundedSetOfNaturalsTest {
    private BoundedSetOfNaturals setA;
    private BoundedSetOfNaturals setB;
    private BoundedSetOfNaturals setC;
    private BoundedSetOfNaturals setD;


    @BeforeEach
    public void setUp() {
        setA = new BoundedSetOfNaturals(1);
        setB = BoundedSetOfNaturals.fromArray(new int[]{10, 20, 30, 40, 50, 60});
        setC = BoundedSetOfNaturals.fromArray(new int[]{50, 60});
        setD = new BoundedSetOfNaturals(2);
    }

    @AfterEach
    public void tearDown() {
        setA = setB = setC = null;
    }

    @Test
    public void testAddElement() {

        setA.add(99);
        assertTrue(setA.contains(99), "add: added element not found in set.");
        assertEquals(1, setA.size());

        // exception expected
        //setB.add(11);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> setB.add(11));
        assertEquals("bounded set is full. no more elements allowed.", exception.getMessage());
        //
        assertFalse(setB.contains(11), "add: added element not found in set.");
        assertEquals(6, setB.size(), "add: elements count not as expected.");
    }

    @Test
    public void addNonNatural() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> setA.add(-1));
        assertEquals("Illegal argument: not a natural number", exception.getMessage());
    }

    @Test
    public void addDuplicate() {
        setD.add(1);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> setD.add(1));
        assertEquals("duplicate value: 1", exception.getMessage());
    }

    @Test
    public void testAddFromBadArray() {
        int[] elems = new int[]{10, -20, -30};

        // must fail with exception
        assertThrows(IllegalArgumentException.class, () -> setA.add(elems));
    }


}
