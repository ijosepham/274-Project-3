/**
 * @author joey pham
 * @date 16 October 2018
 * @description this class represents a list full of contacts and their personal information
 */

public class LinkedList {
	public static class Node {
		/**
		 * represent the contact's personal information
		 */
		private Contact data;
		
		/**
		 * represetns the next node in the linked list
		 */
		private Node next;
		
		/**
		 * constructor
		 * @param c contact to assign
		 */
		public Node ( Contact c ) {
			data = c;
			next = null;
		}
		
		/**
		 * constructor
		 * @param c contact to assign 
		 * @param n node to assign to next
		 */
		public Node ( Contact c, Node n ) {
			data = c;
			next = n;
		}
	}
	
	/**
	 * this represents the first node in the linked list
	 */
	private Node first;
	
	/**
	 * this represents the last node in the lniked list
	 */
	private Node last;
	
	/**
	 * constructor
	 */
	public LinkedList ( ) {
		first = null;
		last = null;
	}
	
	/**
	 * check to see if the linked list is empty or not
	 * @return true or false whether if the list is empty
	 */
	public boolean isEmpty ( ) {
		return first == null;
	}
	
	/**
	 * calculates the size of the linkedlist
	 * @return the size of the linked list
	 */
	public int size ( ) {
		int count = 0; // counter
		Node n = first; 
		while ( n != null ) { // until it hits a null
			count = count + 1; // keep counting
			n = n.next;
		}
		return count; 
	}
	
	/**
	 * get the information of a contact at the given position in the phonebook
	 * @param index position in the phonebook
	 * @return 		contact at the given position
	 */
	public Contact get ( int index ) {
		if ( index < 0 || index >= size ( ) ) { // if index is outside of the list
			System.out.println ( "Index out of bounds." );
			return new Contact ( "", "", "", "", "", ""); // returns a blank contact
		} else { // if within the list
			Node n = first;
			for ( int i = 1; i <= index; i++ ) { // iterate until given position
				n = n.next;
			}
			return n.data; // return contact at the given position
		}
	}

	/**
	 * set the information of the contact at the given position to the given contact info
	 * @param index target index to replace contact
	 * @param c		contact to replace index with
	 */
	public void set ( int index, Contact c ) {
		if ( index < 0 || index >= size ( ) ) { // make sure not outta bounds
			System.out.println ( "Index out of bounds." );
		} else {
			Node n = first;
			for ( int i = 1; i <= index; i++ ) { // iterate to given position
				n = n.next;
			}
			n.data = c; // assign the target index with the given info
		}
	}
	
	/**
	 * add contact to the end of the list
	 * @param c contact to add to the phonebook
	 */
	public void add ( Contact c ) {
		if ( isEmpty ( ) ) { // if list is empty before adding
			first = new Node ( c ); // set the first and last values to the given value
			last = first;
		} else { // if not empty
			Node n = new Node ( c );
			last.next = n; // add the contact to the end of the list
			last = n; // update last
		}
	}

	/**
	 * remove contact at the given index
	 * @param index target position to remove the contact at it
	 */
	public void remove ( int index ) {
		if ( index == 0 ) {
			first = first.next; // move one space over
			if ( first == null ) { // if the new first is null, that means there was only one item in the list
				last = null; 
			}
		} else {
			Node n = first;
			for ( int i = 1; i < index; i++ ) { // iterate to position
				n = n.next;
			}
			n.next = n.next.next; // replace current contact with the next contact, which moves eveything past that back 1 space too
			if ( n.next == null ) { // if the next spot is null
				last = n; // the new last will be the current spot
			}
		}
	}
	
