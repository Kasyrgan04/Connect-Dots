
public class ListaEnlazada {
	

	//Esta clase define las operaciones del nodo como tal
	class Node {
	/*Se definen los atributos de la lista
	this es el equivalente de self en python
	Se utiliza cuando se quiere modificar los atributos de la clase*/
		private Object data; //Esta es la variable del dato que se almacena
		private Node next; //esta es la referencia al siguiente dato
		

		public Node(Object data){
			this.next=null;
			this.data=data;
		}
	//Este metodo retorna el dato
		public Object getData(){
			return this.data;
		}
		
		//Setea el dato en la posición
		public void setData(Object data){
			this.data=data;
		}
		//Obtiene el siguiente nodo
		public Node getNext(){
			return this.next;
		}
		//setea el siguiente nodo
		public void setNext(Node node){
			this.next=node;
		}
	}
	//Esta clase utiliza los nodos que se acaban de crear
	class LinkedList{
		private Node head;
		private int size;
	//Setea el puntero de la lista en vacio con tamaño cero
	//Es el constructor de la clase
		public LinkedList(){
			this.head=null;
			this.size=0;
		}
	//retorna un booleano para saber si la lista está vacia o no
		public boolean isEmpty(){
			return this.head==null;
		}
		
	//retorna el tamaño de la lista
		public int size(){
			return this.size;
		}
	//Inserción al inicio
		public void insertFirst(Object data){
			Node newNode=new Node(data); //crea nuevo nodo con el dato recibido
			newNode.next=this.head; //El puntero va a ser lo que antes era la cabea de la lista
			this.head=newNode; //La cabeza va a ser le nuevo nodo
			this.size++;
		}
	//Eliminar primer elemento
		public Node deletionFirst(){
			if(this.head!=null){
				Node temp=this.head; //crea un nodo temporal que va a ser el head
				this.head=this.head.next; //El nuevo head va a ser el siguiente dato
				this.size--;
				return temp;
			}
			else{
				return null;
			}	
		}
	//Imprime la lista
		public void displayList(){
			Node current=this.head; //se crea un nodo temporal, el cual es el head de la lista
			while(current!=null){
				System.out.println(current.getData());
				current=current.getNext();
			}
		}
	//Busca un dato en la lista
		public Node find(Object searchValue){
			Node current=this.head;
			while(current!=null){ //mientras el nodo no sea vacio, es decir, mientras no se llegue al final
				if (current.getData().equals(searchValue)){
					return current;
				}else{
					current=current.getNext();
				}
			}
			return null;
		}
	//Eliminar un dato dado
		public Node delete(Object searchValue) {
			Node current=this.head;
			Node previous=this.head;
			while(current!=null) {
				if(current.getData().equals(searchValue)) {
					if(current==this.head) {
						this.head=this.head.getNext();
					}else {
						previous.setNext(current.getNext());
					}return current;
				}else {
					previous=current;
					current=current.getNext();
				}
			}return null;
		}
	}
}
