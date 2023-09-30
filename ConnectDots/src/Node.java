	//Esta clase define las operaciones del nodo como tal
	class Node {
	/*Se definen los atributos de la lista
	Se utiliza cuando se quiere modificar los atributos de la clase*/
		private Object data; //Esta es la variable del dato que se almacena
		Node next; //esta es la referencia al siguiente dato
		

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
		private Node last;
		private int size;
	//Setea el puntero de la lista en vacio con tamaño cero
	//Es el constructor de la clase
		public LinkedList(){
			this.head=null;
			this.last=null;
			this.size=0;
		}
	//retorna un booleano para saber si la lista está vacia o no
		public boolean isEmpty(){
			if(this.head==null && this.last==null) {
				return true;
			}
			return false;
		}
		
	//retorna el tamaño de la lista
		public int size(){
			return this.size;
		}
	//Inserción al inicio
		public void insertFirst(Object data){
			Node newNode=new Node(data); //crea nuevo nodo con el dato recibido
			if(this.isEmpty()) {
				this.head=this.last=newNode; //La cabeza va a ser le nuevo nodo
			} else {
				newNode.setNext(this.head);
				this.head=newNode;
			}
			
			this.size++;
		}
		
		public void insertLast(Object data) {
			 Node newNode = new Node(data);
			 if (this.isEmpty()) { 
				 this.head = this.last = newNode;
			 } else { 
				 this.last.setNext(newNode);
				 this.last = newNode;
			 }
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
	}