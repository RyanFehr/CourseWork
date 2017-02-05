package project;

import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.Random;
// import java.util.PriorityQueue;


class HTreeNode{
    private char c;
    private double key;
    private HTreeNode left;
    private HTreeNode right;
    
    HTreeNode(double key){
	this.key = key;
	this.c = Character.MAX_VALUE;
	this.left = null;
	this.right = null;
    }
    
    HTreeNode(double key, char c){
	this.c = c;
	this.key = key;
	this.left = null;
	this.right = null;
    }

    public char getChar(){
	return this.c;
    }

    public void setChar(char c){
	this.c = c;
    }

    public double getKey(){
	return this.key;
    }

    public void setKey(double key){
	this.key = key;
    }

    public HTreeNode getRightTree(){
	return this.right;
    }

    public void setRightTree(HTreeNode right){
	this.right = right;
    }

    public HTreeNode getLeftTree(){
	return this.left;
    }

    public void setLeftTree(HTreeNode left){
	this.left = left;
    }
    
    public String toString(){
	return this.c + ": " + this.key;
    }
}

class Test implements Serializable {
    private Map<Character,Double> charFreqs;
    private Map<Character,String> codes;
    private static final long serialVersionUID = 6757445828146375456L;
	
    Test(Map<Character,Double> charFreqs,Map<Character,String> codes){
	this.charFreqs = charFreqs;
	this.codes = codes;
    }

    public Map<Character,Double> getCharFreq(){
	return this.charFreqs;
    }

    public Map<Character,String> getCodes(){
	return this.codes;
    }

    private void readObject(ObjectInputStream aInputStream)
	throws ClassNotFoundException, IOException {
	aInputStream.defaultReadObject();
    }

    private void writeObject(ObjectOutputStream aOutputStream)
	throws IOException {
	aOutputStream.defaultWriteObject();
    }
}

class HTreeNodeComparator implements Comparator<HTreeNode>
{
    @Override
    public int compare(HTreeNode x, HTreeNode y)
    {
	if (x.getKey() < y.getKey())
	    {
		return -1;
	    }
	if (x.getKey() > y.getKey())
	    {
		return 1;
	    }
	return 0;
    }
}

public class Driver {

    public static HTreeNode createHuffCodeTree(List<HTreeNode> C){
	
	Comparator<HTreeNode> comparator = new HTreeNodeComparator();
	// PriorityQueue<HTreeNode> Q = new PriorityQueue<HTreeNode>(comparator);
	PriorityQueue<HTreeNode> Q = new PriorityQueueImp<HTreeNode>(comparator);
	for (HTreeNode c : C){
	    // Q.add(c);
	    Q.push(c);
	}
	for (int i = 0; i < C.size() -1; ++i){
	    // HTreeNode e1 = Q.poll();
	    // HTreeNode e2 = Q.poll();
	    HTreeNode e1 = Q.pop();
	    HTreeNode e2 = Q.pop();
	    HTreeNode z = new HTreeNode(e1.getKey()+e2.getKey());
	    z.setLeftTree(e1);
	    z.setRightTree(e2);
	    // Q.add(z);
	    Q.push(z);
	}
	// return Q.poll();
	return Q.pop();
    }

    public static String getCode(HTreeNode n,
				 char c,
				 String code){
	if (n == null){
	    return null;
	}
	else if (n.getChar() == c){
	    return code;
	}
	String lcode = getCode(n.getLeftTree(), c, code + "0");
	if (lcode != null){
	    return lcode;
	}
	return getCode(n.getRightTree(), c, code + "1");
    }
    
    public static Map<Character,String> runHuffCode(Map<Character,Double> charFreqs){
	List<HTreeNode> C = new ArrayList<HTreeNode>();
	HTreeNode o;
	
	for (char c : charFreqs.keySet()){
	    o = new HTreeNode(charFreqs.get(c), c);
	    C.add(o);
	    o = null;
	}
	
	HTreeNode root = createHuffCodeTree(C);
	Map<Character,String> codes = new HashMap<Character,String>();

	for (char c : charFreqs.keySet()){
	    codes.put(c,getCode(root, c, ""));
	}
	
	return codes;
    }

