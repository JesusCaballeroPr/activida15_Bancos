package Models

import CuentaBancaria
import Interface.Liquidable

class CuentaCorriente : CuentaBancaria,Liquidable {

    protected var comisionMantenimiento:Double=20.0
    protected var dineroAretirar:Double=0.0

    constructor(nombreCuenta:String, saldo:Double) : super(nombreCuenta, saldo){
        this.comisionMantenimiento=comisionMantenimiento
        println("Se le cobrará $comisionMantenimiento€ por abrir su cuenta:")
        this.saldo-=comisionMantenimiento
        println("Cuenta corriente creada con nombre $nombreCuenta")
    }

    fun retirarDinero(dineroAretirar: Double): Double {
        this.dineroAretirar = dineroAretirar
        val saldoAnterior = this.saldo
        this.saldo -= dineroAretirar
        println("Va a retirar $dineroAretirar")
        if (this.saldo < 0) {
            cobrarComision()
        }
        return saldoAnterior - this.saldo
    }

    override fun liquidar(){
        println("Se procederá a liquidar completamente la cuenta...")
        var dineroPrevio=this.saldo
        this.saldo-=this.saldo
        println("Se cuenta $nombreCuenta se ha liquidado con un efectivo total de: $dineroPrevio")
    }
    fun cobrarComision() {
        println("Se le cobrará una comisión del 10% por descubierto!")
        this.saldo += saldo* 0.1
    }
    override fun toString(): String {
        return "La cuenta corriente se creó a nombre de ${this.nombreCuenta}, el ${fecha} y con un saldo " +
                "inicial de ${saldo}€"
    }
}