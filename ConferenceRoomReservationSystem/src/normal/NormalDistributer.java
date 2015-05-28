package normal;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import transmission.TransmissionData;

public class NormalDistributer extends JPanel{
	private JTabbedPane NMUserTab = new JTabbedPane();
	private ListBookableRoomModel lbookablerm = new ListBookableRoomModel();
	private ListBookableRoomView lbookalberv = new ListBookableRoomView();
	private ListBookableRoomController lbookalberc;
	private ListBookedRoomModel lbookedrm = new ListBookedRoomModel();
	private ListBookedRoomView lbookedrv = new ListBookedRoomView();
	private ListBookedRoomController lbookedrc;
	
	public NormalDistributer() {
		lbookablerm.addObserver(lbookalberv);
		lbookalberc = new ListBookableRoomController(lbookablerm, lbookalberv);
		
		lbookedrm.addObserver(lbookedrv);
		lbookedrc = new ListBookedRoomController(lbookedrm, lbookedrv);
		
		this.setLayout(new BorderLayout());
		NMUserTab.add("예약가능한 회의실 조회", lbookalberv);
		NMUserTab.add("예약한 회의실 조회", lbookedrv);
		this.add(NMUserTab, BorderLayout.CENTER);
	}
	
	public void distribute(TransmissionData data) {
		if (data.getFlags() < 60) {
			// book room
		} else if (data.getFlags() < 70) {
			// list bookalbeRoom
		} else if (data.getFlags() < 80) {
			// show Room info
		} else if (data.getFlags() < 90) {
			// list booked room
		}
	}
}
