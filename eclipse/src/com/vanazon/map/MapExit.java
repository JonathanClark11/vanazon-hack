package com.vanazon.map;

import com.vanazon.utils.Vector2D;

public class MapExit {
	private String exitId;
	private Vector2D position;
	private String mapId;
	
	public MapExit(String exitId, Vector2D position, String mapId) {
		super();
		this.exitId = exitId;
		this.position = position;
		this.mapId = mapId;
	}

	public String getExitId() {
		return exitId;
	}

	public void setExitId(String exitId) {
		this.exitId = exitId;
	}

	public Vector2D getPosition() {
		return position;
	}

	public void setPosition(Vector2D position) {
		this.position = position;
	}

	public String getMapId() {
		return mapId;
	}

	public void setMapId(String mapId) {
		this.mapId = mapId;
	}
	
	
}
