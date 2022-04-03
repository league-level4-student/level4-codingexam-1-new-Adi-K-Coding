package scheduler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * Objective: Create a weekly scheduling application.
 * 
 * You may create any additional enums, classes, methods or variables needed
 * to accomplish the requirements below:
 * 
 * - You should use an array filled with enums for the days of the week and each
 *   enum should contain a LinkedList of events that includes a time and what is 
 *   happening at the event.
 * 
 * - The user should be able to to interact with your application through the
 *   console and have the option to add events, view events or remove events by
 *   day.
 *   
 * - Each day's events should be sorted by chronological order.
 *  
 * - If the user tries to add an event on the same day and time as another event
 *   throw a SchedulingConflictException(created by you) that tells the user
 *   they tried to double book a time slot.
 *   
 * - Make sure any enums or classes you create have properly encapsulated member
 *   variables.
 */
public class Scheduler implements ActionListener {

	enum DaysOfTheWeek {
		SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY;

		private LinkedList<Event> list = new LinkedList<Event>();
	}

	private DaysOfTheWeek[] days = { DaysOfTheWeek.SUNDAY, DaysOfTheWeek.MONDAY, DaysOfTheWeek.TUESDAY,
			DaysOfTheWeek.WEDNESDAY, DaysOfTheWeek.THURSDAY, DaysOfTheWeek.FRIDAY, DaysOfTheWeek.SATURDAY };

	private static JFrame frame = new JFrame();
	private JPanel panel = new JPanel();
	private JButton add = new JButton("Add");
	private JButton view = new JButton("View");
	private JButton remove = new JButton("Remove");

	public static void main(String[] args) {
		Scheduler s = new Scheduler();
		s.run();
	}

	public static JFrame getFrame() {
		return frame;
	}

	void run() {
		frame.add(panel);
		panel.add(add);
		panel.add(view);
		panel.add(remove);
		add.addActionListener(this);
		view.addActionListener(this);
		remove.addActionListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();

	}

	DaysOfTheWeek getDay() {
		DaysOfTheWeek userInput = (DaysOfTheWeek) JOptionPane.showInputDialog(frame, "What Day Of the Week?",
				"Choose Day", JOptionPane.QUESTION_MESSAGE, null, days, days[0]);
		return userInput;
	}

	Event getStrings() {
		String userInpEvent = JOptionPane.showInputDialog(frame, "Enter Your Event");
		String userInpTime = JOptionPane.showInputDialog(frame, "Enter The Time of Your Event");
		Event newEvent = new Event(userInpEvent, userInpTime);
		return newEvent;
	}

	void addEvent(DaysOfTheWeek day) {

//		Node<Event> newNode = new Node<Event>(getStrings());
		try {
			day.list.insert(getStrings());
		} catch (SchedulingConflictException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Event already exists at this time");
		}
	}

	void viewEvents(DaysOfTheWeek day) {
		day.list.print();
	}

	void removeEvent(DaysOfTheWeek day) {
		String toRemove = JOptionPane.showInputDialog(frame, "Enter the Name of the Event you would like to remove");
		day.list.remover(toRemove);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DaysOfTheWeek uI = getDay();
		if (e.getSource() == add) {
			System.out.println("button: add, day: " + uI);
			addEvent(uI);
		}
		if (e.getSource() == view) {
			System.out.println("button: view, day: " + uI);
			viewEvents(uI);
		}
		if (e.getSource() == remove) {
			System.out.println("button: remove, day: " + uI);
			removeEvent(uI);
		}

	}
}
