package edu.hop.diploma.text;

import java.awt.Color;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 * Clase que describe un texto en un documento.
 * 
 * @author jjsanche
 */
public class Text {
    private String text;
    private float fontSize;
    private PDType1Font fontType;
    private Alignments alignment;
    private Color color;
    private boolean isUnderlined;
    private boolean breakLine;

    /**
     * Crea un texto a dibujar con los caracteres indicados en el agurmento.
     * 
     * @param text La cadena de texto a dibujar.
     */
    public Text(String text) {
        setText(text);
        setColor(Color.BLACK);
        setFontSize(12);
        setFontType(PDType1Font.TIMES_ROMAN);
        setAlignment(Alignments.LEFT);
        setBreakLine(true);
        setIsUnderlined(false);
    }
    
    /**
     * Crea un texto a dibujar con los caracteres y tamaño de la fuente indicados
     * en los argumentos.
     * 
     * @param text La cadena de texto a dibujar.
     * @param fontSize Un número decimal con el tamaño de la fuente a dibujar.
     */
    public Text(String text, float fontSize) {
        this(text);
        setFontSize(fontSize);
    }
    
    /**
     * Crea un texto a dibujar con los caracteres y tamaño de la fuente indicados
     * en los argumentos.
     * 
     * @param text La cadena de texto a dibujar.
     * @param fontSize Un número decimal con el tamaño de la fuente a dibujar.
     * @param fontType El tipo de fuente para el texto a dibujar.
     */
    public Text(String text, float fontSize, PDType1Font fontType) {
        this(text, fontSize);
        setFontType(fontType);
    }
    
    /**
     * Crea un texto a dibujar con los caracteres y tamaño de la fuente indicados
     * en los argumentos.
     * 
     * @param text La cadena de texto a dibujar.
     * @param fontSize Un número decimal con el tamaño de la fuente a dibujar.
     * @param fontType El tipo de fuente para el texto a dibujar.
     * @param alignment La constante que representa la alineación de la fuente
     */
    public Text(String text, float fontSize, PDType1Font fontType, Alignments alignment) {
        this(text, fontSize, fontType);
        setAlignment(alignment);
    }
    
    /**
     * Crea un texto a dibujar con los caracteres y tamaño de la fuente indicados
     * en los argumentos.
     * 
     * @param text La cadena de texto a dibujar.
     * @param fontSize Un número decimal con el tamaño de la fuente a dibujar.
     * @param fontType El tipo de fuente para el texto a dibujar.
     * @param alignment La constante que representa la alineación de la fuente
     * @param color El color con el cual se pintará el texto.
     */
    public Text(String text, float fontSize, PDType1Font fontType, 
            Alignments alignment, Color color) {
        this(text, fontSize, fontType);
        setAlignment(alignment);
        setColor(color);
    }
    
    /**
     * Crea un texto a dibujar basado en uno ya creado.
     * @param text El objeto Text a duplicar.
     */
    public Text(Text text) {
        this(text.getText(), text.getFontSize(), text.getFontType(), 
                text.getAlignment(), text.getColor());
    }
    
    /**
     * Regresa el tamaño actual del texto.
     * 
     * @return Un número decimal indicando el tamaño de la fuente en la unidad
     * definida para el documento actual.
     */
    public float getFontSize() {
        return fontSize;
    }

    /**
     * Cambia el tamaño de la fuente.
     * 
     * @param fontSize Un número decimal indicando el tamaño de la fuente en la unidad
     * definida para el documento actual.
     */
    public void setFontSize(float fontSize) {
        this.fontSize = fontSize;
    }

    /**
     * Regresa la cadena de caracteres a dibujar.
     * 
     * @return Un objeto String con los caracteres que se dibujarán en el documento.
     */
    public String getText() {
        return text;
    }

    /**
     * Cambia el texto a dibujar.
     * 
     * @param text Un objeto String con los caracteres que se dibujarán en el documento.
     */
    public void setText(String text) {
        this.text = text;
    }
    
    /**
     * Regresa el tipo de fuente del texto.
     * 
     * @return Una constante que representa el tipo de fuente que se aplicará
     * al texto.
     */
    public PDType1Font getFontType() {
        return fontType;
    }

    /**
     * Cambia el tipo de fuente para el texto.
     * 
     * @param fontType Una constante que representa el tipo de fuente que se aplicará
     * al texto.
     */
    public void setFontType(PDType1Font fontType) {
        this.fontType = fontType;
    }

    /**
     * Regresa el tipo de alineación del texto.
     * 
     * @return La constante que representa la alineación del texto.
     */
    public Alignments getAlignment() {
        return alignment;
    }

    /**
     * Cambia la alineación del texto.
     * 
     * @param alignment La constante que representa la alineación del texto.
     * @return El objeto actual, lo cual permite concatenar llamadas a este.
     */
    public Text setAlignment(Alignments alignment) {
        this.alignment = alignment;
        return this;
    }
    
    /**
     * Cambia el color de la fuente.
     * @param color El color con el cual se pintará el texto.
     */
    public void setColor(Color color) {
        this.color = color;
    }
    
    /**
     * Regresa el color del texto.
     * @return El color con el cual se pintará el texto.
     */
    public Color getColor() {
        return color;
    }

    /**
     * true si el texto será subrayado al dibujarlo.
     * 
     * @return true si se subrayará el texto al dibujarlo, cualquier otro caso
     * false.
     */
    public boolean isUnderlined() {
        return isUnderlined;
    }

    /**
     * Cambia si el texto será subrayado o no.
     * @param isUnderlined true para que el texto se subraye al dibujarse, false
     * para quitar el subrayado al texto.
     * @return el objeto actual, lo cuál permite concatenar llamadas.
     */
    public Text setIsUnderlined(boolean isUnderlined) {
        this.isUnderlined = isUnderlined;
        return this;
    }

    /**
     * true si al finalizar el texto se insertará un cambio de línea automáticamente.
     * 
     * @return true para indicar que se insertará un cambio de línea al dibujar
     * el texto, false en caso contrario.
     */
    public boolean getBreakLine() {
        return breakLine;
    }

    /**
     * Cambia si se agregará un cambio de línea al texto.
     * 
     * @param breakLine true para indicar que se agregará un cambio de línea
     * después del texto, false de lo contrario.
     * @return el objeto actual, lo cual permite concatenar llamadas.
     */
    public Text setBreakLine(boolean breakLine) {
        this.breakLine = breakLine;
        return this;
    }
}
