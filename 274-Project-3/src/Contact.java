/**
 * @author joey pham
 * @date 16 October 2018
 * @description this class represents a contact inside a phonebook
 */
public class Contact {

	/**
	 * represents the contact's first name
	 */
	private String firstName;

	/**
	 * represents the contact's last name
	 */
	private String lastName;
	
	/**
	 * represents the contact's phone number
	 */
	private String phoneNumber;
	
	/**
	 * represents the contact's address
	 */
	private String address;
	
	/**
	 * represents the contact's city that they live in
	 */
	private String city;

	/**
	 * represents the contact's last name
	 */
	private String zipCode;
	
	/**
	 * Constructor
	 * @param newFirst   contact's first name
	 * @param newLast    contact's last name
	 * @param newPhone   contact's phone number
	 * @param newAddress contact's address
	 * @param newCity    contact's city
	 * @param newZip	 contact's zip code
	 */
	public Contact ( String newFirst, String newLast, String newPhone, String newAddress, String newCity, String newZip ) {
		firstName = newFirst;
		lastName = newLast;
		phoneNumber = newPhone;
		address = newAddress;
		city = newCity;
		zipCode = newZip;
	}
	
	/**
	 * sets the contact's first name to the parameter value
	 * @param newFirst name to set the contact's first name to
	 */
	public void setFirst ( String newFirst ) {
		firstName = newFirst;
	}
	
	/**
	 * retrieves the contact's first name
	 * @return contact's first name
	 */
	public String getFirst ( ) {
		return firstName;
	}
	
	/**
	 * sets the contact's last name to the parameter value
	 * @param newLast name to set the contact's last name to
	 */
	public void setLast ( String newLast ) {
		lastName = newLast;
	}
	
	/**
	 * retrieves the contact's last name
	 * @return contact's last name
	 */
	public String getLast ( ) {
		return lastName;
	}
	
	/**
	 * sets the contact's phone number to the parameter value
	 * @param newPhone number to set the contact's phone number to
	 */
	public void setPhone ( String newPhone ) {
		phoneNumber = newPhone;
	}
	
	/**
	 * retrieves the contact's phone number
	 * @return contact's phone number
	 */
	public String getPhone ( ) {
		return phoneNumber;
	}
	
	/**
	 * sets the contact's address to the parameter value
	 * @param newAddress address to set the contact's address to
	 */
	public void setAddress ( String newAddress ) {
		address = newAddress;
	}
	
	/**
	 * retrieves the contact's address
	 * @return contact's address
	 */
	public String getAddress ( ) {
		return address;
	}
	
	/**
	 * sets the contact's city to the parameter value
	 * @param newCity city to set the contact's city to
	 */
	public void setCity ( String newCity ) {
		city = newCity;
	}
	
	/**
	 * retrieves the contact's city
	 * @return contact's city
	 */
	public String getCity ( ) {
		return city;
	}
	
	/**
	 * sets the contact's zip code to the parameter value
	 * @param newZip zip code to set the contact's zip code to
	 */
	public void setZip ( int newZip ) {
		String zipStr = "" + newZip;
		zipCode = zipStr;
	}	
	
	/**
	 * retrieves the contact's zip code
	 * @return contact's zip code
	 */
	public String getZip ( ) {
		return zipCode;
	}
	
	/**
	 * creates a string that contains contact's info
	 * @return string representation of contact's info
	 */
	@Override
	public String toString ( ) {
		return "Name: " + firstName + " " + lastName + ", Phone Number: " + phoneNumber + ", Address: " + address + ", " + city + " " + zipCode;
	}
	
	/**
	 * compares two Contacts to see if they are the same person
	 * @param o explicit Contact to be compared
	 * @return true if the same person, false otherwise
	 */
	@Override
	public boolean equals ( Object o ) {
		if ( o instanceof Contact ) { // if the given object is a Contact
			Contact c = ( Contact ) o;
			// compare the two objects' first and last name
			// returns true if both's first and last are the same, false otherwise
			return this.firstName.equalsIgnoreCase ( c.firstName ) && this.lastName.equalsIgnoreCase ( c.lastName ); 
		} else {
			// returns false if the object given is not a Contact
			return false;
		}
	}
	
	/**
	 * compares two Contacts to determine order
	 * @param c contact to be compared
	 * @return 0 if the same, - if explicit comes first, + if "this" comes first
	 */
	public int compareTo ( Contact c ) {
		if ( this.getLast ( ).compareToIgnoreCase ( c.getLast ( ) ) == 0 ) {
			return this.getFirst ( ).compareToIgnoreCase( c.getLast ( ) );
		} else {
			return this.getLast ( ).compareToIgnoreCase ( c.getLast ( ) );
		}
	}
}
