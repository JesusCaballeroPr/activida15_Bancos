package Models

import java.time.LocalDate
import CuentaBancaria

 class CuentaAhorro : CuentaBancaria {

    protected var interes: Double = 4.0

    constructor(nombreCuenta: String, saldo: Double) : super(nombreCuenta, saldo) {
        this.interes = interes
        this.saldo *= (1 + interes / 100)
        println("Cuenta de ahorros creada con nombre $nombreCuenta")
    }

     fun liquidarCuenta() {
        val fechaActual = LocalDate.now()
        val fechaLimite = fechaActual.minusYears(1)
        if (fechaApertura.isAfter(fechaLimite)) {
            println("Se le pasará una penalización del 3%")
            this.saldo -= saldo * 0.3
        }
         retirarTodo()
    }
    fun retirarTodo(){
        println("La cuenta queda a 0 después de retirar ${this.saldo}")
    }
    override fun toString(): String {
        return "La cuenta de ahorros tiene los siguientes datos: fue creada\n" +
                "en  ${this.fecha},tiene ${saldo}€, con unos intereses aplicados" +
                " del ${this.interes}%"
    }
}
