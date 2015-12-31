package ru.byters.bcbarbershop.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.byters.bcbarbershop.R;
import ru.byters.bcbarbershop.controllers.Controller;
import ru.yandex.yandexmapkit.MapController;
import ru.yandex.yandexmapkit.MapView;
import ru.yandex.yandexmapkit.OverlayManager;
import ru.yandex.yandexmapkit.overlay.Overlay;
import ru.yandex.yandexmapkit.overlay.OverlayItem;

public class FragmentAbout extends FragmentBase {

    MapView mapView;

    public FragmentAbout() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_about,
                container, false);

        Controller controllerMain = ((Controller) rootView.getContext().getApplicationContext());
        controllerMain.barbershop.setUI(rootView);

        mapView = (MapView) rootView.findViewById(R.id.map);
        setMap(controllerMain);

        return rootView;
    }

    void setMap(Controller controllerMain) {
        OverlayItem item = controllerMain.barbershop.getMapPin(controllerMain);
        if (item != null) {
            MapController mMapController = mapView.getMapController();
            OverlayManager mOverlayManager = mMapController.getOverlayManager();
            Overlay overlay = new Overlay(mMapController);

            overlay.addOverlayItem(item);
            mOverlayManager.addOverlay(overlay);
        }
    }
}