	/**
	 * remove the user with the given first and last name from the phonebook
	 * @param firstName target first name to remove
	 * @param lastName  target last name to remove
	 */
	public void remove ( String firstName, String lastName ) {
		Contact rem = new Contact ( firstName, lastName, "", "", "", "" ); // make a temp contact with the given information to compare to
		if ( !isEmpty ( ) ) { // if not empty
			if ( rem.equals( first.data ) ) { // check if first is the target
				first = first.next; // replace the first contact and everything else shifts
				if ( first == null ) { // if it is now null, that means the list is now empty
					last = null; // update the last element
				}
			} else { // if the target is not at the first element
				Node n = first;
				while ( n.next != null && !( rem.equals ( n.next.data ) ) ) { // keep going until you find a match, or you hit the end
					n = n.next;
				}
				if ( n.next == null ) { // if you hit the end, that person doesn't exist
					System.out.print ( "\n" );
					System.out.println ( "That person does not exist." );
					System.out.print ( "\n" );
				} else { // if you found a match
					n.next = n.next.next; // replace the current contact with the next one
					if ( n.next == null ) { // if this is the new last
						last = n; // update the last to this
					}
				}
			}
		}
	}
	
	/**
	 * search for the target contact with the given name and returns his index
	 * @param firstName target's first name
	 * @param lastName  target's last name
	 * @return -1 if nonexistant, or the index of the contact
	 */
	public int search ( String firstName, String lastName ) { // returns index of the match or -1 if nonexistent
		int index = 0;
		Contact searchee = new Contact ( firstName, lastName, "", "", "", "" ); // target contact to compare to
		if ( !isEmpty ( ) ) { // if not empty
			if ( searchee.equals( first.data ) ) { // if the first contact is a match
				return index; // return 0
			} else { // if not first
				Node n = first;
				while ( n.next != null && !( searchee.equals ( n.next.data ) ) ) { // goes until it hits the end or a match
					n = n.next;
					index = index + 1;
				}
				if ( n.next == null ) { // if you hit the end, person doesn't exist
					System.out.print ( "\n" );
					System.out.println ( "That person does not exist." );
					System.out.print ( "\n" );
					return -1;
				} else { // if found a match
					return index; // returns the index
				}
			}
		} // reaches here if found nothing, return -1
		return -1;
	}	
	
	/**
	 * search for all contacts with the target last name
	 * @param lastName target's last name
	 * @return a list of all the found contacts with the last name
	 */
	public LinkedList search ( String lastName ) {
		LinkedList matches = new LinkedList ( ); // initialize
		if ( !isEmpty ( ) ) { // if not empty
			if ( lastName.equalsIgnoreCase( first.data.getLast ( ) ) ) { // if the first contact has the target last name
				matches.add ( first.data ); // add them to the list
			} // not an else because you want to find all the matches
			Node n = first;
			while ( n.next != null ) { // until you hit the end
				if ( n.next.data.getLast ( ).equalsIgnoreCase( lastName ) ) { // if a match
					matches.add ( n.next.data ); // add them to the list
				}
				n = n.next; // iterate to next contact
			}
		}
		return matches;
	}
	
	/**
	 * search for all contacts with the target zip code
	 * @param zipCode target's zip code
	 * @return list of matches with the target zip
	 */
	public LinkedList search ( int zipCode ) { // pass in an int, because last name already uses string
		LinkedList matches = new LinkedList ( ); // initialize
		if ( !isEmpty ( ) ) { // if not empty
			if ( zipCode == Integer.parseInt ( first.data.getZip ( ) ) ) { // if first has a matching zip
				matches.add ( first.data ); // add to the list
			}
			Node n = first;
			while ( n.next != null ) { // until you hit the end 
				if ( Integer.parseInt ( n.next.data.getZip ( ) ) == zipCode ) { // if the current contact is a match
					matches.add ( n.next.data ); // add to list
				}
				n = n.next; // iterate
			}
		}
		return matches;
	}
	
	/**
	 * sort the list to be in alphabetical order
	 */
	public void sort ( ) {
		for ( int current = 0; current < size ( ); current++ ) { // iterate same amount of times to length of list
			int indexOfLowest = current; // initialize from the first position
			for ( int next = current + 1; next < size ( ); next++ ) {
				if ( get ( indexOfLowest ).compareTo ( get ( next ) ) > 0 ) { // compares next contact with current, + if next comes first
					indexOfLowest = next; // keep track of the index of the lowest value
				}
			}
			Contact swap = get ( current ); // contact at leftmost index
			set ( current, get ( indexOfLowest ) ); // at the left most index, set it with the contact of the next person in line of alphabetical order 
			set ( indexOfLowest, swap ); // swap the places of the two contacts
		}
	}
}
