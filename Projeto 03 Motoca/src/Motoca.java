import java.util.Scanner;

class Pessoa {
  String nome;
  int idade;

  Pessoa(String nome, int idade) {
    this.nome = nome;
    this.idade = idade;
  }

  public String toString() {
    return "[" + nome + ":" + idade + "]";
  }
}

class Moto {
  int potencia;
  int tempo;
  Pessoa pass;

  Moto(int potencia) {
    this.potencia = potencia;
    this.tempo = 0;
  }

  void comprarTempo(int valor) {
    this.tempo += valor;
  }

  void subir(Pessoa pessoa) {
    if(this.pass == null) {
      this.pass = pessoa;
      return;
    }
    System.out.println("fail: moto ocupada");
  }

  Pessoa descer() {
    if(this.pass == null) {
      System.out.println("fail: moto vazia");
      return null;
    }
    Pessoa pessoa = this.pass;
    this.pass = null;
    return pessoa;    
  }

  void dirigir(int tempo) {
    if(this.pass == null) {
      System.out.println("fail: moto vazia");
    } else if(this.pass.idade > 10) {
      System.out.println("fail: muito grande para andar de motoca");
    } else if(this.tempo == 0) {
      System.out.println("fail: tempo zerado");
    } else if(this.tempo >= tempo) {
      this.tempo -= tempo;
    } else {
      System.out.println("fail: andou " + this.tempo + " min e acabou o tempo");
      this.tempo = 0;
    }
  }

  public String toString() {
    return "potencia: " + this.potencia + ", minutos: " + this.tempo + ", pessoa: " + this.pass;  
  }
}

public class Motoca {
  public static void main(String[] args) {
    Moto motoca = new Moto(1);
    Scanner scanner = new Scanner(System.in);

    while(true) {
      String line = scanner.nextLine();
      String ui[] = line.split(" ");

      if(ui[0].equals("$init")) {
        int potencia = Integer.parseInt(ui[1]);
        motoca = new Moto(potencia);        
      } else if(ui[0].equals("$in")) {
        int idade = Integer.parseInt(ui[2]);
        Pessoa pessoa = new Pessoa(ui[1], idade);
        motoca.subir(pessoa);
      } else if(ui[0].equals("$out")) {
        motoca.descer();
      } else if(ui[0].equals("$buy")) {
        int valor = Integer.parseInt(ui[1]);
        motoca.comprarTempo(valor);
      } else if(ui[0].equals("$drive")) {
        int tempo = Integer.parseInt(ui[1]);
        motoca.dirigir(tempo);
      } else if(ui[0].equals("$show")) {
        System.out.println(motoca);
      } else if(ui[0].equals("$end")) {
        break;
      } else {
        System.out.println("comando invalido");
      }
    }
    scanner.close();
  }
}
