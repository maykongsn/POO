import java.util.ArrayList;

public class Cliente {
  private String idCliente;
  private ArrayList<Conta> contas;

  public Cliente(String idCliente) {
    this.idCliente = idCliente;
    this.contas = new ArrayList<>();
  }

  public String getIdCliente() {
    return this.idCliente;
  }

  public void setClienteId(String idCliente) {
    this.idCliente = idCliente;
  }

  public ArrayList<Conta> getContas() {
    return this.contas;
  }

  public void setContas(ArrayList<Conta> contas) {
    this.contas = contas;
  }

  public void addConta(Conta conta) {
    this.contas.add(conta);
  }

  public String toString() {
    String solver = "";
    solver += "- " + getIdCliente() + " [ ";
    for(Conta conta : getContas()) {
      solver += conta.getId() + " ";
    }

    solver += "]";
    return solver;
  }
}
