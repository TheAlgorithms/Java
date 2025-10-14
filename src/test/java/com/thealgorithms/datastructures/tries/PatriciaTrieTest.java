@Test
    void insert_splitCausesNewBranch_incrementsSize() {
        var t = new PatriciaTrie<Integer>();
        t.put("romane", 1); // An initial key
        assertEquals(1, t.size());

        // This insertion will split the "romane" edge at "roman"
        // and create a new branch for "us". This is where the bug occurred.
        t.put("romanus", 2);

        assertEquals(2, t.size(), "Size should increment when a split creates a new key branch");
        assertTrue(t.contains("romane"));
        assertTrue(t.contains("romanus"));
        assertEquals(1, t.get("romane"));
        assertEquals(2, t.get("romanus"));
    }
