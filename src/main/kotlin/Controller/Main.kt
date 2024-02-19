    package org.example.Controller

    import Models.CuentaAhorro
    import CuentaBancaria
    import Interface.Liquidable
    import Models.CarteraValores
    import Models.CuentaCorriente



    var cuentasBancarias: MutableList<Liquidable> = mutableListOf()
    var cuentasValores: MutableList<Liquidable> = mutableListOf()
    fun main() {
        var dato=false
        var cartera1:Liquidable=CarteraValores("Ibex35","Zara",10,5.0)
        cuentasValores.add(cartera1)
        println(cartera1.liquidar())

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
                        "6.Liquidar una cuenta\n" +
                        "7.Salir"
            )
            var opcion= readln().toInt()
            when(opcion){
                1-> crearCuentaCorriente()
                2-> crearCuentaAhorro()
                3-> ingresarSaldo()
                4-> consultarSaldo()
                5-> retirarDineroCuentaCorriente()
                6-> liquidar()
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
        var cuentaCorriente:Liquidable=CuentaCorriente(nombre,saldoInicial)
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
        var cuentaAhorro: Liquidable = CuentaAhorro(nombre,saldoInicial)
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
            1 ->{ println("Marque cual quiere consultar:")
                for ((i:Int, cuenta:Liquidable) in cuentasBancarias.withIndex()) {
                    if (cuenta is CuentaCorriente) {
                        println("${i+1}. ${cuenta.gettNombreCuenta()}")
                    }
                }
                var opcion2= readln().toInt()
                if (opcion2 in 1..cuentasBancarias.size ){
                    var cuentaElegida:CuentaCorriente=cuentasBancarias[opcion2-1] as CuentaCorriente
                    if (cuentaElegida !=null){
                        cuentaElegida.consultarSaldo()
                    }
                }
            }

            2 ->{ println("Marque cual quiere consultar:")
                for ((i:Int,cuenta:Liquidable) in cuentasBancarias.withIndex()) {
                    if (cuenta is CuentaAhorro) {
                        println("${i+1}. ${cuenta.gettNombreCuenta()}")
                    }
                }
                var opcion2= readln().toInt()
                if (opcion2 in 0.. cuentasBancarias.size){
                    var cuentaElegida:CuentaAhorro= cuentasBancarias[opcion2-1] as CuentaAhorro
                    if (cuentaElegida !=null){
                        cuentaElegida.consultarSaldo()
                    }
                }
            }
            else ->{
                println("Opción no válida")
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
                cuentaAhorro.liquidar()
            } else {
                println("La opción seleccionada no corresponde a una cuenta de ahorros.")
            }
        } else {
            println("Opción inválida.")
        }
    }
    fun liquidar() {
        println("Seleccione qué tipo de cuenta quiere liquidar: \n" +
                "Marque 1 para cuentas corrientes.\n" +
                "Marque 2 para cuentas de ahorros.")
        var opcion = readLine()!!.toInt()
        when (opcion) {
            1 -> {
                println("Seleccione la cuenta corriente a liquidar:")
                for ((index, cuenta) in cuentasBancarias.withIndex()) {
                    if (cuenta is CuentaCorriente) {
                        println("${index + 1}. ${cuenta.gettNombreCuenta()} Saldo: ${cuenta.gettSaldo()}")
                    }
                }
                val cuentaSeleccionadaIndex = readln().toInt() - 1
                if (cuentaSeleccionadaIndex in 0 until cuentasBancarias.size && cuentasBancarias[cuentaSeleccionadaIndex] is CuentaCorriente) {
                    var cuentaCorrienteSeleccionada = cuentasBancarias[cuentaSeleccionadaIndex] as CuentaCorriente
                    println("Ha seleccionado la cuenta corriente ${cuentaCorrienteSeleccionada.gettNombreCuenta()}")
                    println("Se procederá a su liquidación...")
                    cuentaCorrienteSeleccionada.liquidar()
                } else {
                    println("Opción inválida.")
                }
            }
            2 -> {
                println("Seleccione la cuenta de ahorros a liquidar:")
                for ((index, cuenta) in cuentasBancarias.withIndex()) {
                    if (cuenta is CuentaAhorro) {
                        println("${index + 1}. ${cuenta.gettNombreCuenta()} Saldo: ${cuenta.gettSaldo()}")
                    }
                }
                val cuentaSeleccionadaIndex = readln().toInt() - 1
                if (cuentaSeleccionadaIndex in 0 until cuentasBancarias.size && cuentasBancarias[cuentaSeleccionadaIndex] is CuentaAhorro) {
                    var cuentaAhorroSeleccionada = cuentasBancarias[cuentaSeleccionadaIndex] as CuentaAhorro
                    println("Ha seleccionado la cuenta corriente ${cuentaAhorroSeleccionada.gettNombreCuenta()}")
                    println("Se procederá a su liquidación...")
                    cuentaAhorroSeleccionada.liquidar()
                } else {
                    println("Opción inválida.")
                }
            }
            else -> {
                println("Opción inválid¡a.")
            }
        }
    }

