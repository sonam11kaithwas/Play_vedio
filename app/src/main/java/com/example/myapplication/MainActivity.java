package com.example.myapplication;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.cache.CacheDataSourceFactory;
import com.google.android.exoplayer2.upstream.cache.NoOpCacheEvictor;
import com.google.android.exoplayer2.upstream.cache.SimpleCache;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private SimpleExoPlayer player;
    // MySimpleExoPlayerView2 simpleExoPlayerView;
    private boolean screenRotate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MySimpleExoPlayerView2 simpleExoPlayerView = findViewById(R.id.player_view);

//        player = ExoPlayerFactory.newSimpleInstance(this,
//                new DefaultTrackSelector(new DefaultBandwidthMeter.Builder().build()));

        player = ExoPlayerFactory.newSimpleInstance(this);

        SimpleCache downloadCache = new SimpleCache(new File(getCacheDir(), "exoCache"), new NoOpCacheEvictor());

        Button btn = findViewById(R.id.resolution_textView);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!screenRotate) {
                    simpleExoPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL);
                    btn.setText("Exit from full screen");

                } else {
                    simpleExoPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIT);
                    btn.setText("Full screen");
                }
                screenRotate = !screenRotate;
            }
        });

        /***firebase url**/
        String uri = "https://firebasestorage.googleapis.com/v0/b/vedioplay-af558.appspot.com/o/VID-20200521-WA0010.mp4?alt=media&token=e0a52cc7-5d88-4554-96c9-4605a401dd1c";

        /**using raw folder**/
        //String uri = RawResourceDataSource.buildRawResourceUri(R.raw.vedio_file).toString();

        DataSource.Factory dataSourceFactory = new CacheDataSourceFactory(downloadCache, new DefaultDataSourceFactory(this, "seyed"));

        MediaSource mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(uri));

        player.addListener(new Player.EventListener() {
            @Override
            public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {
                Log.d("exo", "timeLine Changed");
            }

            @Override
            public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

            }

            @Override
            public void onLoadingChanged(boolean isLoading) {
                Log.d("exo", "loding changed= " + isLoading);
            }

            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                Log.d("exo", "state changed");
            }

            @Override
            public void onRepeatModeChanged(int repeatMode) {

            }

            @Override
            public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {
                Log.e("exo", "exoplayer error", error);
            }

            @Override
            public void onPositionDiscontinuity(int reason) {

            }

            @Override
            public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

            }

            @Override
            public void onSeekProcessed() {
                Log.d("exo", "seek processed");
            }
        });
        player.prepare(mediaSource);

        simpleExoPlayerView.setPlayer(player);

        player.setPlayWhenReady(true);

    }


    @Override
    protected void onPause() {
        player.setPlayWhenReady(false);
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}


