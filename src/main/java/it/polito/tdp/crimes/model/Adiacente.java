package it.polito.tdp.crimes.model;

public class Adiacente implements Comparable<Adiacente>{

	Integer distretto;
	Double distanza;
	
	public Adiacente(Integer distretto, Double distanza) {
		super();
		this.distretto = distretto;
		this.distanza = distanza;
	}
	public Integer getDistretto() {
		return distretto;
	}
	public void setDistretto(Integer distretto) {
		this.distretto = distretto;
	}
	public Double getDistanza() {
		return distanza;
	}
	public void setDistanza(Double distanza) {
		this.distanza = distanza;
	}
	
	public int compareTo(Adiacente o) {
		return this.distanza.compareTo(o.getDistanza());
	}
	@Override
	public String toString() {
		return "Adiacente [distretto=" + distretto + ", distanza=" + distanza + "]";
	}
	
	
	
}
