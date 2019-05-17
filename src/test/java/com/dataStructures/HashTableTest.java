package src.test.java.com.dataStructures;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rick
 */
public class HashTableTest {
    
    /**
     * Test of add method, of class HashTable.
     */
    @org.junit.Test
    public void testAdd() {
        System.out.println("add");
        String data = "data";
        String key = "key";
        HashTable<String,String> instance = new HashTable<String,String>();
        instance.add(data, key);
        if(!instance.contains(key))
            fail("failiure in add method.");
    }

    /**
     * Test of delete method, of class HashTable.
     */
    @org.junit.Test
    public void testDelete() {
        System.out.println("delete");
        String data = "data";
        String key = "key";
        HashTable<String,String> instance = new HashTable<String,String>();
        instance.add(data, key);
        instance.delete(key);
        if(instance.contains(key))
            fail("failiure in delete method.");
    }

    /**
     * Test of contains method, of class HashTable.
     */
    @org.junit.Test
    public void testContains() {
        System.out.println("contains");
        HashTable<String,String> instance = new HashTable<>();
        instance.add("object", "key");
        boolean expResult = true;
        boolean result = instance.contains("key");
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("failiure in contains function.");
    }

    /**
     * Test of get method, of class HashTable.
     */
    @org.junit.Test
    public void testGet() {
        System.out.println("get");
        HashTable<String, String> instance = new HashTable();
        instance.add("object","key");
        String expResult = "object";
        String result = instance.get("key");
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("Tfailiure in get function.");
    }
    
}