    public static void normalize(Map<Character,Double> charFreqs){
	int total = 0;
	for (char c : charFreqs.keySet()){
	    total += charFreqs.get(c);
	}
	for (char c : charFreqs.keySet()){
	    charFreqs.put(c,charFreqs.get(c) / total);
	}
    }

    public static double calcTotalBits(Map<Character,Double> freq,Map<Character,String> code){
	double sum = 0;
	for (char k : freq.keySet()){
	    sum += code.get(k).length() * freq.get(k);
	}
	return sum;
    }

    public static void saveCharFreq(List<Test> l){
	try
	    {
		FileOutputStream fos =
		    new FileOutputStream("codes.ser");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(l);
		oos.close();
		fos.close();
	    }catch(IOException ioe)
	    {
		ioe.printStackTrace();
	    }
    }

    @SuppressWarnings("unchecked")
    public static List<Test> loadCharFreq(){
	List<Test> l = null;
	try
	    {
		FileInputStream fis = new FileInputStream("codes.ser");
		ObjectInputStream ois = new ObjectInputStream(fis);
		l = (LinkedList<Test>) ois.readObject();
		ois.close();
		fis.close();
	    }catch(IOException ioe)
	    {
		ioe.printStackTrace();
	    }catch(ClassNotFoundException c)
	    {
		System.out.println("Class not found");
		c.printStackTrace();
	    }
	return l;
    }

    public static String genRandomString(int length){
	String alphabet = "ACGTUWSMKRY";
	Random random = new Random();
	StringBuilder ret = new StringBuilder();
	for(int i = 0; i < length; ++i){
	    ret.append(alphabet.charAt(random.nextInt(alphabet.length())));
	}
	return ret.toString();
    }

    public static Map<Character,Double> getRandomFreqs(){
	String seq = genRandomString(1000);
	Map<Character,Double> charFreqs = new HashMap<Character,Double>();
	for (int i = 0; i < seq.length(); ++i){
	    char c = seq.charAt(i);
	    if (charFreqs.containsKey(c)){
		charFreqs.put(c,charFreqs.get(c)+1);
	    }
	    else {
		charFreqs.put(c,1.0);
	    }
	}
	return charFreqs;
    }

    public static void genTests(){
	List<Test> tests = new LinkedList<Test>();
	for(int i = 0; i < 10; ++i){
	    Map<Character,Double> charFreqs = getRandomFreqs();
	    normalize(charFreqs);
	    Map<Character,String> codes = runHuffCode(charFreqs);
	    tests.add(new Test(charFreqs, codes));
	}
	saveCharFreq(tests);
    }
    
    public static void runBatch(){
	List<Test> tests = loadCharFreq();
	int cnt = 0;
	boolean passed = true;
	for (Test t : tests){
	    Map<Character,String> retCodes = runHuffCode(t.getCharFreq());
	    double tcaseBits = calcTotalBits(t.getCharFreq(), t.getCodes());
	    double huffCodeBits = calcTotalBits(t.getCharFreq(), retCodes);
	    if (tcaseBits != huffCodeBits){
		System.out.println("test#" + cnt + " failed");
		passed = false;
	    }
	    cnt += 1;
	}
	if (passed){
	    System.out.println("all tests passed!");
	}
    }
    
    public static void main(String[] args) {
	switch(args.length){
	case 0:
	    Map<Character, Double> charFreqs = new HashMap<>(6,1);
	    charFreqs.put('a',45.0);
	    charFreqs.put('b',13.0);
	    charFreqs.put('c',12.0);
	    charFreqs.put('d',16.0);
	    charFreqs.put('e',9.0);
	    charFreqs.put('f',5.0);
	    normalize(charFreqs);
	    Map<Character,String> code = runHuffCode(charFreqs);
	    System.out.println(code.toString());
	    System.out.println("total bits with normalized frequencies: " + calcTotalBits(charFreqs, code));
	    break;
	case 1:
	    switch(args[0]){
	    case "gentests":
		genTests();
		break;
	    case "test":
		runBatch();
		break;
	    default:
		System.out.println("Invalid argument.");
	    }
	    break;
	default:
	    System.out.println("Wrong number of arguments.");
	    break;
	}
    }
}
