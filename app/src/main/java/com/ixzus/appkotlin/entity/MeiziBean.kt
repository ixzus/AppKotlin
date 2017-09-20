package com.ixzus.appkotlin.entity

/**
 * Created by huan on 2017/9/19.
 */

class MeiziBean {


    /**
     * error : false
     * results : [{"_id":"59c1b3e0421aa9727ddd19a8","createdAt":"2017-09-20T08:18:40.702Z","desc":"9-20","publishedAt":"2017-09-20T13:17:38.709Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fjppsiclufj20u011igo5.jpg","used":true,"who":"带马甲"},{"_id":"59bf0c37421aa9118887ac33","createdAt":"2017-09-18T07:58:47.204Z","desc":"9-18","publishedAt":"2017-09-19T12:07:31.405Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fjndz4dh39j20u00u0ada.jpg","used":true,"who":"daimajia"}]
     */

    var isError: Boolean = false
    var results: List<ResultsBean>? = null

    class ResultsBean {
        /**
         * _id : 59c1b3e0421aa9727ddd19a8
         * createdAt : 2017-09-20T08:18:40.702Z
         * desc : 9-20
         * publishedAt : 2017-09-20T13:17:38.709Z
         * source : chrome
         * type : 福利
         * url : https://ws1.sinaimg.cn/large/610dc034ly1fjppsiclufj20u011igo5.jpg
         * used : true
         * who : 带马甲
         */

        var _id: String? = null
        var createdAt: String? = null
        var desc: String? = null
        var publishedAt: String? = null
        var source: String? = null
        var type: String? = null
        var url: String? = null
        var isUsed: Boolean = false
        var who: String? = null
    }
}