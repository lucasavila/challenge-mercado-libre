package com.mercado.libre.Magneto.configuracion;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("hibernate")
public class HibernateProperties {
    public String getHbm2ddlAuto() {
        return hbm2ddlAuto;
    }

    public void setHbm2ddlAuto(String hbm2ddlAuto) {
        this.hbm2ddlAuto = hbm2ddlAuto;
    }

    public boolean getShowSql() {
        return showSql;
    }

    public boolean isShowSql() {
        return showSql;
    }

    public void setShowSql(boolean showSql) {
        this.showSql = showSql;
    }

    private String hbm2ddlAuto = "none";
    private boolean showSql = true;
}
