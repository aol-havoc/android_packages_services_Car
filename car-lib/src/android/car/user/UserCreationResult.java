/*
 * Copyright (C) 2020 The Android Open Source Project
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

package android.car.user;

import android.annotation.IntDef;
import android.annotation.Nullable;
import android.content.pm.UserInfo;
import android.os.Parcelable;

import com.android.internal.util.DataClass;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * User creation results.
 *
 * @hide
 */
@DataClass(
        genToString = true,
        genHiddenConstructor = true,
        genHiddenConstDefs = true)
public final class UserCreationResult implements Parcelable {

    /**
     * {@link Status} called when user creation is successful for both HAL and Android.
     *
     * @hide
     */
    public static final int STATUS_SUCCESSFUL = 1;

    /**
     * {@link Status} called when user creation failed on Android - HAL is not even called in this
     * case.
     *
     * @hide
     */
    public static final int STATUS_ANDROID_FAILURE = 2;

    /**
     * {@link Status} called when user was created on Android but HAL returned a failure - the
     * Android user is automatically removed.
     *
     * @hide
     */
    public static final int STATUS_HAL_FAILURE = 3;

    /**
     * {@link Status} called when user creation is failed for HAL for some internal error - the
     * Android user is not automatically removed.
     *
     * @hide
     */
    public static final int STATUS_HAL_INTERNAL_FAILURE = 4;

    /**
     * Gets the user switch result status.
     *
     * @return either {@link UserCreationResult#STATUS_SUCCESSFUL},
     *         {@link UserCreationResult#STATUS_ANDROID_FAILURE},
     *         {@link UserCreationResult#STATUS_HAL_FAILURE},
     *         or
     *         {@link UserCreationResult#STATUS_HAL_INTERNAL_FAILURE}
     */
    private final int mStatus;

    /**
     * Gets the created user.
     */
    @Nullable
    private final UserInfo mUser;

    /**
     * Gets the error message sent by HAL, if any.
     */
    @Nullable
    private final String mErrorMessage;

    /**
     * Checks if this result is successful.
     */
    public boolean isSuccess() {
        return mStatus == STATUS_SUCCESSFUL;
    }



    // Code below generated by codegen v1.0.15.
    //
    // DO NOT MODIFY!
    // CHECKSTYLE:OFF Generated code
    //
    // To regenerate run:
    // $ codegen $ANDROID_BUILD_TOP/packages/services/Car/car-lib/src/android/car/user/UserCreationResult.java
    //
    // To exclude the generated code from IntelliJ auto-formatting enable (one-time):
    //   Settings > Editor > Code Style > Formatter Control
    //@formatter:off


    /** @hide */
    @IntDef(prefix = "STATUS_", value = {
        STATUS_SUCCESSFUL,
        STATUS_ANDROID_FAILURE,
        STATUS_HAL_FAILURE,
        STATUS_HAL_INTERNAL_FAILURE
    })
    @Retention(RetentionPolicy.SOURCE)
    @DataClass.Generated.Member
    public @interface Status {}

    /** @hide */
    @DataClass.Generated.Member
    public static String statusToString(@Status int value) {
        switch (value) {
            case STATUS_SUCCESSFUL:
                    return "STATUS_SUCCESSFUL";
            case STATUS_ANDROID_FAILURE:
                    return "STATUS_ANDROID_FAILURE";
            case STATUS_HAL_FAILURE:
                    return "STATUS_HAL_FAILURE";
            case STATUS_HAL_INTERNAL_FAILURE:
                    return "STATUS_HAL_INTERNAL_FAILURE";
            default: return Integer.toHexString(value);
        }
    }

    /**
     * Creates a new UserCreationResult.
     *
     * @param status
     *   Gets the user switch result status.
     *
     *   @return either {@link UserCreationResult#STATUS_SUCCESSFUL},
     *           {@link UserCreationResult#STATUS_ANDROID_FAILURE},
     *           {@link UserCreationResult#STATUS_HAL_FAILURE},
     *           or
     *           {@link UserCreationResult#STATUS_HAL_INTERNAL_FAILURE}
     * @param user
     *   Gets the created user.
     * @param errorMessage
     *   Gets the error message sent by HAL, if any.
     * @hide
     */
    @DataClass.Generated.Member
    public UserCreationResult(
            int status,
            @Nullable UserInfo user,
            @Nullable String errorMessage) {
        this.mStatus = status;
        this.mUser = user;
        this.mErrorMessage = errorMessage;

        // onConstructed(); // You can define this method to get a callback
    }

