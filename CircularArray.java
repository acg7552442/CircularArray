public class CircularArray {
	final static int SIZE = 10;
	int front;
	int rear;
	
	int [] data = new int[SIZE];
	
	CircularArray() {
		// Empty CircularArray
		this.front = -1;
		this.rear = -1;		
	}
	
	public boolean isEmpty() {
		if (this.front==-1) return true;
		else return false;
	}
	
	public boolean isFull() {
		return((this.rear+1)%SIZE==this.front);
	}
	
	public void pushItem(int x) {
		if (this.isFull())
			System.out.println("Cannot push item. Array is full.");
		else 
			if (this.isEmpty()) {
				this.rear = this.front = 0;
				this.data[this.rear]=x;
				}
			else {
				this.rear += 1 % SIZE;
				this.data[rear]=x;
			}
	}
	
	public int popItem() {
		int x=-1;
		
		if (!this.isEmpty()) {
				x = this.data[this.front];
				this.front += 1 % SIZE;
			}
		return x;	
	}
	
	public int numItems() {
		int num;
		if (this.isEmpty()) num= 0;
		else 
			if (this.isFull()) num= SIZE;
			else {
				num = (this.rear - this.front + 1) % SIZE;
			    if (num<0) num += SIZE;
			}
		return num;				
	}
	
	public void reverseAll() {
		// rotate clockwise if gap > 0
		int mid;
		int temp;
		int indexF, indexR;
		
		if (!this.isEmpty()) {
			// reverse subCircularArray
			mid = this.numItems() / 2;
			for (int i=0; i<=mid; i++) {
				indexF = (this.front + i) % SIZE; // move forward
				indexR = (this.rear - i) % SIZE;  // move backward
				temp = this.data[indexF];
				this.data[indexF] = this.data[indexR];
				this.data[indexR] = temp;
			}
		}
	}
	
	public void reverse(int start, int end) {
		
		int mid;
		int temp;
		int indexF, indexR;
		int numItems;
		
		if (start==-1 || end==-1) return;
		numItems = (end - start+1) % SIZE;
		if (numItems <= 0)
			numItems += SIZE;
		
		if (numItems > 1) {
			// reverse subCircularArray
			//System.out.println("numItems = "+numItems);
			mid = numItems/2;
			//System.out.println("mid = "+mid);
			for (int i=0; i<mid; i++) {
				indexF = (start + i) % SIZE;     // move forward
				indexR = (end   - i) % SIZE;     // move backward
				temp = this.data[indexF];
				this.data[indexF] = this.data[indexR];
				this.data[indexR] = temp;
			}
		}
	}
	
	
	public void rotate(int gap) {
		// rotates clockwise by gap
		// 0<gap < SIZE
		int gapModSize = gap % SIZE;
		
		if (gapModSize <= 0) return;	
		this.reverse(this.front, this.rear);
		this.reverse(this.front, (this.front + gapModSize-1)%SIZE);
		this.reverse(this.front+gapModSize, this.rear);
	}
	
	public void display() {	
		
		if (this.isEmpty()) return;
		if (this.front<=this.rear)
			for (int i=this.front; i<=this.rear; i++) 
					System.out.print(this.data[i]+" ");
		else 
			for (int i =this.front; i<= this.rear+SIZE; i++)
				System.out.print(this.data[i%SIZE]+" ");
		System.out.println();
	}
	
	public static void main(String[] args) {
		CircularArray ring = new CircularArray();
		int item;
		
		for (int i=0; i< SIZE; i++)
			ring.pushItem(i); ring.display();
		
		ring.display();

		int gap = 21;
		System.out.println("Rotate by "+ gap);
		ring.rotate(gap);
		ring.display();
		}
	
	
	
}
