package edu.hop.diploma.physical;

/**
 * Contiene las propiedades físicas de un diploma, tales como el ancho, alto,
 * orientación y unidad de medida a usar.
 * 
 * @author jjsanche
 */
public final class PageProperties {

    private float width;
    private float height;
    private PageOrientation orientation;
    private SizeUnits sizeUnit = SizeUnits.INCHES;
    
    /**
     * Inicializa las propiedades por default para un documento en tamaño carta.
     * 
     * <p>Los valores por default para las propiedades de un documento son las siguientes:
     * 
     * Alto: 11 pulgadas
     * Ancho: 8.5 pulgadas
     * Orientación: vertical (PORTRAIT)
     * Unidad de medida: pulgadas
     * </p>
     */
    public PageProperties() {
        setWidth(PageDimensions.CARTA.getWidth());
        setHeight(PageDimensions.CARTA.getHeight());
        orientation = PageOrientation.PORTRAIT;
    }

    /**
     * Inicializa las propiedades de una página para un documento utilizando 
     * las dimensiones enviadas como parametros utilizando las unidades por
     * default.
     * 
     * @param pageDimensions El objeto que contiene las dimensiones a usar.
     */
    public PageProperties(PageDimensions pageDimensions) {
        this(pageDimensions.getWidth(), pageDimensions.getHeight());
    }

    /**
     * Inicializa las propiedades de una página para un documento utilizando 
     * las dimensiones y orientación enviadas como parametros utilizando las
     * unidades por default.
     * 
     * @param pageDimensions El objeto que contiene las dimensiones a usar.
     * @param orientation La constante que indica la orientación del documento a usar.
     */
    public PageProperties(PageDimensions pageDimensions, PageOrientation orientation) {
        this(pageDimensions);
        this.orientation = orientation;
    }

    /**
     * Inicializa las propiedades de una página para un documento utilizando 
     * las dimensiones enviadas como parametros utilizando las unidades por default.
     * 
     * @param width El ancho del documento.
     * @param height La altura del documento.
     */
    public PageProperties(float width, float height) {
        setWidth(width);
        setHeight(height);
    }

    /**
     *Inicializa las propiedades de una página para un documento utilizando 
     * las dimensiones y orientación enviadas como parametros utilizando las 
     * unidades por default.
     * 
     * @param width El ancho del documento.
     * @param height La altura del documento.
     * @param orientation La constante que indica la orientación del documento a usar. 
     */
    public PageProperties(float width, float height, PageOrientation orientation) {
        this(width, height);
        this.orientation = orientation;
    }

    /**
     * Cambia el valor para la orientación del documento.
     * 
     * @param orientation La constante que indica la orientación del documento a usar.
     */
    public void setPageOrientation(PageOrientation orientation) {
        if (orientation != null && orientation != this.orientation) {
            float width = getWidth();
            //switch values
            setWidth(getHeight());
            setHeight(width);
        }
        
        this.orientation = orientation;
    }

    /**
     * Regresa el valor actual de la orientación del documento.
     * 
     * @return La constante que indica la orientación usada actualmente en del documento.
     */
    public PageOrientation getPageOrientation() {
        return orientation;
    }

    /**
     * Cambia el valor del ancho del documento.
     * 
     * @param width El ancho del documento en la unidad de medida actual para
     * el mismo.
     */
    public void setWidth(float width) {
        if (width > 0) {
            this.width = width;
        } else {
            throw new IllegalArgumentException("Width must be greater than 0.");
        }
    }

    /**
     * Regresa el ancho del documento.
     * 
     * @return El ancho del documento en la unidad de medida actual para el mismo.
     */
    public float getWidth() {
        return width;
    }

    /**
     * Cambia la altura del documento.
     * 
     * @param height La altura del documento en la unidad de medida actual para el
     * mismo.
     */
    public void setHeight(float height) {
        if (height > 0) {
            this.height = height;
        } else {
            throw new IllegalArgumentException("Height must be greater than 0.");
        }
    }

    /**
     * Regresa la altura del documento.
     * 
     * @return La altura del documento en la unidad de medida actual para el
     * mismo.
     */
    public float getHeight() {
        return height;
    }
    
    /**
     * Regresa la unida de medida del documento.
     * 
     * @return La unidad de medida actual para el documento.
     */
    public SizeUnits getSizeUnit() {
        return sizeUnit;
    }
    
    /**
     * Cambia la unidad de medida a usar para el documento.
     * 
     * @param sizeUnit La constante de la unidad de medida a usar para el documento.
     */
    public void setSizeUnit(SizeUnits sizeUnit) {
        this.sizeUnit = sizeUnit;
    }
}
