package com.company;

class Trie
{
    private TrieNode root;
    class TrieNode
    {
        boolean EndOfWord;
        TrieNode [] child = new TrieNode[26];
    }
    public Trie()
    {
        root = new TrieNode();
    }

    public void insert(String word)
    {
        TrieNode crawl = root;
        for(int i = 0;i<word.length();i++)
        {
            int i_i = word.charAt(i)-'a'; //returns difference of ascii
            if(crawl.child[i_i]==null)
                crawl.child[i_i] = new TrieNode();
            crawl = crawl.child[i_i];
        }
        crawl.EndOfWord = true;
    }

    public boolean search(String word)
    {
        TrieNode crawl = root;
        for(int i = 0;i<word.length();i++)
        {
            int i_i = word.charAt(i)-'a'; //returns difference of ascii
            if(crawl.child[i_i]==null)
                return false;
            crawl = crawl.child[i_i];
        }
        return crawl.EndOfWord;
    }


    public boolean startsWith(String prefix)
    {
        TrieNode crawl = root;
        for(int i = 0;i<prefix.length();i++)
        {
            int i_i = prefix.charAt(i)-'a'; //returns difference of ascii
            if(crawl.child[i_i]==null)
                return false;
            crawl = crawl.child[i_i];
        }
        return true;
    }
}


