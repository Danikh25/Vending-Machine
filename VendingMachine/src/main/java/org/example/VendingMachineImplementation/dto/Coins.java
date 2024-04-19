package org.example.VendingMachineImplementation.dto;


import java.math.BigDecimal;

//Use enums to represent the values of different coins
    public enum Coins {
        Quarter(BigDecimal.valueOf(0.25)),
        Dime(BigDecimal.valueOf(0.10)),
        Nickel(BigDecimal.valueOf(0.05)),
        Penny(BigDecimal.valueOf(0.01));

        private final BigDecimal value;
        Coins(BigDecimal value) {
            this.value = value;
        }

        //Enums only need getter since they are constant
        public BigDecimal getValue() {
            return this.value;
        }
    }
