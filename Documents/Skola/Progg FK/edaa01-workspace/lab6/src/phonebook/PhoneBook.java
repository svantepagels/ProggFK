package phonebook;
import java.io.Serializable;
import java.util.*;
import java.util.Map.Entry;

public class PhoneBook implements Serializable{
	private Map<String,ArrayList<String>> phoneBook;
	private int size;
	
	public PhoneBook() {
		phoneBook = new HashMap<String,ArrayList<String>>(); 
	
	}
	
	/** 
	 * Associates the specified number with the specified 
	 * name in this phone book. 
	 * post: If the specified name is not present in this phone book,
	 *        the specified name is added and associated  with
	 *  	  the specified number. Otherwise the specified 
	 *  	  number is added to the set of number associated with name.
	 * @param name The name for which a phone number is to be added
	 * @param number The number associated with the specified name
	 * @return true if the specified name and number was inserted
	 */
	public boolean put(String name, String number) {
		if(!phoneBook.containsKey(name)){
			ArrayList numbers = new ArrayList<String>(); 
				numbers.add(number);
				phoneBook.put(name,numbers);
				size++;
				return true;
			}else if(phoneBook.get(name).contains(number)){
				phoneBook.get(name).add(number);
				return true;
			}
		return false;
	}
	
	
	/**
	 * Removes the the specified name from this phone book.
	 * post: If the specified name is present in this phone book,
	 * 		 it is removed. Otherwise this phone book is
	 * 		 unchanged.
	 * @param name The name to be removed
	 * @return true if the specified name was present
	 */
	public boolean remove(String name) {
		if(phoneBook.containsKey(name)){
			phoneBook.remove(name);
			size--;
			return true;
		}
		return false;
	}
	
	/**
	 * Removes the specified number from name in this phone book.
	 * post: If the specified name and number is present in this phone book,
	 * 		 the number is removed. Otherwise this phone book is
	 * 		 unchanged.
	 * @param number The number to be removed
	 * @param name The name from which to remove the number
	 * @return true if the specified name and number was present
	 */
	public boolean removeNumber(String name, String number) {
		if(phoneBook.containsKey(name)&& phoneBook.get(name).contains(number)){
			phoneBook.get(name).remove(number);
			return true;
		}
		return false;
	}
	
	/**
	 * Retrieves a set of phone numbers for the specified name. If the 
	 * specified name is not present in this phone book an empty set is 
	 * returned.
	 * @param name The name whose associated phone numbers are to be returned  
	 * @return The phone numbers associated with the specified name
	 */
	public ArrayList<String> findNumber(String name) {
		if(phoneBook.containsKey(name)){
			return phoneBook.get(name);			
		} 
		return new ArrayList<String>();
	}
	
	/**
	 * Retrieves a set of names associated with the specified phone number. 
	 * If the specified number is not present in this phone book an empty 
	 * set is returned.
	 * @param number The number for which the set of associated
	 * names is to be returned.
	 * @return The names associated with the specified number
	 */
	public ArrayList<String> findNames(String number) {
		ArrayList names = new ArrayList<String>(); 
		for(String name:phoneBook.keySet()){
			if(phoneBook.get(name).contains(number)){
				names.add(name);
			}	
		}return names;
	}
	
	/**
	 * Retrieves the set of all names present in this phone book.
	 * The set's iterator will return the names in ascending order
	 * @return The set of all names present in this phone book
	 */
	public ArrayList<String> names() {
		ArrayList names = new ArrayList<String>(); 
		for(String name:phoneBook.keySet()){
			names.add(name);
		}		
		return names;
	}
	
	/**
	 * Returns true if this phone book is empty.
	 * @return true if this phone book is empty
	 */	
	public boolean isEmpty() {
		return phoneBook.isEmpty();
	}
	
	/**
	 * Returns the number of names in this phone book.
	 * @return The number of names in this phone book
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Returns a string containing all names and numbers (one entry per row) in the the phonebook.
	 * @return A string containing all names and numbers (one entry per row) in the the phonebook
	 */
	@Override
	public
	String toString(){
		StringBuilder sb = new StringBuilder(); 
		for(String name:phoneBook.keySet()){
			sb.append(name + "\t");
			for(String number:phoneBook.get(name)){
				sb.append(number + " ");			
			}
			sb.append("\n");
			}
		return sb.toString();
	}

}