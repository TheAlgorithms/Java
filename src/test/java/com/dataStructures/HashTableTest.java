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
    
    @org.junit.Test
    public void testAdd() {
        String data = "data";
        String key = "key";
        HashTable<String,String> instance = new HashTable<String,String>();
        instance.add(data, key);
        if(!instance.contains(key))
            fail("failure in add method.");
    }

    @org.junit.Test
    public void testDelete() {
        String data = "data";
        String key = "key";
        HashTable<String,String> instance = new HashTable<String,String>();
        instance.add(data, key);
        instance.delete(key);
        if(instance.contains(key))
            fail("failure in delete method.");
    }

    @org.junit.Test
    public void testContains() {
        HashTable<String,String> instance = new HashTable<>();
        instance.add("object", "key");
        boolean expResult = true;
        boolean result = instance.contains("key");
        assertEquals(expResult, result);
        fail("failure in contains function.");
    }

    @org.junit.Test
    public void testGet() {
        HashTable<String, java.util.ArrayList> instance = new HashTable();
        java.util.ArrayList<String> list = new java.util.ArrayList<>();
        instance.add("key", list);
        String expResult = list;
        String result = instance.get("key");
        assertEquals(expResult, result);
        fail("Tfailure in get function.");
    }
    
}
