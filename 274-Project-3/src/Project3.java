/**
 * @author Joey Pham
 * @date 21 October 2018
 * @description the user is given a phone book, the user may interact with the phonebook by adding, removing, searching, 
 * 				updating, and showing contacts. once the user is finished, the phonebook is updated with all the user's modifications
 */

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.*;

public class Project3 {
	public static void main ( String [ ] args ) { 
		LinkedList contactsList = new LinkedList ( ); // initialize
		fillList ( contactsList ); // fill the array with values from the contacts.txt
		contactsList.sort ( );
		int menuChoice = dispAndGetMenu ( 0 ); // show and get menu choice
		while ( menuChoice != 6 ) { // as long as the user hasn't quit
			doMenuChoice ( contactsList, menuChoice ); // do choice
			contactsList.sort ( ); // always sort after doing something
			menuChoice = dispAndGetMenu ( 0 ); // repeat
		}
		writeFile ( contactsList ); // write all the new contacts in abc order back to the txt file
		System.out.println( "\n" + "Goodbye!" ); // cya@
	}	
	
	/**
	 * fills the phonebook with the contacts' information 
	 * @param contactsList phonebook to fill
	 */
	public static void fillList ( LinkedList contactsList ) {
		try { // needed to read in files
			Scanner read = new Scanner ( new File ( "contacts.txt" ) );
			do {
				String [ ] contactData = read.nextLine ( ).split ( "," ); // create an array to have the 0. first name, 1. last name, 2. phone, 3. address, 4. city, 5. zip stored
				contactsList.add ( new Contact ( contactData [ 0 ], contactData [ 1 ], contactData [ 2 ], contactData [ 3 ], contactData [ 4 ], contactData [ 5 ] ) ); 
				// add the new contact with values to set to to the LinkedList
			} while ( read.hasNextLine ( ) ); // as long as there is another line to read in
		} catch ( FileNotFoundException fnf ) {
			System.out.println ( "File was not found" );
		}
	}
	
	/**
	 * do action corresponding to the user's menu choice
	 * @param contactsList list full of all the contacts and their info
	 * @param menuChoice   the choice the user chose
	 */
	public static void doMenuChoice ( LinkedList contactsList, int menuChoice ) { 
		if ( menuChoice == 1 ) {
			addContact( contactsList );
		} else if ( menuChoice == 2 ) {
			removeContact ( contactsList, dispAndGetMenu ( 2 ) );
		} else if ( menuChoice == 3 ) {
			searchContact ( contactsList, dispAndGetMenu ( 3 ) );
		} else if ( menuChoice == 4 ) {
			updateContact ( contactsList );
		} else if (menuChoice == 5 ) {
			showContacts ( contactsList );
		}
	}
	
	/**
	 * display corresponding menu and get the user's valid menu choice
	 * @param menu number to pick which menu is going to be displayed
	 * @return returns the user's menu choice
	 */
	public static int dispAndGetMenu ( int menu ) {
		if ( menu == 0 ) { // if menu to display is main menu
			System.out.println ( " Contacts Menu" );
			System.out.println ( "1. Add Contact" );
			System.out.println ( "2. Remove Contact" );
			System.out.println ( "3. Search for Contact" );
			System.out.println ( "4. Update Contact" );
			System.out.println ( "5. Show Contacts" );
			System.out.println ( "6. Quit" );
			System.out.print ( "Enter menu choice: " );
			return CheckInput.getIntRange ( 1, 6 ); // choose 1-6
		} else if ( menu == 2 ) { // submenu 2 for removing contacts
			System.out.print ( "\n" );
			System.out.println ( " Contact Removal Menu" );
			System.out.println ( "1. First and Last Name" );
			System.out.println ( "2. Index" );
			System.out.print ( "Remove by: " );
			return CheckInput.getIntRange ( 1, 2 ); // choose 1 or 2
		} else if ( menu == 3 ) { // submenu 3 for searching for a contact
			System.out.print ( "\n" );
			System.out.println ( " Contact Searching Menu" );
			System.out.println ( "1. Last Name" );
			System.out.println ( "2. Zip Code" );
			System.out.print ( "Search by: " );
			return CheckInput.getIntRange ( 1, 2 );
		} else { // submenu 4 for updating contacts
			System.out.print ( "\n" );
			System.out.println ( " Contact Updating Menu" );
			System.out.println ( "1. First Name" );
			System.out.println ( "2. Last Name" );
			System.out.println ( "3. Phone Number" );
			System.out.println ( "4. Address" );
			System.out.println ( "5. City " );
			System.out.println ( "6. Zip Code" );
			System.out.print ( "Update category: " );
			return CheckInput.getIntRange ( 1, 6 ); // choose 1-6
		}
	}
	
