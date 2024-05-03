
public class HotelReservationSystem {
	
	static int counter;

	public static void main(String[] args) {
		System.out.println("deneme");
		Room[] tempRooms=new Room[20];
		StackADT<Room> pile1=new Pile<Room>(5);
		setPiles(pile1,createRooms());
		StackADT<Room> pile2=new Pile<Room>(5);
		setPiles(pile2,createRooms());
		StackADT<Room> pile3=new Pile<Room>(5);
		setPiles(pile3,createRooms());
		StackADT<Room> pile4=new Pile<Room>(5);
		setPiles(pile4,createRooms());
		
		while(!pile1.isEmpty()) {
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
		}
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
			case 2: tempRooms[i*5+j]=new Room(Integer.toString(i*5+j+1),"Suit",true);
			        break;
			case 3: tempRooms[i*5+j]=new Room(Integer.toString(i*5+j+1),"Deluxe",true);
			        break;
		  }
	    }
	  }
		return tempRooms;
	}
	public static void setPiles(StackADT<Room> pile,Room[] rooms) {
		counter++;
		for(int i=counter*5-1;i>counter*5-6;i--) {
			pile.push(rooms[i]);
		}
	}
}
