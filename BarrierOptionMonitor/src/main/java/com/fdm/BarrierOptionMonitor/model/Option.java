package com.fdm.BarrierOptionMonitor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Log4j2
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private boolean isAmerican;
    private boolean isCall;
    private boolean isKnockIn;
    private int quantity;
    private double strikePrice;
    private String expirationDate;
    private boolean barrierDirection;
    private double barrierLevel;
    private double premium;
    @NotNull
    private String stockSymbol;
    private String stockName;
    private String currency;
    private double currentPrice;
    @ManyToOne
    private Client client;
    private String knockedTime;
    private OptionStatus status;

    @Transient
    private static DateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    @Transient
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @PrePersist
    private void prepare() {
        status = (isKnockIn) ? OptionStatus.WAITING : OptionStatus.KNOCKED_IN;
    }

    public boolean exercise(Account account) {
        if (status != OptionStatus.KNOCKED_IN) return false;
        if (isCall) {
            account.lose(strikePrice * quantity);
        } else {
            account.gain(strikePrice * quantity);
        }
        status = OptionStatus.EXERCISED;
        return true;
    }

    public OptionStatus in() {
        setStatus(OptionStatus.KNOCKED_IN);
        return status;
    }

    public OptionStatus out() {
        setStatus(OptionStatus.KNOCKED_OUT);
        return status;
    }

    public OptionStatus expire() {
        setStatus(OptionStatus.EXPIRED);
        return status;
    }

    public boolean isClosed() {
        return OptionStatus.isClosed(status);
    }

    public OptionStatus updateStatus() {
        if (isClosed()) return null;

        try {
            Date expiringDateFormatted = dateFormat.parse(expirationDate);
            Date today = dateFormat.parse(dateFormat.format(new Date()));
            if (expiringDateFormatted.compareTo(today) < 0) {
                return expire();
            }

            if (!isAmerican && expiringDateFormatted.compareTo(today) != 0)
                return null;
        } catch (ParseException e) {
            log.error(e.getMessage());
        }

        if (status == OptionStatus.KNOCKED_IN && isKnockIn) return null;

        if (isKnockIn) {
            if (barrierDirection) {
                if (currentPrice >= barrierLevel) {
                    knockedTime = timeFormat.format(new Date());
                    return in();
                }
            } else {
                if (currentPrice <= barrierLevel) {
                    knockedTime = timeFormat.format(new Date());
                    return in();
                }
            }
        }

        else {
            if (barrierDirection) {
                if (currentPrice >= barrierLevel) {
                    knockedTime = timeFormat.format(new Date());
                    return out();
                }
            } else {
                if (currentPrice <= barrierLevel) {
                    knockedTime = timeFormat.format(new Date());
                    return out();
                }
            }
        }
        return null;
    }

    public OptionStatus update(boolean doUpdateStatus) {
        try {
            Stock stock = YahooFinance.get(stockSymbol);
            stockName = stock.getName();
            currency = stock.getCurrency();
            currentPrice = stock.getQuote().getPrice().doubleValue();
            if (doUpdateStatus) return updateStatus();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
