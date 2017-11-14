/*
 * Copyright 2017 trivago N.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.trivago.rta.json.pojo;

import com.google.gson.annotations.SerializedName;

import java.time.Duration;

public class Result {
    private static final int MICROSECONDS_FACTOR = 1000000;

    private long duration;
    private String status;

    @SerializedName("error_message")
    private String errorMessage;

    private transient String durationString;

    public long getDuration() {
        return duration;
    }

    public void setDuration(final long duration) {
        this.duration = duration;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public long getDurationInMilliseconds() {
        return Duration.ofMillis(duration / MICROSECONDS_FACTOR).toMillis();
    }

    public String getDurationString() {
        Duration durationMilliseconds = Duration.ofMillis(duration / MICROSECONDS_FACTOR);
        long minutes = durationMilliseconds.toMinutes();
        long seconds = durationMilliseconds.minusMinutes(minutes).getSeconds();
        long milliseconds = durationMilliseconds.minusMinutes(minutes).minusSeconds(seconds).toMillis();
        return String.format("%dm %02ds %03dms", minutes, seconds, milliseconds);
    }

    @Override
    public String toString() {
        return "Result{" +
                "duration=" + duration +
                ", status='" + status + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", durationString='" + durationString + '\'' +
                '}';
    }
}