public class ContaCorrente extends Conta {
  public ContaCorrente(int id, String idCliente) {
    super(id, idCliente);
    this.tipo = "CC";
  }

  public void atualizacaoMensal() {
    this.saldo -= 20;
  }
}
