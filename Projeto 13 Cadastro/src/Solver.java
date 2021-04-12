import java.util.Scanner;
public class Solver {
  public static void main(String[] args) {
    Agencia agencia = new Agencia();
    Scanner scanner = new Scanner(System.in);
    
    while(true) {
      String line = scanner.nextLine();
      String ui[] = line.split(" ");
      try {
        if(ui[0].equals("end")){
          break;
        } else if(ui[0].equals("addCli")) {
          agencia.addCliente(ui[1]);
        } else if(ui[0].equals("saque")) {
          agencia.sacar(Integer.parseInt(ui[1]), Float.parseFloat(ui[2]));
        } else if(ui[0].equals("deposito")) {
          agencia.depositar(Integer.parseInt(ui[1]), Float.parseFloat(ui[2]));
        } else if(ui[0].equals("transf")) {
          agencia.transferir(Integer.parseInt(ui[1]), Integer.parseInt(ui[2]), Float.parseFloat(ui[3]));
        } else if(ui[0].equals("update")) {
          agencia.atualizacaoMensal();
        } else if(ui[0].equals("show")) {
          System.out.println(agencia);
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
