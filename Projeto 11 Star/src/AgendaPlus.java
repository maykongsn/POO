import java.util.*;

public class AgendaPlus extends Agenda{
  private TreeMap<String, ContatoPlus> bookmarks;
  
  public AgendaPlus() {
    bookmarks = new TreeMap<>();
  }

  public ArrayList<ContatoPlus> getBookmarks() {
		ArrayList<ContatoPlus> temp = new ArrayList<>();
		for(ContatoPlus contato : this.bookmarks.values()) {
			temp.add(contato);
		}
		return temp;
	}

	@Override
	public void rmContato(String name) {
		if(this.getContato(name) == null) {
			throw new NullPointerException("fail: contato não existe");
		}

		super.rmContato(name);
		this.bookmarks.remove(name);
	}

	public void bookmark(String name) {
		if(this.bookmarks.containsKey(name)) {
			throw new NullPointerException("fail: contato já é favorito");
		}

		if(this.getContato(name) == null) {
			throw new NullPointerException("fail: contato não existe");
		}

		ContatoPlus contato = (ContatoPlus) getContato(name);
    contato.setStarred(true);
    this.bookmarks.put(name, contato);
	}

	public void unBookmark(String name) {
		if(this.getContato(name) == null) {
			throw new NullPointerException("fail: contato não existe");
		}

		if(!this.bookmarks.containsKey(name)) {
			throw new RuntimeException("fail: contato não é favorito");
		}

		ContatoPlus contato = (ContatoPlus) getContato(name);
		contato.setStarred(false);
		this.bookmarks.remove(name);
	}
}
