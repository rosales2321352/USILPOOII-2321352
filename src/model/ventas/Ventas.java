package model.ventas;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Ventas {
    private Integer ventaId;
    private String ventaIdSunat;
    private String reference;
    private String typeAffectationId;
    private String name;

    public Ventas() {
    }

    public Ventas(Integer ventaId, String ventaIdSunat, String reference, String typeAffectationId, String name) {
        this.ventaId = ventaId;
        this.ventaIdSunat = ventaIdSunat;
        this.reference = reference;
        this.typeAffectationId = typeAffectationId;
        this.name = name;
    }

    public Integer getVentaId() {
        return this.ventaId;
    }

    public String getVentaIdSunat() {
        return this.ventaIdSunat;
    }

    public String getReference() {
        return this.reference;
    }

    public String getTypeAffectationId() {
        return this.typeAffectationId;
    }

    public String getName() {
        return this.name;
    }

    public List<Ventas> getListVentas() {
        try {
            String sql = "SELECT venta_id, venta_id_sunat, reference, type_affectation_id, name FROM ventas v";
            ResultSet result = Db.executeQuery(sql);
            List<Ventas> ventas = new ArrayList<>();
            while (result.next()) {
                ventas.add(new Ventas(
                        result.getInt("venta_id"),
                        result.getString("venta_id_sunat"),
                        result.getString("reference"),
                        result.getString("type_affectation_id"),
                        result.getString("name")
                ));
            }
            result.getStatement().close();
            result.close();
            return ventas;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}

