package scheduler;

import javax.swing.JOptionPane;

public class LinkedList<T extends Event> {

	private Node<T> head;
	private Node<T> tail;

	public void add(T value) {

		if (head == null) {
			head = new Node<T>(value);
		} else {

			Node<T> prev = head;
			Node<T> next = head.getNext();

			while (next != null) {
				prev = prev.getNext();
				next = next.getNext();
			}

			next = new Node<T>(value);
			prev.setNext(next);
			next.setPrev(prev);
			tail = next;
		}
	}

	public void insert(T value) throws SchedulingConflictException {

		String time = ((Event) value).eventTime;
		Node<T> inserted = new Node<T>(value);
		Node<T> current = head;
		if (head == null) {
			add(value);
			System.out.println("head null");
			return;
		}
		while (current != null) {
			String currentTime = ((Event) current.getValue()).eventTime;
			if (currentTime.compareToIgnoreCase(time) < 0) {
				System.out.println("-1");
				if (current.getNext() == null) {
					current.setNext(inserted);
					inserted.setPrev(current);
					tail = inserted;
					break;
				}
				// time greater than currentTime
			} else if ((currentTime.compareToIgnoreCase(time) > 0)) {
				System.out.println("1");
				if (current.getPrev() != null) {
					current.getPrev().setNext(inserted);
				} else {
					head = inserted;
				}
				current.setPrev(inserted);
				inserted.setNext(current);
				break;

			} else if ((currentTime.compareToIgnoreCase(time) == 0)) {
				throw new SchedulingConflictException();
			}
			current = current.getNext();
		}

	}

	public void remover(String event) {
		int positionCounter = 0;
		Node<T> current = head;
		while (current != null) {
			if (current.getValue().eventName.equalsIgnoreCase(event)) {
				break;
			}
			positionCounter += 1;
			current = current.getNext();
		}
		remove(positionCounter);
	}

	public void remove(int position) {

		if (head == null) {
			System.out.println("No items to remove!");
		} else if (position == 0) {

			head = head.getNext();

			if (head != null) {
				head.setPrev(null);
			}

		} else {

			int positionCounter = 1;
			Node<T> prev = head;
			Node<T> next = head.getNext();

			while (positionCounter < position) {

				prev = prev.getNext();
				next = next.getNext();
				positionCounter++;

			}

			if (positionCounter == position && next != null) {

				next = next.getNext();
				prev.setNext(next);

				if (next != null) {
					next.setPrev(prev);
				}
			} else {
				System.out.println("Position not found!");
			}
		}

	}

	public void print() {
		if (head == null) {
			System.out.println("No items in list!");
		} else {

			Node<T> next = head;
			String bigString = " ";

			while (next != null) {

				bigString = bigString + (next.getValue().toString() + "\n ");

				next = next.getNext();

			}
			JOptionPane.showMessageDialog(Scheduler.getFrame(), bigString);
			System.out.println();

		}

	}

	public int size() {
		int size = 0;
		if (head != null) {

			Node<T> next = head;

			while (next != null) {

				next = next.getNext();
				size++;

			}

		}
		return size;

	}

	public Node<T> getHead() {
		return head;
	}

	public Node<T> getTail() {
		return tail;
	}

	public void setHead(Node<T> head) {
		this.head = head;
	}

	public void setTail(Node<T> tail) {
		this.tail = tail;
	}

}
