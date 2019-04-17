/*
 * Copyright (C) 2019 The Android Open Source Project
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
 * limitations under the License
 */

package com.android.car.developeroptions.overlay;

import android.content.Context;

import com.android.car.developeroptions.connecteddevice.DevicePreferenceCallback;
import com.android.car.developeroptions.connecteddevice.dock.DockUpdater;

/** Feature provider for the dock updater. */
public interface DockUpdaterFeatureProvider {

    /** Returns the DockUpdater of the connected dock device */
    DockUpdater getConnectedDockUpdater(Context context,
            DevicePreferenceCallback devicePreferenceCallback);

    /** Returns the DockUpdater of the saved dock devices */
    DockUpdater getSavedDockUpdater(Context context,
            DevicePreferenceCallback devicePreferenceCallback);

}