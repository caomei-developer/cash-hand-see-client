package com.cash_hand_see_client.ui.home.local

open class LocalDate {


    var list = setOf(
        Local("最新更新", "new"),
        Local("热门排行", "hot"),
        Local("已完结", "wanjie"),
        Local("连载中", "lianzai"),
        Local("大陆漫画", "dalu"),
        Local("日本漫画", "riben"),
        Local("韩国漫画", "hanguo"),
        Local("香港漫画", "xianggang"),
        Local("台湾漫画", "taiwan"),
        Local("欧美漫画", "oumei"),
        Local("其他漫画", "qita"),
        Local("生活漫画", "shenghuomanhua"),

        Local("少年热血", "shaonianrexue"),
        Local("武侠格斗", "wuxiagedou"),
        Local("恐怖灵异", "kongbulingyi"),
        Local("耽美人生", "danmeirensheng"),
        Local("少女爱情", "shaonianrexue"),
        Local("恋爱生活", "lianaishenghuo"),

        Local("科幻魔幻", "kehuanmohuan"),
        Local("竞技体育", "jingjitiyu"),
        Local("爆笑喜剧", "baoxiaoxiju"),
        Local("侦探推理", "zhentantuili")


    )

    fun localList(): List<Local> {
        var list = setOf(
            Local("最新更新", "new"),
            Local("热门排行", "hot"),
            Local("已完结", "wanjie"),
            Local("连载中", "lianzai"),
            Local("大陆漫画", "dalu"),
            Local("日本漫画", "riben"),
            Local("其他漫画", "qita"),
            Local("生活漫画", "shenghuomanhua"),
//
            Local("少年热血", "shaonianrexue"),
            Local("武侠格斗", "wuxiagedou"),
            Local("恐怖灵异", "kongbulingyi"),
            Local("耽美人生", "danmeirensheng"),
            Local("少女爱情", "shaonianrexue"),
            Local("恋爱生活", "lianaishenghuo"),

            Local("科幻魔幻", "kehuanmohuan"),
            Local("竞技体育", "jingjitiyu"),
            Local("爆笑喜剧", "baoxiaoxiju"),
            Local("侦探推理", "zhentantuili")
        )
        return list.toList()
    }
}