	/**
	 * add a contact to the phonebook
	 * @param contactsList the list to add the contact to
	 */
	public static void addContact ( LinkedList contactsList ) { 
		System.out.print ( "\n" + "Enter the contact's first and last name: " ); // prompt
		String [ ] firstAndLastName = CheckInput.getString( ).split( " " ); // split the name into first and last
		String firstName = firstAndLastName [ 0 ]; // assign
		String lastName = firstAndLastName [ 1 ];
		System.out.print ( "Enter the contact's phone number: " );
		String phoneNumber = CheckInput.getString ( );
		System.out.print ( "Enter the contact's address: " );
		String address =  CheckInput.getString ( );
		System.out.print ( "Enter the contact's city: " );
		String city =  CheckInput.getString ( );
		System.out.print ( "Enter the contact's zip code: " );
		String zip =  CheckInput.getString( );
		contactsList.add ( new Contact ( firstName, lastName, phoneNumber, address, city, zip ) );
		// adds to the LinkedList a new contact with the user given information
		System.out.print ( "\n" );
	}
	
	/**
	 * remove the contact from the phonebook
	 * @param contactsList the phonebook of contacts
	 * @param menuChoice
	 */
	public static void removeContact ( LinkedList contactsList, int menuChoice ) { 
		if ( menuChoice == 1 ) { // if they chose to remove contact by first and last name
			System.out.print ( "\n" + "Enter the contact's first name and last name: " ); // prompt
			String [ ] firstAndLastName = CheckInput.getString( ).split( " " ); // split into first and last
			String firstName = firstAndLastName [ 0 ]; // assign
			String lastName = firstAndLastName [ 1 ];
			contactsList.remove ( firstName, lastName ); // remove with the given first and last
		} else if ( menuChoice == 2 ) { // if by index
			System.out.print ( "\n" + "Enter an index 1 - " + contactsList.size ( ) + ":" ); // prompt given the range 
			int index = CheckInput.getIntRange ( 1, contactsList.size ( ) ); // make sure within range
			contactsList.remove ( index - 1 ); // -1 because user will put in 1, when computer means 0
		}
		System.out.print ( "\n" );
	}
	
	/**
	 * search for a contact in the phonebook
	 * @param contactsList phonebook to update
	 * @param menuChoice   if the user chose to search by last name or index
	 */
	public static void searchContact ( LinkedList contactsList, int menuChoice ) { 
		if ( menuChoice == 1 ) { // if chosen to search by last name
			System.out.print ( "\n" + "Enter the contact's last name: " ); // prompt
			String lastName = CheckInput.getString ( ); // assign
			LinkedList matches = contactsList.search ( lastName ); // .search will return an array with corresponding last names
			System.out.print ( "\n" );
			if ( matches.isEmpty ( ) ) { // matches is empty if user gave a last name no on has
				System.out.println ( "No one with that last name exists." );
			} else { // if the list is not empty
				for ( int i = 0; i < matches.size ( ); i++ ) { // iterate to print the given matches
					System.out.print ( matches.get ( i ).toString ( ) ); // prints out the matching contacts
				}
			}
		} else if ( menuChoice == 2 ) { // if chosen to search by zip
			System.out.print ( "\n" + "Enter the contact's zip code: " ); // prompt
			int zipCode = CheckInput.getInt ( ); // assign
			LinkedList matches = contactsList.search ( zipCode ); // gets array of matches
			System.out.print ( "\n" );
			if ( matches.isEmpty ( ) ) { // if match array is empty
				System.out.println ( "No one with that zip code exists." ); // means no matches were found
			} else { // if not empty
				for ( int i = 0; i < matches.size ( ); i++ ) {
					System.out.print ( matches.get ( i ).toString ( ) ); // prints the matches
				}
			}
		}
		System.out.print ( "\n" + "\n" );
	}
	
