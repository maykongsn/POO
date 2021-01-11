import java.util.ArrayList;
import java.util.Scanner;

class Operacao {
  	String descricao;
  	float valor;
  	float saldo;

	public Operacao(String descricao, float valor, float saldo) {
		this.descricao = descricao;
		this.valor = valor;
		this.saldo = saldo;
	}  
	
	public String toString() {
		return descricao + ": " + valor + ": " + saldo;
	}
}

class Conta {
  	private float saldo;
  	int numero;
	ArrayList<Operacao> extrato = new ArrayList<>();
	  
	public Conta(int numero) {
		this.numero = numero;
		this.extrato = new ArrayList<>();
		this.extrato.add(new Operacao("abertura", 0f, 0f));
	}

	public float getSaldo() {
		return this.saldo;
	}

	public void setSaldo(float value) {
		this.saldo = value;
	}
	
	public boolean sacar(float value) {
		if(value > 0) {
			if(value > this.getSaldo()) {
				System.out.println("fail: saldo insuficiente");
				return false;
			}
			this.setSaldo(this.getSaldo() - value);
			this.extrato.add(new Operacao("saque", -value, this.saldo));
			return true;
		} else {
			System.out.println("fail: valor invalido");
			return false;
		}
	}

	public boolean depositar(float value) {
		if(value <= 0) {
			System.out.println("fail: valor invalido");
			return false;
		}
		this.setSaldo(this.getSaldo() + value);
		extrato.add(new Operacao("deposito", value, this.saldo));
		return true;
	}

	public void tarifas(float value) {
		if(value > 0) {
			this.setSaldo(this.getSaldo() - value);
			this.extrato.add(new Operacao("tarifa", -value, this.saldo));
		} else {
			System.out.println("fail: valor invalido");
			return;
		}
	}

	public void extornar(int[] indexes) {
		for(int i = 0; i < indexes.length; i++) {
			if(indexes[i] >= extrato.size()) {
				System.out.println("fail: indice " + indexes[i] + " invalido");
			} else {
				if(extrato.get(indexes[i]).descricao == "tarifa") {
					this.setSaldo(this.getSaldo() + extrato.get(indexes[i]).valor * -1);
					this.extrato.add(new Operacao("extorno", extrato.get(indexes[i]).valor * -1, this.saldo));
				} else {
					System.out.println("fail: indice " + indexes[i] + " invalido");
				}
			}
		}
	}

	public void extrato() {
		for(Operacao operacao : extrato) {
			System.out.println(extrato.indexOf(operacao) + ": " + operacao.toString());
		}
	}

	public void extratoParcial(int value) {
		for(int i = extrato.size() - value; i < extrato.size(); i++) {
			System.out.println(i + ": " + extrato.get(i));
		}
	}

	public String toString() {
		return "conta:" + numero + " saldo:" + getSaldo();
	}
}

public class ContaBancaria {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Conta conta = null;
		
		while(true) {
		 	String line = scanner.nextLine();
		 	String[] ui = line.split(" ");
		 	
		 	if(ui[0].equals("init")){
	 			conta = new Conta(Integer.parseInt(ui[1]));
		 	} else if(ui[0].equals("deposito")) {
		 		conta.depositar(Float.parseFloat(ui[1]));
		 	} else if(ui[0].equals("saque")) {
		 		conta.sacar(Float.parseFloat(ui[1]));
		 	} else if(ui[0].equals("tarifa")) {
		 		conta.tarifas(Float.parseFloat(ui[1]));
		 	} else if(ui[0].equals("extrato")) {
		 		conta.extrato();
		 	} else if(ui[0].equals("extornar")) {
				int[] indexes = new int[ui.length - 1];
				for(int i = 1; i < ui.length; i++) {
					indexes[i-1] = Integer.parseInt(ui[i]);
				}
				conta.extornar(indexes); 
		 	} else if(ui[0].equals("extratoN")) {
		 		conta.extratoParcial(Integer.parseInt(ui[1]));
		 	} else if(ui[0].equals("show")) {
		 		System.out.println(conta);
		 	} else if(ui[0].equals("end")) {
		 		break;	
		 	} else {
				System.out.println("Comando invalido");
			}
		}
		
		scanner.close();
	}
}
