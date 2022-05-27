/*
 * Copyright (C) 2022  The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import com.husseinrasti.build_core.BuildAndroidConfig

plugins {
    id("core-android-library")
}

android {
    buildTypes {
        release {
            buildConfigField("String", "APPLICATION_BASE_ID", "\"" + BuildAndroidConfig.APPLICATION_ID + "\"")
            buildConfigField("String", "SHARED_PREF_NAME", "\"" + BuildAndroidConfig.SHARED_PREF_NAME + "\"")
            buildConfigField("String", "DATABASE_NAME", "\"" + BuildAndroidConfig.DATABASE_NAME + "\"")
            buildConfigField("String", "VERSION_NAME", "\"" + BuildAndroidConfig.VERSION_NAME + "\"")
            buildConfigField("String", "BASE_URL", "\"" + BuildAndroidConfig.BASE_URL + "\"")
            buildConfigField("String", "API_KEY", "\"" + BuildAndroidConfig.API_KEY + "\"")
        }
        debug {
            buildConfigField("String", "APPLICATION_BASE_ID", "\"" + BuildAndroidConfig.APPLICATION_ID + "\"")
            buildConfigField("String", "SHARED_PREF_NAME", "\"" + BuildAndroidConfig.SHARED_PREF_NAME + "\"")
            buildConfigField("String", "DATABASE_NAME", "\"" + BuildAndroidConfig.DATABASE_NAME + "\"")
            buildConfigField("String", "VERSION_NAME", "\"" + BuildAndroidConfig.VERSION_NAME + "\"")
            buildConfigField("String", "BASE_URL", "\"" + BuildAndroidConfig.BASE_URL + "\"")
            buildConfigField("String", "API_KEY", "\"" + BuildAndroidConfig.API_KEY + "\"")
        }
    }
}