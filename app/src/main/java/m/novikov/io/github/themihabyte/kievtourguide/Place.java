package m.novikov.io.github.themihabyte.kievtourguide;

public class Place {
    private final static int NO_PHOTO = -1;
    private final static int NO_VOICE = -2;

    private String mName;
    private int mVoiceResourceID = NO_VOICE;
    private String mAddress;
    private int mPhotoResourceID = NO_PHOTO;
    private String mDescription;

    public Place(String mName, String mDescription, String mAddress) {
        this.mName = mName;
        this.mAddress = mAddress;
        this.mDescription = mDescription;
    }

    public Place(String mName, String mDescription, String mAddress, int mPhoto) {
        this.mName = mName;
        this.mAddress = mAddress;
        this.mPhotoResourceID = mPhoto;
        this.mDescription = mDescription;
    }

    public String getDescription() {
        return mDescription;
    }

    public Place(String mName, String mDescription, String mAddress, int mPhoto, int mVoiceResourceID) {
        this.mName = mName;
        this.mVoiceResourceID = mVoiceResourceID;
        this.mAddress = mAddress;
        this.mPhotoResourceID = mPhoto;
        this.mDescription = mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mPlaceName) {
        this.mName = mPlaceName;
    }

    public int getVoiceResourceID() {
        return mVoiceResourceID;
    }

    public void setVoiceResourceID(int mVoiceResourceIDe) {
        this.mVoiceResourceID = mVoiceResourceIDe;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public int getPhoto() {
        return mPhotoResourceID;
    }

    public void setPhoto(int mPhoto) {
        this.mPhotoResourceID = mPhoto;
    }

    public boolean isHavePhoto() {
        return this.mPhotoResourceID != NO_PHOTO;
    }

    public boolean isHaveVoice() {
        return this.mVoiceResourceID != NO_VOICE;
    }
}
