package Trie;

import java.util.*;

public class WordDictionnary {

    private Set<String>words;
    private Node root;
    
    /** Initialize your data structure here. */
    public WordDictionnary() {
        root = new Node();
        words = new HashSet<String>();
    }
    
    private class Node{
        private boolean isWord;
        private TreeMap<Character,Node>next;
        
        public Node(boolean isWord){
            this.isWord = isWord;
            next = new TreeMap<Character,Node>();
        }
        
        public Node(){
            this(false);
            next = new TreeMap<Character,Node>();
        }
    }
    
	public void completed(String word){
		String cur = "";
		List<Character>choices = new ArrayList<Character>();
		choices.add(word.charAt(0));
		choices.add('.');
		int index = 0;
		int taille = word.length();
		backtracking(word,cur,choices,taille,index);
	}
    
	public void backtracking(String word,String cur,List<Character>choices,int taille,int index){
		if(cur.length()==taille-1) {
            //add(cur+choices.get(0));
            //add(cur+choices.get(1));
            words.add(cur+choices.get(0));
            words.add(cur+choices.get(1));
			return;
		}
		for(int i = 0;i<choices.size();i++) {
			char ch = choices.get(i);
			cur+=choices.get(i);
			index++;
			List<Character>choix = new ArrayList<Character>();
			choix.add(word.charAt(index));
			choix.add('.');
			backtracking(word,cur,choix,taille,index);
			cur = cur.substring(0,cur.length()-1);	
			index--;			
		}
	}
 
// p .
// a .
// d .
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        Node cur = root;
        words.add(word);
        for(int i = 0;i<word.length();i++){
            char ch = word.charAt(i); 
            if(cur.next.get(ch)==null){
                cur.next.put(ch,new Node());
                //cur.next.put('.',cur.next.get(ch));
            }
            cur = cur.next.get(ch);
        }
        cur.isWord = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        if(word.indexOf('.')==-1){
            Node cur = root;
            boolean flag = true;
            int i = 0;
            while(i<word.length()){
                char ch = word.charAt(i);
                //System.out.println(ch+" -- "+cur.next);
                if(cur.next.get(ch)==null){
                    flag = false;
                    break;
                }
                //System.out.println(cur.next);
                cur = cur.next.get(ch);
                i++;
            } 
            //System.out.println(word+" "+flag);
            return (flag)?cur.isWord:false;            
        } // DFS 
        else{
        	Set<Boolean>res = new HashSet<Boolean>();
            Node cur = root;
            search1(word,cur,0,true,res);
            return res.contains(true);
        }
    }
    
 //   p   p
 //  a    .
 // b     b
    
    public void search1(String word,Node cur,int index,boolean flag,Set<Boolean>res){
        if(index==word.length()){
        	res.add((flag)?cur.isWord:false);
            //System.out.println("resultat : "+((flag)?cur.isWord:false)); 
            return;
        }
        char ch = word.charAt(index);
        //System.out.println(ch+" "+index+" "+cur.next);
        if(ch != '.'){
            if(cur.next.get(ch)!=null){
            	Node trace = cur;
                search1(word,cur.next.get(ch),index+1,flag,res);   
                cur = trace;
                //index--;
            }
            else{
            	return;             	
            }
        }
        else{
        	//System.out.println(ch+" "+index+" "+cur.next);
        	if(index==word.length()-1) {
        		if(!cur.next.isEmpty()) {
        			for(char x:cur.next.keySet()) {
        				if(cur.next.get(x).isWord) {
        					res.add(true);
        					break;
        				}
        			}
        		}
        		else {
        			return;
        		}
        	}
            for(char c:cur.next.keySet()){
            	//System.out.println("choices : "+c+" "+index);
            	Node trace = cur;
                search1(word,cur.next.get(c),index+1,flag,res);
                cur = trace;
                //index--;
            }
        }
    }

}
