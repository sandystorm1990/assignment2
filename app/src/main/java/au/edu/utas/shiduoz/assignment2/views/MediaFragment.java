package au.edu.utas.shiduoz.assignment2.views;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import au.edu.utas.shiduoz.assignment2.R;
import au.edu.utas.shiduoz.assignment2.utils.Helper;

public class MediaFragment extends Fragment {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    TextView uploadBtn;
    public MediaFragment() {}
    private View mView;
    ImageView mImageView;

    public static String mCurrentPhotoPath="";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflateView = inflater.inflate(R.layout.fragment_media, container, false);

        //edit
        Log.d("path", mCurrentPhotoPath.length()+"asdf");
        if (mCurrentPhotoPath.length() > 1 && mCurrentPhotoPath != null) {

            mImageView = inflateView.findViewById(R.id.itemMedia);
            Helper.setPic(mImageView, mCurrentPhotoPath);
        }

        uploadBtn = inflateView.findViewById(R.id.uploadBtn);
        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestToTakeAPicture();
            }
        });
        // set as true，when configuration change，fragment instance will not be recreated
        mView = inflateView;

        return inflateView;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void setmImagePath(String path)
    {
        mCurrentPhotoPath = path;
    }

    public String getmImagePath()
    {
        return  mCurrentPhotoPath;
    }


    //step 4
    public void requestToTakeAPicture()
    {
        MediaFragment.this.requestPermissions(new String[]{Manifest.permission.CAMERA},1);
    }

    //step 5
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_IMAGE_CAPTURE:
                Log.d("camera", "onRequestPermissionsResult: "+PackageManager.PERMISSION_GRANTED);
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    Log.d("camera", "granted");
                    // permission was granted, yay!
                    takeAPicture();
                } else {
                    Log.d("camera", "deny");
                    // permission denied, boo!
                }
        }
    }

    //step 6
    private void takeAPicture()
    {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //Intent takePictureIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        //takePictureIntent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 10);

        // Ensure that theres a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null)
        {
            // Create the File where the photo should go
            try
            {
                File photoFile = createImageFile();
                Uri photoURI = FileProvider.getUriForFile(getContext(),
                        "au.edu.utas.shiduoz.assignment2", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
            catch (IOException ex)
            {
                // Error occurred while creating the File
            }
        }
    }


    private File createImageFile() throws IOException
    {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir =getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".png",         /* suffix */
                storageDir      /* directory */
        );

        String mPhotoPath = image.getAbsolutePath();
        setmImagePath(mPhotoPath);
        Log.d("path", mPhotoPath);

        return image;
    }

    // step 7
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == getActivity().RESULT_OK)
        {
            //to get just the thumbnail:
            //Bundle extras = data.getExtras();
            //Bitmap imageBitmap = (Bitmap) extras.get("data");

            mImageView = mView.findViewById(R.id.itemMedia);
            setPic(mImageView, getmImagePath());
        }
    }

    private void setPic(ImageView myImageView, String path)
    {
        // Get the dimensions of the View
        int targetW = myImageView.getWidth();
        int targetH = myImageView.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(path, bmOptions);
        myImageView.setImageBitmap(bitmap);
    }
}
