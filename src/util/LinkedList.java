package util;

public class LinkedList<T> implements java.io.Serializable {
	public Node<T> first;
	
	public LinkedList(){
		this.first=new Node<T>();
	}
	
	public Node<T> getLast(){
		Node<T> current=first;
		while(current.next!=null){
			current=current.next;
		}
		return current;
	}
	
	public void removeNode(Node<T> node){
		Node<T> current=first.next;
		Node<T> prev=first;
		if(first==node){
			first=null;
			return;
		}
		while(current!=null){
			if(current==node){
				prev.next=current.next;
				return;
			}
			current=current.next;
			prev=prev.next;
		}
	}
	
	public LinkedList<T> copy(){
		LinkedList<T> copy=new LinkedList<T>();
		Node<T> aux=null,copyNode=null,next=null,nextCopy=null;
		if(this.first!=null){
			Node<T> firstcopy=new Node<T>(this.first.data);
			copy.first=firstcopy;
			aux=first;
			copyNode=firstcopy;
			
			next=first.next;
			
			while(next!=null){
				nextCopy=new Node<T>(next.data);
				copyNode.next=nextCopy;
				aux=next;
				copyNode=nextCopy;
				next=next.next;
			}
		}
		return copy;
			
			
		}
	}

