package org.example.VendingMachineImplementation.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class Change {
    private final Map<Coins, Integer> coins;

    public Change() {
        this.coins = new HashMap<>();
        for (Coins coin : Coins.values()) {
            this.coins.put(coin, 0);
        }
    }

    public Map<Coins, Integer> getCoins() {
        return this.coins;
    }

    public void calculateChange(BigDecimal amount) {
        BigDecimal changeDue = amount.multiply(BigDecimal.valueOf(100));
        for (Coins coin : Coins.values()) {
            int count = changeDue.divide(coin.getValue().multiply(BigDecimal.valueOf(100)), RoundingMode.FLOOR).intValue();
            changeDue = changeDue.remainder(coin.getValue().multiply(BigDecimal.valueOf(100)));
            this.coins.put(coin, count);
        }
    }

    public BigDecimal calculateTotalValue(Map<Coins, Integer> coinsMap) {
        BigDecimal totalValue = BigDecimal.ZERO;
        for (Map.Entry<Coins, Integer> entry : coinsMap.entrySet()) {
            BigDecimal value = entry.getKey().getValue().multiply(BigDecimal.valueOf(entry.getValue()));
            totalValue = totalValue.add(value);
        }
        return totalValue;
    }
}