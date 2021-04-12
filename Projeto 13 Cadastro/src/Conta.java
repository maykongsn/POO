public abstract class Conta {
    protected int id;
    protected String idCliente;
    protected float saldo;
    protected String tipo;

    public Conta(int id, String idCliente) {
        this.id = id;
        this.idCliente = idCliente;
        this.saldo = 0;
    }

    public float getSaldo() {
        return this.saldo;
    }

    public int getId() {
        return id;
    }

    public String getIdCliente() {
        return this.idCliente;
    }

    public String getTipo() {
        return tipo;
    }

    public void depositar(float value) {
        if(value <= 0) {
            throw new RuntimeException("fail: valor inválido");
        }

        this.saldo += value;
    }

    public void sacar(float value) {
        if(this.getSaldo() < value) {
            throw new RuntimeException("fail: saldo insuficiente");
        }
        this.saldo -= value;
    }

    public void transferir(Conta other, float value) {
        if(this.getSaldo() < value) {
            throw new RuntimeException("fail: saldo insuficiente");
        }
        other.depositar(value);
        this.saldo -= value;
    }

    public abstract void atualizacaoMensal();

    public String toString() {
        return this.getId() + ":" + this.getIdCliente() + ":" + this.getSaldo() + ":" + this.getTipo();
    }
}