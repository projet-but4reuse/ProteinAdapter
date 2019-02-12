package org.but4reuse.adapters.proteins;

import java.net.URI;
import java.util.ArrayList;

import org.but4reuse.adapters.IElement;
import org.but4reuse.adapters.impl.AbstractElement;
import org.but4reuse.adapters.impl.IManualComparison;
import org.but4reuse.adapters.markers.IMarkerElement;
import org.but4reuse.adapters.proteins.ProteinElement;
import org.but4reuse.adapters.proteins.utils.ProteinUtils;
import org.but4reuse.utils.workbench.WorkbenchUtils;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

/**
 * Pixel Element
 * 
 * @author jabier.martinez
 */
public class ProteinElement extends AbstractElement implements IMarkerElement{

	public Character letter;
	public int frequency;

	//construtor
	public ProteinElement(Character letter,int frequency) {
		this.letter = letter;
		this.frequency=frequency;
	}

	@Override
	public String getText() {
		String text = "ProteinElment: "+letter 
				+ " , " + "Frequencey: "+frequency;
		return text;
	}

	//ce quoi prime
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((letter == null) ? 0 : letter.hashCode());
		return result;
	}

	@Override
	public double similarity(IElement anotherElement) {
		if (anotherElement instanceof ProteinElement) {
			ProteinElement protein = (ProteinElement) anotherElement;
			//compare the frequency of two elements in the diffrents proteins
			if(this.letter.equals(protein.letter)){
				return ProteinUtils.getProteinSimilarity(this,protein);
			}
		}
		return 0;
	}

	@Override
	public ArrayList<String> getWords() { 
		ArrayList<String> words= new ArrayList<String>();
		String name = letter.toString();
		if (!name.equals("Erreur"))
			words.add(name);
		return words;
	}
	
	/*@Override
	public IManualComparison getManualComparison(double calculatedSimilarity, IElement anotherElement) {
		return 0;
	}*/

	// marker information
	public URI uri;
	public int letterNumber;

	@Override
	public IMarker getMarker() {
		IMarker marker = null;
		IResource ifile = WorkbenchUtils.getIResourceFromURI(uri);
		if (ifile != null && ifile.exists()) {
			try {
				marker = ifile.createMarker(IMarker.TEXT);
				marker.setAttribute(IMarker.LOCATION, ifile.getName());
				//Need to ask
				marker.setAttribute(IMarker.CHAR_START, letterNumber);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		return marker;
	}

	public void setMarkerInfo(URI uri, int letterNumber) throws CoreException {
		this.uri = uri;
		this.letterNumber = letterNumber;
	}
}
