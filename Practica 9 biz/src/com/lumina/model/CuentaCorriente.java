package com.lumina.model;

import java.math.BigDecimal;

public class CuentaCorriente extends Cuenta {

	public void giroDescubierto(BigDecimal giroDescubierto) {
		setSaldo(getSaldo().subtract(giroDescubierto));

	}

	@Override
	public void realizarExtraccion(BigDecimal realizarExtraccion) {

		if (getSaldo().compareTo(realizarExtraccion) >= 0) {
			setSaldo(getSaldo().subtract(realizarExtraccion));
		} else
			this.giroDescubierto(realizarExtraccion);

	}

	public CuentaCorriente(boolean cuenta, int numeroDeCuenta, BigDecimal saldo) {
		super(cuenta, numeroDeCuenta, saldo);
		// TODO Auto-generated constructor stub
	}

}
