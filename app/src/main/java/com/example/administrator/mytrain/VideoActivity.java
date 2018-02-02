package com.example.administrator.mytrain;

import android.hardware.Camera;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

public class VideoActivity extends BaseActivity implements SurfaceHolder.Callback {

    private static final String TAG = "MainActivity";
    private SurfaceView mSurfaceview;
    private Button mBtnStartStop;
    private Button mBtnPlay;
    private boolean mStartedFlg = false;//是否正在录像
    private boolean mIsPlay = false;//是否正在播放录像
    private MediaRecorder mRecorder;
    private MediaRecorder mRecorderVice;
    private SurfaceHolder mSurfaceHolder;
    private ImageView mImageView;
    private Camera camera;
    private MediaPlayer mediaPlayer;
    private String path;
    private TextView textView;
    private int text = 0;
    private MediaPlayer mp = new MediaPlayer();

    private android.os.Handler handler = new android.os.Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            text++;
            textView.setText(text + "");
            handler.postDelayed(this, 1000);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView(R.layout.activity_video);
        setTitle("音频视频");

        mSurfaceview = (SurfaceView) findViewById(R.id.surfaceview);
        mImageView = (ImageView) findViewById(R.id.imageview);
        mBtnStartStop = (Button) findViewById(R.id.btnStartStop);
        mBtnPlay = (Button) findViewById(R.id.btnPlayVideo);
        textView = (TextView) findViewById(R.id.text);
        findViewById(R.id.btnPlayVice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playVoice();
            }
        });
        mBtnStartStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.equals("Start", mBtnStartStop.getText().toString().trim())) {
                    textVoice();
                } else {
                    stopVoice();
                }
                textVideo();
            }
        });

        mBtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIsPlay = true;
                mImageView.setVisibility(View.GONE);
                if (mediaPlayer == null) {
                    mediaPlayer = new MediaPlayer();
                }
                mediaPlayer.reset();
                Uri uri = Uri.parse(path);
                mediaPlayer = MediaPlayer.create(VideoActivity.this, uri);
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setDisplay(mSurfaceHolder);
                try {
                    mediaPlayer.prepare();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mediaPlayer.start();
            }
        });

        SurfaceHolder holder = mSurfaceview.getHolder();
        holder.addCallback(this);
        // setType必须设置，要不出错.
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

    }

    private void playVoice() {
        if (!mp.isPlaying()){
            File file = new File(getSDPath() + "/sound.mp3");
            try {
                mp.setDataSource(file.getPath());//设置播放音频文件的路径
                mp.prepare();//mp就绪
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!mp.isPlaying()) {
                mp.start();//播放音频
            }
        }
    }

    public void textVideo() {
        if (mIsPlay) {
            if (mediaPlayer != null) {
                mIsPlay = false;
                mediaPlayer.stop();
                mediaPlayer.reset();
                mediaPlayer.release();
                mediaPlayer = null;
            }
        }
        if (!mStartedFlg) {
            handler.postDelayed(runnable, 1000);
            mImageView.setVisibility(View.GONE);
            if (mRecorder == null) {
                mRecorder = new MediaRecorder();
            }

            camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);
            if (camera != null) {
                camera.setDisplayOrientation(90);
                camera.unlock();
                mRecorder.setCamera(camera);
            }

            try {
                // 这两项需要放在setOutputFormat之前
//                mRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
                mRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);

                // Set output file format
                mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);

                // 这两项需要放在setOutputFormat之后
//                mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                mRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);

                mRecorder.setVideoSize(640, 480);
                mRecorder.setVideoFrameRate(30);
                mRecorder.setVideoEncodingBitRate(3 * 1024 * 1024);
                mRecorder.setOrientationHint(90);
                //设置记录会话的最大持续时间（毫秒）
                mRecorder.setMaxDuration(30 * 1000);
                mRecorder.setPreviewDisplay(mSurfaceHolder.getSurface());

                path = getSDPath();
                if (path != null) {
                    File dir = new File(path + "/recordtest");
                    if (!dir.exists()) {
                        dir.mkdir();
                    }
                    path = dir + "/" + getDate() + ".mp4";
                    mRecorder.setOutputFile(path);
                    mRecorder.prepare();
                    mRecorder.start();
                    mStartedFlg = true;
                    mBtnStartStop.setText("Stop");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            //stop
            if (mStartedFlg) {
                try {
                    handler.removeCallbacks(runnable);
                    mRecorder.stop();
                    mRecorder.reset();
                    mRecorder.release();
                    mRecorder = null;
                    mBtnStartStop.setText("Start");
                    if (camera != null) {
                        camera.release();
                        camera = null;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            mStartedFlg = false;
        }
    }

    File soundFile;

    public void textVoice() {

        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(VideoActivity.this, "SD卡不存在，请插入SD卡", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            //创建音频文件
            soundFile = new File(getSDPath() + "/sound.mp3");
            //实例化对象
            mRecorderVice = new MediaRecorder();
            //设置声音来源（麦克风）
            mRecorderVice.setAudioSource(MediaRecorder.AudioSource.MIC);
            //设置音频输出格式
            mRecorderVice.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            //设置编码模式
            mRecorderVice.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mRecorderVice.setOutputFile(soundFile.getAbsolutePath());
            mRecorderVice.prepare();
            //录音开始
            mRecorderVice.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopVoice() {
        if (soundFile != null && soundFile.exists()&&mRecorderVice!=null) {
            //停止录音
            mRecorderVice.stop();
            mRecorderVice.release();
            mRecorderVice = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!mStartedFlg) {
            mImageView.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 获取系统时间
     *
     * @return
     */
    public static String getDate() {
        Calendar ca = Calendar.getInstance();
        int year = ca.get(Calendar.YEAR);           // 获取年份
        int month = ca.get(Calendar.MONTH);         // 获取月份
        int day = ca.get(Calendar.DATE);            // 获取日
        int minute = ca.get(Calendar.MINUTE);       // 分
        int hour = ca.get(Calendar.HOUR);           // 小时
        int second = ca.get(Calendar.SECOND);       // 秒

        String date = "" + year + (month + 1) + day + hour + minute + second;
        Log.d(TAG, "date:" + date);

        return date;
    }

    /**
     * 获取SD path
     *
     * @return
     */
    public String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();// 获取跟目录
            return sdDir.toString();
        }

        return null;
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        mSurfaceHolder = surfaceHolder;
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        // 将holder，这个holder为开始在onCreate里面取得的holder，将它赋给mSurfaceHolder
        mSurfaceHolder = surfaceHolder;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        mSurfaceview = null;
        mSurfaceHolder = null;
        handler.removeCallbacks(runnable);
        if (mRecorder != null) {
            mRecorder.release();
            mRecorder = null;
            Log.d(TAG, "surfaceDestroyed release mRecorder");
        }
        if (camera != null) {
            camera.release();
            camera = null;
        }
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }


}
