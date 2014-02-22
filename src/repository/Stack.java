package repository;

import util.LinkedList;
import util.Node;
import domain.StackException;

public class Stack<T> implements java.io.Serializable {

	private LinkedList<T> elements;
	private int size=0;
	
	public Stack(){
		elements = new LinkedList<T>();
	}
	
	public void push(T data){
		Node<T> last=this.elements.getLast();
		if(last!=null){
			last.next=new Node<T>(data);
		}
		else{
			this.elements.first=new Node<T>(data);
		}
		size++;
	}
	
	public T pop() throws StackException{
		Node<T> aux=this.elements.first;
		if(size==0){
			throw new StackException("Stack is empty!");
		}
		this.size--;
	    Node<T> lastNode = this.elements.getLast();
	    T returnData = lastNode.data;
	    this.elements.removeNode(lastNode);
	    return returnData;
		
		
	}
	
	public int getSize(){
		return this.size;
	}
	
	public boolean isEmpty(){
		return this.size==0;
	}
	
	public Stack<T> getAll(){
		Stack<T> copy=new Stack<T>();
		copy.elements=this.elements.copy();
		copy.size=this.size;
		return copy;
	}
}
