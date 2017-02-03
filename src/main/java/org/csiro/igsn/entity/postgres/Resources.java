package org.csiro.igsn.entity.postgres;

// Generated 09/01/2017 4:40:37 PM by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Resources generated by hbm2java
 */
@Entity
@Table(name = "resources")
@NamedQueries({
	@NamedQuery(
			name="Resources.searchByIdentifier",
		    query="SELECT r FROM Resources r left join fetch r.location left join fetch r.logDate left join fetch r.method left join fetch r.resourceDate "
		    		+ "left join fetch r.contributorses c left join fetch c.cvIdentifierType left join fetch r.relatedResourceses rr left join fetch rr.cvIdentifierType left join fetch r.alternateIdentifierses "
		    		+ "left join fetch r.classificationses "
		    		+ "left join fetch r.resourceTypeses rt left join fetch rt.cvResourceType "
		    		+ "left join fetch r.sampledFeatureses left join fetch r.curationDetailses left join fetch r.materialTypeses mt left join fetch mt.cvMaterialTypes "		    		
		    		+ "where upper(r.resourceIdentifier) = upper(:resourceIdentifier)"
	),
	@NamedQuery(
			name="Resources.searchpublic",
		    query="SELECT r FROM Resources r where r.isPublic = true and r.resourceIdentifier = :resourceIdentifier"
	)
})		
public class Resources implements java.io.Serializable {

	private Integer resourceid;
	private String resourceIdentifier;
	private Location location;
	private LogDate logDate;
	private Method method;
	private ResourceDate resourceDate;
	private String registeredObjectType;
	private String landingPage;
	private Boolean isPublic;
	private String resourceTitle;
	private String purpose;
	private String campaign;
	private String comments;
	private Date modified;
	private Set<Contributors> contributorses = new HashSet<Contributors>(0);
	private Set<RelatedResources> relatedResourceses = new HashSet<RelatedResources>(
			0);
	private Set<AlternateIdentifiers> alternateIdentifierses = new HashSet<AlternateIdentifiers>(
			0);
	private Set<Classifications> classificationses = new HashSet<Classifications>(
			0);
	private Set<ResourceTypes> resourceTypeses = new HashSet<ResourceTypes>(0);
	private Set<SampledFeatures> sampledFeatureses = new HashSet<SampledFeatures>(
			0);
	private Set<CurationDetails> curationDetailses = new HashSet<CurationDetails>(
			0);
	private Set<MaterialTypes> materialTypeses = new HashSet<MaterialTypes>(0);
	
	private Registrant registrant;

	public Resources() {
	}



	public Resources(Location location,
			LogDate logDate, Method method, ResourceDate resourceDate,
			String registeredObjectType, String landingPage, Boolean isPublic,
			String resourceTitle, String purpose, String campaign,
			String comments,Registrant registrant, Set<Contributors> contributorses,
			Set<RelatedResources> relatedResourceses,
			Set<AlternateIdentifiers> alternateIdentifierses,
			Set<Classifications> classificationses,
			Set<ResourceTypes> resourceTypeses,
			Set<SampledFeatures> sampledFeatureses,		
			Set<CurationDetails> curationDetailses,
			Set<MaterialTypes> materialTypeses) {
		
		this.location = location;
		this.logDate = logDate;
		this.method = method;
		this.resourceDate = resourceDate;
		this.registeredObjectType = registeredObjectType;
		this.landingPage = landingPage;
		this.isPublic = isPublic;
		this.resourceTitle = resourceTitle;
		this.purpose = purpose;
		this.campaign = campaign;
		this.comments = comments;
		this.contributorses = contributorses;
		this.relatedResourceses = relatedResourceses;
		this.alternateIdentifierses = alternateIdentifierses;
		this.classificationses = classificationses;
		this.resourceTypeses = resourceTypeses;
		this.sampledFeatureses = sampledFeatureses;		
		this.curationDetailses = curationDetailses;
		this.materialTypeses = materialTypeses;
		this.registrant = registrant;
	}
	

	@SequenceGenerator(name="resources_resourceid_seq", schema="version30", sequenceName="resources_resourceid_seq",allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="resources_resourceid_seq")
	@Column(name = "idwebuser", updatable=false)
	public Integer getResourceid() {
		return resourceid;
	}

	public void setResourceid(Integer resourceid) {
		this.resourceid = resourceid;
	}

	@Id
	@Column(name = "resource_identifier", unique = true, nullable = false)	
	public String getResourceIdentifier() {
		return this.resourceIdentifier;
	}

	public void setResourceIdentifier(String resourceIdentifier) {
		this.resourceIdentifier = resourceIdentifier;
	}

