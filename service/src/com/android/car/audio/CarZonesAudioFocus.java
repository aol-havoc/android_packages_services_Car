/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.car.audio;

import android.annotation.NonNull;
import android.content.pm.PackageManager;
import android.media.AudioAttributes;
import android.media.AudioFocusInfo;
import android.media.AudioManager;
import android.media.audiopolicy.AudioPolicy;
import android.util.Log;

import com.android.car.CarLog;
import com.android.internal.util.Preconditions;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Implements {@link AudioPolicy.AudioPolicyFocusListener}
 *
 * @note Manages audio focus on a per zone basis.
 */
class CarZonesAudioFocus extends AudioPolicy.AudioPolicyFocusListener {

    private CarAudioService mCarAudioService; // Dynamically assigned just after construction
    private AudioPolicy mAudioPolicy; // Dynamically assigned just after construction

    private final Map<Integer, CarAudioFocus> mFocusZones = new HashMap<>();

    CarZonesAudioFocus(AudioManager audioManager,
            PackageManager packageManager,
            @NonNull CarAudioZone[] carAudioZones) {
        //Create the zones here, the policy will be set setOwningPolicy,
        // which is called right after this constructor.

        Preconditions.checkNotNull(carAudioZones);
        Preconditions.checkArgument(carAudioZones.length != 0,
                "There must be a minimum of one audio zone");

        //Create focus for all the zones
        for (CarAudioZone audioZone : carAudioZones) {
            Log.d(CarLog.TAG_AUDIO,
                    "CarZonesAudioFocus adding new zone " + audioZone.getId());
            CarAudioFocus zoneFocusListener = new CarAudioFocus(audioManager, packageManager);
            mFocusZones.put(audioZone.getId(), zoneFocusListener);
        }
    }


    /**
     * Sets the owning policy of the audio focus
     *
     * @param audioService owning car audio service
     * @param parentPolicy owning parent car audio policy
     * @Note This has to happen after the construction to avoid a chicken and egg
     * problem when setting up the AudioPolicy which must depend on this object.
     */
    void setOwningPolicy(CarAudioService audioService, AudioPolicy parentPolicy) {
        mCarAudioService = audioService;
        mAudioPolicy = parentPolicy;

        for (int zoneId : mFocusZones.keySet()) {
            mFocusZones.get(zoneId).setOwningPolicy(mCarAudioService, mAudioPolicy);
        }
    }

    @Override
    public void onAudioFocusRequest(AudioFocusInfo afi, int requestResult) {
        CarAudioFocus focus = getFocusForUid(afi.getClientUid());
        focus.onAudioFocusRequest(afi, requestResult);
    }

    /**
     * @see AudioManager#abandonAudioFocus(AudioManager.OnAudioFocusChangeListener, AudioAttributes)
     * Note that we'll get this call for a focus holder that dies while in the focus stack, so
     * we don't need to watch for death notifications directly.
     */
    @Override
    public void onAudioFocusAbandon(AudioFocusInfo afi) {
        CarAudioFocus focus = getFocusForUid(afi.getClientUid());
        focus.onAudioFocusAbandon(afi);
    }

    private CarAudioFocus getFocusForUid(int uid) {
        //getZoneIdForUid defaults to returning default zoneId
        //if uid has not been mapped, thus the value returned will be
        //default zone focus
        int zoneId = mCarAudioService.getZoneIdForUid(uid);

        CarAudioFocus focus =  mFocusZones.get(zoneId);
        return focus;
    }

    /**
     * dumps the current state of the CarZoneAudioFocus
     *
     * @param indent indent to append to each new line
     * @param writer stream to write current state
     */
    synchronized void dump(String indent, PrintWriter writer) {
        writer.printf("%s*CarZonesAudioFocus*\n", indent);

        writer.printf("%s\tCar Zones Audio Focus Listeners:\n", indent);
        Integer[] keys = mFocusZones.keySet().stream().sorted().toArray(Integer[]::new);
        for (Integer zoneId : keys) {
            writer.printf("%s\tZone Id: %s", indent, zoneId.toString());
            mFocusZones.get(zoneId).dump(indent + "\t", writer);
        }
    }
}
