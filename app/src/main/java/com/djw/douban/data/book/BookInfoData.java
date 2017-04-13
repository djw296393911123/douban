package com.djw.douban.data.book;

import java.util.List;

/**
 * Created by JasonDong on 2017/4/10.
 */

public class BookInfoData {

    /**
     * rating : {"max":10,"numRaters":13,"average":"7.5","min":0}
     * subtitle :
     * author : ["张维波"]
     * pubdate : 2009-4
     * tags : [{"count":17,"name":"中医","title":"中医"},{"count":17,"name":"经络","title":"经络"},{"count":5,"name":"水通道","title":"水通道"},{"count":4,"name":"健康","title":"健康"},{"count":4,"name":"医学","title":"医学"},{"count":3,"name":"J椎","title":"J椎"},{"count":3,"name":"循经低流阻通道的发现","title":"循经低流阻通道的发现"},{"count":2,"name":"涌现","title":"涌现"}]
     * origin_title :
     * image : https://img3.doubanio.com/mpic/s3865492.jpg
     * binding :
     * translator : []
     * catalog :
     * pages : 264
     * images : {"small":"https://img3.doubanio.com/spic/s3865492.jpg","large":"https://img3.doubanio.com/lpic/s3865492.jpg","medium":"https://img3.doubanio.com/mpic/s3865492.jpg"}
     * alt : https://book.douban.com/subject/3712873/
     * id : 3712873
     * publisher :
     * isbn10 : 7802452597
     * isbn13 : 9787802452596
     * title : 经络是水通道《经络是什么》第二版
     * url : https://api.douban.com/v2/book/3712873
     * alt_title :
     * author_intro :
     * summary : 本书作者在国家自然科学基金和科技部“九五”攀登等多个国家级经络课题研究的基础上，运用组织液生理学和流体力学等知识，对“经络是什么”这一千古之谜作出了回答，即“经络是水通道”。 书中首先对中医经络的古典文献进行了训诂研究，指出最古的脉是指组织间隙，营卫之气指人体中的组织液。评述了国内外经络研究和各种经络假说，指出经络研究需解决的四大关系。在回顾了经络体液论历史的基础上，运用生物流体力学原理，提出了经络是组织间质中具有低流阻特性的多孔介质通道，其中存在向经脉和沿经脉的两种组织液运动及流体性约束，化学物质和物理信号可沿此通道传递。作者通过测量流阻/组织液压波传播和同位素示踪验证了本假说，并引证了国内外支持本假说的实验。作者还论证了神经通过血管和肌肉对经络通道的调控原理，指出穴位作为相关调控网络输入点的另一种经络形式，并通过经皮二氧化碳释放量的测量和聚类分析证明了此关系。根据这些实质，作者对经络现象作了详细解释，指出经络具有传递营养物质、清除代谢废物和作为旁分泌及物理化学信息通道平衡内环境的功能。本书还对针灸、中药、武术和气功等经络治疗保健原理进行了论述，最后就经络对未来人类社会的影响进行了展望。本书第零章是新增的内容，它从美国巴特曼的喝水疗法谈到经络作为水通道的作用；在理论方面，以坎农的稳态生理学思想讨论了组织液流动的重要性……
     * price : 29.00元
     */

    private RatingBean rating;
    private String subtitle;
    private String pubdate;
    private String origin_title;
    private String image;
    private String binding;
    private String catalog;
    private String pages;
    private ImagesBean images;
    private String alt;
    private String id;
    private String publisher;
    private String isbn10;
    private String isbn13;
    private String title;
    private String url;
    private String alt_title;
    private String author_intro;
    private String summary;
    private String price;
    private List<String> author;
    private List<TagsBean> tags;
    private List<?> translator;

    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getOrigin_title() {
        return origin_title;
    }

    public void setOrigin_title(String origin_title) {
        this.origin_title = origin_title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public ImagesBean getImages() {
        return images;
    }

    public void setImages(ImagesBean images) {
        this.images = images;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlt_title() {
        return alt_title;
    }

    public void setAlt_title(String alt_title) {
        this.alt_title = alt_title;
    }

    public String getAuthor_intro() {
        return author_intro;
    }

    public void setAuthor_intro(String author_intro) {
        this.author_intro = author_intro;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public List<TagsBean> getTags() {
        return tags;
    }

    public void setTags(List<TagsBean> tags) {
        this.tags = tags;
    }

    public List<?> getTranslator() {
        return translator;
    }

    public void setTranslator(List<?> translator) {
        this.translator = translator;
    }

    public static class RatingBean {
        /**
         * max : 10
         * numRaters : 13
         * average : 7.5
         * min : 0
         */

        private int max;
        private int numRaters;
        private String average;
        private int min;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public int getNumRaters() {
            return numRaters;
        }

        public void setNumRaters(int numRaters) {
            this.numRaters = numRaters;
        }

        public String getAverage() {
            return average;
        }

        public void setAverage(String average) {
            this.average = average;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }
    }

    public static class ImagesBean {
        /**
         * small : https://img3.doubanio.com/spic/s3865492.jpg
         * large : https://img3.doubanio.com/lpic/s3865492.jpg
         * medium : https://img3.doubanio.com/mpic/s3865492.jpg
         */

        private String small;
        private String large;
        private String medium;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }
    }

    public static class TagsBean {
        /**
         * count : 17
         * name : 中医
         * title : 中医
         */

        private int count;
        private String name;
        private String title;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
