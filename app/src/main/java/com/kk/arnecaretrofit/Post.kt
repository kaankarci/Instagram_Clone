package com.kk.arnecaretrofit

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

data class Post   (
    @SerializedName("id")
    @Expose
    var id: Int,

    @SerializedName("attendee_id")
    @Expose
    var attendeeId: Int,

    @SerializedName("attendee_name")
    @Expose
    var attendeeName: String,

    @SerializedName("attendee_profile_img")
    @Expose
    var attendeeProfileImg: String,

    @SerializedName("comment")
    @Expose
    var comment: String,

    @SerializedName("is_like")
    @Expose
    var isLike: Int,

    @SerializedName("like_count")
    @Expose
    var likeCount: Int,

    @SerializedName("comment_count")
    @Expose
    var commentCount: Int,

    @SerializedName("video_view")
    @Expose
    var videoView: Int,

    @SerializedName("media")
    @Expose
    var media: String,

    @SerializedName("media_thumb")
    @Expose
    var mediaThumb: String,

    @SerializedName("media_type")
    @Expose
    var mediaType: String,

    @SerializedName("media_width")
    @Expose
    var mediaWidth: Int,

    @SerializedName("media_height")
    @Expose
    var mediaHeight: Int,

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String,

    @SerializedName("created_at")
    @Expose
    var createdAt: String

):Serializable{}