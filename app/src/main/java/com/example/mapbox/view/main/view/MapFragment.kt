package com.example.mapbox.view.main.view

import android.R
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mapbox.geojson.Point
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.maps.MapView
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager


class MapFragment : BottomSheetDialogFragment() {
    private lateinit var location: TextView
    private lateinit var mapView: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, com.example.mapbox.R.style.BottomSheetDialogStyle)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        // Inflate the layout for this fragment
        val view: View = inflater.inflate(com.example.mapbox.R.layout.fragment_map, container, false)

        location = view.findViewById<View>(com.example.mapbox.R.id.tvFragment) as TextView

        val bundle = arguments
        val loc = bundle?.getString("Location")
        val lat = bundle?.getDouble("Lat")
        val long = bundle?.getDouble("Long")

        location.text = loc

        Log.d("mapFragment", "Location: " + loc)
        Log.d("mapFragment", "Location: " + lat)
        Log.d("mapFragment", "Location: " + long)


        var bitmap = convertDrawableToBitmap(AppCompatResources.getDrawable(requireContext(),
            com.example.mapbox.R.drawable.ic_baseline_location_on_24))

        mapView = view.findViewById(com.example.mapbox.R.id.mapView)
        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS) {
            val annotationApi = mapView.annotations
            val pointAnnotationManager = annotationApi?.createPointAnnotationManager()
            val pointAnnotationOptions: PointAnnotationOptions? = bitmap?.let {
                PointAnnotationOptions()
                    .withPoint(Point.fromLngLat(long!!, lat!!))
                    .withIconImage(it)
            }
            if (pointAnnotationOptions != null) {
                pointAnnotationManager?.create(pointAnnotationOptions)
            }
        }

        return view
    }

    private fun convertDrawableToBitmap(sourceDrawable: Drawable?): Bitmap? {
        if (sourceDrawable == null) {
            return null
        }

        return if (sourceDrawable is BitmapDrawable) {
            sourceDrawable.bitmap
        } else {
            val constantState = sourceDrawable.constantState ?: return null

            val drawable = constantState.newDrawable().mutate()
            val bitmap: Bitmap = Bitmap.createBitmap(
                drawable.intrinsicWidth, drawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            bitmap
        }
    }

    override fun onStart() {
        super.onStart()
        mapView?.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView?.onStop()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView?.onDestroy()
    }


}