    /**
     * Gets the user switch result status.
     *
     * @return either {@link UserCreationResult#STATUS_SUCCESSFUL},
     *         {@link UserCreationResult#STATUS_ANDROID_FAILURE},
     *         {@link UserCreationResult#STATUS_HAL_FAILURE},
     *         or
     *         {@link UserCreationResult#STATUS_HAL_INTERNAL_FAILURE}
     */
    @DataClass.Generated.Member
    public int getStatus() {
        return mStatus;
    }

    /**
     * Gets the created user.
     */
    @DataClass.Generated.Member
    public @Nullable UserInfo getUser() {
        return mUser;
    }

    /**
     * Gets the error message sent by HAL, if any.
     */
    @DataClass.Generated.Member
    public @Nullable String getErrorMessage() {
        return mErrorMessage;
    }

    @Override
    @DataClass.Generated.Member
    public String toString() {
        // You can override field toString logic by defining methods like:
        // String fieldNameToString() { ... }

        return "UserCreationResult { " +
                "status = " + mStatus + ", " +
                "user = " + mUser + ", " +
                "errorMessage = " + mErrorMessage +
        " }";
    }

    @Override
    @DataClass.Generated.Member
    public void writeToParcel(@android.annotation.NonNull android.os.Parcel dest, int flags) {
        // You can override field parcelling by defining methods like:
        // void parcelFieldName(Parcel dest, int flags) { ... }

        byte flg = 0;
        if (mUser != null) flg |= 0x2;
        if (mErrorMessage != null) flg |= 0x4;
        dest.writeByte(flg);
        dest.writeInt(mStatus);
        if (mUser != null) dest.writeTypedObject(mUser, flags);
        if (mErrorMessage != null) dest.writeString(mErrorMessage);
    }

    @Override
    @DataClass.Generated.Member
    public int describeContents() { return 0; }

    /** @hide */
    @SuppressWarnings({"unchecked", "RedundantCast"})
    @DataClass.Generated.Member
    /* package-private */ UserCreationResult(@android.annotation.NonNull android.os.Parcel in) {
        // You can override field unparcelling by defining methods like:
        // static FieldType unparcelFieldName(Parcel in) { ... }

        byte flg = in.readByte();
        int status = in.readInt();
        UserInfo user = (flg & 0x2) == 0 ? null : (UserInfo) in.readTypedObject(UserInfo.CREATOR);
        String errorMessage = (flg & 0x4) == 0 ? null : in.readString();

        this.mStatus = status;
        this.mUser = user;
        this.mErrorMessage = errorMessage;

        // onConstructed(); // You can define this method to get a callback
    }

    @DataClass.Generated.Member
    public static final @android.annotation.NonNull Parcelable.Creator<UserCreationResult> CREATOR
            = new Parcelable.Creator<UserCreationResult>() {
        @Override
        public UserCreationResult[] newArray(int size) {
            return new UserCreationResult[size];
        }

        @Override
        public UserCreationResult createFromParcel(@android.annotation.NonNull android.os.Parcel in) {
            return new UserCreationResult(in);
        }
    };

    @DataClass.Generated(
            time = 1591121994170L,
            codegenVersion = "1.0.15",
            sourceFile = "packages/services/Car/car-lib/src/android/car/user/UserCreationResult.java",
            inputSignatures = "public static final  int STATUS_SUCCESSFUL\npublic static final  int STATUS_ANDROID_FAILURE\npublic static final  int STATUS_HAL_FAILURE\npublic static final  int STATUS_HAL_INTERNAL_FAILURE\nprivate final  int mStatus\nprivate final @android.annotation.Nullable android.content.pm.UserInfo mUser\nprivate final @android.annotation.Nullable java.lang.String mErrorMessage\npublic  boolean isSuccess()\nclass UserCreationResult extends java.lang.Object implements [android.os.Parcelable]\n@com.android.internal.util.DataClass(genToString=true, genHiddenConstructor=true, genHiddenConstDefs=true)")
    @Deprecated
    private void __metadata() {}


    //@formatter:on
    // End of generated code

}
