import java.util.Scanner;

public class Carro {
    int tanque;
    int tanqueMax;
    int passageiros;
    int passageirosMax;
    int quilometragem;

    Carro() {
        this.tanqueMax = 100;
        this.passageirosMax = 2;
        this.tanque = 0;
        this.passageiros = 0;
        this.quilometragem = 0;
    }

    void in() {
        if(passageiros != passageirosMax) {
            passageiros++;
            return;
        }
        System.out.println("fail: limite de pessoas atingido");
    }

    void out() {
        if(passageiros != 0) {
            passageiros--;
            return;
        }
        System.out.println("fail: nao ha ninguem no carro");
    }

    void fuel(int qtd) {
        if(qtd + tanque >= tanqueMax) {
            tanque = tanqueMax;
            System.out.println("tanque cheio");
            return;
        }
        tanque += qtd;
        System.out.println("abastecido");
    }

    void drive(int qtd) {
        if(passageiros != 0 && tanque != 0) {
            if(tanque < qtd) {
                quilometragem += tanque;
                System.out.println("fail: Tanque vazio apos andar "+ tanque + " km");
                tanque = 0;
            } else {
                tanque -= qtd;
                quilometragem += qtd;
                System.out.println("Concluimos o trajeto");
                return;
            }
        } else if(passageiros == 0) {
            System.out.println("fail: nao ha ninguem no carro");
        } else {
            System.out.println("fail: tanque vazio");
        }
    }

    public String toString() {
        return "pass: " + passageiros + ", gas: " + tanque + ", km: " + quilometragem;
    }

    public static void main(String[] args) {
        Carro fuscaConversivel = new Carro();
        Scanner scanner = new Scanner(System.in);

        while(true) {
            String line = scanner.nextLine();
            String ui[] = line.split(" ");
            if(ui[0].equals("$in")) {
                fuscaConversivel.in();
            } else if(ui[0].equals("$out")) {
                fuscaConversivel.out();
            } else if(ui[0].equals("$show")) {
                System.out.println(fuscaConversivel);
            } else if(ui[0].equals("$fuel")) {
                int qtd = Integer.parseInt(ui[1]);
                fuscaConversivel.fuel(qtd);
            } else if(ui[0].equals("$drive")) {
                int qtd = Integer.parseInt(ui[1]);
                fuscaConversivel.drive(qtd);
            } else if(ui[0].equals("$end")) {
                break;
            }
        }
        scanner.close();
    }
}
