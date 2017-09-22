package com.ixzus.appkotlin.pic

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.ixzus.appkotlin.R
import com.ixzus.appkotlin.entity.PicBean
import com.ixzus.appkotlin.util.loge
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.entity.LocalMedia
import kotlinx.android.synthetic.main.activity_select_img.*


class SelectImgActivity : AppCompatActivity() {

    private var adapter: SelectImgAdapter? = null
    private var listData = ArrayList<PicBean>()
    private var selectList = ArrayList<LocalMedia>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_img)

//        var picBean = PicBean()
//        picBean.id="1"
//        picBean.url = "https://raw.githubusercontent.com/bumptech/glide/master/static/glide_logo.png"
//        listData.add(picBean)

        adapter = SelectImgAdapter(this)
        recyclerview.layoutManager = GridLayoutManager(this, 4)
        recyclerview.adapter = adapter
        adapter!!.setList(listData)
        adapter!!.setSelectMax(4)
        adapter!!.setUpdate(true)
        adapter!!.setOnAddClickListener(object : SelectImgAdapter.OnAddClickListener {
            override fun onAddClick() {
                selectImg()
            }
        })
    }

    private fun selectImg() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .theme(R.style.picture_white_style)//picture_QQ_style picture_Sina_style picture_white_style picture_default_style
                .maxSelectNum(4)
                .minSelectNum(1)
                .imageSpanCount(3)
                .selectionMode(PictureConfig.MULTIPLE)
                .previewImage(true)
                .isCamera(true)
                .isZoomAnim(true)
//                .enableCrop(true)
                .compress(true)
                .compressMode(PictureConfig.LUBAN_COMPRESS_MODE)
                .glideOverride(160, 160)
                .withAspectRatio(1, 1)
                .hideBottomControls(true)
                .isGif(true)
                .freeStyleCropEnabled(true)
                .showCropFrame(true)
                .openClickSound(true)
                .previewImage(true)
                .compressMaxKB(200)
                .scaleEnabled(true)
                .forResult(99)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (99 == requestCode) {
            selectList = PictureSelector.obtainMultipleResult(data) as ArrayList<LocalMedia>
            selectImgFit(selectList)
        }
    }

    fun selectImgFit(listLocal: ArrayList<LocalMedia>) {
        for (localMedia in listLocal) {
            var picBean = PicBean()
            picBean.id = ""
            picBean.url = localMedia.compressPath
            listData.add(picBean)
            loge(localMedia.compressPath+"")
        }
        adapter!!.updateList(listData)
    }
}
