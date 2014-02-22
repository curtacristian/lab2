package util;



public class Node<T> implements java.io.Serializable{
	public T data;
	public Node<T> next;
	
	public Node(){
		this.data=null;
		this.next=null;
	}
	
	public Node(T data){
		this.data=data;
		this.next=null;
	}
}