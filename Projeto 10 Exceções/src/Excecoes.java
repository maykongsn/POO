import java.util.Scanner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Contact {
	private String name;
	private ArrayList<Phone> phones;

	public Contact(String name) {
		this.name = name;
		this.phones = new ArrayList<>();
	}
	
	public ArrayList<Phone> getPhones() {
		return phones;
	}

	public String getName() {
		return name;
	}

	public void addPhone(String label, String number) {
        try {
            phones.add(new Phone(label, number));
        } catch(RuntimeException err) {
            System.out.println(err.getMessage());
        }
	}

	public void rmPhone(int index) {
        try {
            phones.remove(index);
        } catch(IndexOutOfBoundsException ex) {
            System.out.println("fail: índice inválido");
        }
	}

	public String toString() {
		String solver = "- " + this.name + " ";
		for(int i = 0; i < phones.size(); i++) {
			solver += "[" + i + ":" + this.phones.get(i) + "] ";
		}
		return solver;
	}
}

class ComparatorContacts implements Comparator<Contact> {

	@Override
	public int compare(Contact arg0, Contact arg1) {
		if(arg0 == null || arg1 == null)
			return -1;
		
		return arg0.getName().compareTo(arg1.getName());
	}
}

class Phone {
	private String label;
	private String number;

	public Phone(String serial) {
		String[] temp = serial.split(":");
        try {
            if(!validateNumber(temp[1])) {
                throw new RuntimeException("fail: número inválido");
            }
            this.label = temp[0];
            this.number = temp[1];
        } catch(IndexOutOfBoundsException err) {
            throw new RuntimeException("fail: serial inválida");
        }
	}

	public Phone(String label, String number) {
        if(!validateNumber(number)) {
            throw new RuntimeException("fail: número inválido");
        }
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
	private ArrayList<Contact> contacts;
	
	public Phonebook() {
		this.contacts = new ArrayList<>();
	}

	public int findContact(String name) {
		for(int i = 0; i < contacts.size(); i++) {
			if(contacts.get(i).getName().equals(name)) {
				return i;
			}
		}
		return -1;
	}

	public void addContact(String name, ArrayList<Phone> phones) {
		if(phones.size() == 0) {
			throw new NullPointerException("fail: nenhum fone");
		}

		if(findContact(name) == -1) {
			contacts.add(new Contact(name));
			for(Phone phone : phones) {
				contacts.get(findContact(name)).addPhone(phone.getLabel(), phone.getNumber());
			}
			Collections.sort(contacts, new ComparatorContacts());
			return;
		}
		for(Phone phone : phones) {
			contacts.get(findContact(name)).addPhone(phone.getLabel(), phone.getNumber());
		}
		Collections.sort(contacts, new ComparatorContacts());
	}

    public boolean rmContact(String name) {
        int index = this.findContact(name);
		if(index == -1)
            throw new NullPointerException("fail: contato não existe");
        
        this.contacts.remove(index);
        return true;
	}

	public void rmPhone(String name, int index) {
		try {
            if(findContact(name) != -1) {
                this.contacts.get(findContact(name)).rmPhone(index);;
            }
        } catch(IndexOutOfBoundsException ex) {
            System.out.println("fail: esse contato não existe");
        }
	}
	
	public ArrayList<Contact> search(String pattern) {
		ArrayList<Contact> search = new ArrayList<>();

		for(Contact contact : contacts) {
			if(contact.toString().contains(pattern)) {
				search.add(contact);
			}
		}

		return search;
	}

	public ArrayList<Contact> getContacts() {
		return this.contacts;
	}

	public String toString(){
        String saida = "";
        for(Contact contact : contacts)
            saida += contact + "\n";
        return saida + "";
    }

    public static void main(String[] args){
		Phonebook phonebook = new Phonebook();
		Scanner scanner = new Scanner(System.in);

		while(true) {
			String line = scanner.nextLine();
			String[] ui = line.split(" ");
            try {
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
               } else if(ui[0].equals("search")) {
                   ArrayList<Contact> find = phonebook.search(ui[1]);
   
                   String solver = "";
                   for(Contact contact : find) {
                       solver += contact.toString() + "\n";
                   }
   
                   System.out.println(solver);
               } else if(ui[0].equals("show")) {
                   System.out.println(phonebook);
               } else if(ui[0].equals("end")) {
                   break;
               } else {
                   System.out.println("Opção inválida");
               }

            } catch(IndexOutOfBoundsException ex) {
                System.out.println(ex.getMessage());
            } catch(RuntimeException err) {
                System.out.println(err.getMessage());
            }
		}

		scanner.close();
    }
}