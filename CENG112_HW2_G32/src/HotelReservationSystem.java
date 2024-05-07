
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
		printPiles();
		singleWaitList=new WaitingLine<Reservation>();
		doubleWaitList=new WaitingLine<Reservation>();
		suiteWaitList=new WaitingLine<Reservation>();
		deluxeWaitList=new WaitingLine<Reservation>();		
		FileProcess();
		bookedRooms=new List<Room>();
		processRezervations();
		printReservations();
		makeOddNumbersAvailable();
		printPiles();
		processRezervations();
		printReservations();
		unavailableRooms=new List<Room>();
		availableRooms=new List<Room>();
		addToAvailabilityList(pile1);
		addToAvailabilityList(pile2);
		addToAvailabilityList(pile3);
		addToAvailabilityList(pile4);
		printAvailabilityLists();
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
		for(int i=bookedRooms.getLength();i>=1;i=i-2) {
			bookedRooms.getEntry(i).changeAvailability();
			pushRoomToPile(bookedRooms.remove(i));
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
		processRezervation(pile1,singleWaitList);
		processRezervation(pile2,doubleWaitList);
		processRezervation(pile3,suiteWaitList);
		processRezervation(pile4,deluxeWaitList);
	}
	private void processRezervation(StackADT<Room> pile,QueueADT<Reservation> waitList) {
		while(!pile.isEmpty()&&!waitList.isEmpty()) {
			if(pile.peek().getAvailability()==true) {
				pile.peek().changeAvailability();
				waitList.dequeue();
				Room temp=pile.pop();
				if(!bookedRooms.contains(temp))
				bookedRooms.add(temp);
			}
		}
	}
	private void FileProcess() {
		FileIO.readFile(singleWaitList,"Single");
		FileIO.readFile(doubleWaitList, "Double");
		FileIO.readFile(suiteWaitList,"Suite");
		FileIO.readFile(deluxeWaitList, "Deluxe");
	}
	private void pushRoomToPile(Room room) {
		switch(room.getRoomType()) {
		case "Single":pile1.push(room);
		break;
		case "Double":pile2.push(room);
		break;
		case "Suite":pile3.push(room);
		break;
		case "Deluxe":pile4.push(room);
		break;
		}
	}
	private void addToAvailabilityList(StackADT<Room> pile) {
		while(!pile.isEmpty()) {
			availableRooms.add(pile.pop());
			}
		while(!bookedRooms.isEmpty()) {
				unavailableRooms.add(bookedRooms.remove());
		}
	}
	private void printPiles() {
		System.out.println("***************************************************************************");
		System.out.println("");
		System.out.println("Single pile of room");
		pile1.printInfo();
		System.out.println("Double pile of room");
		pile2.printInfo();
		System.out.println("Suite pile of room");
		pile3.printInfo();
		System.out.println("Deluxe pile of room");
		pile4.printInfo();
	}
	private void printReservations() {
		System.out.println("***************************************************************************");
		System.out.println("");
		System.out.println("Single waiting line of reservations");
		singleWaitList.printInfo();
		System.out.println("Double waiting line of reservations");
		doubleWaitList.printInfo();
		System.out.println("Suite waiting line of reservations");
		suiteWaitList.printInfo();
		System.out.println("Deluxe waiting line of reservations");
		deluxeWaitList.printInfo();
	}
	private void printAvailabilityLists() {
		System.out.println("***************************************************************************");
		System.out.println("");
		System.out.println("Unavailable Rooms");
		sortList();
		unavailableRooms.printInfo();
		System.out.println("Available Rooms");
		availableRooms.printInfo();
	}
	private void sortList() {
		Room temp;
		for(int i=1;i<=unavailableRooms.getLength();i++) {
			for(int j=i;j<=unavailableRooms.getLength();j++) {
				if(Integer.parseInt(unavailableRooms.getEntry(i).getRoomNumber())>Integer.parseInt(unavailableRooms.getEntry(j).getRoomNumber())) {
					temp=unavailableRooms.getEntry(j);
					unavailableRooms.replace(j,unavailableRooms.getEntry(i));
					unavailableRooms.replace(i, temp);
				}
			}
		}
	}
}


