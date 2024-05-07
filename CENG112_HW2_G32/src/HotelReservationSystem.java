
public class HotelReservationSystem {
	//convert to a class not main
	private int counter;
	private Room[] tempRooms;
	private StackADT<Room> pile1;
	private StackADT<Room> pile2;
	private StackADT<Room> pile3;
	private StackADT<Room> pile4;
	private QueueADT<Reservation> singleWaitList;
	private QueueADT<Reservation> doubleWaitList;
	private QueueADT<Reservation> suiteWaitList;
	private QueueADT<Reservation> deluxeWaitList;
	private ListADT<Room> bookedRooms;
	private ListADT<Room> unavailableRooms;
	private ListADT<Room> availableRooms;

	public HotelReservationSystem() {
		tempRooms=new Room[20];
		pile1=new Pile<Room>(5);
		pile2=new Pile<Room>(5);
		pile3=new Pile<Room>(5);
		pile4=new Pile<Room>(5);
		setAllPiles();
		
		/*while(!pile1.isEmpty()) {
			System.out.println(pile1.pop());
		}
		while(!pile2.isEmpty()) {
			System.out.println(pile2.pop());
		}
		while(!pile3.isEmpty()) {
			System.out.println(pile3.pop());
		}
		while(!pile4.isEmpty()) {
			System.out.println(pile4.pop());
		}*/
		singleWaitList=new WaitingLine<Reservation>();
		doubleWaitList=new WaitingLine<Reservation>();
		suiteWaitList=new WaitingLine<Reservation>();
		deluxeWaitList=new WaitingLine<Reservation>();		
		FileProcess();
		/*while(!singleReservations.isEmpty()) {
		System.out.println(singleReservations.dequeue());
		}
		while(!doubleReservations.isEmpty()) {
			System.out.println(doubleReservations.dequeue());
			}
		while(!deluxeReservations.isEmpty()) {
			System.out.println(deluxeReservations.dequeue());
			}
		while(!suiteReservations.isEmpty()) {
			System.out.println(suiteReservations.dequeue());
			}*/
		bookedRooms=new List<Room>();
		processRezervations();
		while(!bookedRooms.isEmpty()) {
			System.out.println(bookedRooms.remove());
		}
		makeOddNumbersAvailable();
		processRezervation(pile1);
		unavailableRooms=new List<Room>();
		availableRooms=new List<Room>();
	}
	
	public static Room[] createRooms(){
		Room[] tempRooms=new Room[20];
		for(int i=0;i<4;i++) {
			for(int j=0;j<5;j++) {
			switch(i) {
			case 0: tempRooms[i*5+j]=new Room(Integer.toString(i*5+j+1),"Single",true);
			        break;
			case 1: tempRooms[i*5+j]=new Room(Integer.toString(i*5+j+1),"Double",true);
			        break;
			case 2: tempRooms[i*5+j]=new Room(Integer.toString(i*5+j+1),"Suite",true);
			        break;
			case 3: tempRooms[i*5+j]=new Room(Integer.toString(i*5+j+1),"Deluxe",true);
			        break;
		  }
	    }
	  }
		return tempRooms;
	}
	private void makeOddNumbersAvailable() {
		for(int i=1;i<=bookedRooms.getLength();i+=2) {
			bookedRooms.getEntry(i).changeAvailability();
		}
	}
	private void setAllPiles() {
		setPiles(pile1,createRooms());
		setPiles(pile2,createRooms());
		setPiles(pile3,createRooms());
		setPiles(pile4,createRooms());
	}
	private void setPiles(StackADT<Room> pile,Room[] rooms) {
		counter++;
		for(int i=counter*5-1;i>counter*5-6;i--) {
			pile.push(rooms[i]);
		}
	}
	private void processRezervations() {
		processRezervation(pile1);
		processRezervation(pile2);
		processRezervation(pile3);
		processRezervation(pile4);
	}
	private void processRezervation(StackADT<Room> pile) {
		while(!pile.isEmpty()) {
			if(pile.peek().getAvailability()==true) {
				pile.peek().changeAvailability();
				bookedRooms.add(pile.pop());
			}
		}
	}
	private void FileProcess() {
		FileIO.readFile(singleWaitList,"Single");
		FileIO.readFile(doubleWaitList, "Double");
		FileIO.readFile(suiteWaitList,"Suite");
		FileIO.readFile(deluxeWaitList, "Deluxe");
	}
}


