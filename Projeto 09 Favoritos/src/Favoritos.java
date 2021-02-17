import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Scanner;

class Contact {
	private String name;
	private ArrayList<Phone> phones;
    private boolean starred;

	public Contact(String name) {
		this.name = name;
        this.phones = new ArrayList<>();
        this.starred = false;
	}
	
	public ArrayList<Phone> getPhones() {
		return phones;
	}

	public String getName() {
		return name;
	}

	public boolean getStarred() {
		return starred;
	}

	public void addPhone(String label, String number) {
		if(!Phone.validateNumber(number)) {
			System.out.println("fail: número inválido");
			return;
		}
		phones.add(new Phone(label, number));
	}

	public void rmPhone(int index) {
		if(index < 0 || index >= phones.size()) {
			System.out.println("fail: índice inválido");
			return;
		}
		phones.remove(index);
	}

	public void setBookmark(boolean value) {
		this.starred = value;
	}

	public String toString() {
		int i = 0;
		String solver = this.name;
		for(Phone pn : this.phones){
			solver += "[" + i++ + ":" + pn.getLabel() + ":" + pn.getNumber() + "]"; 
		}
		// solver += "- " + this.name + " ";
		// for(int i = 0; i < phones.size(); i++) {
		// 	solver += "[" + i + ":" + this.phones.get(i) + "] ";
		// }
		return solver;
	
	}
}

class Phone {
	private String label;
	private String number;

	public Phone(String serial) {
		String[] temp = serial.split(":");
		this.label = temp[0];
		this.number = temp[1];
	}

	public Phone(String label, String number) {
		this.label = label;
		this.number = number;
	}

	public String getLabel() {
		return this.label;
	}

	public String getNumber() {
		return this.number;
	}

	public static boolean validateNumber(String number) {
		String solve = "0123456789()-";
		for(int i = 0; i < number.length(); i++) {
			if(solve.indexOf(number.charAt(i)) == -1) {
				return false;
			}
		}
		return true;
	}

	public String toString() {
		return this.label + ":" + this.number;
	}
}

class Phonebook {
	private TreeMap<String, Contact> contacts;
	private TreeMap<String, Contact> bookmarks;

	public Phonebook() {
		this.contacts = new TreeMap<>();
		this.bookmarks = new TreeMap<>();
	}

	public ArrayList<Contact> getContacts() {
		ArrayList<Contact> temp = new ArrayList<>();
		for(Contact contact : this.contacts.values()) {
			temp.add(contact);
		}
		return temp;
	}

	public Contact getContact(String name) {
		return this.contacts.get(name);
	}

	public void addContact(String name, ArrayList<Phone> phones) {
		if(phones.size() == 0 || name.equals("")) {
			return;
		}

		if(this.contacts.containsKey(name)) {
			for(Phone phone : phones) {
				this.contacts.get(name).addPhone(phone.getLabel(), phone.getNumber());
			}
			return;
		}

		this.contacts.put(name, new Contact(name));
		for(Phone phone : phones) {
			this.contacts.get(name).addPhone(phone.getLabel(), phone.getNumber());
		}
	}

	public boolean rmContact(String name) {
		if(this.contacts.containsKey(name)) {
			this.contacts.remove(name);
			this.bookmarks.remove(name);
			return true;
		}
		return false;
	}

	public void rmPhone(String name, int index) {
		if(this.contacts.containsKey(name)) {
			this.contacts.get(name).rmPhone(index);
			return;
		}
		System.out.println("fail: esse contato não existe");
	}

	public ArrayList<Contact> search(String pattern) {
		ArrayList<Contact> search = new ArrayList<>();

		for(Contact contact : this.contacts.values()) {
			if(contact.toString().contains(pattern)) {
				search.add(contact);
			}
		}

		return search;
	}

	public void Bookmark(String name) {
		if(!this.contacts.containsKey(name)) {
			System.out.println("fail: esse contato não existe");
			return;
		}

		if(!this.bookmarks.containsKey(name)) {
			this.contacts.get(name).setBookmark(true);
			this.bookmarks.put(name, this.contacts.get(name));
			return;
		}
		System.out.println("fail: esse contato já é favorito");
	}

	public void UnBookmark(String name) {
		if(!this.contacts.containsKey(name)) {
			System.out.println("fail: esse contato não existe");
			return;
		}
		if(this.bookmarks.containsKey(name)) {
			this.contacts.get(name).setBookmark(false);
			this.bookmarks.remove(name);
			return;
		}
		System.out.println("fail: esse contato não é favorito");
	}

	public ArrayList<Contact> getBookmarks() {
		ArrayList<Contact> temp = new ArrayList<>();

		for(Contact contact : this.bookmarks.values()) {
			temp.add(contact);
		}

		return temp;
	}

	public String toString(){
		String solver = "";
		
		for(Contact contact : this.bookmarks.values()){
			solver += "@ " + contact + "\n";
		}
		for(Contact contact : this.contacts.values()){
			if(!this.bookmarks.containsKey(contact.getName()))
				solver += "- " + contact + "\n";
		}
		return solver;
	}
}

public class Favoritos {
	public static void main(String[] args){
		Phonebook phonebook = new Phonebook();
		Scanner scanner = new Scanner(System.in);

		while(true) {
			String line = scanner.nextLine();
			String[] ui = line.split(" ");

			if(ui[0].equals("add")) {
				ArrayList<Phone> phones = new ArrayList<>();
				String name = ui[1];

				for(int i = 2; i < ui.length; i++) {
					String[] phone = ui[i].split(":");
					phones.add(new Phone(phone[0], phone[1]));
				}

				phonebook.addContact(name, phones);
			} else if(ui[0].equals("rmFone")) {
				phonebook.rmPhone(ui[1], Integer.parseInt(ui[2]));
			} else if(ui[0].equals("rm")) {
				phonebook.rmContact(ui[1]);
			} else if(ui[0].equals("star")) {
				phonebook.Bookmark(ui[1]);
			} else if(ui[0].equals("unstar")) {
				phonebook.UnBookmark(ui[1]);
			} else if(ui[0].equals("search")) {
				ArrayList<Contact> find = phonebook.search(ui[1]);

				String solver = "";
				for(Contact contact : find) {
					solver += contact.toString() + "\n";
				}

				System.out.println(solver);
			} else if(ui[0].equals("starred")) {
				for(Contact contact : phonebook.getBookmarks()) {
					System.out.println(contact);
				}
			} else if(ui[0].equals("show")) {
				System.out.println(phonebook);
			} else if(ui[0].equals("end")) {
				break;
			}
		}

		scanner.close();
    }
}