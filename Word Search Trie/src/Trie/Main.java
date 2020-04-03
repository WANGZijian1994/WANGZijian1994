package Trie;

public class Main {

	public static void main(String[] args) {
		Trie t = new Trie();
		t.add("pandas");
		t.add("panda");
		t.add("pand");
		boolean flag = t.contains("panda");
		boolean flag1 = t.contains("pans");
		System.out.println(flag+" "+flag1);
		t.add("姑姑");
		flag1 = t.contains("姑");
		System.out.println(flag1);//false
	}

}
