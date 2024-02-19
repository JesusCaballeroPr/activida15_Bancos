package org.example.Controller

import Models.CuentaAhorro
import CuentaBancaria
import Models.CuentaCorriente



var cuentasBancarias: MutableList<CuentaBancaria> = mutableListOf()
fun main() {
    var dato=false


    println(
        "Bienvenido a tu banco, y cada día el de nás gente.\n Por favor," +
                "diga que quiere hacer;"
    )
    do {
        println(
            "1.Crear un compte corrent\n" +
                    "2.Crear un compte estalvi\n" +
                    "3.Ingressar (penseu que hi ha dos tipus de comptes)\n" +
                    "4.Consultar saldo\n" +
                    "5.Retirar diners d’un compte corrent\n" +
                    "6.Liquidar un compte estalvi\n" +
                    "7.Salir"
        )
        var opcion= readln().toInt()
        when(opcion){
            1-> crearCuentaCorriente()
            2-> crearCuentaAhorro()
            3-> ingresarSaldo()
            4-> consultarSaldo()
            5-> retirarDineroCuentaCorriente()
            6-> liquidarCuentaAhorros()
            7->dato=true
        }
    }while (!dato)
}

fun crearCuentaCorriente(){
    var nombre=""
    var saldoInicial=0.0
    println("Diga el nombre de la cuenta")
    nombre= readln()
    println("Diga el saldo inicial de la cuenta")
    saldoInicial= readln().toDouble()
    var cuentaCorriente:CuentaCorriente=CuentaCorriente(nombre,saldoInicial)
    cuentasBancarias.add(cuentaCorriente)
    println("Cuentas corrientes creadas: ")
    for (i in cuentasBancarias.indices){
        println("Cuenta creada ${i+1}: ${cuentasBancarias[i]}")
    }
}
fun crearCuentaAhorro(){
    var nombre=""
    var saldoInicial=0.0
    println("Diga el nombre de la cuenta")
    nombre= readln()
    println("Diga el saldo inicial de la cuenta")
    saldoInicial= readln().toDouble()
    var cuentaAhorro: CuentaAhorro = CuentaAhorro(nombre,saldoInicial)
    cuentasBancarias.add(cuentaAhorro)
    for (i in cuentasBancarias.indices){
        println("Cuenta creada ${i+1}: ${cuentasBancarias[i]}")
    }
}
fun ingresarSaldo(){
    println("Diga en que cuenta desea ingresar saldo, por favor\n" +
            "Marque 1 si es cuenta corriente\n" +
            "Marque 2 si es cuenta de ahorros")
    var opcion= readln().toInt()
    when (opcion){
        1-> for (i in cuentasBancarias){
            if (i is CuentaCorriente){
                println("Ingrese el saldo que quiera depositar:")
                var saldo= readln().toDouble()
                i.ingresar(saldo)
                return
            }else{
                println("No se encontró ninguna cuenta corriente")
            }
        }
        2-> for (i in cuentasBancarias){
            if (i is CuentaAhorro){
                println("Ingrese el saldo a ingresar:")
                var saldo= readln().toDouble()
                i.ingresar(saldo)
            }else{
                println("No se encontró ninguna cuenta de ahorros")
            }
        }
    }
}
fun consultarSaldo() {
    println(
        "Diga de que cuenta desea consultar el saldo, por favor\n" +
                "Marque 1 si es cuenta corriente\n" +
                "Marque 2 si es cuenta de ahorros"
    )
    var opcion = readln().toInt()
    when (opcion) {
        1 -> for (i in cuentasBancarias) {
            if (i is CuentaCorriente) {
                i.consultarSaldo()
            }
        }

        2 -> for (i in cuentasBancarias) {
                if (i is CuentaAhorro) {
                    i.consultarSaldo()
                }
            }
        }
    }
fun retirarDineroCuentaCorriente() {
    println("Seleccione el número de cuenta corriente de la cual desea retirar dinero: ")
    for ((index, cuenta) in cuentasBancarias.withIndex()) {
        if (cuenta is CuentaCorriente) {
            println("${index + 1}. ${cuenta.gettNombreCuenta()}")
        }
    }
    val opcion = readln().toInt()
    if (opcion in 1..cuentasBancarias.size) {
        var cuentaCorriente = cuentasBancarias[opcion - 1] as CuentaCorriente
        if (cuentaCorriente != null) {
            println("Ingrese la cantidad de dinero que desea retirar: ")
            val cantidadARetirar = readln().toDouble()
            val dineroRetirado = cuentaCorriente.retirarDinero(cantidadARetirar)
            println("Ha retirado $dineroRetirado€. Nuevo saldo: ${cuentaCorriente.gettSaldo()}€")
        } else {
            println("La opción seleccionada no corresponde a una cuenta corriente.")
        }
    } else {
        println("Opción inválida.")
    }
}

fun liquidarCuentaAhorros() {
    println("Seleccione el número de cuenta de ahorros de la cual desea retirar dinero: ")
    for ((index, cuenta) in cuentasBancarias.withIndex()) {
        if (cuenta is CuentaAhorro) {
            println("${index + 1}. ${cuenta.gettNombreCuenta()}")
        }
    }
    val opcion = readln().toInt()
    if (opcion in 1..cuentasBancarias.size) {
        var cuentaAhorro = cuentasBancarias[opcion - 1] as CuentaAhorro
        if (cuentaAhorro != null) {
            cuentaAhorro.liquidarCuenta()
        } else {
            println("La opción seleccionada no corresponde a una cuenta de ahorros.")
        }
    } else {
        println("Opción inválida.")
    }
}
