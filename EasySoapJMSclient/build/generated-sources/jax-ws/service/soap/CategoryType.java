
package service.soap;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for categoryType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="categoryType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="ELECTRONIC"/&gt;
 *     &lt;enumeration value="SPORTS"/&gt;
 *     &lt;enumeration value="ANIMALS"/&gt;
 *     &lt;enumeration value="FURNITURE"/&gt;
 *     &lt;enumeration value="CLOTHES"/&gt;
 *     &lt;enumeration value="GARDEN"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "categoryType")
@XmlEnum
public enum CategoryType {

    ELECTRONIC,
    SPORTS,
    ANIMALS,
    FURNITURE,
    CLOTHES,
    GARDEN;

    public String value() {
        return name();
    }

    public static CategoryType fromValue(String v) {
        return valueOf(v);
    }

}
