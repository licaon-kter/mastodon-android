package org.joinmastodon.android.model;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.google.gson.annotations.SerializedName;

import org.joinmastodon.android.api.ObjectValidationException;
import org.joinmastodon.android.api.RequiredField;
import org.joinmastodon.android.ui.utils.BlurHashDecoder;
import org.joinmastodon.android.ui.utils.BlurHashDrawable;

public class Card extends BaseModel{
	@RequiredField
	public String url;
	@RequiredField
	public String description;
	@RequiredField
	public Type type;
	public String authorName;
	public String authorUrl;
	public String providerName;
	public String providerUrl;
//	public String html;
	public int width;
	public int height;
	public String image;
	public String embedUrl;
	public String blurhash;

	public transient Drawable blurhashPlaceholder;

	@Override
	public void postprocess() throws ObjectValidationException{
		super.postprocess();
		if(blurhash!=null){
			Bitmap placeholder=BlurHashDecoder.decode(blurhash, 16, 16);
			if(placeholder!=null)
				blurhashPlaceholder=new BlurHashDrawable(placeholder, width, height);
		}
	}

	@Override
	public String toString(){
		return "Card{"+
				"url='"+url+'\''+
				", description='"+description+'\''+
				", type="+type+
				", authorName='"+authorName+'\''+
				", authorUrl='"+authorUrl+'\''+
				", providerName='"+providerName+'\''+
				", providerUrl='"+providerUrl+'\''+
				", width="+width+
				", height="+height+
				", image='"+image+'\''+
				", embedUrl='"+embedUrl+'\''+
				", blurhash='"+blurhash+'\''+
				'}';
	}

	public enum Type{
		@SerializedName("link")
		LINK,
		@SerializedName("photo")
		PHOTO,
		@SerializedName("video")
		VIDEO,
		@SerializedName("rich")
		RICH
	}
}