package com.centro.util.algo;

import com.centro.util.CartesianCoordinate;
import com.centro.util.CoordinatesConverter;
import com.centro.util.GeoCoordinate;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("equiDistantCalculator")
public class EquiDistantCalculator implements MeetingPointCalculator {

	public GeoCoordinate getMidPoint(List<GeoCoordinate> locations){
            GeoCoordinate midPoint = null ;
            CoordinatesConverter converter = new CoordinatesConverter();
            double sumX=0, sumY=0, sumZ=0;
            for (GeoCoordinate location : locations) {
                CartesianCoordinate cartesianLocation = converter.fromGeoToCartesian(location);
                sumX += cartesianLocation.getX();
                sumY += cartesianLocation.getY();
                sumZ += cartesianLocation.getZ();      
            }
            double midPointX = sumX/locations.size();
            double midPointY = sumY/locations.size();
            double midPointZ = sumZ/locations.size();
            
            midPoint = converter.fromCartesianToGeo(new CartesianCoordinate(midPointX, midPointY, midPointZ));
		
            return midPoint;		
	}
        
        public GeoCoordinate getMeetingPoint(List<GeoCoordinate> locations) {
            return getMidPoint(locations);
        }

}