	/**
	 * update one category if a contact 
	 * @param contactsList phonebook to update
	 */
	public static void updateContact ( LinkedList contactsList ) { 
		System.out.print ( "\n" + "Enter the contact's first and last name: " ); // prompt
		String [ ] firstAndLastName = CheckInput.getString ( ).split( " "); // split the input into an array for first and last name
		String firstName = firstAndLastName [ 0 ];
		String lastName = firstAndLastName [ 1 ];
		int index = contactsList.search ( firstName, lastName ); // returns the index of the use target
		if ( index >= 0 ) { // if it's not negative, which would mean the target doesn't exist
			int menuChoice = dispAndGetMenu ( 4 ); // display submenu 4 and get choice of which category to change
			System.out.print ( "\n" + "Set it to: ");
			if ( menuChoice == 1 ) { // change the category corresponding to the user's choice
				contactsList.get( index + 1 ).setFirst ( CheckInput.getString ( ) ); 
			} else if ( menuChoice == 2 ) {
				contactsList.get( index + 1).setLast ( CheckInput.getString ( ) );
			} else if ( menuChoice == 3 ) {
				contactsList.get( index + 1 ).setPhone ( CheckInput.getString ( ) );
			} else if ( menuChoice == 4 ) {
				contactsList.get( index + 1 ).setAddress ( CheckInput.getString ( ) );
			} else if ( menuChoice == 5 ) {
				contactsList.get( index + 1 ).setCity ( CheckInput.getString ( ) );
			} else {
				contactsList.get( index + 1 ).setZip ( CheckInput.getInt ( ) );
			}
			System.out.print ( "\n" );
		}
	}
	
	/**
	 * display all the contacts in the phonebook which is in alphabetical order
	 * @param contactsList phonebook to display the contacts from 
	 */
	public static void showContacts ( LinkedList contactsList ) {
		System.out.print ( "\n" );
		for ( int i = 0; i < contactsList.size ( ); i++ ) { // iterate through all contacts in the list
			if ( i < 9 ) { // this is to make the numbers line up
				System.out.println ( " " + ( i + 1 ) + ". " + contactsList.get ( i ).toString ( ) );
				// i + 1 because first index is 0, but user is used to seeing 1 as first
			} else {
				System.out.println ( ( i + 1 ) + ". " + contactsList.get ( i ).toString ( ) );
			}
		}
		System.out.print ( "\n" );
	}	
	
	/**
	 * write the updated phonebook to the contacts.txt in alphabetical order
	 * @param contactsList phonebook to get the contacts' information from
	 */
	public static void writeFile ( LinkedList contactsList ) {
		try {
            PrintWriter writer = new PrintWriter ( "contacts.txt" );
            for ( int row = 0; row < contactsList.size ( ); row++ ) {
            	// this is basically a toString right here, adapted to the original contacts.txt
            	String line = contactsList.get ( row ).getFirst ( ) + "," + contactsList.get ( row ).getLast ( ) + "," + contactsList.get ( row ).getPhone ( ) 
            			+ "," + contactsList.get ( row ).getAddress ( ) + "," + contactsList.get ( row ).getCity ( ) + "," + contactsList.get ( row ).getZip ( );
                writer.println ( line );
            }
            writer.close();
        } catch ( FileNotFoundException fnf ) {
            System.out.println("File was not found.");
        }
	}
}