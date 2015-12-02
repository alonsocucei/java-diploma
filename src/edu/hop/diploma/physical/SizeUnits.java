package edu.hop.diploma.physical;

/**
 * Contiene las distintas unidades a usar cuando se construye un documento PDF.
 * <p>Se recomienda usar las pulgadas debido a que tiene un número entero de puntos
 * por unidad, el cuál son 72. En otras palabras por cada pulgada se pintarán 72 
 * puntos en el documento. En cambio para los centímetros el total de puntos
 * es igual a 72/2.54.
 * </p>
 * 
 * 
 * @author jjsanche
 */
public enum SizeUnits {
    /**
     * Constante que representa unidades en pulgadas.
     */
    INCHES(72),
    /**
     * Constante que representa unidades en centimetros.
     */
    CM(72f/2.54f);
    
    private float pointsPerUnit;
    
    /**
     * Inicializa un tipo de medida con los puntos por unidad de medida que soporta.
     */
    private SizeUnits(float pointsPerUnit) {
        this.pointsPerUnit = pointsPerUnit;
    }
    
    /**
     * Regresa los puntos por unidad de medida.
     * 
     * @return Un decimal con los puntos por unidad de medida que representa esta constante.
     */
    public float getPointsPerUnit() {
        return pointsPerUnit;
    }
}
