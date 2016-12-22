

app.service('selectListService', ['$q','$http',function($q,$http) {

	this.getResourceType = function(){
    	  return [
				"http://pid.geoscience.gov.au/def/voc/igsn-codelists/automated",
				"http://pid.geoscience.gov.au/def/voc/igsn-codelists/core",
				"http://pid.geoscience.gov.au/def/voc/igsn-codelists/coreHalfRound",
				"http://pid.geoscience.gov.au/def/voc/igsn-codelists/corePiece",
				"http://pid.geoscience.gov.au/def/voc/igsn-codelists/coreQuarterRound",
				"http://pid.geoscience.gov.au/def/voc/igsn-codelists/coreSection",
				"http://pid.geoscience.gov.au/def/voc/igsn-codelists/coreSectionHalf",
				"http://pid.geoscience.gov.au/def/voc/igsn-codelists/coreSub-Piece",
				"http://pid.geoscience.gov.au/def/voc/igsn-codelists/coreWholeRound",
				"http://pid.geoscience.gov.au/def/voc/igsn-codelists/cuttings",
				"http://pid.geoscience.gov.au/def/voc/igsn-codelists/dredge",
				"http://pid.geoscience.gov.au/def/voc/igsn-codelists/foliageDigestion",
				"http://pid.geoscience.gov.au/def/voc/igsn-codelists/foliageLeaching",
				"http://pid.geoscience.gov.au/def/voc/igsn-codelists/forestFloorDigestion",
				"http://pid.geoscience.gov.au/def/voc/igsn-codelists/grab",
				"http://pid.geoscience.gov.au/def/voc/igsn-codelists/individualSample",
				"http://pid.geoscience.gov.au/def/voc/igsn-codelists/litterFallDigestion",
				"http://pid.geoscience.gov.au/def/voc/igsn-codelists/orientedCore",
				"http://pid.geoscience.gov.au/def/voc/igsn-codelists/other",
				"http://pid.geoscience.gov.au/def/voc/igsn-codelists/petriDishDryDeposition",
				"http://pid.geoscience.gov.au/def/voc/igsn-codelists/precipitationBulk",
				"http://pid.geoscience.gov.au/def/voc/igsn-codelists/rockPowder",
				"http://pid.geoscience.gov.au/def/voc/igsn-codelists/standardReferenceSpecimen",
				"http://pid.geoscience.gov.au/def/voc/igsn-codelists/terrestrialSection",
				"http://pid.geoscience.gov.au/def/voc/igsn-codelists/thinSection",
				"http://www.opengis.net/def/nil/OGC/0/inapplicable",
				"http://www.opengis.net/def/nil/OGC/0/missing",
				"http://www.opengis.net/def/nil/OGC/0/template",
				"http://www.opengis.net/def/nil/OGC/0/unknown",
				"http://www.opengis.net/def/nil/OGC/0/withheld"
    	          ];
      };
      
      this.getMaterialType = function(){
    	  return [
				"http://pid.geoscience.gov.au/def/voc/igsn-codelists/air",
				"http://pid.geoscience.gov.au/def/voc/igsn-codelists/gas",
				"http://pid.geoscience.gov.au/def/voc/igsn-codelists/habitat",
				"http://pid.geoscience.gov.au/def/voc/igsn-codelists/ice",
				"http://pid.geoscience.gov.au/def/voc/igsn-codelists/liquidAqueous",
				"http://pid.geoscience.gov.au/def/voc/igsn-codelists/liquidOrganic",
				"http://pid.geoscience.gov.au/def/voc/igsn-codelists/mineral",
				"http://pid.geoscience.gov.au/def/voc/igsn-codelists/notApplicable",
				"http://pid.geoscience.gov.au/def/voc/igsn-codelists/organism",
				"http://pid.geoscience.gov.au/def/voc/igsn-codelists/other",
				"http://pid.geoscience.gov.au/def/voc/igsn-codelists/particulate",
				"http://pid.geoscience.gov.au/def/voc/igsn-codelists/regolith",
				"http://pid.geoscience.gov.au/def/voc/igsn-codelists/rock",
				"http://pid.geoscience.gov.au/def/voc/igsn-codelists/sediment",
				"http://pid.geoscience.gov.au/def/voc/igsn-codelists/snow",
				"http://pid.geoscience.gov.au/def/voc/igsn-codelists/soil",
				"http://pid.geoscience.gov.au/def/voc/igsn-codelists/tissue",
				"http://www.opengis.net/def/nil/OGC/0/inapplicable",
				"http://www.opengis.net/def/nil/OGC/0/missing",
				"http://www.opengis.net/def/nil/OGC/0/template",
				"http://www.opengis.net/def/nil/OGC/0/unknown",
				"http://www.opengis.net/def/nil/OGC/0/withheld"
    	          ];
      };
      
      this.getEpsg = function(){
    	  return [
				"https://epsg.io/3112",
				"https://epsg.io/4283",
				"https://epsg.io/4326",
				"https://epsg.io/4939",
				"https://epsg.io/5711",
				"https://epsg.io/5712",
				"https://epsg.io/8311"
    	          ];
      };
      
      
      
}]);