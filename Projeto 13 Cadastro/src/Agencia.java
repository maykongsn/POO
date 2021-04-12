import java.util.TreeMap;

public class Agencia {
  private TreeMap<String, Cliente> clientes;
  private TreeMap<Integer, Conta> contas;
  private int nextIdConta;  

  public Agencia() {
    this.clientes = new TreeMap<>();
    this.contas = new TreeMap<>();
    this.nextIdConta = 0;
  }

  private Conta getConta(int id) {
    for(Conta conta : this.contas.values()) {
      if(conta.getId() == id) {
        return conta;
      }
    }
    throw new RuntimeException("fail: Conta não encontrada");
  }

  public void addCliente(String idCliente) {
    if(clientes.containsKey(idCliente)) {
      throw new RuntimeException("fail: Cliente já existe");
    }
    
    Cliente cliente = new Cliente(idCliente);
    clientes.put(idCliente, cliente);
    Conta contaCorrente = new ContaCorrente(nextIdConta, idCliente);
    contas.put(nextIdConta, contaCorrente);
    nextIdConta++;

    Conta contaPoupanca = new ContaPoupanca(nextIdConta, idCliente);
    contas.put(nextIdConta, contaPoupanca);
    nextIdConta++;

    cliente.addConta(contaCorrente);
    cliente.addConta(contaPoupanca);
  }

  public void sacar(int idConta, float valor) {
    if(getConta(idConta).getSaldo() - valor >= 0) {
      getConta(idConta).sacar(valor);
    } else {
      throw new RuntimeException("fail: Saldo insuficiente");
    }
  }

  public void depositar(int idConta, float valor) {
    if(!contas.containsKey(idConta)) {
      throw new RuntimeException("fail: Conta não encontrada");
    }
    getConta(idConta).depositar(valor);
  }

  public void transferir(int contaDe, int contaPara, float value) {
    if(getConta(contaDe).getSaldo() - value >= 0) {
      getConta(contaDe).transferir(getConta(contaPara), value);
    } else {
      System.out.println("fail: Saldo insuficiente");
    }
  }

  public void atualizacaoMensal() {
    for(Conta conta : this.contas.values()) {
      conta.atualizacaoMensal();
    }
  }

  public String toString() {
    String solver = "";
    solver += "Clientes: \n";
    for(Cliente cliente : clientes.values()) {
      solver += cliente + "\n";
    }
    solver += "Contas: \n";
    for(Conta conta : contas.values()) {
      solver += conta + "\n";
    }

    return solver;
  }
}
