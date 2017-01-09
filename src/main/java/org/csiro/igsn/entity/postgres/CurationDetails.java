package org.csiro.igsn.entity.postgres;

// Generated 09/01/2017 4:40:37 PM by Hibernate Tools 4.3.1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CurationDetails generated by hbm2java
 */
@Entity
@Table(name = "curation_details")
public class CurationDetails implements java.io.Serializable {

	private int curationId;
	private Resources resources;
	private String curator;
	private Date curationDate;
	private String curationLocation;
	private String institutionUri;
	private String curatingInstitution;

	public CurationDetails() {
	}

	public CurationDetails(int curationId) {
		this.curationId = curationId;
	}

	public CurationDetails(int curationId, Resources resources, String curator,
			Date curationDate, String curationLocation, String institutionUri,
			String curatingInstitution) {
		this.curationId = curationId;
		this.resources = resources;
		this.curator = curator;
		this.curationDate = curationDate;
		this.curationLocation = curationLocation;
		this.institutionUri = institutionUri;
		this.curatingInstitution = curatingInstitution;
	}

	@Id
	@Column(name = "curation_id", unique = true, nullable = false)
	public int getCurationId() {
		return this.curationId;
	}

	public void setCurationId(int curationId) {
		this.curationId = curationId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "resource_identifier")
	public Resources getResources() {
		return this.resources;
	}

	public void setResources(Resources resources) {
		this.resources = resources;
	}

	@Column(name = "curator")
	public String getCurator() {
		return this.curator;
	}

	public void setCurator(String curator) {
		this.curator = curator;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "curation_date", length = 29)
	public Date getCurationDate() {
		return this.curationDate;
	}

	public void setCurationDate(Date curationDate) {
		this.curationDate = curationDate;
	}

	@Column(name = "curation_location")
	public String getCurationLocation() {
		return this.curationLocation;
	}

	public void setCurationLocation(String curationLocation) {
		this.curationLocation = curationLocation;
	}

	@Column(name = "institution_uri")
	public String getInstitutionUri() {
		return this.institutionUri;
	}

	public void setInstitutionUri(String institutionUri) {
		this.institutionUri = institutionUri;
	}

	@Column(name = "curating_institution")
	public String getCuratingInstitution() {
		return this.curatingInstitution;
	}

	public void setCuratingInstitution(String curatingInstitution) {
		this.curatingInstitution = curatingInstitution;
	}

}