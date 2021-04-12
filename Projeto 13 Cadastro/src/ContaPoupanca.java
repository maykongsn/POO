public class ContaPoupanca extends Conta {
  public ContaPoupanca(int id, String idCliente) {
    super(id, idCliente);
    this.tipo = "CP";
  }

  public void atualizacaoMensal() {
    this.saldo = (float)(this.getSaldo() * 1.01);
  }
}
