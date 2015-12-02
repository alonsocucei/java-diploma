package edu.hop.diploma.border;

import java.util.Collection;

/**
 * Defines the behavior a for a class that needs to include borders in the document.
 * 
 * @author jjsanche
 */
public interface BordersManager {
    /**
     * Adds a new border to the document.
     * 
     * @param border The PageBorder object to be added to the document.
     * @return true if the border was added, false if the parameter is a null value.
     */
    public boolean addPageBorder(PageBorder border);
//    /**
//     * Adds a new  border to the document taking as reference another border.
//     * 
//     * @param border The PageBorder object to add.
//     * @param referenceBorder A previously added border which works as reference.
//     * @param distance A decimal number indicating the distance the new border
//     * must be from the reference border.
//     */
//    public void addPageBorder(PageBorder border, PageBorder referenceBorder, float distance);
    /**
     * Removes a border.
     * 
     * @param border The PageBorder to remove.
     * @return The PageBorder removed or null in case the border doesn't exist.
     */
    public boolean removePageBorder(PageBorder border);
    /**
     * Returns all the borders of the document.
     * @return A Collection with all borders of the document.
     */
    public Collection<PageBorder> getPageBorders();
    /**
     * Sets all borders to draw for the document
     * @param borders A Collection with all borders to draw in the document.
     */
    public void setPageBorders(Collection<PageBorder> borders);
}

