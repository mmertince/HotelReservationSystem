public class Room {
	private String roomNumber;
	private String roomType;
	private boolean availability;

	public Room(String roomNumber, String roomType, boolean availabilitiy) {
		assert roomNumber != null && roomType != null;
		this.roomNumber = roomNumber;
		this.roomType = roomType;
		this.availability = availabilitiy;
	}

	@Override
	public String toString() {
		return "Room Number :" + roomNumber + " Room Type :" + roomType;
	}
}