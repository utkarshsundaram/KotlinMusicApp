package utkarshsundaram.kotlinproject.model

class CurrentWeatherModel
{
    public var temp_c: Float = 0.0f
        get() = 0.toFloat()
    private set(field: Float) {
        field =temp_c
    }
    public var avgtemp_c: Float = 0.0f
        get() = 0.toFloat()
        set(field) {
            avgtemp_c =field;
        }

}