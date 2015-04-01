CREATE TABLE Cuenta (
    oid_cta NUMBER(10),
    oid_user NUMBER(10),
    saldo NUMBER(10),
	oid_sube NUMBER(10),
	CONSTRAINT cuenta_pk PRIMARY KEY (oid_cta),
	CONSTRAINT user_owner_fk FOREIGN KEY(oid_user) REFERENCES Usuario(oid_user)),
	CONSTRAINT sube_assoc_fk FOREIGN KEY(oid_sube) REFERENCES Sube(oid_sube)),
	CONSTRAINT check_saldo CHECK (saldo > -10)
)