
import java.io.File;
import java.util.Scanner;
import java.io.IOException;

public class FileIO {
	//getting data from text file
	public static void readFile(QueueADT<Reservation> reservations,String roomType) {
		
		try {
			File file = new File("src/reservations.txt");
            Scanner scanner = new Scanner(file);
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String[] resInfo = scanner.nextLine().split(","); 
                if(resInfo[2].equals(roomType)){
                Reservation reservation=new Reservation(resInfo[0].strip(), resInfo[1].strip(), resInfo[2].strip());
                reservations.enqueue(reservation);
                }
            }
			scanner.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
