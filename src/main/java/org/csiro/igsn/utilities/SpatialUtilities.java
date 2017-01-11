package org.csiro.igsn.utilities;

import javax.measure.converter.ConversionException;

import org.geotools.geometry.jts.JTS;
import org.geotools.gml.producer.GeometryTransformer;
import org.geotools.referencing.CRS;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.PrecisionModel;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

public class SpatialUtilities {
	
	public static Geometry wktToGeometry(String wkt,String sridXML) throws ParseException {
		
		int srid = Integer.parseInt(sridXML.replace("https://epsg.io/", ""));
				
				
        WKTReader fromText = new WKTReader(new GeometryFactory(new PrecisionModel(),srid));
        Geometry geom = null;
        try {
            geom = fromText.read(wkt);
            if( srid != 4326){
            	CoordinateReferenceSystem sourceCRS = CRS.decode("EPSG:" + srid);
        		CoordinateReferenceSystem targetCRS = CRS.decode("EPSG:4326");        		
        		MathTransform transform = CRS.findMathTransform(sourceCRS, targetCRS);
        		geom = JTS.transform( geom, transform);
        		geom.setSRID(4326);
        		
        		//VT: For some reason this transformation swapped X and Y around for EPGS:4326 vs the others
//        		Coordinate[] original = geom.getCoordinates();
//                for(int i =0; i<original.length; i++){
//                    Double swapValue = original[i].x;
//                    original[i].x = original[i].y;
//                    original[i].y = swapValue;
//                }
            }
            
        } catch (Exception e) {
            throw new ParseException("Unable to parse and convert WKT");
        }
        return geom;
    }
	

}
