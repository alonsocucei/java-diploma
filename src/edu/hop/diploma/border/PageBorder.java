package edu.hop.diploma.border;

import java.awt.Color;

/**
 * Clase que representa el borde para una página en un documento.
 * 
 * 
 * @author jjsanche
 */
public final class PageBorder {
    private float lineWidth;
    private float marginWidth;
    private Color color;
    private BorderSides side;
    //Having points margin can be painted drawing lines from point to point.
    //private List<Point> points;
    
    /**
     * Inicializa un borde con los valores por default.
     * 
     * Los valores por default para un borde son:
     * 
     * Ancho de línea: 0.1
     * Color: negro
     * Ancho del margen: 1
     * Lado del border: superior (TOP)
     * Las unidad de medida a usar será la misma que usa el documento.
     * @param side La constante que representa donde se dibujará el borde.
     */
    public PageBorder(BorderSides side) {
        this(side, 0.1f, Color.BLACK, 1);
    }

    /**
     * Inicializa un borde con los valores por default para color y ancho del
     * margen.
     * 
     * @param side La constante que representa donde se dibujará el borde.
     * @param lineWidth Un número decimal para el ancho de la línea a usar para
     * este margen.
     */
    public PageBorder(BorderSides side, float lineWidth) {
        this(side, lineWidth, Color.BLACK, 1);
    }

    /**
     * Inicializa un borde con el valor por default para el ancho de margen.
     * 
     * @param side La constante que representa donde se dibujará el borde.
     * @param lineWidth Un número decimal para el ancho de la línea a usar para
     * este margen.
     * @param color Un objeto de tipo Color el cuál determinará el color del
     * margen.
     */
    public PageBorder(BorderSides side, float lineWidth, Color color) {
        this(side, lineWidth, color, 1);
    }

    /**
     * Inicializa un borde con los argumentos redibidos.
     * 
     * @param side La constante que representa donde se dibujará el borde.
     * @param lineWidth Un número decimal para el ancho de la línea a usar para
     * este margen.
     * @param color Un objeto de tipo Color el cuál determinará el color del
     * margen.
     * @param marginWidth Un número decimal para el ancho del margen a usar.
     */
    public PageBorder(BorderSides side, float lineWidth, Color color, float marginWidth) {
        setBorderSide(side);
        setLineWidth(lineWidth);
        setMarginWidth(marginWidth);
        setColor(color);
    }

    /**
     * Regresa el ancho de línea del borde.
     * 
     * @return the lineWidth Un número decimal que representa el ancho de línea
     * para el borde en la unidad de medida que usa el documento.
     */
    public float getLineWidth() {
        return lineWidth;
    }

    /**
     * Cambia el ancho de línea del borde.
     * 
     * @param lineWidth Un número decimal que representa el ancho de línea
     * para el borde en la unidad de medida que usa el documento.
     */
    public void setLineWidth(float lineWidth) {
        if (lineWidth > 0) {
            this.lineWidth = lineWidth;
        } else {
            throw new IllegalArgumentException("line width must be a positive number.");
        }
    }

    /**
     * Regresa el ancho del margen para el borde.
     * 
     * @return Un número decimal que representa el ancho del margen para el borde
     * en la unidad de medida usada por el documento.
     */
    public float getMarginWidth() {
        return marginWidth;
    }

    /**
     * Cambia el ancho del margen del borde.
     * 
     * @param marginWidth Un número decimal que representa el ancho del margen para el borde
     * en la unidad de medida usada por el documento.
     */
    public void setMarginWidth(float marginWidth) {
        if (marginWidth >= 0) {
            this.marginWidth = marginWidth;
        } else {
            throw new IllegalArgumentException("Margin must be a positive number.");
        }
    }

    /**
     * Regresa el color del borde.
     * 
     * @return Un objeto de tipo Color que representa el color del borde.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Cambia el color del borde.
     * 
     * @param color Un objeto de tipo Color que representa el color del borde.
     */
    public void setColor(Color color) {
        if (color != null) {
            this.color = color;
        } else {
            this.color = Color.BLACK;
        }
    }
    
    /**
     * Cambia el lado de este borde.
     * 
     * @param side La constante que representa el nuevo lado para un borde.
     */
    public void setBorderSide(BorderSides side) {
        this.side = side;
    }
    
    /**
     * Regresa el lado del borde.
     * @return La constante que representa el lado donde se dibujará el borde.
     */
    public BorderSides getBorderSide() {
        return side;
    }
}
