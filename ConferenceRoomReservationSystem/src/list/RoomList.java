package list;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import room.Room;
import user.EPuser;
import user.NMuser;

public class RoomList extends GenericList<Room>{
	private RoomList bufRL;
	
	public RoomList() {
		
	}
	
	@Override
	public void deleteByKey(int k) {
		Iterator<Room> itr = this.getList().iterator();
		Room temp;

		while (itr.hasNext()) {
			temp = itr.next();

			if (temp.getKey() == k) {
				itr.remove();
				break;
			}
		}
	}
	
	public RoomList findByEPKey(int epkey) {
		bufRL = new RoomList();
		Iterator<Room> itr = this.getList().iterator();
		Room temp;
		
		while (itr.hasNext()) {
			temp = itr.next();

			if (temp.getEnterpriseKey() == epkey) {
				bufRL.add(temp);
			}
		}
		
		return bufRL;
	}
	
	public RoomList findBookableRoomList(Room room,Date date) {
		bufRL = new RoomList();
		Iterator<Room> itr = this.getList().iterator();
		Iterator<Room> itr2 = this.getList().iterator();
		Room temp;
		
		while (itr.hasNext()) {
			temp = itr.next();

			if(temp.getCity().equals(room.getCity())) {
				if(temp.getMaxNumber() >= room.getMaxNumber()) {
					if(isReservedDate(date))
						bufRL.add(temp);
				}
			}	
		}
		
		if(bufRL.getList().size() == 0) {
			while (itr2.hasNext()) {
				temp = itr2.next();

				if(temp.getMaxNumber() >= room.getMaxNumber()) {
					if(isReservedDate(date))
						bufRL.add(temp);
				}
			}
		}
		
		return bufRL;
	}
	
	public boolean isReservedDate(Date date) {
		Iterator<Room> itr = this.getList().iterator();
		Room temp;
		
		while (itr.hasNext()) {
			temp = itr.next();

			int end = temp.getBookingUserKeyList().size();
			
			for(int i=0;i<end;i++) {
				if(temp.getBookingUserKeyList().get(i).getDate().equals(date)) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	@Override
	public Room findByKey(int k) {
		Iterator<Room> itr = this.getList().iterator();
		Room temp;

		while (itr.hasNext()) {
			temp = itr.next();

			if (temp.getKey() == k) {
				return temp;
			}
		}

		return null;
	}

	@Override
	public Room findByName(String name) {
		Iterator<Room> itr = this.getList().iterator();
		Room temp;

		while (itr.hasNext()) {
			temp = itr.next();

			if (temp.getName().equals(name)) {
				return temp;
			}
		}

		return null;
	}


	@Override
	public boolean isItDuplicated(String name) {
		// search by id
		Room temp = findByName(name);

		if (temp != null)
			return true;
		else
			return false;
	}

}