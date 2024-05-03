


public class Reservation {

	private String reservationID;
	private String customerName;
	private String roomType;

	public Reservation(String reservationID, String customerName, String roomType) {
		assert reservationID != null && customerName != null;
		this.reservationID = reservationID;
		this.customerName = customerName;
		this.roomType = roomType;
	}
}