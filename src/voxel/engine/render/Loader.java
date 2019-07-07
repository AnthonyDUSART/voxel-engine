package voxel.engine.render;

import java.util.ArrayList;

public class Loader {
	
	private ArrayList<Integer> vaos;
	private ArrayList<Integer> vbos;
	
	public Loader() {
		this(new ArrayList<Integer>(), new ArrayList<Integer>());
	}
	
	public Loader(ArrayList<Integer> vaos, ArrayList<Integer> vbos) {
		this.vaos = vaos;
		this.vbos = vbos;
	}
	
	public ArrayList<Integer> getVaos() {
		return this.vaos;
	}
	
	public void setVaos(ArrayList<Integer> vaos) {
		this.vaos = vaos;
	}
	
	public boolean addVao(Integer vao) {
		return this.vaos.add(vao);
	}
	
	public boolean removeVao(Integer vao) {
		return this.vaos.remove(vao);
	}
	
	public ArrayList<Integer> getVbos() {
		return this.vbos;
	}
	
	public void setVbos(ArrayList<Integer> vbos) {
		this.vbos = vbos;
	}
	
	public boolean addVbo(Integer vbo) {
		return this.vbos.add(vbo);
	}
	
	public boolean removeVbo(Integer vbo) {
		return this.vbos.remove(vbo);
	}

}
