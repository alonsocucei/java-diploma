package edu.hop.diploma.physical;

/**
 * Contiene los diferentes tamaños predeterminados que se pueden aplicar a un
 * documento PDF.
 * 
 * @author honprogramming
 */
public enum PageDimensions {
    /**
     * Tamaño carta equivalente a 8.5 x 11 pulgadas.
     */
    CARTA(8.5f, 11), 
    /**
     * Tamaño oficio equivalente a 8.5 x 14 pulgadas.
     */
    OFICIO(8.5f, 14);
    
    private final float width;
    private final float height;
    
    /**
     * Inicializa las dimensiones a establecer para un tipo enumerado predeterminado.
     * 
     * @param width El ancho del documento.
     * @param height La altura del documento.
     */
    private PageDimensions(float width, float height) {
        this.width = width;
        this.height = height;
    }
    
    /**
     * Regresa el ancho del documento.
     * 
     * @return Un número decimal correspondiente al ancho del documento.
     */
    public float getWidth() {
        return width;
    }
    
    /**
     * Regresa la altura del documento.
     * 
     * @return Un número decimal correspondiente a la altura del documento.
     */
    public float getHeight() {
        return height;
    }
}
