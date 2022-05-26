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

package com.husseinrasti.core.usecase


import kotlinx.coroutines.flow.Flow


/**
 * Created by Hussein Rasti on 2/22/22.
 */

interface BaseUseCase<in Params, out Type> {
    suspend operator fun invoke(params: Params): Type
}

interface FlowUseCase<in Params, Type> {
    suspend operator fun invoke(params: Params): Flow<Type>
}

interface CompletableUseCase<in Params> {
    suspend operator fun invoke(params: Params)
}
