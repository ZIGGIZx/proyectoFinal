package libreria.estructuras;

import java.util.Iterator;

public class DoubleLinkedList<ELEMENT> implements ILinkedList<ELEMENT> {

 // Región Clase Nodo
 // Clase protegida que representa un nodo en la lista doblemente enlazada.
 protected class Node<ELEMENT> {
     protected ELEMENT item;  // Elemento almacenado en el nodo.
     protected Node<ELEMENT> next;  // Referencia al siguiente nodo en la lista.
     protected Node<ELEMENT> prev;  // Referencia al nodo anterior en la lista.

     protected Node() {
         this(null, null, null);
     }
     protected Node(ELEMENT item) {
         this(item, null, null);
     }
     protected Node(ELEMENT item, Node<ELEMENT> next) {
         this(item, next, null);
     }
     protected Node(ELEMENT item, Node<ELEMENT> next, Node<ELEMENT> prev) {
         this.item = item;
         this.next = next;
         this.prev = prev;
     }

     @Override
     public String toString() {
         return this.item.toString();
     }
 }
 // Fin Región Clase Nodo

 // Región Atributos
 // Nodo de cabeza (primer elemento), nodo de cola (último elemento) y contador de elementos en la lista.
 protected Node<ELEMENT> head;
 protected int count;
 protected Node<ELEMENT> tail;
 // Fin Región Atributos

 // Región Constructores
 // Constructor por defecto que inicializa una lista vacía.
 public DoubleLinkedList() {
     this.head = null;
     this.count = 0;
     this.tail = null;
 }
 // Fin Región Constructores

 // Región Métodos de la Lista Enlazada

 // Devuelve el número de elementos en esta lista.
 public int size() {
     return this.count;
 }
/*
 // Añade un elemento al principio de la lista (versión básica).
 public void addFirstRookieVersion(ELEMENT item) {
     if (this.size() <= 0) {
         this.head = this.tail = new Node<ELEMENT>(item, null, null);
         ++this.count;
     }
     else {
         Node<ELEMENT> temp = new Node<ELEMENT>(item, null, null);
         temp.next = this.head;
         this.head.prev = temp;
         this.head = temp;
         ++this.count;
     }
 }
 */
 // Inserta el elemento especificado al principio de la lista.
 public void addFirst(ELEMENT item) {
     Node<ELEMENT> temp = new Node<ELEMENT>(item, this.head, null);
     if (this.size() <= 0) {
         this.tail = temp;
     }
     else {
         this.head.prev = temp;
     }
     this.head = temp;
     ++this.count;
 }
//Añade el elemento especificado al medio de la lista.
 public void addMiddle(ELEMENT item) {
	    if (this.size() == 0) {
	        addFirst(item);
	        return;
	    }
	    // Calcular la posición intermedia
	    int posicionMedio = this.size() / 2;
	    // Recorrer la lista hasta la posición deseada
	    Node<ELEMENT> actual = this.head;
	    for (int i = 0; i < posicionMedio; i++) {
	        actual = actual.next;
	    }

	    // Crear el nuevo nodo con el elemento a insertar
	    Node<ELEMENT> nuevoNodo = new Node<>(item, actual, actual.prev);
	    if (actual.prev != null) {
	        actual.prev.next = nuevoNodo;
	    } else {
	        this.head = nuevoNodo;
	    }
	    actual.prev = nuevoNodo;
	    this.count++;
	}

 // Añade un elemento al final de la lista (versión básica).
 public void addLastRookieVersion(ELEMENT item) {
     if (this.size() <= 0) {
         this.head = this.tail = new Node<ELEMENT>(item, null, null);
         ++this.count;
     }
     else {
         Node<ELEMENT> temp = new Node<ELEMENT>(item, null, null);
         temp.prev = this.tail;
         this.tail.next = temp;
         this.tail = temp;
         ++this.count;
     }
 }

 // Añade el elemento especificado al final de la lista.
 public void addLast(ELEMENT item) {
     Node<ELEMENT> temp = new Node<ELEMENT>(item, null, this.tail);
     if (this.size() <= 0) {
         this.head = temp;
     }
     else {
         this.tail.next = temp;
     }
     this.tail = temp;
     ++this.count;
 }

 // Elimina y devuelve el primer elemento de la lista.
 public ELEMENT removeFirst() {
     if (this.size() <= 0) {
         throw new RuntimeException("La lista está vacía...");
     }
     ELEMENT item = this.head.item;
     this.head = this.head.next;
     if (this.head == null) {
         this.tail = null;
     }
     else {
         this.head.prev = null;
     }
     --this.count;
     return item;
 }

 // Elimina y devuelve el último elemento de la lista.
 public ELEMENT removeLast() {
     if (this.size() <= 0) {
         throw new RuntimeException("La lista está vacía...");
     }
     ELEMENT item = this.tail.item;
     if (this.head.next == null) {
         this.head = this.tail = null;
     } else {
         this.tail = this.tail.prev;
         this.tail.next = null;
     }
     --this.count;
     return item;
 }
 // Fin Región Métodos de la Lista Enlazada

 // Región Métodos de Objeto

 // Devuelve una representación en forma de cadena de la lista doblemente enlazada.
 @Override
 public String toString() {

     if (this.size() <= 0) {
         return "";
     }

     // de https://docs.oracle.com/en/java/javase/15/docs/api/java.base/java/lang/StringBuilder.html
     StringBuilder sb = new StringBuilder();

     sb.append("[" + this.head.item.toString());
     for (Node<ELEMENT> skip = this.head.next; skip != null; skip = skip.next) {
         sb.append(", " + skip.item.toString());
     }
     sb.append("]");

     return sb.toString();
 }
 // Fin Región Métodos de Objeto

 // Región Métodos de Iterable

 // Retorna un iterador que recorre la lista de principio a fin.
 @Override
 public Iterator<ELEMENT> iterator() {
     return new DoubleLinkedListIterator(this.head);
 }

 // Clase interna para iterar de manera progresiva sobre los elementos de la lista.
 public class DoubleLinkedListIterator implements Iterator<ELEMENT> {
     private Node<ELEMENT> actual;

     public DoubleLinkedListIterator(Node<ELEMENT> inicio) {
         this.actual = inicio;
     }

     @Override
     public boolean hasNext() {
         return this.actual != null;
     }

     @Override
     public ELEMENT next() {
         if (!this.hasNext()) {
             throw new RuntimeException("La lista está vacía...");
         }
         ELEMENT item = this.actual.item;
         this.actual = this.actual.next;
         return item;
     }
 }

 // Retorna un iterador que recorre la lista de fin a principio.
 public Iterator<ELEMENT> iteratorBack() {
     return new DoubleLinkedListIteratorBack(this.tail);
 }

 // Clase interna para iterar de manera inversa sobre los elementos de la lista.
 public class DoubleLinkedListIteratorBack implements Iterator<ELEMENT> {
     private Node<ELEMENT> actual;

     public DoubleLinkedListIteratorBack(Node<ELEMENT> actual) {
         this.actual = actual;
     }

     @Override
     public boolean hasNext() {
         return this.actual != null;
     }

     @Override
     public ELEMENT next() {
         if (!this.hasNext()) {
             throw new RuntimeException("La lista está vacía...");
         }
         ELEMENT item = this.actual.item;
         this.actual = this.actual.prev;
         return item;
     }

 }

 // Fin Región Métodos de Iterable

}
