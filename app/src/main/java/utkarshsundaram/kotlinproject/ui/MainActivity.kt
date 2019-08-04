package utkarshsundaram.kotlinproject.ui
import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_main.*
import utkarshsundaram.kotlinproject.R
import utkarshsundaram.kotlinproject.adapter.WeatherAdapter
import utkarshsundaram.kotlinproject.model.WeatherModel
import utkarshsundaram.kotlinproject.presenter.WeatherContract
import utkarshsundaram.kotlinproject.presenter.WeatherPresenter
import utkarshsundaram.kotlinproject.utils.CommonUtils
import utkarshsundaram.kotlinproject.utils.Constants.ALL_PERMISSIONS_RESULT
import utkarshsundaram.kotlinproject.utils.Constants.FASTEST_REQUEST
import utkarshsundaram.kotlinproject.utils.Constants.UPDATE_REQUEST
import utkarshsundaram.kotlinproject.utils.SharedPreferenceUtils

class MainActivity : AppCompatActivity(),GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener,
    LocationListener,WeatherContract.View {

    lateinit var  mGoogleApiClient:GoogleApiClient;
    lateinit var weatherPresenter: WeatherContract.Presenter
    private var permissionToGet= ArrayList<String>()
    private var permissionsRejected= ArrayList<String>()
    private var permissionToRequest=ArrayList<String>()
    var weatherAdapter: WeatherAdapter = WeatherAdapter(this)

    override fun onConnected(p0: Bundle?) {
        startLocationUpdates()
    }

    override fun onConnectionSuspended(p0: Int) {

    }

    override fun onConnectionFailed(p0: ConnectionResult) {

    }
    override fun onLocationChanged(location: Location?) {
       Toast.makeText(this,"Location is"+location?.latitude+location?.longitude,Toast.LENGTH_LONG).show()
        SharedPreferenceUtils.setLatitude(this,  location?.latitude.toString())
        SharedPreferenceUtils.setLongitude(this,location?.longitude.toString())
        weatherPresenter.getCurrentWeather();
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        permissionToGet.add(ACCESS_FINE_LOCATION)
        permissionToGet.add(ACCESS_COARSE_LOCATION)
        permissionToRequest =CommonUtils.findUnAskedPermission(permissionToGet,this)
        if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.M){
           if(permissionToRequest.size>0){
               requestPermissions(permissionToRequest.toTypedArray(), ALL_PERMISSIONS_RESULT)
           }else{
               buildGoogleApiClient()
           }
        }else{
            buildGoogleApiClient()
        }
        recycler_view.layoutManager=LinearLayoutManager(this)
        recycler_view.adapter=weatherAdapter
        setPresenter(WeatherPresenter(this,this))
    }

    private fun buildGoogleApiClient() {
        mGoogleApiClient=GoogleApiClient.Builder(this).
            addConnectionCallbacks(this).
            addOnConnectionFailedListener(this).
            addApi(LocationServices.API).build()

    }

    override fun onResume() {
        super.onResume()
        if (::mGoogleApiClient.isInitialized) {
            if (!mGoogleApiClient.isConnected()) {
                mGoogleApiClient.connect()
            } else {
                startLocationUpdates()
            }
        }else{
            buildGoogleApiClient()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            ALL_PERMISSIONS_RESULT -> {
                for (perms in permissionToGet) {
                    if (!CommonUtils.hasPermission(perms, this)) {
                        permissionsRejected.add(perms)
                    }
                }
                if (permissionsRejected.size > 0) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(permissionsRejected.get(0))) {
                            CommonUtils.showMessageOKCancel(this,getString(R.string.permission_mandatory),DialogInterface.OnClickListener { dialog, which ->
                                requestPermissions(permissionsRejected.toTypedArray(), ALL_PERMISSIONS_RESULT)
                            })
                        } else {
                            buildGoogleApiClient()
                        }
                    }
                }
            }
        }
    }
    private fun startLocationUpdates() {
     var locationManager:LocationManager= getSystemService(Context.LOCATION_SERVICE) as LocationManager;
        var locationRequest:LocationRequest= LocationRequest()
          locationRequest.setFastestInterval(FASTEST_REQUEST)
          locationRequest.setInterval(UPDATE_REQUEST)
          locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
        if (ActivityCompat.checkSelfPermission(
                this,
                ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        )
        {
            Toast.makeText(this, getString(R.string.permission_mandatory), Toast.LENGTH_LONG).show()
        }else{
            if(mGoogleApiClient.isConnected){
              var mFusedApiClient: FusedLocationProviderClient =LocationServices.getFusedLocationProviderClient(this)
                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, locationRequest, this)
            }else{
                mGoogleApiClient.connect()
            }
        }

        }

    override fun onWeatherReport(weatherModel: WeatherModel) {
        weatherModel.forecast?.let { weatherAdapter.setListItem(it) }
    }

    override fun setPresenter(presenter: WeatherContract.Presenter) {
        weatherPresenter=presenter;

    }

    override fun onResponseFailure(ex: Throwable) {

    }


}

