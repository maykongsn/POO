import java.util.*;
public class ContatoPlus extends Contato {
	private boolean starred;

	public ContatoPlus(String name, ArrayList<Fone> fones) {
		super(name, fones);
		this.starred = false;
	}

	public void setStarred(boolean starred) {
		this.starred = starred;
	}

	@Override
	public String toString(){
		String saida = "";
		if(starred==true)
				saida+="@ "+getName();
		else
				saida+="- "+getName();
		int i=0;
		for(Fone fone : getFones()){
				if(starred==true)
						saida +=" ["+i+":"+fone.toString()+"]";
				else
						saida +=" ["+i+":"+fone.toString()+"]";
				i++;
		}
		return saida;
	}
}
