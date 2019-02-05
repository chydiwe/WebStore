import React from 'react';
import {Map, Placemark, YMaps} from 'react-yandex-maps';

const mapState = {
    center: [55.754699, 37.621472],
    zoom: 16,
    controls: ['zoomControl', 'fullscreenControl']
};

export const MyPlacemark = () => (
    <YMaps>
        <Map state={mapState}
             width={600}
             height={400}
             modules={['control.ZoomControl', 'control.FullscreenControl']}
        >
            <Placemark
                geometry={ mapState.center }
                modules={['geoObject.addon.balloon']}
                properties={{
                    balloonContentBody:
                        'Москва, Красная площадь, д.3, 2 этаж, Magic Stationary',
                }}
            />

        </Map>
    </YMaps>
);
