package com.example.weatherapp

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.location.Geocoder
import android.location.LocationManager
import android.provider.Settings
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weatherapp.ui.navigation.Screen
import com.example.weatherapp.ui.screen.LocationViewModel
import com.example.weatherapp.ui.screen.detail.DetailScreen
import com.example.weatherapp.ui.screen.home.HomeScreen
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import java.util.*

@SuppressLint("PermissionLaunchedDuringComposition")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun WeatherApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    locationViewModel: LocationViewModel = hiltViewModel(),
    context: Context
) {
    var currentCity by remember { mutableStateOf("") }
    val currentLocation = locationViewModel.currentLocation
    val geoCoder = Geocoder(LocalContext.current, Locale.getDefault())
    if (currentLocation != null) {
        val address =
            geoCoder.getFromLocation(currentLocation.latitude, currentLocation.longitude, 1)
        if (address != null) {
            currentCity = address[0].subAdminArea
        }
    }

    val locationPermissions = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
        )
    ) { permissions ->
        when {
            permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: false -> {
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivityForResult(context as Activity, intent, 123, null)
            }
            permissions[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false -> {
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivityForResult(context as Activity, intent, 123, null)
            }
        }
    }
    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    val isGpsEnabled = locationManager
        .isProviderEnabled(LocationManager.NETWORK_PROVIDER) ||
            locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner){
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START
                && !isGpsEnabled
            ){
                locationPermissions.launchMultiplePermissionRequest()
            } else if ( event == Lifecycle.Event.ON_START
                && locationPermissions.allPermissionsGranted
            ) {
                locationViewModel.getCurrentLocation()
            } else if ( event == Lifecycle.Event.ON_STOP) {
                locationViewModel.getCurrentLocation()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            locationViewModel.getCurrentLocation()
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    currentCity = currentCity,
                    navigateToDetail = { cityName ->
                        navController.navigate(Screen.Detail.createRoute(cityName))
                    }
                )
            }
            composable(
                route = Screen.Detail.route,
                arguments = listOf(navArgument("cityName") { type = NavType.StringType })
            ) {
                val cityName = it.arguments?.getString("cityName") ?: ""
                DetailScreen(
                    cityName = cityName,
                    currentCity = currentCity,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}