


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
	public String getReservationID() {
		return reservationID;
	}
	public String getCustomerName() {
		return customerName;
	}
	public String getRoomType() {
		return roomType;
	}
	@Override
	public boolean equals(Object obj) {
		assert getClass()==obj.getClass();
		Reservation temp=(Reservation)obj;
		return(reservationID.equals(temp.getCustomerName())&&
				customerName.equals(temp.getCustomerName())&&
				roomType.equals(temp.getRoomType()));
	}
	@Override
	public String toString() {
		return "Reservation Id :"+reservationID+
				" Customer Name :"+ customerName+
				" Room Type :"+ roomType;
	}
}