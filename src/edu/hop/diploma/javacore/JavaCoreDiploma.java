package edu.hop.diploma.javacore;

import edu.hop.diploma.border.BordersManager;
import edu.hop.diploma.border.PageBorder;
import edu.hop.diploma.builder.Diploma;
import edu.hop.diploma.physical.PageProperties;
import edu.hop.diploma.text.Alignments;
import edu.hop.diploma.text.Text;
import java.awt.Color;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 * Clase creadora de diplomas para el curso Java Core. Esta clase tendrá de
 * manera predefinida los datos que todos los diplomas llevarán, así como la
 * fecha y firma del instructor que dictó el curso.
 *
 * @author jjsanche
 */
public final class JavaCoreDiploma implements Diploma, BordersManager {
    private final String OTORGA_EL_PRESENTE = "Otorga el presente";
    private final String DIPLOMA = "DIPLOMA";
    private final String A = "A:   ";
    private final String DE = "   de   ";
    private final String DEL = "   del   ";
    
    private PageProperties pageProperties;
    private List<PageBorder> pageBorders;
    private List<Text> texts;
    private String fileName = "Java Core Diploma.pdf";
    private String courseTitle = " Java Core ";
    private String to = " José Alonso de Jesús Sánchez Fuentes";
    private String instructorName = "José Alonso de Jesús Sánchez Fuentes";
    private String from = "Hands-on Programming";
    private String place = "Guadalajara, Jalisco a   ";
    private String motive = "Por haber concluido satisfactoriamente el curso de:";
    private String signerTitle = "Java Certifications Instructor";
    private String lasting = " Con una duración de: ";
    private String duration = " horas";
    private LocalDate date = LocalDate.now();
    private float contentMarginSize = 0;
    private int hours = 60;
    private String signFileName = "sign.jpg";

