package com.arty.currencyConversion.domains;

import java.math.BigDecimal;

public class Currency
{
    private String id;
    private Integer nominal;
    private String numCode;
    private String charCode;
    private String name;
    private BigDecimal value;

    public Currency (String id, Integer nominal, String numCode, String charCode, String name, BigDecimal value)
    {
        this.id = id;
        this.nominal = nominal;
        this.numCode = numCode;
        this.charCode = charCode;
        this.name = name;
        this.value = value;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public Integer getNominal ()
    {
        return nominal;
    }

    public void setNominal (Integer nominal)
    {
        this.nominal = nominal;
    }

    public String getNumCode ()
    {
        return numCode;
    }

    public void setNumCode (String numCode)
    {
        this.numCode = numCode;
    }

    public String getCharCode ()
    {
        return charCode;
    }

    public void setCharCode (String charCode)
    {
        this.charCode = charCode;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public BigDecimal getValue ()
    {
        return value;
    }

    public void setValue (BigDecimal value)
    {
        this.value = value;
    }
}
