import java.util.ArrayList;

public class Contato {
	private String name;
	private ArrayList<Fone> fones;

	public Contato(String name, ArrayList<Fone> fone) {
		this.name = name;
		this.fones = fone;
	}

	public String getName() {
		return this.name;
	}

	public ArrayList<Fone> getFones() {
		return this.fones;
	}

	public void addFone(String label, String number) {
        try {
            fones.add(new Fone(label, number));
        } catch(RuntimeException err) {
            System.out.println(err.getMessage());
        }
	}

	public void rmFone(int index) {
        try {
            fones.remove(index);
        } catch(IndexOutOfBoundsException ex) {
            System.out.println("fail: índice inválido");
        }
	}

	public String toString() {
		String solver = "- " + this.name + " ";
		for(int i = 0; i < fones.size(); i++) {
			solver += "[" + i + ":" + this.fones.get(i) + "] ";
		}
		return solver;
	}
}
