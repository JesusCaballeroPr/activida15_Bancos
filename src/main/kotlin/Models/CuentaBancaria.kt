import Interface.Liquidable
import java.time.LocalDate

abstract class CuentaBancaria:Liquidable {
    protected var fecha: LocalDate = LocalDate.now()
    protected var nombreCuenta: String = ""
    protected var saldo: Double = 0.0
    protected var fechaApertura: LocalDate = LocalDate.parse("2024-01-30") //Ajustar

    constructor(nombreCuenta: String, saldo: Double) {
        this.fecha = LocalDate.now()
        this.nombreCuenta = nombreCuenta
        this.saldo = saldo
    }
    fun gettNombreCuenta():String{
        return nombreCuenta
    }
    fun gettSaldo():Double{
        return saldo
    }
    fun ingresar(cantidad: Double) {
        saldo += cantidad
        println("Ingresará un total de $cantidad€. Le queda un saldo de ${this.saldo}")

    }

    fun consultarSaldo() {
        println("Saldo de la cuenta $nombreCuenta: $saldo")
    }

}
