package edu.hop.diploma.builder;

import edu.hop.diploma.border.BorderSides;
import edu.hop.diploma.border.PageBorder;
import edu.hop.diploma.physical.PageProperties;
import edu.hop.diploma.text.Alignments;
import edu.hop.diploma.text.Text;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;

/**
 * Clase encargada de la construcción de diplomas. Esta clase utiliza Apache
 * PDFBox para crear un diploma en formato PDF.
 *
 * @author jjsanche
 */
public class DiplomaBuilder {

    private static float pointsPerUnit;
    private static float pageWidth;
    private static float pageHeight;
    private static float currentYPosition;
    private static float currentXPosition;
    private static float heightTopLimit;
    private static float heightBottomLimit;
    private static float widthRightLimit;
    private static float widthLeftLimit;
    private static float contentMarginSize;
    private static Diploma diploma;
    private static PDJpeg signImage;
    /**
     * Construye un diploma haciendo uso de la libreria Apache PDFBox.
     *
     * @param diploma El {@link Diploma} a construir.
     */
    public static void buildDiploma(Diploma diploma) throws IOException {
        DiplomaBuilder.diploma = diploma;
        PageProperties pageProperties = diploma.getPageProperties();
        pointsPerUnit = pageProperties.getSizeUnit().getPointsPerUnit();
        contentMarginSize = diploma.getContentMarginSize() * pointsPerUnit;
        pageWidth = pageProperties.getWidth() * pointsPerUnit;
        pageHeight = pageProperties.getHeight() * pointsPerUnit;
        heightTopLimit = pageHeight - contentMarginSize;
        heightBottomLimit = 0 + contentMarginSize;
        widthRightLimit = pageWidth - contentMarginSize;
        widthLeftLimit = 0 + contentMarginSize;
        currentYPosition = heightTopLimit - contentMarginSize;
        currentXPosition = widthLeftLimit + contentMarginSize;

        PDDocument diplomaPDF = new PDDocument();
        PDPage page = new PDPage(new PDRectangle(pageWidth, pageHeight));
        InputStream inputStream = new FileInputStream(new File(diploma.getSignFileName()));
        PDJpeg signImage = new PDJpeg(diplomaPDF, inputStream);
        DiplomaBuilder.signImage = signImage;
        PDPageContentStream contentStream = createContentStream(diplomaPDF, page);

        diplomaPDF.addPage(page);
        drawBorders(diploma.getPageBorders(), contentStream);
        drawTexts(diploma.getTexts(), contentStream);

        try {
            contentStream.close();
            diplomaPDF.save(diploma.getFileName());
            diplomaPDF.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            Logger.getLogger(DiplomaBuilder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (COSVisitorException ex) {
            ex.printStackTrace();
            Logger.getLogger(DiplomaBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Crea un flujo de contenido para el documento.
     *
     * @param document El documento donde se encuentra la página.
     * @param page La página donde se creara el flujo de contenido.
     * @return Un flujo de contenido para el documento.
     */
    private static PDPageContentStream createContentStream(PDDocument document, PDPage page) {
        PDPageContentStream contentStream = null;

        try {
            contentStream = new PDPageContentStream(document, page);
        } catch (IOException e) {
            System.err.println("Error al intentar crear contenido para el archivo");
            e.printStackTrace();
        }

        return contentStream;
    }

    /**
     * 
     * @param image
     * @param contentStream
     * @throws IOException 
     */
    private static void drawSignature(PDXObjectImage image, float x, float y, 
            float width, float height, PDPageContentStream contentStream) throws IOException {
        contentStream.drawXObject(image, x, y, width, height);
    }

    /**
     * Dibuja los bordes del diploma.
     *
     * @param borders La colección de bordes a dibujar.
     * @param contentStream El flujo de contenido donde se dibujarán los bordes.
     */
    private static void drawBorders(Collection<PageBorder> borders, PDPageContentStream contentStream) {
        for (PageBorder border : borders) {
            drawBorder(border, contentStream);
        }
    }

    /**
     * Dibuja un border en el flujo de contenido.
     *
     * @param border El borde a dibujar.
     * @param contentStream El flujo de contenido donde se dibujará el borde.
     */
    private static void drawBorder(PageBorder border, PDPageContentStream contentStream) {
        try {
            contentStream.setNonStrokingColor(border.getColor());
            BorderSides side = border.getBorderSide();
            float margin = border.getMarginWidth() * pointsPerUnit;
            float lineWidth = border.getLineWidth() * pointsPerUnit;
            float widthRight = pageWidth - margin;
            float heightTop = pageHeight - margin;

            float x = margin;
            float y = margin;
            float width = 1; //1 is assigned just to avoid the compile error
            float height = 1; //1 is assigned just to avoid the compile error

            switch (side) {
                case TOP:
                    y = heightTop - lineWidth;

                    if (heightTopLimit >= y) {
                        heightTopLimit = currentYPosition = y - contentMarginSize;
                    }
                case BOTTOM:
                    width = widthRight - margin;
                    height = lineWidth;

                    if (side == BorderSides.BOTTOM && heightBottomLimit <= y) {
                        heightBottomLimit = y + contentMarginSize + lineWidth;
                    }
                    break;
                case RIGHT:
                    x = widthRight - lineWidth;

                    if (widthRightLimit >= x) {
                        widthRightLimit = x - contentMarginSize;
                        currentXPosition = widthRightLimit + lineWidth;
                    }
                case LEFT:
                    width = lineWidth;
                    height = heightTop - margin;

                    if (side == BorderSides.LEFT && widthLeftLimit <= x) {
                        widthLeftLimit = currentXPosition = x + contentMarginSize;
                    }
                    break;
            }

//            System.out.printf("side %s x: %s y: %s  width: %s  height: %s\n", side, x, y, width, height);
            contentStream.fillRect(x, y, width, height);
        } catch (IOException ioe) {
            System.err.println("Error al dibuar los bordes");
            ioe.printStackTrace();
        }
    }

    /**
     * Dibuja los textos en el flujo de contenido del documento.
     *
     * @param texts Una colección con los textos a dibujar.
     * @param contentStream El flujo de contenido del documento donde se
     * dibujarán los textos.
     */
    private static void drawTexts(Collection<Text> texts, PDPageContentStream contentStream) {
        List<Text> centeredTexts = new ArrayList<>();
        List<Float> deltaWidths = new ArrayList<>();
        float centeredX = pageWidth / 2;
        
        for (Text text : texts) {
            try {
                TextDimensions textDimensions = getTextDimensions(text);
                switch(text.getAlignment()) {
                    case CENTER:
                        centeredTexts.add(new Text(text).setAlignment(Alignments.LEFT).setIsUnderlined(text.isUnderlined()));
                        DiplomaPoint textPoint = calculateTextPosition(text, textDimensions, text.getBreakLine());
                        float delta = pageWidth / 2 - textPoint.x;
                        deltaWidths.add(delta * 2);//la longitud del texto completo
                        centeredX -= delta;
                        
                        if (text.getBreakLine()) {
                            while(centeredTexts.size() > 0) {
                                drawText(centeredTexts.remove(0), textDimensions, contentStream, new DiplomaPoint(centeredX, currentYPosition));
                                centeredX += deltaWidths.remove(0);
                            }
                            
                            centeredX = pageWidth / 2;
                        }
                        
                        break;
                    default:
                        drawText(text, textDimensions, contentStream);
                        break;
                }
            } catch (IOException ioe) {
                System.err.println("Error al dibujar el texto: " + text.getText());
            }
        }
    }

    /**
     * Dibuja un objeto Text en el documento. El lugar que en donde se dibujará
     * será en una nueva línea, las coordenadas serán calculadas para que los
     * textos no se encimen. En el cálculo de la posición se tomará en cuenta el
     * margen del contenido, la alineación, tamaño y tipo del texto.
     *
     * @param text El objeto Text a dibujar.
     * @param contentStream El flujo de contenido del documento.
     * @throws IOException En caso de que exista un error al dibujar el texto.
     */
    private static void drawText(Text text, TextDimensions textDimensions, PDPageContentStream contentStream) throws IOException {
        DiplomaPoint textPoint = calculateTextPosition(text, textDimensions, true);
        drawText(text, textDimensions, contentStream, textPoint);
    }
    
    /**
     * Dibuja un objeto Text en el documento. El lugar que en donde se dibujará
     * será en una nueva línea, las coordenadas serán calculadas para que los
     * textos no se encimen. En el cálculo de la posición se tomará en cuenta el
     * margen del contenido, la alineación, tamaño y tipo del texto.
     *
     * @param text El objeto Text a dibujar.
     * @param textDimensions
     * @param contentStream El flujo de contenido del documento.
     * @param textPoint El punto que indica las coordenadas donde se iniciará
     * a dibujar el texto.
     * @throws IOException En caso de que exista un error al dibujar el texto.
     */
    private static void drawText(Text text, TextDimensions textDimensions, PDPageContentStream contentStream, DiplomaPoint textPoint) throws IOException {
        contentStream.setNonStrokingColor(text.getColor());
        contentStream.beginText();
        contentStream.setFont(text.getFontType(), text.getFontSize());
        contentStream.moveTextPositionByAmount(textPoint.x, textPoint.y);
        contentStream.drawString(text.getText());
        contentStream.endText();
        
        if (text.isUnderlined()) {
            drawLine(textDimensions, new DiplomaPoint(textPoint.x, textPoint.y - 1.5f), contentStream);
            drawLine(textDimensions, new DiplomaPoint(textPoint.x, textPoint.y - 1), contentStream);
        }
        
        if (text.getText().equals(diploma.getInstructorName())) {
            drawLine(textDimensions, new DiplomaPoint(textPoint.x, textPoint.y + textDimensions.height + 1.5f), contentStream);
            drawLine(textDimensions, new DiplomaPoint(textPoint.x, textPoint.y + textDimensions.height + 1), contentStream);
            float width = signImage.getWidth() * 0.2f;
            float height = signImage.getHeight() * 0.2f;
            float x = (textDimensions.width - width) / 2 + textPoint.x;
            float y = textPoint.y + textDimensions.height + 5;
            drawSignature(signImage, x, y,width, height, contentStream);
        }
    }
    
    /**
     * 
     * @param textDimensions
     * @param diplomaPoint
     * @param contentStream
     * @throws IOException 
     */
    private static void drawLine(TextDimensions textDimensions, DiplomaPoint diplomaPoint, PDPageContentStream contentStream) throws IOException {
        contentStream.drawLine(diplomaPoint.x, diplomaPoint.y, 
                diplomaPoint.x + textDimensions.width, diplomaPoint.y);
    }
    
    /**
     * Calcula la posición correcta de dónde debe iniciar a dibujarse el texto.
     *
     * @param text El objeto Text a dibujar.
     * @param textDimensions
     * @param modifyPositions Indica si modifica las posiciones actuales para
     * las coordenadas en el diploma.
     * @return El punto de inicio para dibujar el texto.
     * @throws IOException En caso de que no exista un error al calcular la
     * posición del texto.
     */
    private static DiplomaPoint calculateTextPosition(Text text, TextDimensions textDimensions, boolean modifyPositions) throws IOException {
        Alignments textAlignment = text.getAlignment();
        float textWidth = textDimensions.width;
        float textHeight = textDimensions.height;
        float y = currentYPosition - textHeight;
        float x = 0;
        
        switch (textAlignment) {
            case CENTER:
                x = (pageWidth - textWidth) / 2;
                break;
            case LEFT:
                x = currentXPosition;
                break;
            case RIGHT:
                x = currentXPosition - textWidth;
                break;
        }

        if (modifyPositions) {
            if (text.getBreakLine()) {
                currentXPosition = widthLeftLimit + contentMarginSize;
                currentYPosition -= currentYPosition - textHeight > heightBottomLimit
                        ? textHeight : currentYPosition - heightBottomLimit;
            } else {
                currentXPosition += textAlignment == Alignments.LEFT ? textWidth : -textWidth;
            }
        }
        
        return new DiplomaPoint(x, y);
    }
    
    /**
     * 
     * @param text
     * @return
     * @throws IOException 
     */
    private static TextDimensions getTextDimensions(Text text) throws IOException {
        PDFont fontType = text.getFontType();
        float textWidth = fontType.getStringWidth(text.getText()) / 1000 * text.getFontSize();
        float textHeight = fontType.getFontDescriptor().getFontBoundingBox().
                getHeight() / 1000 * text.getFontSize();
        
        return new TextDimensions(textWidth, textHeight);
    }
    
    /**
     * 
     */
    private static class TextDimensions {
        public float width;
        public float height;
        
        /**
         * 
         * @param width
         * @param height 
         */
        public TextDimensions(float width, float height) {
            this.width = width;
            this.height = height;
        }
    }
    
    /**
     * Clase estática utilizada para agrupar los valores de un punto en el
     * documento utilizando números de tipo float.
     */
    private static class DiplomaPoint {

        public float x;
        public float y;

        /**
         * Construye un punto que se ubicará en el diploma. Ambas coordenadas
         * deben ser mayores o igual a 0 debido a que no se cuenta con
         * coordenadas negativas.
         *
         * Si alguno de los valores es negativo, se asignará el valor por
         * default el cuál es 0;
         *
         * @param x El valor correspondiente a la coordenada x.
         * @param y El valor correspondiente a la coordenada y.
         */
        public DiplomaPoint(float x, float y) {
            this.x = x >= 0 ? x : 0;
            this.y = y >= 0 ? y : 0;
        }
    }
}
