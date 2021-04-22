package de.ma_vin.util.layer.generator.config.elements;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * Can be used to override default jpa column values
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "de.ma_vin.util.gen.model")
@Data
public class DaoInfo {

    /**
     * different column name compared to fieldName
     */
    @XmlAttribute
    private String columnName;

    /**
     * (Optional) Whether the database column is nullable.
     */
    @XmlAttribute
    private Boolean nullable;
    /**
     * (Optional) The column length. (Applies only if a
     * string-valued column is used.)
     */
    @XmlAttribute
    private Integer length;
    /**
     * (Optional) The precision for a decimal (exact numeric)
     * column. (Applies only if a decimal column is used.)
     * Value must be set by developer if used when generating
     * the DDL for the column.
     */
    @XmlAttribute
    private Integer precision;
    /**
     * (Optional) The scale for a decimal (exact numeric) column.
     * (Applies only if a decimal column is used.)
     */
    @XmlAttribute
    private Integer scale;

    /**
     * True if enum values should be stored by text and not by id
     */
    @XmlAttribute
    private Boolean useEnumText;

    public boolean isValid() {
        return true;
    }
}
