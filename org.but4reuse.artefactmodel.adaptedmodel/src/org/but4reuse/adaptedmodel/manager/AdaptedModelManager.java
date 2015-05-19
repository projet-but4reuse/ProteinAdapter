package org.but4reuse.adaptedmodel.manager;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.but4reuse.adaptedmodel.AdaptedModel;
import org.but4reuse.adapters.IAdapter;

/**
 * Adapted Model Manager
 * 
 * @author jabier.martinez
 * 
 */
public class AdaptedModelManager {

	private static AdaptedModel adaptedModel = null;
	private static List<IAdapter> adapters = null;
	private static Map<String, Long> elapsedTimeRegistry = new LinkedHashMap<String, Long>();

	public static AdaptedModel getAdaptedModel() {
		return adaptedModel;
	}

	public static void setAdaptedModel(AdaptedModel adaptedModel) {
		AdaptedModelManager.adaptedModel = adaptedModel;
	}

	public static List<IAdapter> getAdapters() {
		return adapters;
	}

	public static void setAdapters(List<IAdapter> adapters) {
		AdaptedModelManager.adapters = adapters;
	}

	/**
	 * Register elapsed time. Use milliseconds.
	 * 
	 * @param text
	 * @param timestamp
	 */
	public static void registerTime(String text, long timestamp) {
		elapsedTimeRegistry.put(text, timestamp);
	}

	public static Map<String, Long> getElapsedTimeRegistry() {
		return elapsedTimeRegistry;
	}

}