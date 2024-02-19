package Models

import Interface.Liquidable

class CarteraValores : Liquidable {

    private var nombreCartera:String
    private var nombreEmpresa:String
    private var cantidad:Int
    private var valor:Double

    constructor(nombreCartera:String,nombreEmpresa:String,cantidad:Int,valor:Double){
        this.nombreCartera=nombreCartera
        this.nombreEmpresa=nombreEmpresa
        this.cantidad=cantidad
        this.valor=valor
    }
    fun calcularValor():Double{
        return this.cantidad*this.valor
    }

    fun consultarValor(){
        println("El valor de total de las acciones es: ${calcularValor()}: ")
    }

    override fun liquidar() {
        println("Liquidación de la cuenta de valores con nombre: ${this.nombreCartera} de la empresa\n" +
                "${this.nombreEmpresa} por un valor de: ${calcularValor()}")
    }

}