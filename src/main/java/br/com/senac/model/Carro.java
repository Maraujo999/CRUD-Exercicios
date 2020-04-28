package br.com.senac.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Carro implements Serializable {

	private static final long serialVersionUID = -8029690907699214700L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String modelo;

	public Integer getId() {
		return id;
	}

	@OneToOne
	@JoinColumn(name = "id_chave")
	private Chave chave;
	
	@OneToOne
	@JoinColumn(name ="id_documento")
	private Documento documento;
	
	@ManyToOne
	@JoinColumn(name = "id_fabricante")
	private Fabricante fabricante;
	
	
	@ManyToMany
	@JoinTable(name="carro_acessorio",
			joinColumns = { @JoinColumn(name = "id") },
			inverseJoinColumns = {@JoinColumn(name = "id_acessorio")
			}
			)
	private List<Acessorio> acessorios;
	
	
	
	

	public List<Acessorio> getAcessorios() {
		return acessorios;
	}

	public void setAcessorios(List<Acessorio> acessorios) {
		this.acessorios = acessorios;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public Chave getChave() {
		return chave;
	}

	public void setChave(Chave chave) {
		this.chave = chave;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

}