	@ManyToOne(fetch = FetchType.EAGER,cascade={CascadeType.ALL})
	@JoinColumn(name = "location_id")
	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@ManyToOne(fetch = FetchType.EAGER,cascade={CascadeType.ALL})
	@JoinColumn(name = "log_date_id")
	public LogDate getLogDate() {
		return this.logDate;
	}

	public void setLogDate(LogDate logDate) {
		this.logDate = logDate;
	}

	@ManyToOne(fetch = FetchType.EAGER,cascade={CascadeType.ALL})
	@JoinColumn(name = "method_id")
	public Method getMethod() {
		return this.method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	@ManyToOne(fetch = FetchType.EAGER,cascade={CascadeType.ALL})
	@JoinColumn(name = "date")
	public ResourceDate getResourceDate() {
		return this.resourceDate;
	}

	public void setResourceDate(ResourceDate resourceDate) {
		this.resourceDate = resourceDate;
	}

	@Column(name = "registered_object_type")
	public String getRegisteredObjectType() {
		return this.registeredObjectType;
	}

	public void setRegisteredObjectType(String registeredObjectType) {
		this.registeredObjectType = registeredObjectType;
	}

	@Column(name = "landing_page")
	public String getLandingPage() {
		return this.landingPage;
	}

	public void setLandingPage(String landingPage) {
		this.landingPage = landingPage;
	}

	@Column(name = "is_public")
	public Boolean getIsPublic() {
		return this.isPublic;
	}

	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}

	@Column(name = "resource_title")
	public String getResourceTitle() {
		return this.resourceTitle;
	}

	public void setResourceTitle(String resourceTitle) {
		this.resourceTitle = resourceTitle;
	}

	@Column(name = "purpose")
	public String getPurpose() {
		return this.purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	@Column(name = "campaign")
	public String getCampaign() {
		return this.campaign;
	}

	public void setCampaign(String campaign) {
		this.campaign = campaign;
	}

	@Column(name = "comments")
	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "resources",cascade={CascadeType.ALL}, orphanRemoval=true)
	public Set<Contributors> getContributorses() {
		return this.contributorses;
	}

	public void setContributorses(Set<Contributors> contributorses) {
		this.contributorses = contributorses;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "resources",cascade={CascadeType.ALL}, orphanRemoval=true)
	public Set<RelatedResources> getRelatedResourceses() {
		return this.relatedResourceses;
	}

	public void setRelatedResourceses(Set<RelatedResources> relatedResourceses) {
		this.relatedResourceses = relatedResourceses;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "resources",cascade={CascadeType.ALL}, orphanRemoval=true)
	public Set<AlternateIdentifiers> getAlternateIdentifierses() {
		return this.alternateIdentifierses;
	}

	public void setAlternateIdentifierses(
			Set<AlternateIdentifiers> alternateIdentifierses) {
		this.alternateIdentifierses = alternateIdentifierses;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "resources",cascade={CascadeType.ALL}, orphanRemoval=true)
	public Set<Classifications> getClassificationses() {
		return this.classificationses;
	}

	public void setClassificationses(Set<Classifications> classificationses) {
		this.classificationses = classificationses;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "resources",cascade={CascadeType.ALL}, orphanRemoval=true)
	public Set<ResourceTypes> getResourceTypeses() {
		return this.resourceTypeses;
	}

	public void setResourceTypeses(Set<ResourceTypes> resourceTypeses) {
		this.resourceTypeses = resourceTypeses;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "resources",cascade={CascadeType.ALL}, orphanRemoval=true)
	public Set<SampledFeatures> getSampledFeatureses() {
		return this.sampledFeatureses;
	}

	public void setSampledFeatureses(Set<SampledFeatures> sampledFeatureses) {
		this.sampledFeatureses = sampledFeatureses;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "resources",cascade={CascadeType.ALL}, orphanRemoval=true)
	public Set<CurationDetails> getCurationDetailses() {
		return this.curationDetailses;
	}

	public void setCurationDetailses(Set<CurationDetails> curationDetailses) {
		this.curationDetailses = curationDetailses;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "resources",cascade={CascadeType.ALL}, orphanRemoval=true)
	public Set<MaterialTypes> getMaterialTypeses() {
		return this.materialTypeses;
	}

	public void setMaterialTypeses(Set<MaterialTypes> materialTypeses) {
		this.materialTypeses = materialTypeses;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified", length = 29)
	public Date getModified() {
		return this.modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn( name = "registrant", referencedColumnName="registrantid")
	@JsonIgnore
	public Registrant getRegistrant() {
		return this.registrant;
	}

	public void setRegistrant(Registrant registrant) {
		this.registrant = registrant;
	}


}
