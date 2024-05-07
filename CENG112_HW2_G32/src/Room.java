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
	public void changeAvailability() {
		availability=!availability;
	}
	public String getRoomNumber() {
		return roomNumber;
	}
	public String getRoomType() {
		return roomType;
	}
	public boolean getAvailability() {
		return availability;
	}
	@Override
	public String toString() {
		return "Room Number :" + roomNumber + " Room Type :" + roomType+" Available :"+availability;
	}
}