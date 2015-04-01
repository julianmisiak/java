package com.lumina.model;

import java.math.BigDecimal;

public class CajaDeAhorro extends Cuenta {

	@Override
	public void realizarDeposito(BigDecimal realizarDeposito) {

		if (realizarDeposito.compareTo(new BigDecimal(0)) > 0) {
			setSaldo(getSaldo().add(realizarDeposito));
			this.generarInteres();
		}

	}





	public void generarInteres() {

		setSaldo(getSaldo().multiply(new BigDecimal(1.05)));
		// deposito = saldo + %5
	}

	public CajaDeAhorro(boolean cerrada, int numeroDeCuenta, BigDecimal saldo) {
		super(cerrada, numeroDeCuenta, saldo);

	}

}
