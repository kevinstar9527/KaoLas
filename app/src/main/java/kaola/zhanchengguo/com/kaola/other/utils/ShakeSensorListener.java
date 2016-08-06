package kaola.zhanchengguo.com.kaola.other.utils;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.SystemClock;
import android.util.Log;

/**
 * Created by Administrator on 2016/6/18.
 */
public class ShakeSensorListener implements SensorEventListener {

    /**
     * 传感器对象
     */
    private Sensor sensor;

    /**
     * 传感器管理者
     */
    private SensorManager manager;

    /**
     * 速度阀值
     */
    private final int speedTag = 180;

    //延时刷新时间
    private final int delay = 200;

    /**
     * 记录上一次监听的时间
     */
    private long lastTime;

    private IShakeListener shakeListener;

    private float lastX,lastY,lastZ;

    public ShakeSensorListener(Context context)
    {
        //获取传感器服务
        manager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);

        //获取加速度传感器对象
        sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        //注册传感器
        manager.registerListener(this,sensor,Sensor.TYPE_ACCELEROMETER);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        float[] value = event.values;
        float x = value[0];
        float y = value[1];
        float z = value[2];

        //long currTime = SystemClock.currentThreadTimeMillis(); 这个方法有延迟

        long currTime = System.currentTimeMillis();

        if( currTime - lastTime < delay)
        {
           return;
        }

        lastTime = currTime;
        //LogUtil.w("onSensorChanged x = " + x + "y = " + y + "z = " + z);

        //计算距离
        float distanceX = x - lastX;
        float distanceY = y - lastX;
        float distanceZ = z - lastX;

        double  distance = Math.sqrt( distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ);

        double  speed = distance / delay * 1000;

        LogUtil.e("distance = " + distance +" speed = " + speed);

        if(speed > speedTag)
        {
            //已达到阀值，可以进行你想要的操作
            LogUtil.e("speed = " + speed);

            if (shakeListener != null)
            {
                shakeListener.OnShake();
            }

        }

        //重新记录下上一次的空间坐标
        lastX = x ;
        lastY = y ;
        lastZ = z ;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    public void unregister()
    {
        //注册销毁
        manager.unregisterListener(this);
    }

    public void setIShakeListener(IShakeListener listener)
    {
        shakeListener = listener;
    }

    public interface IShakeListener
    {
        /**
         * 摇动时要干嘛
         */
        void OnShake();
    }
}
