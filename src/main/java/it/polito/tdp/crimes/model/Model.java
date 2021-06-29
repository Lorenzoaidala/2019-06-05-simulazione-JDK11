package it.polito.tdp.crimes.model;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.crimes.db.EventsDao;

public class Model {
	private Graph <Integer,DefaultWeightedEdge> grafo;
	private EventsDao dao;
	
	public Model() {
		dao=new EventsDao();
	}
	
	public void creaGrafo(int anno) {
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(this.grafo, dao.getVertici());
		
		for(Integer v1 : this.grafo.vertexSet()) {
			for (Integer v2 : this.grafo.vertexSet()) {
				if(!v1.equals(v2)) {
					
					double latMediaV1 = dao.getLatMedia(anno, v1);
					double lonMediaV1 = dao.getLonMedia(anno, v1);
					
					double latMediaV2 = dao.getLatMedia(anno, v2);
					double lonMediaV2 = dao.getLonMedia(anno, v2);
					
					double distanza = LatLngTool.distance(new LatLng(latMediaV1,lonMediaV1), new LatLng(latMediaV2,lonMediaV2), LengthUnit.KILOMETER);
					
					Graphs.addEdgeWithVertices(this.grafo, v1, v2,distanza);
				}
			}
		}
	}
	public int getNArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public int getNVerici() {
		return this.grafo.vertexSet().size();
				
	}
	

}
