package com.vanazon.utils;

import java.io.InputStream;

import android.content.res.AssetManager;

public class XmlLoader {
	AssetManager mgr = null;
	public XmlLoader(AssetManager mgr) {
		this.mgr = mgr;
	}
	
	public InputStream getInputStream(String filename) throws Exception {
		return mgr.open(filename);
	}
}
