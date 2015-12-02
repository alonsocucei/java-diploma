package edu.hop.diploma.builder;

import edu.hop.diploma.border.PageBorder;
import edu.hop.diploma.physical.PageProperties;
import edu.hop.diploma.text.Text;
import java.time.LocalDate;
import java.util.Collection;

/**
 * Interface que debe implementar cualquier clase de la cuál se requiera
 * construir un diploma.
 *
 * @author jjsanche
 */
public interface Diploma {

    /**
     * Regresa las propiedades físicas de un diploma.
     *
     * @return Un objeto de tipo PageProperties que contiene las propiedades
     * para construir un diploma.
     */
    public PageProperties getPageProperties();

    /**
     * Regresa los border a pintar en el diploma.
     *
     * @return Una colección que contiene objetos de tipo PageBorder con los
     * cuales se pintarán los bordes del diploma.
     */
    public Collection<PageBorder> getPageBorders();

    /**
     * Cambia el nombre del archivo para el diploma.
     *
     * @param fileName Un String con la ruta completa para el nombre del
     * archivo.
     */
    public void setFileName(String fileName);

    /**
     * Regresa el nombre del archivo para el diploma.
     *
     * @return Un String con la ruta completa para el nombre del archivo.
     */
    public String getFileName();

    /**
     * Regresa una colección de los textos a dibujar en el diploma.
     *
     * @return Una colección de objetos Text los cuáles se dibujarán en el
     * diploma.
     */
    public Collection<Text> getTexts();

    /**
     * Cambia el nombre de quien entrega el diploma.
     *
     * @param from Una cadena de caracteres representando a quien entrega el
     * diploma.
     */
    public void setFrom(String from);

    /**
     * Regresa quien entrega el diploma
     *
     * @return Una cadena de caracteres representando a quien entrega el
     * diploma.
     */
    public String getFrom();

    /**
     * Cambia el nombre de quien recibe el diploma.
     *
     * @param to Una cadena de caracteres representando a quien recibe el
     * diploma.
     */
    public void setTo(String to);

    /**
     * Regresa quien recibe el diploma
     *
     * @return Una cadena de caracteres representando a quien recibe el diploma.
     */
    public String getTo();

    /**
     * Cambia el motivo del por qué se entrega el diploma.
     *
     * @param motive Una cadena de caracteres representando el por qué del
     * diploma.
     */
    public void setMotive(String motive);

    /**
     * Regresa el motivo del por qué se entrega el diploma.
     *
     * @return Una cadena de caracteres representando a el por qué del diploma.
     */
    public String getMotive();

    /**
     * Cambia la fecha de la entrega del diploma
     *
     * @param date La fecha de la entrega del diploma. Si la fecha es null, por
     * default se escribirá la fecha del día en que se crea el mismo.
     */
    public void setDate(LocalDate date);

    /**
     * Regresa la fecha de la entrega del diploma.
     *
     * @return La fecha de entrega del diploma o en caso de ser null, regresará
     * la fecha actual.
     */
    public LocalDate getDate();

    /**
     * Cambia el lugar donde se entrega el diploma.
     *
     * @param place Una cadena de caracteres representando el lugar de entrega
     * del diploma.
     */
    public void setPlace(String place);

    /**
     * Regresa el lugar de entrega del diploma.
     *
     * @return Una cadena de caracteres representando el lugar de entrega del
     * diploma.
     */
    public String getPlace();

    /**
     * Cambia el margen del contenido.
     * @param marginSize Un número decimal que representa el margen del contenido
     * indicado en la unidad de medida del documento.
     */
    public void setContentMarginSize(float marginSize);

    /**
     * Regresa la longitud del margen del contenido del documento.
     * @return Un número decimal que representa el margen del contenido
     * indicado en la unidad de medida del documento.
     */
    public float getContentMarginSize();
    
    /**
     * Regresa la ruta del archivo que contiene la firma de la persona que entrega
     * el diploma.
     * @return Un String con la ruta del archivo que contiene la firma que se
     * agregará al diploma.
     */
    public String getSignFileName();
    
    /**
     * Regresa el nombre del instructor que impartió el curso.
     * @return Un String con el nombre del instructor del curso.
     */
    public String getInstructorName();
    
    /**
     * Cambia el número de horas del curso.
     * @param hours Un entero que representa las horas del curso.
     */
    public void setHours(int hours);
    
    /**
     * Regresa el numero de horas del curso.
     * @return El número de horas del curso.
     */
    public int getHours();
}
