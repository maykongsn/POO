import java.util.*;

public class Agenda {
	private TreeMap<String, Contato> contatos;

	public Agenda() {
		this.contatos = new TreeMap<>();
	}

	public ArrayList<Contato> getContatos(){
		ArrayList<Contato> temp = new ArrayList<>();
		for(Contato contato : contatos.values())
				temp.add(contato);
		return temp;
	}

	public Contato getContato(String name) {
		if(!contatos.containsKey(name)) {
			throw new NullPointerException("fail: contato nao existe");
		}
		return this.contatos.get(name);
	}

	public void addContato(Contato contato) {
		if(contato == null) {
			throw new NullPointerException("fail: informe um contato");
		}
		
		if(this.contatos.containsKey(contato.getName())) {
			for(Fone fone : contato.getFones()) {
				this.contatos.get(contato.getName()).addFone(fone.getName(), fone.getNumber());
			}
			return;
		}

		this.contatos.put(contato.getName(), contato);
	}

	public void rmContato(String name) {
		if(!this.contatos.containsKey(name)) {
			throw new NullPointerException("fail: contato não existe");
		}
		this.contatos.remove(name);
	}

	public void rmFone(String name, int index) {
		if(!this.contatos.containsKey(name)) {
			throw new NullPointerException("fail: contato não existe");
		}
		this.contatos.get(name).rmFone(index);
	}

	public ArrayList<Contato> search(String pattern) {
		ArrayList<Contato> search = new ArrayList<>();

		for(Contato contato : this.contatos.values()) {
			if(contato.toString().contains(pattern)) {
				search.add(contato);
			}
		}

		return search;
	}

	public String toString(){
		String saida = "";
		for(Contato contato : contatos.values()){
				saida+= contato+"\n";
		}
		return saida;
	}  
}
