package com.lumina.model;

import java.math.BigDecimal;

public class Cuenta {

	private boolean cerrada;
	private int numeroDeCuenta;
	private BigDecimal saldo;

	public void cerrarCuenta() {
		
		if(this.saldo.equals(new BigDecimal(0)))
			this.cerrada = true;

	}

	public void realizarDeposito(BigDecimal realizarDeposito) {
	

	}

	/*
	 * 5) implementar el método retirar dinero que no permita retirar dinero si
	 * el monto a retirar es mayor al monto en la cuenta, si la cuenta es una
	 * cuenta corriente se deberá permitir girar en descubierto (sobreescribir
	 * el método)
	 */

	public void realizarExtraccion(BigDecimal realizarExtraccion) {
		if (saldo.compareTo(realizarExtraccion) >= 0) {
			saldo = saldo.subtract(realizarExtraccion);

		}

	}

	public Cuenta(boolean cuenta, int numeroDeCuenta, BigDecimal saldo) {
		super();
		this.cerrada = cerrada;
		this.numeroDeCuenta = numeroDeCuenta;
		this.saldo = saldo;
	}

	@Override
	public String toString() {
		return "Cuenta [cuenta=" + cerrada + ", numeroDeCuenta="
				+ numeroDeCuenta + ", saldo=" + saldo + "]";
	}

	public boolean isCuenta() {
		return cerrada;
	}

	public void setCuenta(boolean cerrada) {
		this.cerrada = cerrada;
	}

	public int getNumeroDeCuenta() {
		return numeroDeCuenta;
	}

	public void setNumeroDeCuenta(int numeroDeCuenta) {
		this.numeroDeCuenta = numeroDeCuenta;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