    /**
     * Crea un diploma con los valores por default.
     */
    public JavaCoreDiploma() {
        this(new PageProperties());
    }
    /**
     * Crea un diploma con los valores por default.
     *
     * @param pageBorders Una colección con los borders a pintar en el
     * documento.
     */
    public JavaCoreDiploma(Collection<PageBorder> pageBorders) {
        this(new PageProperties());
        setPageBorders(pageBorders);
    }
    /**
     * Crea un diploma con propiedades definidas por el usuario.
     *
     * @param pageProperties Un objeto de tipo PageProperties el cuál contiene
     * las propiedades físicas del diploma.
     */
    public JavaCoreDiploma(PageProperties pageProperties) {
        setPageProperties(pageProperties);
        buildTexts();
    }
    /**
     * Crea un diploma con propiedades y bordes definidos por el usuario.
     *
     * @param pageProperties Un objeto de tipo PageProperties el cuál contiene
     * las propiedades físicas del diploma.
     * @param pageBorders Una colección de borders a dibujar en el diploma.
     */
    public JavaCoreDiploma(PageProperties pageProperties, Collection<PageBorder> pageBorders) {
        this(pageProperties);
        setPageBorders(pageBorders);
    }
    /**
     * Construye los textos con el tamaño, tipo de letra y alineación correctos
     * para cada uno de ellos.
     */
    private void buildTexts() {
        List<Text> texts = new ArrayList<>();
        
        texts.add(new Text(getFrom(), 18, PDType1Font.HELVETICA_BOLD, Alignments.CENTER, Color.BLUE).setIsUnderlined(true));
        texts.add(new Text(OTORGA_EL_PRESENTE, 14, PDType1Font.TIMES_ROMAN, Alignments.CENTER));
        texts.add(new Text(DIPLOMA, 100, PDType1Font.TIMES_ROMAN, Alignments.CENTER));
        texts.add(new Text("", 50, PDType1Font.TIMES_ROMAN, Alignments.CENTER));
        texts.add(new Text(A, 18, PDType1Font.TIMES_ROMAN, Alignments.CENTER).setBreakLine(false));
        texts.add(new Text(getTo(), 18, PDType1Font.HELVETICA_BOLD_OBLIQUE, Alignments.CENTER));
        texts.add(new Text("", 18, PDType1Font.TIMES_ROMAN, Alignments.CENTER));
        texts.add(new Text(getMotive(), 18, PDType1Font.TIMES_ROMAN, Alignments.CENTER).setBreakLine(false));
        texts.add(new Text(courseTitle, 18, PDType1Font.HELVETICA_BOLD_OBLIQUE, Alignments.CENTER));
        texts.add(new Text("", 18, PDType1Font.TIMES_ROMAN, Alignments.CENTER));
        texts.add(new Text(lasting, 18, PDType1Font.TIMES_ROMAN, Alignments.CENTER).setBreakLine(false));
        texts.add(new Text(String.valueOf(getHours()), 18, PDType1Font.HELVETICA_BOLD_OBLIQUE, Alignments.CENTER).setBreakLine(false));
        texts.add(new Text(duration, 18, PDType1Font.HELVETICA_BOLD_OBLIQUE, Alignments.CENTER));
        texts.add(new Text("", 18, PDType1Font.TIMES_ROMAN, Alignments.CENTER));
        texts.add(new Text(getPlace(), 18, PDType1Font.TIMES_ROMAN, Alignments.CENTER).setBreakLine(false));
        texts.add(new Text(DateTimeFormatter.ofPattern("dd").format(getDate()), 18, PDType1Font.HELVETICA_BOLD_OBLIQUE, Alignments.CENTER).setBreakLine(false));
        texts.add(new Text(DE, 18, PDType1Font.TIMES_ROMAN, Alignments.CENTER).setBreakLine(false).setBreakLine(false));
        texts.add(new Text(DateTimeFormatter.ofPattern("MMMM").format(getDate()), 18, PDType1Font.HELVETICA_BOLD_OBLIQUE, Alignments.CENTER).setBreakLine(false));
        texts.add(new Text(DEL, 18, PDType1Font.TIMES_ROMAN, Alignments.CENTER).setBreakLine(false));
        texts.add(new Text(DateTimeFormatter.ofPattern("yyyy").format(getDate()), 18, PDType1Font.HELVETICA_BOLD_OBLIQUE, Alignments.CENTER).setBreakLine(true));
        texts.add(new Text("", 50, PDType1Font.HELVETICA_BOLD_OBLIQUE, Alignments.CENTER).setBreakLine(true));
        texts.add(new Text(instructorName, 14, PDType1Font.TIMES_ROMAN, Alignments.CENTER).setBreakLine(true));
        texts.add(new Text(signerTitle, 14, PDType1Font.TIMES_ROMAN, Alignments.CENTER).setBreakLine(true));
        
        this.texts = texts;
    }
    /**
     * Cambia las propiedades físicas del documento actual. Estas propiedades no
     * tendrán efecto si se cambian después de haber invocado a la construcción
     * del diploma.
     *
     * @param pageProperties Las nuevas propiedades físicas que se asignarán al
     * documento.
     */
    public void setPageProperties(PageProperties pageProperties) {
        this.pageProperties = pageProperties;
    }
    /**
     * Regresa las propiedades físicas del diploma.
     *
     * @return Regresa el objeto que contiene las propiedades físicas actuales
     * del diploma.
     */
    public PageProperties getPageProperties() {
        return pageProperties;
    }
    /**
     * {@inheritDoc}
     */
    public boolean addPageBorder(PageBorder border) {
        if (border != null) {
            if (pageBorders == null) {
                pageBorders = new ArrayList<>();
            }

            return pageBorders.add(border);
        } else {
            return false;
        }
    }
    /**
     * {@inheritDoc}
     */
    public boolean removePageBorder(PageBorder border) {
        if (border != null) {
            if (pageBorders != null) {
                return pageBorders.remove(border);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    /**
     * {@inheritDoc}
     */
    public Collection<PageBorder> getPageBorders() {
        return Collections.unmodifiableList(pageBorders);
    }
    /**
     * {@inheritDoc}
     */
    public void setPageBorders(Collection<PageBorder> pageBorders) {
        for (PageBorder border : pageBorders) {
            addPageBorder(border);
        }
    }
    /**
     * {@inheritDoc}
     */
    public void setPageBorders(PageBorder[] pageBorders) {
        setPageBorders(Arrays.asList(pageBorders));
    }
    /**
     * Regresa el nombre del archivo.
     *
     * @return Regresa un String con el nombre del archivo para este diploma
     * incluyendo la ruta del mismo.
     */
    public String getFileName() {
        return fileName;
    }
    /**
     * Cambia el nombre del archivo.
     *
     * @param fileName El nombre del archivo incluyendo la ruta del mismo.
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    /**
     * {@inheritDoc}
     */
    public Collection<Text> getTexts() {
        return texts;
    }
    /**
     * Cambia el nombre del participante del curso.
     * @param to Una cadena de caracteres con el nombre del
     * participante del curso.
     */
    public void setTo(String to) {
        this.to = to;
        buildTexts();
    }
    /**
     * Regresa el nombre del participante del curso.
     * 
     * @return Una cadena de caracteres con el nombre del participante del curso.
     */
    public String getTo() {
        return to;
    }
    /**
     * Cambia el nombre de quien otorga diploma.
     * @param from Una cadena de caracteres con el nombre de la organización o
     * persona que entrega el diploma.
     */
    public void setFrom(String from) {
        this.from = from;
    }
    /**
     * Regresa el nombre de la compañia.
     * @return Una cadena de caracteres con el nombre de la organización o
     * persona que entrega el diploma.
     */
    public String getFrom() {
        return from;
    }
    /**
     * Cambia la fecha que aparecerá en el diploma.
     * @param date Un objeto de tipo LocalDate con la fecha que aparecerá en el
     * diploma.
     */
    public void setDate(LocalDate date) {
        this.date = date != null ? date : LocalDate.now();
    }
    /**
     * Regresa la fecha del diploma.
     * @return Un objeto LocalDate con la fecha que aparecerá en el diploma.
     */
    public LocalDate getDate() {
        return date != null ? date : LocalDate.now();
    }
    /**
     * Cambia el motivo que aparecerá en el diploma.
     * @param motive Una cadena de caracteres con el nuevo motivo que se
     * mostrará en el diploma.
     */
    public void setMotive(String motive) {
        this.motive = motive;
    }
    /**
     * Regresa el motivo del diploma.
     * @return Una cadena de caracteres con el motivo que aparecerá en el diploma.
     */
    public String getMotive() {
        return this.motive;
    }
    /**
     * 
     * {@inheritDoc }
     */
    public void setPlace(String place) {
        this.place = place;
    }
    /**
     * {@inheritDoc }
     */
    public String getPlace() {
        return place;
    }
    
    /**
     * {@inheritDoc }
     */
    public void setContentMarginSize(float contentMarginSize) {
        this.contentMarginSize = contentMarginSize > 0 ? contentMarginSize : 0;
    }
    
    /**
     * {@inheritDoc }
     */
    public float getContentMarginSize() {
        return contentMarginSize;
    }
    
    /**
     * {@inheritDoc }
     */
    public String getSignFileName() {
        return signFileName;
    }
    
    /**
     * {@inheritDoc }
     */
    public String getInstructorName() {
       return instructorName; 
    }
    
    /**
     * {@inheritDoc }
     */
    public void setHours(int hours) {
        this.hours = hours;
        buildTexts();
    }
    
    /**
     * {@inheritDoc }
     */
    public int getHours() {
        return hours;
    }
}