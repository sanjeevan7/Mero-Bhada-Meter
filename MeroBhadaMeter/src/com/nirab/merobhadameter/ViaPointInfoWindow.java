package com.nirab.merobhadameter;

import org.osmdroid.bonuspack.overlays.DefaultInfoWindow;
import org.osmdroid.bonuspack.overlays.ExtendedOverlayItem;
import org.osmdroid.views.MapView;
import android.view.View;
import android.widget.Button;

/**
 * A customized InfoWindow handling "itinerary" points (start, destination and via-points). 
 * We inherit from DefaultInfoWindow as it already provides most of what we want. 
 * And we just add support for a "remove" button. 
 * 
 * @author M.Kergall
 */
public class ViaPointInfoWindow extends DefaultInfoWindow {

	int mSelectedPoint;
	
	public ViaPointInfoWindow(int layoutResId, MapView mapView) {
		super(layoutResId, mapView);
		Button btnDelete = (Button)(mView.findViewById(R.id.bubble_delete));
		btnDelete.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				//Call the removePoint method on MapActivity. 
				//TODO: find a cleaner way to do that!
				MapActivity mapActivity = (MapActivity)view.getContext();
				mapActivity.removePoint(mSelectedPoint);
				close();
			}
		});
	}

	@Override public void onOpen(Object item) {
		ExtendedOverlayItem eItem = (ExtendedOverlayItem)item;
		mSelectedPoint = (Integer)eItem.getRelatedObject();
		super.onOpen(item);
	}

}
