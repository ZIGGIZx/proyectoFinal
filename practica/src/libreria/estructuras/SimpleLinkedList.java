package libreria.estructuras;
import java.util.Iterator;


 
public class SimpleLinkedList<ELEMENT> implements ILinkedList<ELEMENT> {
 
    //region Node Class
 
    private class Node<ELEMENT> {
        public ELEMENT item;
        public Node<ELEMENT> next;
 
        public Node() {
            this(null, null);
        }
        public Node(ELEMENT item) {
            this(item, null);
        }
        public Node(ELEMENT item, Node<ELEMENT> next) {
            this.item = item;
            this.next = next;
        }
 
        @Override
        public String toString() {
            return this.item.toString();
        }
    }
    //endregion
 
    //region Attributes
 
    protected Node<ELEMENT> head;
    protected int count;
    protected Node<ELEMENT> tail;
    //endregion
 
    //region Constructors
 
    public SimpleLinkedList() {
        this.head = null;
        this.count = 0;
        this.tail = null;
    }
    //endregion
 
    //region Linked List Methods
 
    // Returns the number of elements in this list.
    public int size() {
        return this.count;
    }
 
    public void addFirstRookieVersion(ELEMENT item) {
        if (this.count == 0) {
            this.head = this.tail = new Node<ELEMENT>(item, null);
            ++this.count;
        } else {
            Node<ELEMENT> temp = new Node<ELEMENT>(item, null);
            temp.next = this.head;
            this.head = temp;
            ++this.count;
        }
    }
    // Inserts the specified element at the beginning of this list.
    public void addFirst(ELEMENT item) {
        Node<ELEMENT> temp = new Node<ELEMENT>(item, this.head);
        if (this.count == 0) {
            this.tail = temp;
        }
        this.head = temp;
        ++this.count;
    }
 
    public void addLastRookieVersion(ELEMENT item) {
        if (this.count == 0) {
            this.head = this.tail = new Node<ELEMENT>(item, null);
            ++this.count;
        } else {
            Node<ELEMENT> temp = new Node<ELEMENT>(item, null);
            this.tail.next = temp;
            this.tail = temp;
            ++this.count;
        }
    }
    // Appends the specified element to the end of this list.
    public void addLast(ELEMENT item) {
        Node<ELEMENT> temp = new Node<ELEMENT>(item, null);
        if (this.count == 0) {
            this.head = temp;
        } else {
            this.tail.next = temp;
        }
        this.tail = temp;
        ++this.count;
    }
 
    // Removes and returns the first element from this list.
    public ELEMENT removeFirst() {
        if (this.count == 0) {
            throw new RuntimeException("La lista está vacía...");
        }
        ELEMENT item = this.head.item;
        this.head = this.head.next;
        if (this.head == null) {
            this.tail = null;
        }
        --this.count;
        return item;
    }
 
    // Removes and returns the last element from this list.
    public ELEMENT removeLast() {
        if (this.count == 0) {
            throw new RuntimeException("La lista está vacía...");
        }
        ELEMENT item = this.tail.item;
        if (this.head.next == null) {
            this.head = this.tail = null;
        } else {
            Node<ELEMENT> skip = this.head;
            while (skip.next.next != null) {
                skip = skip.next;
            }
            this.tail = skip;
            this.tail.next = null;
        }
        --this.count;
        return item;
    }
    
    public ELEMENT eliminarEmpleado(ELEMENT elemento) {
    	ELEMENT aux;
    	if(this.count==0) {
    		throw new RuntimeException("La lista está vacía...");
    	}
    	if(this.tail.item.equals(elemento)) {
    		aux=removeLast();
    		return aux;
    	}
    	if(this.head.item.equals(elemento)) {
    		aux=removeFirst();
    		return aux;
    	}
    	Node<ELEMENT> skip=this.head;
    	while(!skip.next.item.equals(elemento)) {
    		skip=skip.next;
    		if(skip.next==null) {
    			throw new RuntimeException("No se encontro el empleado");
    		}
    	}
    	aux=skip.next.item;
		skip.next=skip.next.next;
		return aux;
    }
    
    public void eliminarDuplicados() {
    	
    	for (Node<ELEMENT> skip1 = this.head; skip1.next != null; skip1 = skip1.next) {
    		for (Node<ELEMENT> skip2 = skip1; skip2.next != null ; skip2 = skip2.next) {
    			if(skip1.item.equals(skip2.next.item)) {    				
					skip2.next=skip2.next.next;
					--this.count;    					    		
    			}
    			if(skip2.next==null) {
    				break;
    			}
    		}
    		if(skip1.next==null) {
				break;
			}
    	}
    	
    	
    	
    	
    	/*int aux;
    	for (int i=0;i<this.count;i++) {
    		Node<ELEMENT> skip1 = this.head;
    		aux=i;
        	while(aux>0 && skip1.next!=null) {
        		skip1=skip1.next;
        		aux--;
        	}
            for (; skip1.next != null; skip1 = skip1.next) {  
            	if(skip1.item.equals(skip1.next.item)) {
            		if(skip1.next.equals(this.head)) {            		
            			this.head=skip1;
            			this.head.next=null;
            		}
            		else {
            			skip1.next=skip1.next.next;
            		}
            		count--;
            	}
            }
    	}*/
    }
    
   
    //endregion
 
    //region Object Methods
 
    @Override
    public String toString() {
 
        if (this.size() <=0) {
            return "La lista esta vacia";
        }
 
        // from https://docs.oracle.com/en/java/javase/15/docs/api/java.base/java/lang/StringBuilder.html
        StringBuilder sb = new StringBuilder();
 
        sb.append("[" + this.head.item.toString());
        for (Node<ELEMENT> skip = this.head.next; skip != null; skip = skip.next) {
            sb.append(", " + skip.item.toString());
        }
        sb.append("]");
 
        return sb.toString();
    }
    //endregion
 
 
    //region Iterable Methods
    @Override
    public Iterator<ELEMENT> iterator() {
        return new SimpleLinkedListIterator(this.head);
    }
 
    private class SimpleLinkedListIterator implements Iterator<ELEMENT> {
        private Node<ELEMENT> current;
 
        public SimpleLinkedListIterator(Node<ELEMENT> current) {
            this.current = current;
        }
 
        @Override
        public boolean hasNext() {
            return this.current != null;
        }
 
        @Override
        public ELEMENT next() {
            if (!this.hasNext()) {
                throw new RuntimeException("La lista está vacía...");
            }
            ELEMENT item = this.current.item;
            this.current = this.current.next;
            return item;
        }
 
    }
    
    //Mostrar lista
    public void mostrar() {
    	if (head == null) {  // Verifica si la lista está vacía
            System.out.println("La lista está vacía.");
        }
    	Node <ELEMENT> recorrer=this.head;
    	while(recorrer!=null) {
    		System.out.print("["+recorrer.item+"]--->");
    		recorrer=recorrer.next;
    	}
    }
 
 
 
    //endregion
 
}