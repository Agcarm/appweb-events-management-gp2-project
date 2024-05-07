function initMap() {
  var map = new google.maps.Map(document.getElementById('map'), {
    center: {lat: 3.884100099999999, lng: 11.390738},
    zoom: 13
  });
  var input = document.getElementById('searchInput');
  map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

  var autocomplete = new google.maps.places.Autocomplete(input);
  autocomplete.bindTo('bounds', map);

  var infowindow = new google.maps.InfoWindow();
  var marker = new google.maps.Marker({
    map: map,
    anchorPoint: new google.maps.Point(0, -29)
  });

  // Function to handle marker placement and info window display on click
  function handleMapClick(event) {
    marker.setPosition(event.latLng);
    marker.setVisible(true);

    var address = '';
    var geocoder = new google.maps.Geocoder();
    geocoder.geocode({ location: event.latLng }, function(results, status) {
      if (status === google.maps.GeocoderStatus.OK) {
        if (results[0]) {
          address = results[0].formatted_address;
          infowindow.setContent('<div><strong>Location:</strong><br>' + address + '</div>');
          infowindow.open(map, marker);

          // Extract location details from the geocoded result
          for (var i = 0; i < results[0].address_components.length; i++) {
            if (results[0].address_components[i].types[0] === 'postal_code') {
              document.getElementById('postal_code').innerHTML = results[0].address_components[i].long_name;
            }
            if (results[0].address_components[i].types[0] === 'country') {
              document.getElementById('venueCounrty').value = results[0].address_components[i].long_name;
            }
            if (results[0].address_components[i].types[0] === 'locality') {
              document.getElementById('venueCity').value = results[0].address_components[i].long_name;
            }
          }
          document.getElementById('venueFullAddress').value = address;
          document.getElementById('venuelatitude').value = event.latLng.lat();
          document.getElementById('venuelongitude').value = event.latLng.lng();
        } else {
          window.alert('No results found for this location.');
        }
      } else {
        window.alert('Geocoder failed due to: ' + status);
      }
    });
  }

  // Add click listener to the map
  google.maps.event.addListener(map, 'click', handleMapClick);

  // Handle search bar functionality
  autocomplete.addListener('place_changed', function() {
    infowindow.close();
    marker.setVisible(false);
    var place = autocomplete.getPlace();
    if (!place.geometry) {
      window.alert("Autocomplete's returned place contains no geometry");
      return;
    }

    // If the place has a geometry, then present it on a map.
    if (place.geometry.viewport) {
      map.fitBounds(place.geometry.viewport);
    } else {
      map.setCenter(place.geometry.location);
      map.setZoom(17);
    }
    marker.setIcon(({
      url: place.icon,
      size: new google.maps.Size(71, 71),
      origin: new google.maps.Point(0, 0),
      anchor: new google.maps.Point(17, 34),
      scaledSize: new google.maps.Size(35, 35)
    }));
    marker.setPosition(place.geometry.location);
    marker.setVisible(true);

    var address = '';
    if (place.address_components) {
      address = [
        (place.address_components[0] && place.address_components[0].short_name || ''),
        (place.address_components[1] && place.address_components[1].short_name || ''),
        (place.address_components[2] && place.address_components[2].short_name || '')
      ].join(' ');
    }

    infowindow.setContent('<div><strong>' + place.name + '</strong><br>' + address);
    infowindow.open(map, marker);

    // Location details
    for (var i = 0; i < place.address_components.length; i++) {
      if(place.address_components[i].types[0] == 'postal_code'){
        document.getElementById('postal_code').innerHTML = place.address_components[i].long_name;
      }
      if(place.address_components[i].types[0] == 'country'){
        document.getElementById('venueCounrty').value = place.address_components[i].long_name;
      }
      if(place.address_components[i].types[0] == 'locality'){
        document.getElementById('venueCity').value = place.address_components[i].long_name;
      }
    }
    document.getElementById('venueFullAddress').value = place.formatted_address;
    document.getElementById('venuelatitude').value = place.geometry.location.lat();
    document.getElementById('venuelongitude').value = place.geometry.location.lng();
  });
}
