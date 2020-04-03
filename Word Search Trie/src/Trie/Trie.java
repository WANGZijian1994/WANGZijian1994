package Trie;

import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

public class Trie {
	
	private class Node{
		
		public boolean isWord;// identify whether it is already a word 
		public TreeMap<Character,Node> next;//if it is for Chinese, Japanese, Korean, Character could be changed for other types 
	
		public Node(boolean isWord) {
			this.isWord = isWord;
			next = new TreeMap<>();
		}
		
		public Node() {
			this(false);
		}
	}
	//create root for Trie
	private Node root;
	//How many words in this Trie?
	private int size;
	
	public Trie() {
		this.root=new Node();
		this.size = 0;
	}
	
	// know how many words exist in this Trie
	public int getSize() {
		return this.size;
	}
	
	// add a word in this Trie
	public void add(String word) {
		Node cur = root;
		word = word.toLowerCase();
		for(int i = 0;i<word.length();i++) {
			char ch = word.charAt(i);
			if(cur.next.get(ch)==null) {
				cur.next.put(ch, new Node());
			}
			cur = cur.next.get(ch);
		}
		if(!cur.isWord) {
			cur.isWord = true;
			size++;
		}
	}
	
	public boolean contains(String word) {
		Node cur = root;
		word = word.toLowerCase();
		for(int i = 0;i<word.length();i++) {
			char ch = word.charAt(i);
			if(cur.next.get(ch)==null) {
				break;
			}
			cur = cur.next.get(ch);
		}
		return cur.isWord;
	}
	
	public boolean contains_pre(String pre) {
		Node cur = root;
		boolean flag = true;
		for(int i = 0;i<pre.length();i++) {
			char ch = pre.charAt(i);
			if(cur.next.get(ch)==null) {
				flag = false;
				break;
			}
			cur = cur.next.get(ch);
		}
		return flag;
	}
	
	/*
	public List<String> startsWith(String pre) {
		Node cur = root;
		List<String>mots = new ArrayList<String>();
		boolean flag = true;
		for(int i = 0;i<pre.length();i++) {
			char ch = pre.charAt(i);
			if(cur.next.get(ch)==null) {
				flag = false;
				break;
			}
			cur = cur.next.get(ch);
		}
		
		String tmp = pre;
		Stack<Node>dfs = new Stack<Node>();
		List<Node>l = new ArrayList<Node>();
		dfs.push(cur);
		if(flag) {
			while(!dfs.isEmpty()) {
				while(!dfs.isEmpty()) {
					l.add(cur.next.get()));
				}
			}
		}
		return mots;
		
	}
	*/
}
