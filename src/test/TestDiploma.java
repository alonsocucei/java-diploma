package test;


import edu.hop.diploma.border.BorderSides;
import edu.hop.diploma.border.PageBorder;
import edu.hop.diploma.builder.DiplomaBuilder;
import edu.hop.diploma.javacore.JavaCoreDiploma;
import edu.hop.diploma.physical.PageOrientation;
import edu.hop.diploma.physical.PageProperties;
import java.awt.Color;
import java.io.IOException;

/**
 *
 * @author jjsanche
 */
public class TestDiploma {
    public static void main(String[] args) throws IOException {
        PageBorder[] borders = new PageBorder[4];
        borders[0] = new PageBorder(BorderSides.TOP, 0.1f, new Color(255, 0, 0), 0.5f);
        borders[1] = new PageBorder(BorderSides.RIGHT, 0.1f, new Color(50, 30, 20), 0.5f);
        borders[2] = new PageBorder(BorderSides.BOTTOM, 0.1f, new Color(50, 30, 20), 0.5f);
        borders[3] = new PageBorder(BorderSides.LEFT, 0.1f, new Color(50, 30, 20), 0.5f);
//        borders[0] = new PageBorder(BorderSides.TOP, 0.05f, new Color(0, 255, 0), 0.1f);
        
        PageProperties pageProperties = new PageProperties();
        pageProperties.setPageOrientation(PageOrientation.LANDSCAPE);
        
        JavaCoreDiploma jcDiploma = new JavaCoreDiploma();
        jcDiploma.setTo("Juan Antonio");
        jcDiploma.setHours(53);
        jcDiploma.setPageProperties(pageProperties);
        jcDiploma.setPageBorders(borders);
        jcDiploma.setContentMarginSize(0.5f);
        jcDiploma.setFileName("D:\\Personal Projects\\Java Core Diploma\\Java Core Diploma.pdf");
        
        DiplomaBuilder.buildDiploma(jcDiploma);
    }
}
