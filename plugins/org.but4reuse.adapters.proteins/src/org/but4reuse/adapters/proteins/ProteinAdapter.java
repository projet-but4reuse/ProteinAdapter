package org.but4reuse.adapters.proteins;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.but4reuse.adapters.IAdapter;
import org.but4reuse.adapters.IElement;
import org.but4reuse.adapters.proteins.ProteinElement;
import org.but4reuse.utils.files.FileUtils;
import org.eclipse.core.runtime.IProgressMonitor;

public class ProteinAdapter implements IAdapter {

	@Override
	public boolean isAdaptable(URI uri, IProgressMonitor monitor) {
		File file = FileUtils.getFile(uri);
		if (file != null && file.exists() && !file.isDirectory()) {
			return true;
		}
		return false;
	}

	@Override
	public List<IElement> adapt(URI uri, IProgressMonitor monitor) {
		List<IElement> elements = new ArrayList<IElement>();
		File file = FileUtils.getFile(uri);
		Map<Character, Integer> letters= new HashMap<Character, Integer>();
		try {
			FileInputStream fstream = new FileInputStream(file);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLetter;
			int elementNumber = 0;
			while ((strLetter = br.readLine()) != null) {
				String[] pieces = strLetter.split("\t");
				for (String piece : pieces) {
					for(Character letter :piece.toCharArray()){

						if(!letters.containsKey(letter)){
							letters.put(letter, 0);
						}else{
							letters.put(letter, letters.get(letter)+1);
						}
					}
				}
			}
			
			for(Character letter : letters.keySet()){
				ProteinElement proteinElement = new ProteinElement(letter,letters.get(letter));
				proteinElement.setMarkerInfo(uri, elementNumber);
				elementNumber++;
				elements.add(proteinElement);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return elements;
	}

	@Override
	public void construct(URI uri, List<IElement> elements, IProgressMonitor monitor) {
	}

}
